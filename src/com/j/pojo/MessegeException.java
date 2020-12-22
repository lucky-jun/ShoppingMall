package com.j.pojo;

public class MessegeException extends Exception{
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "MessegeException [msg=" + msg + "]";
	}

	public MessegeException(String msg) {
		super();
		this.msg = msg;
	}
}
