package com.j.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.j.pojo.Employee;
import com.j.pojo.InvitationCode;
import com.j.pojo.PasswordTable;
import com.j.pojo.User;

public interface UserManageDao {
//��
	//ע���û�
		//��ͨ�û���Ϣ����
	public int insertByPasswordTable(PasswordTable pawT);
	public int insertByUser(User user);
	public int insertByMember(String user_id);
		//Ա����Ϣ����
	public int insertByEmployee(Employee employee);
//ɾ
	//��IDɾ��
//��
	//��¼��ѯ
	public PasswordTable queryByUsernameAndPassword(@Param("username")String username,@Param("password")String password);
	public User queryByUser(@Param("pas_id")int pas_id);
	public Employee queryByEmployee(@Param("pas_id")int pas_id);
	//��ͨ�û�ע���ѯ
		//��ѯ����
	public int queryRegistByUsername(@Param("username")String username);
		//����֤�Ų�ѯ
	public int queryRegistByuserID(@Param("user_uid")String user_id);
	//Ա��ע��
		//��ѯע����
	public InvitationCode queryByInvitationCode(@Param("code")String code); 
	
	//��ѯȫ��
	public List<Employee> queryEmpAll();
	public List<User> queryUseAll();
	
	//������ģ����ѯ
	public List<Employee> queryEmpFuByName(@Param("emp_name")String emp_name);
	public List<User> queryUseFuByName(@Param("user_name")String user_name);
//��
	
}