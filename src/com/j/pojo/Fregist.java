package com.j.pojo;

public class Fregist {
	private String username;
	private String pass;
	private String question_one;
	private String question_one_key;
	private String question_two;
	private String question_two_key;
	private String name;
	private String gender;
	private String uuid;
	private String tel_number;
	private String address;
	private String depid;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getQuestion_one() {
		return question_one;
	}
	public void setQuestion_one(String question_one) {
		this.question_one = question_one;
	}
	public String getQuestion_one_key() {
		return question_one_key;
	}
	public void setQuestion_one_key(String question_one_key) {
		this.question_one_key = question_one_key;
	}
	public String getQuestion_two() {
		return question_two;
	}
	public void setQuestion_two(String question_two) {
		this.question_two = question_two;
	}
	public String getQuestion_two_key() {
		return question_two_key;
	}
	public void setQuestion_two_key(String question_two_key) {
		this.question_two_key = question_two_key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getTel_number() {
		return tel_number;
	}
	public void setTel_number(String tel_number) {
		this.tel_number = tel_number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDepid() {
		return depid;
	}
	public void setDepid(String depid) {
		this.depid = depid;
	}
	@Override
	public String toString() {
		return "fregist [username=" + username + ", pass=" + pass + ", question_one=" + question_one
				+ ", question_one_key=" + question_one_key + ", question_two=" + question_two + ", question_two_key="
				+ question_two_key + ", name=" + name + ", gender=" + gender + ", uuid=" + uuid + ", tel_number="
				+ tel_number + ", address=" + address + ", depid=" + depid + "]";
	}
}
