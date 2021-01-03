package com.j.pojo;

public class Goods {
	private  int goo_id;
	private  String goo_name;
	private  int goo_stock;
	private  double goo_buying_price;
	private  double goo_selling_price;
	private  int goo_supid;
	private  String goo_type;
	private  String goo_image;
	private  String goo_text;
	private  String goo_details;
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
	public int getGoo_stock() {
		return goo_stock;
	}
	public void setGoo_stock(int goo_stock) {
		this.goo_stock = goo_stock;
	}
	public double getGoo_buying_price() {
		return goo_buying_price;
	}
	public void setGoo_buying_price(double goo_buying_price) {
		this.goo_buying_price = goo_buying_price;
	}
	public double getGoo_selling_price() {
		return goo_selling_price;
	}
	public void setGoo_selling_price(double goo_selling_price) {
		this.goo_selling_price = goo_selling_price;
	}
	public int getGoo_supid() {
		return goo_supid;
	}
	public void setGoo_supid(int goo_supid) {
		this.goo_supid = goo_supid;
	}
	public String getGoo_type() {
		return goo_type;
	}
	public void setGoo_type(String goo_type) {
		this.goo_type = goo_type;
	}
	public String getGoo_image() {
		return goo_image;
	}
	public void setGoo_image(String goo_image) {
		this.goo_image = goo_image;
	}
	public String getGoo_text() {
		return goo_text;
	}
	public void setGoo_text(String goo_text) {
		this.goo_text = goo_text;
	}
	public String getGoo_details() {
		return goo_details;
	}
	public void setGoo_details(String goo_details) {
		this.goo_details = goo_details;
	}
	@Override
	public String toString() {
		return "[goo_id=" + goo_id + ", goo_name=" + goo_name + ", goo_stock=" + goo_stock + ", goo_buying_price="
				+ goo_buying_price + ", goo_selling_price=" + goo_selling_price + ", goo_supid=" + goo_supid
				+ ", goo_type=" + goo_type + ", goo_image=" + goo_image + ", goo_text=" + goo_text + ", goo_details="
				+ goo_details + "]";
	}
	public Goods() {
		super();
	}
	public Goods(int goo_id, String goo_name, int goo_stock, double goo_buying_price, double goo_selling_price,
			int goo_supid, String goo_type, String goo_image, String goo_text, String goo_details) {
		super();
		this.goo_id = goo_id;
		this.goo_name = goo_name;
		this.goo_stock = goo_stock;
		this.goo_buying_price = goo_buying_price;
		this.goo_selling_price = goo_selling_price;
		this.goo_supid = goo_supid;
		this.goo_type = goo_type;
		this.goo_image = goo_image;
		this.goo_text = goo_text;
		this.goo_details = goo_details;
	}
}
