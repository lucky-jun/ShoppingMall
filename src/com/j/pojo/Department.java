package com.j.pojo;

public class Department {
	private int dep_id;
	private String dep_name;
	private int dep_power;
	private String dep_code;
	public int getDep_id() {
		return dep_id;
	}
	public void setDep_id(int dep_id) {
		this.dep_id = dep_id;
	}
	public String getDep_name() {
		return dep_name;
	}
	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}
	public int getDep_power() {
		return dep_power;
	}
	public void setDep_power(int dep_power) {
		this.dep_power = dep_power;
	}
	public String getDep_code() {
		return dep_code;
	}
	public void setDep_code(String dep_code) {
		this.dep_code = dep_code;
	}
	@Override
	public String toString() {
		return "Department [dep_id=" + dep_id + ", dep_name=" + dep_name + ", dep_power=" + dep_power + ", dep_code="
				+ dep_code + "]";
	}
	public Department() {
		super();
	}
	public Department(int dep_id, String dep_name, int dep_power, String dep_code) {
		super();
		this.dep_id = dep_id;
		this.dep_name = dep_name;
		this.dep_power = dep_power;
		this.dep_code = dep_code;
	}
}
