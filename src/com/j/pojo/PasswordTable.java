package com.j.pojo;

import java.util.Date;

public class PasswordTable {
	private int pwd_id;
	private String pwd_username;
	private String pwd_password;
	private int pwd_power;
	private Date pwd_regist_data;
	private Date pwd_login_data;
	private String pwd_question_one;
	private String  pwd_question_two;
	private String  pwd_question_one_key;
	private String  pwd_question_two_key;
	public int getPwd_id() {
		return pwd_id;
	}
	public void setPwd_id(int pwd_id) {
		this.pwd_id = pwd_id;
	}
	public String getPwd_username() {
		return pwd_username;
	}
	public void setPwd_username(String pwd_username) {
		this.pwd_username = pwd_username;
	}
	public String getPwd_password() {
		return pwd_password;
	}
	public void setPwd_password(String pwd_password) {
		this.pwd_password = pwd_password;
	}
	public int getPwd_power() {
		return pwd_power;
	}
	public void setPwd_power(int pwd_power) {
		this.pwd_power = pwd_power;
	}
	public Date getPwd_regist_data() {
		return pwd_regist_data;
	}
	public void setPwd_regist_data(Date pwd_regist_data) {
		this.pwd_regist_data = pwd_regist_data;
	}
	public Date getPwd_login_data() {
		return pwd_login_data;
	}
	public void setPwd_login_data(Date pwd_login_data) {
		this.pwd_login_data = pwd_login_data;
	}
	public String getPwd_question_one() {
		return pwd_question_one;
	}
	public void setPwd_question_one(String pwd_question_one) {
		this.pwd_question_one = pwd_question_one;
	}
	public String getPwd_question_two() {
		return pwd_question_two;
	}
	public void setPwd_question_two(String pwd_question_two) {
		this.pwd_question_two = pwd_question_two;
	}
	public String getPwd_question_one_key() {
		return pwd_question_one_key;
	}
	public void setPwd_question_one_key(String pwd_question_one_key) {
		this.pwd_question_one_key = pwd_question_one_key;
	}
	public String getPwd_question_two_key() {
		return pwd_question_two_key;
	}
	public void setPwd_question_two_key(String pwd_question_two_key) {
		this.pwd_question_two_key = pwd_question_two_key;
	}
	@Override
	public String toString() {
		return "PasswordTable [pwd_id=" + pwd_id + ", pwd_username=" + pwd_username + ", pwd_password=" + pwd_password
				+ ", pwd_power=" + pwd_power + ", pwd_regist_data=" + pwd_regist_data + ", pwd_login_data="
				+ pwd_login_data + ", pwd_question_one=" + pwd_question_one + ", pwd_question_two=" + pwd_question_two
				+ ", pwd_question_one_key=" + pwd_question_one_key + ", pwd_question_two_key=" + pwd_question_two_key
				+ "]";
	}
	public PasswordTable() {
		super();
	}
	public PasswordTable(int pwd_id, String pwd_username, String pwd_password, int pwd_power, Date pwd_regist_data,
			Date pwd_login_data, String pwd_question_one, String pwd_question_two, String pwd_question_one_key,
			String pwd_question_two_key) {
		super();
		this.pwd_id = pwd_id;
		this.pwd_username = pwd_username;
		this.pwd_password = pwd_password;
		this.pwd_power = pwd_power;
		this.pwd_regist_data = pwd_regist_data;
		this.pwd_login_data = pwd_login_data;
		this.pwd_question_one = pwd_question_one;
		this.pwd_question_two = pwd_question_two;
		this.pwd_question_one_key = pwd_question_one_key;
		this.pwd_question_two_key = pwd_question_two_key;
	}
}
