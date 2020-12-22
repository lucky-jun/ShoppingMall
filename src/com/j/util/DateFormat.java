package com.j.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;
@Component
public class DateFormat {
	
	public String formatDate(Long date) {
		//时间格式规则
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//long类型转换String时间类型
		String stringDate = sdf.format(date);
		return stringDate;
	}
	
	//util时间转换成sql时间
	public java.sql.Date DateUtilLongtoSql(Long date){
		//转换时间格式
		String formatDate = this.formatDate(date);
		//String时间类型转换sql.Date类型
		Date sqlDate = java.sql.Date.valueOf(formatDate);
		return sqlDate;
	}
	//util时间转String时间
	public String DateUtiltoString(java.util.Date date) {
		return this.formatDate(date.getTime());
	}
	
	//sql时间转换成String时间
	public String DateSqlLongtoString(Long date){
		//转换时间格式
		String formatDate = this.formatDate(date);
		return formatDate;
	}
	//sql时间转化成util.Date时间
	public java.util.Date DateSqlLongtoUtil(java.sql.Date date){
		String formatDate = this.formatDate(date.getTime());
		return new java.util.Date(formatDate);
	}
}
//// util.date转换成sql.date
//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");    //格式化规则
//java.util.Date utilDate = new java.util.Date();	//获取当前时间
//System.out.println("utilDate:"+sdf.format(utilDate));//转换时间格式
//java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());//util.Date转换sql.Date显示年月日时分秒毫秒
//System.out.println("sqlDate:"+sdf.format(sqlDate));//转换时间格式("yyyy-MM-dd HH:ss:mm")，去除毫秒，转换成String类型
//String format2 = sdf.format(sqlDate);//将String类型Date转换sql.Date类型
//java.sql.Date valueOf = java.sql.Date.valueOf(format2);
//
//// sql.date转换成util.date
//java.sql.Date sqlDate1 = new java.sql.Date(new java.util.Date().getTime());
//System.out.println("sqlDate1:"+sqlDate1);
//java.util.Date utilDate1 = new java.util.Date(sqlDate1.getTime());
//System.out.println("utilDate1:"+utilDate1);