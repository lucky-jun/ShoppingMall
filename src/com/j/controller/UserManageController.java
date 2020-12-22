package com.j.controller;

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
import com.j.service.UserManageService;

@Controller
public class UserManageController {
	
	@Autowired
	private UserManageService userManageService;
	// http://localhost:8080/SSMDemo3/login.do 参数：username password post
//	@ResponseBody // 设置数据返回json格式的
//	跳转登录界面
	@RequestMapping(value="/login.action",method=RequestMethod.GET)
	public String log(HttpServletRequest request) {
		System.out.println("jajajjajajajajajj");
		request.setAttribute("list", "哈哈哈哈哈哈");
		return "login";
	}
	
//	登录
	@ResponseBody //设置数据返回json格式
	@RequestMapping(value="/login.action",method=RequestMethod.POST)
	public String login(HttpSession session,String username,String password) {
		System.out.println(username);
		System.out.println(password);
//		boolean b = userService.queryByNameAndPassword(username, password);
		Map<String, Object> map2 = userManageService.queryByUsernameAndPassword(username, password);
		System.out.println("Controller返回值："+map2.get("flag"));
		System.out.println("Controller返回值："+map2.get("flag").equals(false));
		Map<String,Object> map = new HashMap<String, Object>();
		//int i = 1/0;
		if(map2.get("flag").equals(true)) {
			System.out.println("查询到用户，正在登陆");
			session.setAttribute("USER_SESSION", map2.get("userID")+"-"+username);
			map.put("flag", map2.get("flag"));
			map.put("userId", session.getId());
			return JSON.toJSONString(map);
		}else {
			//return "login";
			System.out.println("未查询到用户，登陆失败");
			map.put("flag", false);
			return JSON.toJSONString(map);
		}
	}
	
//注册
	//普通用户注册
		//查询是否有重名
	@ResponseBody
	@RequestMapping(value="/queryByUsername.action",method = RequestMethod.POST)
	public String registByName(String username) {
		System.out.println("controller:"+username);
		boolean queryRegistByUsername = userManageService.queryRegistByUsername(username);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("flag", queryRegistByUsername);
		return JSON.toJSONString(map);
	}
		//查询身份证号
	@ResponseBody
	@RequestMapping(value="/queryByuserID.action",method = RequestMethod.POST)
	public String registByuserID(String userID) {
		System.out.println(userID);
		boolean queryRegistByuserID = userManageService.queryRegistByuserID(userID);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("flag", queryRegistByuserID);
		return JSON.toJSONString(map);
	}
	//员工注册
		//邀请码查询
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
