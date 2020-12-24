package com.j.pojo;

import java.util.Date;

public class MyCart {
	private int car_id;
	private int car_userid;
	private int car_gooid;
	private int car_goonum;
	private double car_sumprice;
	private Date car_starttime;
	private int car_state;
	public int getCar_id() {
		return car_id;
	}
	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}
	public int getCar_userid() {
		return car_userid;
	}
	public void setCar_userid(int car_userid) {
		this.car_userid = car_userid;
	}
	public int getCar_gooid() {
		return car_gooid;
	}
	public void setCar_gooid(int car_gooid) {
		this.car_gooid = car_gooid;
	}
	public int getCar_goonum() {
		return car_goonum;
	}
	public void setCar_goonum(int car_goonum) {
		this.car_goonum = car_goonum;
	}
	public double getCar_sumprice() {
		return car_sumprice;
	}
	public void setCar_sumprice(double car_sumprice) {
		this.car_sumprice = car_sumprice;
	}
	public Date getCar_starttime() {
		return car_starttime;
	}
	public void setCar_starttime(Date car_starttime) {
		this.car_starttime = car_starttime;
	}
	public int getCar_state() {
		return car_state;
	}
	public void setCar_state(int car_state) {
		this.car_state = car_state;
	}
	@Override
	public String toString() {
		return "MyCart [car_id=" + car_id + ", car_userid=" + car_userid + ", car_gooid=" + car_gooid + ", car_goonum="
				+ car_goonum + ", car_sumprice=" + car_sumprice + ", car_starttime=" + car_starttime + ", car_state="
				+ car_state + "]";
	}
	public MyCart() {
		super();
	}
	public MyCart(int car_id, int car_userid, int car_gooid, int car_goonum, double car_sumprice, Date car_starttime,
			int car_state) {
		super();
		this.car_id = car_id;
		this.car_userid = car_userid;
		this.car_gooid = car_gooid;
		this.car_goonum = car_goonum;
		this.car_sumprice = car_sumprice;
		this.car_starttime = car_starttime;
		this.car_state = car_state;
	}
}
