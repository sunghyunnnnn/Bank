package com.example.demo.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name="deposit_manager")
public class DepositVO {
	
	@Id
	private String deposit_num;
	private String deposit_name;
	private String create_date;
	private String min_money;
	private String rate;
	public String getDeposit_num() {
		return deposit_num;
	}
	public void setDeposit_num(String deposit_num) {
		this.deposit_num = deposit_num;
	}
	public String getDeposit_name() {
		return deposit_name;
	}
	public void setDeposit_name(String deposit_name) {
		this.deposit_name = deposit_name;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getMin_money() {
		return min_money;
	}
	public void setMin_money(String min_money) {
		this.min_money = min_money;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	@Override
	public String toString() {
		return "DepositVO [deposit_num=" + deposit_num + ", deposit_name=" + deposit_name + ", create_date="
				+ create_date + ", min_money=" + min_money + ", rate=" + rate + "]";
	}
	
	
}
