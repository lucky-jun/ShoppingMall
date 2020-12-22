package com.j.pojo;

public class User {
	private int user_id;
	private String user_name;
	private String user_gender;
	private int user_power;
	private String user_uid;
	private String user_phone_number;
	private String user_address;
	private int user_pasid;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_gender() {
		return user_gender;
	}
	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}
	public int getUser_power() {
		return user_power;
	}
	public void setUser_power(int user_power) {
		this.user_power = user_power;
	}
	public String getUser_uid() {
		return user_uid;
	}
	public void setUser_uid(String user_uid) {
		this.user_uid = user_uid;
	}
	public String getUser_phone_number() {
		return user_phone_number;
	}
	public void setUser_phone_number(String user_phone_number) {
		this.user_phone_number = user_phone_number;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public int getUser_pasid() {
		return user_pasid;
	}
	public void setUser_pasid(int user_pasid) {
		this.user_pasid = user_pasid;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name + ", user_gender=" + user_gender
				+ ", user_power=" + user_power + ", user_uid=" + user_uid + ", user_phone_number=" + user_phone_number
				+ ", user_address=" + user_address + ", user_pasid=" + user_pasid + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	public User() {
		super();
	}
	public User(int user_id, String user_name, String user_gender, int user_power, String user_uid,
			String user_phone_number, String user_address, int user_pasid) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_gender = user_gender;
		this.user_power = user_power;
		this.user_uid = user_uid;
		this.user_phone_number = user_phone_number;
		this.user_address = user_address;
		this.user_pasid = user_pasid;
	}
}
