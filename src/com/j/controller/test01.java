package com.j.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test01 {
	public static void main(String[] args) throws ParseException {

		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", "张三");
		map.put("age",18);
		map.put("maptest",list1);
		List<User> list2 = mapper.query(map);
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = "2020-12-12 12:12:00";
		Date parse = sdf.parse(str);
		System.out.println(parse);
		Date s = new Date();
		String format = sdf.format(s);
		System.out.println(format);
		Date parse2 = sdf.parse(format);
		System.out.println(parse2);
		String format2 = sdf.format(s);
		System.out.println("现在时间："+format2);
		Timestamp valueOf = Timestamp.valueOf(format2);
		System.out.println("----------:"+valueOf);
		java.sql.Timestamp t = new java.sql.Timestamp(s.getTime());
		
		System.out.println("Timestamp:"+t);
	}
}
