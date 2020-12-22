package com.j.controller;

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
	}
}
