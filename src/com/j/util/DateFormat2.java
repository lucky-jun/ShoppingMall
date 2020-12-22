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

		System.out.println("ʱ��ת�������ࣺ"+date);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");    //��ʽ������
		
		String format = sdf.format(date);
		System.out.println("ת����"+format);
		
		// util.dateת����sql.date
		java.util.Date utilDate = new java.util.Date();	//��ȡ��ǰʱ��
		System.out.println("utilDate:"+sdf.format(utilDate));//ת��ʱ���ʽ
		java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());//util.Dateת��sql.Date��ʾ������ʱ�������
		System.out.println("sqlDate:"+sdf.format(sqlDate));//ת��ʱ���ʽ("yyyy-MM-dd HH:ss:mm")��ȥ�����룬ת����String����
		String format2 = sdf.format(sqlDate);//��String����Dateת��sql.Date����
		java.sql.Date valueOf = java.sql.Date.valueOf(format2);

		// sql.dateת����util.date
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
