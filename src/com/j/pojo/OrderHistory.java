package com.j.pojo;

import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class OrderHistory {
	private int his_id;
	private int his_userid;
	private String his_goodsinf;
	private double his_sumprice;
	private String his_orderstate;
	private Date his_starttime;
	private Date his_stoptime;
	public int getHis_id() {
		return his_id;
	}
	public void setHis_id(int his_id) {
		this.his_id = his_id;
	}
	public int getHis_userid() {
		return his_userid;
	}
	public void setHis_userid(int his_userid) {
		this.his_userid = his_userid;
	}
	public String getHis_goodsinf() {
		return his_goodsinf;
	}
	public void setHis_goodsinf(String his_goodsinf) {
		this.his_goodsinf = his_goodsinf;
	}
	public double getHis_sumprice() {
		return his_sumprice;
	}
	public void setHis_sumprice(double his_sumprice) {
		this.his_sumprice = his_sumprice;
	}
	public String getHis_orderstate() {
		return his_orderstate;
	}
	public void setHis_orderstate(String his_orderstate) {
		this.his_orderstate = his_orderstate;
	}
	public Date getHis_starttime() {
		return his_starttime;
	}
	public void setHis_starttime(Date his_starttime) {
		this.his_starttime = his_starttime;
	}
	public Date getHis_stoptime() {
		return his_stoptime;
	}
	public void setHis_stoptime(Date his_stoptime) {
		this.his_stoptime = his_stoptime;
	}
	@Override
	public String toString() {
		return "OrderHistory [his_id=" + his_id + ", his_userid=" + his_userid + ", his_goodsinf=" + his_goodsinf
				+ ", his_sumprice=" + his_sumprice + ", his_orderstate=" + his_orderstate + ", his_starttime="
				+ his_starttime + ", his_stoptime=" + his_stoptime + "]";
	}
	public OrderHistory() {
		super();
	}
	public OrderHistory(int his_id, int his_userid, String his_goodsinf, double his_sumprice, String his_orderstate,
			Date his_starttime, Date his_stoptime) {
		super();
		this.his_id = his_id;
		this.his_userid = his_userid;
		this.his_goodsinf = his_goodsinf;
		this.his_sumprice = his_sumprice;
		this.his_orderstate = his_orderstate;
		this.his_starttime = his_starttime;
		this.his_stoptime = his_stoptime;
	}
}
