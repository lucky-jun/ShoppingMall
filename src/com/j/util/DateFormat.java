package com.j.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;
@Component
public class DateFormat {

	
	//utilʱ��ת����sqlʱ��
	public java.sql.Date DateUtiltoSql(Date date){
		//ʱ���ʽ����
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//long����ת��Stringʱ������
		String stringDate = sdf.format(date);
		//Stringʱ������ת��sql.Date����
		Date sqlDate = java.sql.Date.valueOf(stringDate);
		return sqlDate;
	}
	//utilʱ��תStringʱ��
	public String DateUtiltoString(java.util.Date date) {
		//ʱ���ʽ����
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//long����ת��Stringʱ������
		String stringDate = sdf.format(date);
		return stringDate;
	}
	
	//sqlʱ��ת����Stringʱ��
	public String DateSqltoString(java.sql.Date date){
		//ʱ���ʽ����
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//long����ת��Stringʱ������
		String stringDate = sdf.format(date);
		return stringDate;
	}
	//sqlʱ��ת����util.Dateʱ��
	public java.util.Date DateSqltoUtil(java.util.Date date) throws ParseException{
		//ʱ���ʽ����
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//long����ת��Stringʱ������
		String stringDate = sdf.format(date);
		java.util.Date parse = sdf.parse(stringDate);
		return parse;
	}
}
//// util.dateת����sql.date
//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");    //��ʽ������
//java.util.Date utilDate = new java.util.Date();	//��ȡ��ǰʱ��
//System.out.println("utilDate:"+sdf.format(utilDate));//ת��ʱ���ʽ
//java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());//util.Dateת��sql.Date��ʾ������ʱ�������
//System.out.println("sqlDate:"+sdf.format(sqlDate));//ת��ʱ���ʽ("yyyy-MM-dd HH:ss:mm")��ȥ�����룬ת����String����
//String format2 = sdf.format(sqlDate);//��String����Dateת��sql.Date����
//java.sql.Date valueOf = java.sql.Date.valueOf(format2);
//
//// sql.dateת����util.date
//java.sql.Date sqlDate1 = new java.sql.Date(new java.util.Date().getTime());
//System.out.println("sqlDate1:"+sqlDate1);
//java.util.Date utilDate1 = new java.util.Date(sqlDate1.getTime());
//System.out.println("utilDate1:"+utilDate1);