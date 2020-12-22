package com.j.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.j.pojo.Employee;
import com.j.pojo.PasswordTable;
import com.j.pojo.User;
import com.j.service.UserManageService;

@Controller
public class UserManageController {
	
	@Autowired
	private UserManageService userManageService;
	// http://localhost:8080/SSMDemo3/login.do ������username password post
//	@ResponseBody // �������ݷ���json��ʽ��
//	��ת��¼����
	@RequestMapping(value="/login.action",method=RequestMethod.GET)
	public String log(HttpServletRequest request) {
		System.out.println("������ת��¼����");
		return "login";
	}
	
//	��¼
	@ResponseBody //�������ݷ���json��ʽ
	@RequestMapping(value="/login.action",method=RequestMethod.POST)
	public String login(HttpSession session,String username,String password) {
		System.out.println(username);
		System.out.println(password);
		Map<String, Object> map2 = userManageService.queryByUsernameAndPassword(username, password);
		System.out.println("Controller����ֵ��"+map2.get("flag"));
		System.out.println("Controller����ֵ��"+map2.get("flag").equals(false));
		Map<String,Object> map = new HashMap<String, Object>();
		//int i = 1/0;
		if(map2.get("flag").equals(true)) {
			System.out.println("��ѯ���û������ڵ�½");
			session.setAttribute("USER_SESSION", map2.get("userID")+"-"+username);
			map.put("flag", map2.get("flag"));
			map.put("userId", session.getId());
			System.out.println(map2.get("power"));
			if(map2.get("power").equals(10)) {
				map.put("user","��ͨ�û���¼");
				map.put("power",map2.get("power"));
			}else {
				map.put("user","Ա����¼");
				map.put("power",map2.get("power"));
			}
			return JSON.toJSONString(map);
		}else {
			//return "login";
			System.out.println("δ��ѯ���û�����½ʧ��");
			map.put("flag", false);
			return JSON.toJSONString(map);
		}
	}
	
//ע��
	//��ͨ�û�ע��
		//��ѯ�Ƿ�������
	@ResponseBody
	@RequestMapping(value="/queryByUsername.action",method = RequestMethod.POST)
	public String registByName(String username) {
		System.out.println("controller:"+username);
		boolean queryRegistByUsername = userManageService.queryRegistByUsername(username);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("flag", queryRegistByUsername);
		return JSON.toJSONString(map);
	}
		//��ѯ���֤��
	@ResponseBody
	@RequestMapping(value="/queryByuserID.action",method = RequestMethod.POST)
	public String registByuserID(String userID) {
		System.out.println(userID);
		boolean queryRegistByuserID = userManageService.queryRegistByuserID(userID);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("flag", queryRegistByuserID);
		return JSON.toJSONString(map);
	}
		//ע���������
	@ResponseBody
	@RequestMapping(value="/insertByRegist.action",method=RequestMethod.POST)
						// ���֣�			�Ա�		Ȩ��(������Ȩ��)�����֤�ţ�		�绰��		��ַ ��		��¼�ǳƣ�			����				�ܱ�1				�ܱ�2					�ܱ�1��					�ܱ�2��
	public String regist(String name,String gender,String power,String uid,String tel,String address,String username,String password,String question_one,String question_two,String question_one_key,String question_two_key) {
		System.out.println(name);
		System.out.println(gender);
		System.out.println(power);
		System.out.println(uid);
		System.out.println(tel);
		System.out.println(address);
		System.out.println(username);
		System.out.println(password);
		System.out.println(question_one);
		System.out.println(question_two);
		System.out.println(question_one_key);
		System.out.println(question_two_key);
		Map<String,Object> map = new HashMap<String , Object>();
		PasswordTable pwd = new PasswordTable();
		pwd.setPwd_username(username);
		pwd.setPwd_password(password);
		//ע������
		pwd.setPwd_power(Integer.valueOf(power));
		pwd.setPwd_question_one(question_one);
		pwd.setPwd_question_two(question_two);
		pwd.setPwd_question_one_key(question_one_key);
		pwd.setPwd_question_two_key(question_two_key);
		//power==10?��ͨ�û���Ա��
		System.out.println("����ش�Ȩ��ֵ��"+power);
		if(Integer.valueOf(power)==10){
			User user = new User();
			user.setUser_name(name);
			user.setUser_gender(gender);
			user.setUser_uid(uid);
			user.setUser_phone_number(tel);
			user.setUser_address(address);
			user.setUser_power(10);
			int insertUserRegist = userManageService.insertUserRegist(pwd, user);
			System.out.println("Controller��ͨ�û�ע�᷵��ֵ��"+insertUserRegist);
			if(insertUserRegist>0) {
				map.put("flag", true);
			}else {
				map.put("flag", false);
			}
			return JSON.toJSONString(map);
		}else {
			Employee employee = new Employee();
			employee.setEmp_name(name);
			employee.setEmp_gender(gender);
			employee.setEmp_userid(uid);
			employee.setEmp_phone_number(tel);
			employee.setEmp_address(address);
			employee.setEmp_depid(Integer.valueOf(power));
			int insertEmployeeRegist = userManageService.insertEmployeeRegist(pwd, employee);
			System.out.println("Controller��ͨ�û�ע�᷵��ֵ��"+insertEmployeeRegist);
			if(insertEmployeeRegist>0) {
				map.put("flag", true);
			}else {
				map.put("flag", false);
			}
			return JSON.toJSONString(map);
		}
	}
	//Ա��ע��
		//�������ѯ
	@ResponseBody
	@RequestMapping(value="/queryByinvcode.action",method = RequestMethod.POST)
	public String registByInv(String inv_code) {
		System.out.println(inv_code);
		Map<String, Object> map2 = userManageService.queryByInvitationCode(inv_code);
		Map<String,Object> map = new HashMap<String, Object>();
		System.out.println("1111:"+map2.get("flag"));
		if(map2.get("flag").equals(false)) {
			map.put("flag", false);
			return JSON.toJSONString(map);
		}else {
			map.put("flag", true);
			map.put("data", map2.get("data"));
			return JSON.toJSONString(map);
		}
	}
	
}
