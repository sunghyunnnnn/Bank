package com.example.demo.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name="remit")
public class RemitVO {
	
	@Id
	private int num;
	private String remit_sign;
	private String account_num;
	private String remit_account;
	private String remit_text;
	private int remit_money;
	private String remit_date;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getRemit_sign() {
		return remit_sign;
	}
	public void setRemit_sign(String remit_sign) {
		this.remit_sign = remit_sign;
	}
	public String getAccount_num() {
		return account_num;
	}
	public void setAccount_num(String account_num) {
		this.account_num = account_num;
	}
	public String getRemit_account() {
		return remit_account;
	}
	public void setRemit_account(String remit_account) {
		this.remit_account = remit_account;
	}
	public String getRemit_text() {
		return remit_text;
	}
	public void setRemit_text(String remit_text) {
		this.remit_text = remit_text;
	}
	public int getRemit_money() {
		return remit_money;
	}
	public void setRemit_money(int remit_money) {
		this.remit_money = remit_money;
	}
	public String getRemit_date() {
		return remit_date;
	}
	public void setRemit_date(String remit_date) {
		this.remit_date = remit_date;
	}
	@Override
	public String toString() {
		return "RemitVO [num=" + num + ", remit_sign=" + remit_sign + ", account_num=" + account_num
				+ ", remit_account=" + remit_account + ", remit_text=" + remit_text + ", remit_money=" + remit_money
				+ ", remit_date=" + remit_date + "]";
	}
	
}
