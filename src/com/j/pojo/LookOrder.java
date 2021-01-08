package com.j.pojo;

public class LookOrder {
	private int goo_id;
	private String goo_name;
	private String goo_image;
	private double goo_selling_price;
	private double number;
	private double sumprice;
	public int getGoo_id() {
		return goo_id;
	}
	public void setGoo_id(int goo_id) {
		this.goo_id = goo_id;
	}
	public String getGoo_image() {
		return goo_image;
	}
	public void setGoo_image(String goo_image) {
		this.goo_image = goo_image;
	}
	public String getGoo_name() {
		return goo_name;
	}
	public void setGoo_name(String goo_name) {
		this.goo_name = goo_name;
	}
	public double getGoo_selling_price() {
		return goo_selling_price;
	}
	public void setGoo_selling_price(double goo_selling_price) {
		this.goo_selling_price = goo_selling_price;
	}
	public double getNumber() {
		return number;
	}
	public void setNumber(double number) {
		this.number = number;
	}
	public double getSumprice() {
		return sumprice;
	}
	public void setSumprice(double sumprice) {
		this.sumprice = sumprice;
	}
	@Override
	public String toString() {
		return "LookOrder [goo_id=" + goo_id + ", goo_name=" + goo_name+ ", goo_image=" + goo_image 
				+ ", goo_selling_price=" + goo_selling_price + ", number=" + number + ", sumprice=" + sumprice + "]";
	}
	public LookOrder() {
		super();
	}
	public LookOrder(int goo_id, String goo_name, String goo_image,  double goo_selling_price, double number,
			double sumprice) {
		super();
		this.goo_id = goo_id;
		this.goo_name = goo_name;
		this.goo_image = goo_image;
		this.goo_selling_price = goo_selling_price;
		this.number = number;
		this.sumprice = sumprice;
	}
}
