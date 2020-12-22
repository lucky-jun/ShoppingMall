package com.j.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateFormat2 implements Converter<String, Date>{

	@Override
	public Date convert(String date) {
		if(date != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				 return simpleDateFormat.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Date convertSqlDatetoString(Long date){

		System.out.println("时间转换工具类："+date);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");    //格式化规则
		
		String format = sdf.format(date);
		System.out.println("转换后："+format);
		
		// util.date转换成sql.date
		java.util.Date utilDate = new java.util.Date();	//获取当前时间
		System.out.println("utilDate:"+sdf.format(utilDate));//转换时间格式
		java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());//util.Date转换sql.Date显示年月日时分秒毫秒
		System.out.println("sqlDate:"+sdf.format(sqlDate));//转换时间格式("yyyy-MM-dd HH:ss:mm")，去除毫秒，转换成String类型
		String format2 = sdf.format(sqlDate);//将String类型Date转换sql.Date类型
		java.sql.Date valueOf = java.sql.Date.valueOf(format2);

		// sql.date转换成util.date
		java.sql.Date sqlDate1 = new java.sql.Date(new java.util.Date().getTime());
		System.out.println("sqlDate1:"+sqlDate1);
		java.util.Date utilDate1 = new java.util.Date(sqlDate1.getTime());
		System.out.println("utilDate1:"+utilDate1);
		return new Date();
	}
	
	public java.sql.Date convertStringtoSqlDate(String date){
		System.out.println(date);
		Date convert = this.convert(date);
		java.sql.Date sd;
		java.util.Date ud = convert;
		sd = new java.sql.Date(ud.getTime()); 
		return sd;
	}

}
