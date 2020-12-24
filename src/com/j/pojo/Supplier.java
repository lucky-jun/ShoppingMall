package com.j.pojo;

public class Supplier {
	private int sup_id;
	private String sup_name;
	private String sup_phone_number;
	private String sup_address;
	private String sup_type;
	public int getSup_id() {
		return sup_id;
	}
	public void setSup_id(int sup_id) {
		this.sup_id = sup_id;
	}
	public String getSup_name() {
		return sup_name;
	}
	public void setSup_name(String sup_name) {
		this.sup_name = sup_name;
	}
	public String getSup_phone_number() {
		return sup_phone_number;
	}
	public void setSup_phone_number(String sup_phone_number) {
		this.sup_phone_number = sup_phone_number;
	}
	public String getSup_address() {
		return sup_address;
	}
	public void setSup_address(String sup_address) {
		this.sup_address = sup_address;
	}
	public String getSup_type() {
		return sup_type;
	}
	public void setSup_type(String sup_type) {
		this.sup_type = sup_type;
	}
	@Override
	public String toString() {
		return "Supplier [sup_id=" + sup_id + ", sup_name=" + sup_name + ", sup_phone_number=" + sup_phone_number
				+ ", sup_address=" + sup_address + ", sup_type=" + sup_type + "]";
	}
	public Supplier() {
		super();
	}
	public Supplier(int sup_id, String sup_name, String sup_phone_number, String sup_address, String sup_type) {
		super();
		this.sup_id = sup_id;
		this.sup_name = sup_name;
		this.sup_phone_number = sup_phone_number;
		this.sup_address = sup_address;
		this.sup_type = sup_type;
	}
}
