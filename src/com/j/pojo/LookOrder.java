package com.j.pojo;

public class LookOrder {
	private int goo_id;
	private String goo_name;
	private String goo_image;
	private double goo_selling_price;
	private int number;
	private double sumprice;
	private String paystate;
	private String orderstate;
	private String creattime;
	public int getGoo_id() {
		return goo_id;
	}
	public void setGoo_id(int goo_id) {
		this.goo_id = goo_id;
	}
	public String getGoo_name() {
		return goo_name;
	}
	public void setGoo_name(String goo_name) {
		this.goo_name = goo_name;
	}
	public String getGoo_image() {
		return goo_image;
	}
	public void setGoo_image(String goo_image) {
		this.goo_image = goo_image;
	}
	public double getGoo_selling_price() {
		return goo_selling_price;
	}
	public void setGoo_selling_price(double goo_selling_price) {
		this.goo_selling_price = goo_selling_price;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getSumprice() {
		return sumprice;
	}
	public void setSumprice(double sumprice) {
		this.sumprice = sumprice;
	}
	public String getPaystate() {
		return paystate;
	}
	public void setPaystate(String paystate) {
		this.paystate = paystate;
	}
	public String getOrderstate() {
		return orderstate;
	}
	public void setOrderstate(String orderstate) {
		this.orderstate = orderstate;
	}
	public String getCreattime() {
		return creattime;
	}
	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}
	@Override
	public String toString() {
		return "LookOrder [goo_id=" + goo_id + ", goo_name=" + goo_name + ", goo_image=" + goo_image
				+ ", goo_selling_price=" + goo_selling_price + ", number=" + number + ", sumprice=" + sumprice
				+ ", paystate=" + paystate + ", orderstate=" + orderstate + ", creattime=" + creattime + "]";
	}
	public LookOrder() {
		super();
	}
	public LookOrder(int goo_id, String goo_name, String goo_image, double goo_selling_price, int number,
			double sumprice, String paystate, String orderstate, String creattime) {
		super();
		this.goo_id = goo_id;
		this.goo_name = goo_name;
		this.goo_image = goo_image;
		this.goo_selling_price = goo_selling_price;
		this.number = number;
		this.sumprice = sumprice;
		this.paystate = paystate;
		this.orderstate = orderstate;
		this.creattime = creattime;
	}
}
