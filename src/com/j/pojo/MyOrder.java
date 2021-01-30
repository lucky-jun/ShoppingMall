package com.j.pojo;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class MyOrder {
	private int ord_id;
	private int ord_userid;
	private String ord_deliadd;
	private String ord_goodsinf;
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
	public int getOrd_userid() {
		return ord_userid;
	}
	public void setOrd_userid(int ord_userid) {
		this.ord_userid = ord_userid;
	}
	public String getOrd_deliadd() {
		return ord_deliadd;
	}
	public void setOrd_deliadd(String ord_deliadd) {
		this.ord_deliadd = ord_deliadd;
	}
	public String getOrd_goodsinf() {
		return ord_goodsinf;
	}
	public void setOrd_goodsinf(String ord_goodsinf) {
		this.ord_goodsinf = ord_goodsinf;
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
		return "MyOrder [ord_id=" + ord_id + ", ord_userid=" + ord_userid + ", ord_deliadd=" + ord_deliadd
				+ ", ord_goodsinf=" + ord_goodsinf + ", ord_sumprice=" + ord_sumprice + ", ord_paystate=" + ord_paystate
				+ ", ord_orderstate=" + ord_orderstate + ", ord_createtime=" + ord_createtime + "]";
	}
	public MyOrder() {
		super();
	}
	public MyOrder(int ord_id, int ord_userid, String ord_deliadd, String ord_goodsinf, double ord_sumprice,
			String ord_paystate, String ord_orderstate, Date ord_createtime) {
		super();
		this.ord_id = ord_id;
		this.ord_userid = ord_userid;
		this.ord_deliadd = ord_deliadd;
		this.ord_goodsinf = ord_goodsinf;
		this.ord_sumprice = ord_sumprice;
		this.ord_paystate = ord_paystate;
		this.ord_orderstate = ord_orderstate;
		this.ord_createtime = ord_createtime;
	}
	public MyOrder(int ord_userid, String ord_deliadd, String ord_goodsinf, double ord_sumprice,
			String ord_paystate, String ord_orderstate, Date ord_createtime) {
		super();
		this.ord_id = ord_id;
		this.ord_userid = ord_userid;
		this.ord_deliadd = ord_deliadd;
		this.ord_goodsinf = ord_goodsinf;
		this.ord_sumprice = ord_sumprice;
		this.ord_paystate = ord_paystate;
		this.ord_orderstate = ord_orderstate;
		this.ord_createtime = ord_createtime;
	}
}
