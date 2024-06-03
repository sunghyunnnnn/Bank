package com.example.demo.vo;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity(name="savings_manager")
public class SavingsManagerVO {
	
	@Id
	@NotBlank(message = "상품 번호는 필수 항목입니다.")
	private String savings_num;
	@NotBlank(message = "상품 이름은 필수 항목입니다.")
	private String savings_name;
	@NotBlank(message = "기간은 필수 항목입니다.")
	private String create_date;
    @NotNull(message = "가입 최소 금액은 필수 항목입니다.")
    @Min(value = 0, message = "가입 최소 금액은 0 이상이어야 합니다.")
	private int min_money;
    @NotBlank(message = "이율은 필수 항목입니다.")
    @Pattern(regexp = "^[0-9]+(\\.[0-9]+)?%$", message = "올바른 이율 형식이 아닙니다.")
	private String rate;
	private String fr_or_in;
	
	public String getSavings_num() {
		return savings_num;
	}
	public void setSavings_num(String savings_num) {
		this.savings_num = savings_num;
	}
	public String getSavings_name() {
		return savings_name;
	}
	public void setSavings_name(String savings_name) {
		this.savings_name = savings_name;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public int getMin_money() {
		return min_money;
	}
	public void setMin_money(int min_money) {
		this.min_money = min_money;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getFr_or_in() {
		return fr_or_in;
	}
	public void setFr_or_in(String fr_or_in) {
		this.fr_or_in = fr_or_in;
	}
	@Override
	public String toString() {
		return "SavingsManagerVO [savings_num=" + savings_num + ", savings_name=" + savings_name + ", create_date="
				+ create_date + ", min_money=" + min_money + ", rate=" + rate + ", fr_or_in=" + fr_or_in + "]";
	}
	

}
