package com.j.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test01 {
	public static void main(String[] args) throws ParseException {

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
