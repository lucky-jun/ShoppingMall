package com.j.pojo;

public class Employee {
	private int emp_id;
	private String emp_name;
	private String emp_gender;
	private int emp_depid;
	private String emp_userid;
	private String emp_phone_number;
	private String emp_address;
	private int emp_pasid;
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_gender() {
		return emp_gender;
	}
	public void setEmp_gender(String emp_gender) {
		this.emp_gender = emp_gender;
	}
	public int getEmp_depid() {
		return emp_depid;
	}
	public void setEmp_depid(int emp_depid) {
		this.emp_depid = emp_depid;
	}
	public String getEmp_userid() {
		return emp_userid;
	}
	public void setEmp_userid(String emp_userid) {
		this.emp_userid = emp_userid;
	}
	public String getEmp_phone_number() {
		return emp_phone_number;
	}
	public void setEmp_phone_number(String emp_phone_number) {
		this.emp_phone_number = emp_phone_number;
	}
	public String getEmp_address() {
		return emp_address;
	}
	public void setEmp_address(String emp_address) {
		this.emp_address = emp_address;
	}
	public int getEmp_pasid() {
		return emp_pasid;
	}
	public void setEmp_pasid(int emp_pasid) {
		this.emp_pasid = emp_pasid;
	}
	@Override
	public String toString() {
		return "Employee [emp_id=" + emp_id + ", emp_name=" + emp_name + ", emp_gender=" + emp_gender + ", emp_depid="
				+ emp_depid + ", emp_userid=" + emp_userid + ", emp_phone_number=" + emp_phone_number + ", emp_address="
				+ emp_address + ", emp_pasid=" + emp_pasid + "]";
	}
	public Employee() {
		super();
	}
	public Employee(int emp_id, String emp_name, String emp_gender, int emp_depid, String emp_userid,
			String emp_phone_number, String emp_address, int emp_pasid) {
		super();
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.emp_gender = emp_gender;
		this.emp_depid = emp_depid;
		this.emp_userid = emp_userid;
		this.emp_phone_number = emp_phone_number;
		this.emp_address = emp_address;
		this.emp_pasid = emp_pasid;
	}
}
