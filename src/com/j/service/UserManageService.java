package com.j.service;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.j.pojo.Department;
import com.j.pojo.Employee;
import com.j.pojo.InvitationCode;
import com.j.pojo.PasswordTable;
import com.j.pojo.User;

public interface UserManageService {
//增
	//注册用户
	public int insertUserRegist(PasswordTable pawT,User user);
	public int insertEmployeeRegist(PasswordTable pawT, Employee emp);
//	public int insertByPasswordTable(PasswordTable pawT);
//	public int insertByUser(User user);
//	public int insertByEmployee(Employee employee);
//删
//查
	//登录查询
	public Map<String, Object> queryByUsernameAndPassword(String username,String password);
	//普通用户注册查询
		//查询username重名
	public boolean queryRegistByUsername(String username);
		//身份证号查询
	public boolean queryRegistByuserID(String user_id);
	//员工注册
		//查询邀请码
	public Map<String, Object> queryByInvitationCode(String code);
	
	
	//信息查询
		//部门信息查询
	public Department queryDepByPower(String dep_power);
	
	
//改
		
}
