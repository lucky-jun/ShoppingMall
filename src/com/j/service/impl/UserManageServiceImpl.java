package com.j.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.j.dao.UserManageDao;
import com.j.pojo.Department;
import com.j.pojo.Employee;
import com.j.pojo.InvitationCode;
import com.j.pojo.PasswordTable;
import com.j.pojo.User;
import com.j.service.UserManageService;
import com.j.util.DateFormat;


@Service
@Transactional
public class UserManageServiceImpl implements UserManageService{

	@Autowired
	private UserManageDao userManageDao;
	@Autowired
	private DateFormat dateFormat;

	

	@Override
	public int insertUserRegist(PasswordTable pawT, User user) {
		System.out.println("------------------------service:start");
		System.out.println(pawT.getPwd_username());
		System.out.println(pawT.getPwd_password());
		pawT.setPwd_power(user.getUser_power());
		System.out.println(pawT.getPwd_power());
		System.out.println(pawT.getPwd_regist_data());
		System.out.println(pawT.getPwd_regist_data());
		System.out.println(pawT.getPwd_question_one());
		System.out.println(pawT.getPwd_question_two());
		System.out.println(pawT.getPwd_question_one_key());
		System.out.println(pawT.getPwd_question_two_key());
		System.out.println("------------------------service:end");
		//�������
		pawT.setPwd_regist_data(new Date());
		int insertByPasswordTable = userManageDao.insertByPasswordTable(pawT);
		PasswordTable queryByUsernameAndPassword = userManageDao.queryByUsernameAndPassword(pawT.getPwd_username(), pawT.getPwd_password());
		//�û�����
		if(user.getUser_uid()==null)user.setUser_uid(Integer.toString(queryByUsernameAndPassword.getPwd_id()));
		user.setUser_pasid(queryByUsernameAndPassword.getPwd_id());
		int insertByUser = userManageDao.insertByUser(user);
		//��ȡ�û�ID��������Ա�˺�
		int user_id = userManageDao.queryUserByPwdId(queryByUsernameAndPassword.getPwd_id()).getUser_id();
		int insertByMember = userManageDao.insertByMember(user_id);
		System.out.println("ִ�н��1��"+insertByPasswordTable);
		System.out.println("ִ�н��2��"+insertByUser);
		System.out.println("ִ�н��3��"+insertByMember);
//		int i = 1/0;
		return insertByPasswordTable*insertByUser*insertByMember;
	}
	
	//Ա����Ϣ����
	public int insertEmployeeRegist(PasswordTable pawT, Employee emp) {
		//�������
		pawT.setPwd_regist_data(new Date());
		int insertByPasswordTable = userManageDao.insertByPasswordTable(pawT);
		PasswordTable queryByUsernameAndPassword = userManageDao.queryByUsernameAndPassword(pawT.getPwd_username(), pawT.getPwd_password());
		
		//�û�����
		Department queryDepByPower = this.queryDepByPower(Integer.toString(pawT.getPwd_power()));
		emp.setEmp_pasid(queryByUsernameAndPassword.getPwd_id());
		emp.setEmp_depid(queryDepByPower.getDep_id());
		int insertByEmployee = userManageDao.insertByEmployee(emp);
		
		return insertByPasswordTable*insertByEmployee;
	}
	
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
		if(pw.getPwd_power()==10) {
			User queryByUser = userManageDao.queryByUser(pw.getPwd_id());
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
	public Map<String,Object> queryByInvitationCode(String code) {
		System.out.println("service:"+code);
		InvitationCode queryByInvitationCode = userManageDao.queryByInvitationCode(code);
		Map<String,Object> map = new HashMap<String, Object>();
		System.out.println("----:"+queryByInvitationCode);
		if(queryByInvitationCode==null) {
			map.put("flag", false);
		}else {
			map.put("flag", true);
			System.out.println("++++++++++:"+queryByInvitationCode.getInv_date().toString());
			//��ֵ
			map.put("data",queryByInvitationCode);
		}
		return map;
	}

	@Override
	public Department queryDepByPower(String dep_power) {
		Department queryDepByPower = userManageDao.queryDepByPower(dep_power);
		return queryDepByPower;
	}

}
