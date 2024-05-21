package com.example.demo.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name="plus")
public class PlusVO {
	
	@Id
	private int num;
	private String plus_sign;
	private String account_num;
	private String plus_text;
	private int plus_money;
	private String plus_date;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getPlus_sign() {
		return plus_sign;
	}
	public void setPlus_sign(String plus_sign) {
		this.plus_sign = plus_sign;
	}
	public String getAccount_num() {
		return account_num;
	}
	public void setAccount_num(String account_num) {
		this.account_num = account_num;
	}
	public String getPlus_text() {
		return plus_text;
	}
	public void setPlus_text(String plus_text) {
		this.plus_text = plus_text;
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
		return "PlusVO [num=" + num + ", plus_sign=" + plus_sign + ", account_num=" + account_num + ", plus_text="
				+ plus_text + ", plus_money=" + plus_money + ", plus_date=" + plus_date + "]";
	}
	
}
