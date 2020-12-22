package com.j.service;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.j.pojo.Department;
import com.j.pojo.Employee;
import com.j.pojo.InvitationCode;
import com.j.pojo.PasswordTable;
import com.j.pojo.User;

public interface UserManageService {
//��
	//ע���û�
	public int insertUserRegist(PasswordTable pawT,User user);
	public int insertEmployeeRegist(PasswordTable pawT, Employee emp);
//	public int insertByPasswordTable(PasswordTable pawT);
//	public int insertByUser(User user);
//	public int insertByEmployee(Employee employee);
//ɾ
//��
	//��¼��ѯ
	public Map<String, Object> queryByUsernameAndPassword(String username,String password);
	//��ͨ�û�ע���ѯ
		//��ѯusername����
	public boolean queryRegistByUsername(String username);
		//���֤�Ų�ѯ
	public boolean queryRegistByuserID(String user_id);
	//Ա��ע��
		//��ѯ������
	public Map<String, Object> queryByInvitationCode(String code);
	
	
	//��Ϣ��ѯ
		//������Ϣ��ѯ
	public Department queryDepByPower(String dep_power);
	
	
//��
		
}
