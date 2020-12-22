package com.j.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j.dao.UserManageDao;
import com.j.pojo.Employee;
import com.j.pojo.InvitationCode;
import com.j.pojo.PasswordTable;
import com.j.pojo.User;
import com.j.service.UserManageService;
import com.j.util.DateFormat;


@Service
public class UserManageServiceImpl implements UserManageService{

	@Autowired
	private UserManageDao userManageDao;
	@Autowired
	private DateFormat dateFormat;

	

	@Override
	public int insertUserRegist(PasswordTable pawT, User user) {
		
		//�������
		pawT.setPwd_regist_data(new Date());
		int insertByPasswordTable = userManageDao.insertByPasswordTable(pawT);
		PasswordTable queryByUsernameAndPassword = userManageDao.queryByUsernameAndPassword(pawT.getPwd_username(), pawT.getPwd_password());
		
		//�û�����
		user.setUser_power(10);
		
		
		return 0;
	}
	
//	@Override
//	public int insertByPasswordTable(PasswordTable pawT) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int insertByUser(User user) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int insertByEmployee(Employee employee) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	@Override
	public Map<String,Object> queryByUsernameAndPassword(String username, String password) {
		System.out.println("Service:"+username);
		System.out.println("Service:"+password);
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
			map.put("flag", true);
			return map;
		}else if(pw.getPwd_power()>0&&pw.getPwd_power()<10||pw.getPwd_power()>10){
			Employee queryByEmployee = userManageDao.queryByEmployee(pw.getPwd_id());
			map.put("userID", queryByEmployee.getEmp_id());
			map.put("flag", true);
			return map;
		}else {
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

}
