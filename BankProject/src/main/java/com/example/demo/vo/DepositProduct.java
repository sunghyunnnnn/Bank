package com.example.demo.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name="deposit_product")
public class DepositProduct {
	@Id
	private String account_num;
	private String deposit_num;
	private String id;
	private String reg_date;
	private String total;
	public String getAccount_num() {
		return account_num;
	}
	public void setAccount_num(String account_num) {
		this.account_num = account_num;
	}
	public String getDeposit_num() {
		return deposit_num;
	}
	public void setDeposit_num(String deposit_num) {
		this.deposit_num = deposit_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "DepositProduct [account_num=" + account_num + ", deposit_num=" + deposit_num + ", id=" + id
				+ ", reg_date=" + reg_date + ", total=" + total + "]";
	}
	
	

}
