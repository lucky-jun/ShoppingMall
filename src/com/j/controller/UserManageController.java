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
		System.out.println("jajajjajajajajajj");
		request.setAttribute("list", "������������");
		return "login";
	}
	
//	��¼
	@ResponseBody //�������ݷ���json��ʽ
	@RequestMapping(value="/login.action",method=RequestMethod.POST)
	public String login(HttpSession session,String username,String password) {
		System.out.println(username);
		System.out.println(password);
//		boolean b = userService.queryByNameAndPassword(username, password);
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
	@RequestMapping(value="/insertByRegist.action")
						// ���֣�			�Ա�		Ȩ��(������Ȩ��)�����֤�ţ�		�绰��		��ַ ��		��¼�ǳƣ�			����				�ܱ�1				�ܱ�2					�ܱ�1��					�ܱ�2��
	public String regist(String name,String gender,String power,String uid,String tel,String address,String username,String password,String question_one,String question_two,String question_one_key,String question_two_key) {
		Map<String,Object> map = new HashMap<String , Object>();
		PasswordTable pwd = new PasswordTable();
		pwd.setPwd_username(username);
		pwd.setPwd_password(password);
		//ע������
		//Ȩ��
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
		}else {
			System.out.println("Ա����ʼע��");
		}

		
		
		
		
		return null;
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
