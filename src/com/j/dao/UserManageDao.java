package com.j.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.j.pojo.Employee;
import com.j.pojo.InvitationCode;
import com.j.pojo.PasswordTable;
import com.j.pojo.User;

public interface UserManageDao {
//增
	//注册用户
	public int insertByPasswordTable(PasswordTable pawT);
	public int insertByUser(User user);
	public int insertByEmployee(Employee employee);
//删
	//按ID删除
//查
	//登录查询
	public PasswordTable queryByUsernameAndPassword(@Param("username")String username,@Param("password")String password);
	public User queryByUser(@Param("pas_id")int pas_id);
	public Employee queryByEmployee(@Param("pas_id")int pas_id);
	//普通用户注册查询
		//查询重名
	public int queryRegistByUsername(@Param("username")String username);
		//身份证号查询
	public int queryRegistByuserID(@Param("user_uid")String user_id);
	//员工注册
		//查询注册码
	public InvitationCode queryByInvitationCode(@Param("code")String code); 
	
	//查询全部
	public List<Employee> queryEmpAll();
	public List<User> queryUseAll();
	
	//按名字模糊查询
	public List<Employee> queryEmpFuByName(@Param("emp_name")String emp_name);
	public List<User> queryUseFuByName(@Param("user_name")String user_name);
//改
	
}
