package com.j.pojo;

import java.util.Date;

public class InvitationCode {
	private String inv_code;
	private int inv_power;
	private Date inv_date;
	public String getInv_code() {
		return inv_code;
	}
	public void setInv_code(String inv_code) {
		this.inv_code = inv_code;
	}
	public int getInv_power() {
		return inv_power;
	}
	public void setInv_power(int inv_power) {
		this.inv_power = inv_power;
	}
	public Date getInv_date() {
		return inv_date;
	}
	public void setInv_date(Date inv_date) {
		this.inv_date = inv_date;
	}
	@Override
	public String toString() {
		return "[inv_code=" + inv_code + ", inv_power=" + inv_power + ", inv_date=" + inv_date + "]";
	}
	public InvitationCode() {
		super();
	}
	public InvitationCode(String inv_code, int inv_power, Date inv_date) {
		super();
		this.inv_code = inv_code;
		this.inv_power = inv_power;
		this.inv_date = inv_date;
	}

}
