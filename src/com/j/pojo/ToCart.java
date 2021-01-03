package com.j.pojo;

public class ToCart {
	private int goodsId;
	private int userId;
	private int number;
	private double price;
	private String name;
	private String img;
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "[goodsId=" + goodsId + ", userId=" + userId + ", number=" + number + ", price=" + price
				+ ", name=" + name + ", img=" + img + "]";
	}
	public ToCart() {
		super();
	}
	public ToCart(int goodsId, int userId, int number, double price, String name, String img) {
		super();
		this.goodsId = goodsId;
		this.userId = userId;
		this.number = number;
		this.price = price;
		this.name = name;
		this.img = img;
	}
}
