package com.j.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.j.pojo.Employee;
import com.j.pojo.Fregist;
import com.j.pojo.PasswordTable;
import com.j.pojo.User;
import com.j.service.UserManageService;

@Controller
//����Access to XMLHttpRequest at 'http://localhost:8080/ShoppingMall/login.action
//�����������취����@CrossOrigin 
@CrossOrigin 
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
	public String login(HttpSession session,@RequestBody Map username1) {
//		public String login(@RequestBodyString data) {
		System.out.println("session ��ֵ��"+session.getId());
//		System.out.println(user.get);
		System.out.println(username1);
		System.out.println(username1.get("username"));
		System.out.println(username1.get("password"));
		String username = (String) username1.get("username");
		String password = (String) username1.get("password");
		System.out.println(username);
		System.out.println(password);
		Map<String, Object> map2 = userManageService.queryByUsernameAndPassword(username, password);
		System.out.println("Controller����ֵ��"+map2.get("flag"));
		System.out.println("Controller����ֵ��"+map2.get("flag").equals(false));
		Map<String,Object> map = new HashMap<String, Object>();
		//int i = 1/0;
		if(map2.get("flag").equals(true)) {
			System.out.println("��ѯ���û������ڵ�½");
//			session.setAttribute("USER_SESSION", map2.get("userID")+"-"+username);
			map.put("flag", map2.get("flag"));
			map.put("sessionID", session.getId());
			System.out.println(map2.get("lastTime"));
			map.put("lastTime", map2.get("lastTime"));
			map.put("userID", map2.get("userID"));
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
	public String regist(@RequestBody Fregist fregist) {
//		public String regist(String name,String gender,String power,String uid,String tel,String address,String username,String password,String question_one,String question_two,String question_one_key,String question_two_key) {
//		System.out.println(ruleForm);
		System.out.println(fregist);
//		username=111,
//				pass=222, 
//				checkPass=222, 
//				question_one=, 
//				question_one_key=, 
//				question_two=, 
//				question_two_key=, 
//				name=333, 
//				gender=Ů, 
//				uuid=, 
//				tel_number=, 
//				address=, 
//				depid=
//		System.out.println(ruleForm.get("username"));
//		System.out.println(ruleForm.get("pass"));
//		System.out.println(ruleForm.get("question_one"));
//		System.out.println(ruleForm.get("question_one_key"));
//		System.out.println(ruleForm.get("question_two"));
//		System.out.println(ruleForm.get("question_two_key"));
//		System.out.println(ruleForm.get("name"));
//		System.out.println(ruleForm.get("gender"));
//		System.out.println(ruleForm.get("uuid"));
//		System.out.println(ruleForm.get("tel_number"));
//		System.out.println(ruleForm.get("address"));
//		System.out.println(ruleForm.get("depid"));
////		
//		String name=fregist.get;
//		
//		String gender=(String) ruleForm.get("gender");
//		
//		String power=fregist.get;
////		Object power = ruleForm.get("depid");
//		
//		String uid=fregist.get;
//		
//		String tel=fregist.get;
//		
//		String address=fregist.get;
//		
//		String username=fregist.get;
//		
//		String password=fregist.get;
//		
//		String question_one=fregist.get;
//		
//		String question_two=fregist.get;
//		
//		String question_one_key=fregist.get;
//		
//		String question_two_key=fregist.get;
//		
//		System.out.println("----------------------------------------------");
//		
////		
//		System.out.println(gender);
//		System.out.println(power);
//		System.out.println(uid);
//		System.out.println(tel);
//		System.out.println(address);
//		System.out.println(username);
//		System.out.println(password);
//		System.out.println(question_one);
//		System.out.println(question_two);
//		System.out.println(question_one_key);
//		System.out.println(question_two_key);
//		
		
		Map<String,Object> map = new HashMap<String , Object>();
		PasswordTable pwd = new PasswordTable();
		pwd.setPwd_username(fregist.getUsername());
		pwd.setPwd_password(fregist.getPass());
		//ע������
//		pwd.setPwd_power((int)power);
		pwd.setPwd_power(Integer.valueOf(fregist.getDepid()));
		pwd.setPwd_question_one(fregist.getQuestion_one());
		pwd.setPwd_question_two(fregist.getQuestion_two());
		pwd.setPwd_question_one_key(fregist.getQuestion_one_key());
		pwd.setPwd_question_two_key(fregist.getQuestion_two_key());
		//power==10?��ͨ�û���Ա��
		System.out.println("����ش�Ȩ��ֵ��"+Integer.valueOf(fregist.getDepid()));
		if(Integer.valueOf(Integer.valueOf(fregist.getDepid()))==10){
//			if(Integer.valueOf(power==10){
			User user = new User();
			user.setUser_name(fregist.getName());
			user.setUser_gender(fregist.getGender());
			user.setUser_uid(fregist.getUuid());
			user.setUser_phone_number(fregist.getTel_number());
			user.setUser_address(fregist.getAddress());
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
			employee.setEmp_name(fregist.getName());
			employee.setEmp_gender(fregist.getGender());
			employee.setEmp_userid(fregist.getUuid());
			employee.setEmp_phone_number(fregist.getTel_number());
			employee.setEmp_address(fregist.getAddress());
			employee.setEmp_depid(Integer.valueOf(fregist.getDepid()));
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
	@RequestMapping(value="/queryBydepcode.action",method = RequestMethod.POST)
	public String registByInv(String dep_code) {
		System.out.println(dep_code);
//		Map<String, Object> map2 = userManageService.queryByInvitationCode(inv_code);
		Map<String, Object> map2 = userManageService.queryDepByCode(dep_code);
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
