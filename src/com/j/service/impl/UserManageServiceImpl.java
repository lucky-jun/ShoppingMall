package com.j.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.j.dao.UserManageDao;
import com.j.pojo.Department;
import com.j.pojo.Employee;
import com.j.pojo.PasswordTable;
import com.j.pojo.User;
//import com.j.service.InvitationCode;
import com.j.service.UserManageService;
import com.j.util.DateFormat;

//import javafx.print.PageOrientation;


@Service
public class UserManageServiceImpl implements UserManageService{

	@Autowired
	private UserManageDao userManageDao;
	@Autowired
	private DateFormat dateFormat;
	@Autowired
	private PasswordTable passwordTable;

	

	//�û�ע��
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,timeout = 30000)
	public int insertUserRegist(PasswordTable pawT, User user) {
		System.out.println("------------------------service:start");
		System.out.println(pawT.getPwd_username());
		System.out.println(pawT.getPwd_password());
		pawT.setPwd_power(user.getUser_power());
		System.out.println(pawT.getPwd_power());
		System.out.println(pawT.getPwd_regist_data());
		System.out.println(pawT.getPwd_login_data());
		System.out.println(pawT.getPwd_question_one());
		System.out.println(pawT.getPwd_question_two());
		System.out.println(pawT.getPwd_question_one_key());
		System.out.println(pawT.getPwd_question_two_key());
		System.out.println("------------------------service:end");
		try {
			//�������
			pawT.setPwd_regist_data(new Date());
			System.out.println("��ȡ��ע��ʱ�䣺"+pawT.getPwd_regist_data());
			int insertByPasswordTable = userManageDao.insertByPasswordTable(pawT);
			PasswordTable queryByUsernameAndPassword = userManageDao.queryByUsernameAndPassword(pawT.getPwd_username(), pawT.getPwd_password());
			//�û�����
			System.out.println("UID:"+user.getUser_uid());
			if(user.getUser_uid()==null||user.getUser_uid()=="")user.setUser_uid(Integer.toString(queryByUsernameAndPassword.getPwd_id()));
			user.setUser_pasid(queryByUsernameAndPassword.getPwd_id());
			int insertByUser = userManageDao.insertByUser(user);
			//��ȡ�û�ID��������Ա�˺�
			int user_id = userManageDao.queryUserByPwdId(queryByUsernameAndPassword.getPwd_id()).getUser_id();
			int insertByMember = userManageDao.insertByMember(user_id);
			System.out.println("ִ�н��1��"+insertByPasswordTable);
			System.out.println("ִ�н��2��"+insertByUser);
			System.out.println("ִ�н��3��"+insertByMember);
			return 1;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return 0;
		}
		
//		int i = 1/0;
	}
	
	//Ա����Ϣ����
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,timeout = 30000)
	public int insertEmployeeRegist(PasswordTable pawT, Employee emp) {
		try {
			//�������
			pawT.setPwd_regist_data(new Date());
			int insertByPasswordTable = userManageDao.insertByPasswordTable(pawT);
			PasswordTable queryByUsernameAndPassword = userManageDao.queryByUsernameAndPassword(pawT.getPwd_username(), pawT.getPwd_password());
			
			//��Ϣ����
			Department queryDepByPower = this.queryDepByPower(Integer.toString(pawT.getPwd_power()));
			emp.setEmp_pasid(queryByUsernameAndPassword.getPwd_id());
			emp.setEmp_depid(queryDepByPower.getDep_id());
			int insertByEmployee = userManageDao.insertByEmployee(emp);
			return 1;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return 0;
		}
	}
	
	//��¼
	@Override
	public Map<String,Object> queryByUsernameAndPassword(String username, String password) {
		System.out.println("Service1:"+username);
		System.out.println("Service2:"+password);
		Map<String,Object> map = new HashMap<String, Object>();
		PasswordTable pw = userManageDao.queryByUsernameAndPassword(username, password);
		System.out.println("ServiceImpl:"+pw);
		//�����ѯΪ�գ�����false
		if(pw==null) {
			map.put("flag", false);
			return map;
		}
		//��ֵ����true
		System.out.println("1111111111:"+username);
		passwordTable.setPwd_username(username);
		passwordTable.setPwd_login_data(new Date());
		System.out.println(passwordTable.getPwd_username());
		System.out.println(passwordTable.getPwd_password());
		System.out.println(passwordTable.getPwd_login_data());
		int updatePWTByUsername = userManageDao.updatePWTByUsername(passwordTable);
		PasswordTable queryByUsernameAndPassword = userManageDao.queryByUsernameAndPassword(username, password);
		System.out.println("123123123:"+queryByUsernameAndPassword.getPwd_login_data());
		System.out.println("123123123:"+pw.getPwd_login_data());
		System.out.println(updatePWTByUsername);
		System.out.println("ʱ�䣺"+pw.getPwd_login_data());
		if(pw.getPwd_login_data()==null||pw.getPwd_login_data().equals("")) {
			map.put("lastTime","�������ĵ�һ�ε�½����ӭ���ĵ�����");
		}else {
			map.put("lastTime",dateFormat.DateUtiltoString(pw.getPwd_login_data()));
		}
		if(pw.getPwd_power()==10) {
			User queryByUser = userManageDao.queryByUser(pw.getPwd_id());
			System.out.println("pw.getPwd_id():"+pw.getPwd_id());
			System.out.println("queryByUser:"+queryByUser);
			System.out.println("getUser_id():"+queryByUser.getUser_id());
			map.put("userID", queryByUser.getUser_id());
			map.put("power", pw.getPwd_power());
			map.put("flag", true);
			return map;
		}else{
			Employee queryByEmployee = userManageDao.queryByEmployee(pw.getPwd_id());
			map.put("userID", queryByEmployee.getEmp_id());
			map.put("power", pw.getPwd_power());
			map.put("flag", true);
			return map;
		}
	}

//	ע���ѯ
	//��ͨ�û�ע��
		//��ѯusername����
	@Override
	public boolean queryRegistByUsername(String username) {
		System.out.println("Service:"+username);
		int i = userManageDao.queryRegistByUsername(username);
		return i>0;
	}
		//���֤�Ų�ѯ
	@Override
	public boolean queryRegistByuserID(String user_id) {
		int i = userManageDao.queryRegistByuserID(user_id);
		return i>0;
	}
	//Ա��ע��

	//��ѯ������
	@Override
	public Map<String,Object> queryDepByCode(String code) {
		System.out.println("service:"+code);
//		InvitationCode queryByInvitationCode = userManageDao.queryByInvitationCode(code);
		Department queryDepByCode = userManageDao.queryDepByCode(code);
		Map<String,Object> map = new HashMap<String, Object>();
		System.out.println("----:"+queryDepByCode);
		if(queryDepByCode==null) {
			map.put("flag", false);
		}else {
			map.put("flag", true);
//			System.out.println("++++++++++:"+queryByInvitationCode.getInv_date().toString());
			//��ֵ
			map.put("data",queryDepByCode);
		}
		return map;
	}

	//Ȩ�޲�ѯ
	@Override
	public Department queryDepByPower(String dep_power) {
		Department queryDepByPower = userManageDao.queryDepByPower(dep_power);
		return queryDepByPower;
	}

}
