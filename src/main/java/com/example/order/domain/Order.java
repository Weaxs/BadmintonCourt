package com.example.order.domain;

import java.io.Serializable;

public class Order implements Serializable{

	//用户ID
	private String userId;
	//日期
	private String orderDate;
	//起始时间
	private String startTime;
	//结束时间
	private String endTime;
	//场地
	private char court;
	//取消标记
	private char mark;
	//花费
	private double fee;

	public String getUserId() {return userId;}
	public void setUserId(String userId) {this.userId = userId;}
	public String getOrderDate() {
		String tmp[] = orderDate.split(" ");
		return tmp[0];
	}
	public void setOrderDate(String orderDate) {this.orderDate = orderDate;}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public char getCourt() {
		return court;
	}
	public void setCourt(char court) {
		this.court = court;
	}
	public char getMark() {
		return mark;
	}
	public void setMark(char mark) {
		this.mark = mark;
	}
	public double getFee() {return fee;}
	public void setFee(double fee) {this.fee = fee;}

	public String toString(){
		int tmp = 0;
		if(this.mark == 'C') {
			tmp = (int)this.getFee();
			if (tmp == this.fee)
				return this.getOrderDate() + " " + this.getStartTime() +
						"~" + this.getEndTime() + " 违约金 " + tmp + "元";
			else
				return this.getOrderDate() + " " + this.getStartTime() +
						"~" + this.getEndTime() + " 违约金 " + this.getFee() + "元";
		}
		else{
			tmp = (int)this.getFee();
			if (tmp == this.fee)
				return this.getOrderDate() + " " + this.getStartTime() +
						"~" + this.getEndTime() + " " + tmp + "元";
			else
				return this.getOrderDate() + " " + this.getStartTime() +
						"~" + this.getEndTime() + " " + this.getFee() + "元";
		}
	}
}
