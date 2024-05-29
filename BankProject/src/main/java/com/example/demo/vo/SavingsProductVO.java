package com.example.demo.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name="savings_product")
public class SavingsProductVO {

	@Id
	String account_num;
	String id;
	String savings_num;
	String reg_date;
	int total;
	int plus_money;
	String plus_date;
	public String getAccount_num() {
		return account_num;
	}
	public void setAccount_num(String account_num) {
		this.account_num = account_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSavings_num() {
		return savings_num;
	}
	public void setSavings_num(String savings_num) {
		this.savings_num = savings_num;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPlus_money() {
		return plus_money;
	}
	public void setPlus_money(int plus_money) {
		this.plus_money = plus_money;
	}
	public String getPlus_date() {
		return plus_date;
	}
	public void setPlus_date(String plus_date) {
		this.plus_date = plus_date;
	}
	
	@Override
	public String toString() {
		return "SavingsProductVO [account_num=" + account_num + ", id=" + id + ", savings_num=" + savings_num
				+ ", reg_date=" + reg_date + ", total=" + total + ", plus_money=" + plus_money + ", plus_date="
				+ plus_date + "]";
	}
	
}
