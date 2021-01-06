package com.j.pojo;

public class ToCart {
	private int car_gooid;
	private int car_userid;
	private int number;
	private double goo_selling_price;
	private String goo_name;
	private String goo_image;
	public int getCar_gooid() {
		return car_gooid;
	}
	public void setCar_gooid(int car_gooid) {
		this.car_gooid = car_gooid;
	}
	public int getCar_userid() {
		return car_userid;
	}
	public void setCar_userid(int car_userid) {
		this.car_userid = car_userid;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getGoo_selling_price() {
		return goo_selling_price;
	}
	public void setGoo_selling_price(double goo_selling_price) {
		this.goo_selling_price = goo_selling_price;
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
	@Override
	public String toString() {
		return "ToCart [car_gooid=" + car_gooid + ", car_userid=" + car_userid + ", number=" + number
				+ ", goo_selling_price=" + goo_selling_price + ", goo_name=" + goo_name + ", goo_image=" + goo_image
				+ "]";
	}
	public ToCart() {
		super();
	}
	public ToCart(int car_gooid, int car_userid, int number, double goo_selling_price, String goo_name,
			String goo_image) {
		super();
		this.car_gooid = car_gooid;
		this.car_userid = car_userid;
		this.number = number;
		this.goo_selling_price = goo_selling_price;
		this.goo_name = goo_name;
		this.goo_image = goo_image;
	}
	
}