package com.j.pojo;

import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class EmpOrder {
	private int ord_id;
	private String ord_username;
	private String ord_useradd;
	private int ord_goodsid;
	private String ord_goodsname;
	private int ord_goodsnumber;
	private double ord_sumprice;
	private String ord_paystate;
	private String ord_orderstate;
	private Date ord_createtime;
	public int getOrd_id() {
		return ord_id;
	}
	public void setOrd_id(int ord_id) {
		this.ord_id = ord_id;
	}
	public String getOrd_username() {
		return ord_username;
	}
	public void setOrd_username(String ord_username) {
		this.ord_username = ord_username;
	}
	public String getOrd_useradd() {
		return ord_useradd;
	}
	public void setOrd_useradd(String ord_useradd) {
		this.ord_useradd = ord_useradd;
	}
	public int getOrd_goodsid() {
		return ord_goodsid;
	}
	public void setOrd_goodsid(int ord_goodsid) {
		this.ord_goodsid = ord_goodsid;
	}
	public String getOrd_goodsname() {
		return ord_goodsname;
	}
	public void setOrd_goodsname(String ord_goodsname) {
		this.ord_goodsname = ord_goodsname;
	}
	public int getOrd_goodsnumber() {
		return ord_goodsnumber;
	}
	public void setOrd_goodsnumber(int ord_goodsnumber) {
		this.ord_goodsnumber = ord_goodsnumber;
	}
	public double getOrd_sumprice() {
		return ord_sumprice;
	}
	public void setOrd_sumprice(double ord_sumprice) {
		this.ord_sumprice = ord_sumprice;
	}
	public String getOrd_paystate() {
		return ord_paystate;
	}
	public void setOrd_paystate(String ord_paystate) {
		this.ord_paystate = ord_paystate;
	}
	public String getOrd_orderstate() {
		return ord_orderstate;
	}
	public void setOrd_orderstate(String ord_orderstate) {
		this.ord_orderstate = ord_orderstate;
	}
	public Date getOrd_createtime() {
		return ord_createtime;
	}
	public void setOrd_createtime(Date ord_createtime) {
		this.ord_createtime = ord_createtime;
	}
	@Override
	public String toString() {
		return "EmpOrder [ord_id=" + ord_id + ", ord_username=" + ord_username + ", ord_useradd=" + ord_useradd
				+ ", ord_goodsid=" + ord_goodsid + ", ord_goodsname=" + ord_goodsname + ", ord_goodsnumber="
				+ ord_goodsnumber + ", ord_sumprice=" + ord_sumprice + ", ord_paystate=" + ord_paystate
				+ ", ord_orderstate=" + ord_orderstate + ", ord_createtime=" + ord_createtime + "]";
	}
	public EmpOrder() {
		super();
	}
	public EmpOrder(int ord_id, String ord_username, String ord_useradd, int ord_goodsid, String ord_goodsname,
			int ord_goodsnumber, double ord_sumprice, String ord_paystate, String ord_orderstate,
			Date ord_createtime) {
		super();
		this.ord_id = ord_id;
		this.ord_username = ord_username;
		this.ord_useradd = ord_useradd;
		this.ord_goodsid = ord_goodsid;
		this.ord_goodsname = ord_goodsname;
		this.ord_goodsnumber = ord_goodsnumber;
		this.ord_sumprice = ord_sumprice;
		this.ord_paystate = ord_paystate;
		this.ord_orderstate = ord_orderstate;
		this.ord_createtime = ord_createtime;
	}
}
