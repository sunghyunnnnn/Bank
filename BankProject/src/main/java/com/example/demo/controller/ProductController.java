package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.jpa.SavingsManagerRepo;
import com.example.demo.vo.SavingsManagerVO;
import com.example.demo.jpa.AccountRepo;
import com.example.demo.jpa.DepositProductRepo;
import com.example.demo.jpa.DepositRepo;
import com.example.demo.jpa.RemitRepo;
import com.example.demo.vo.DepositVO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ProductController {
	
	@Autowired
	SavingsManagerRepo smr;
	ModelAndView mav = new ModelAndView();
	
	DepositRepo depositRepo;
	@Autowired
	AccountRepo accountRepo;
	@Autowired
	DepositProductRepo  depositProductRepo;
	@Autowired
	RemitRepo remitRepo;
	
	
	@RequestMapping(value="/depositList")
	public ModelAndView deposit() {
		ModelAndView mav = new ModelAndView();
		List<DepositVO> depositVO =  depositRepo.findAll();
//		System.out.println(">>> " + depositVO);
		mav.addObject("depositVO", depositVO);
		mav.setViewName("products/depositList");
		return mav;
	}
	@RequestMapping(value="/savings")
	public ModelAndView savings() {
		List<SavingsManagerVO> list = smr.savings_list();
		mav.addObject("list", list);
		mav.setViewName("products/savings");
		return mav;
	}
	@RequestMapping(value="/depositProduct")
	public ModelAndView depositProduct(HttpServletRequest request) {
		String deposit_num = request.getParameter("deposit_num");
		String id = request.getParameter("id");
		List<Map<String, Integer>> accountList = accountRepo.selectAccount2(id);
		
		List<String> accountTotal = new ArrayList<>();
		List<String> accountNum = new ArrayList<>();
		
		for(Map<String, Integer> a : accountList) {
			accountNum.add(String.valueOf(a.get("ACCOUNT_NUM")));
			accountTotal.add(a.get("TOTAL").toString());
		}
		
		ModelAndView mav = new ModelAndView();
		DepositVO deposit = depositRepo.getById(deposit_num);
		
		mav.addObject("accountNum",accountNum);
		mav.addObject("accountTotal", accountTotal);
		mav.addObject("deposit", deposit);
		mav.setViewName("products/depositPage");
		return mav;
	}
	@RequestMapping(value="depositController")
	public ModelAndView depositController(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String deposit_num = request.getParameter("deposit_num");
		String account_num = request.getParameter("account_num");
		String id = request.getParameter("id");
		String total = request.getParameter("total");
		int exchange_money = Integer.parseInt(total);
		
		try {
			depositProductRepo.insertDeposit(account_num, deposit_num, id, total);
			
		} catch (Exception e) {
			
		}
		try {
			remitRepo.insertRemit(account_num, "----", "----", exchange_money);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			remitRepo.updateRemit(exchange_money, account_num);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		mav.setViewName("index");
		return mav;
		
		
		
	}
	
	@RequestMapping(value="detailProduct")
	public ModelAndView detail(@RequestParam(name="savings_num") String num) {
		Optional<SavingsManagerVO> list = smr.findById(num);
		SavingsManagerVO savings = list.get();
		System.out.println(">>>>>>>>>>>>>>>"+savings);
		mav.addObject("savings", savings);
		mav.setViewName("product/detailSavings");
		return mav;
	}
}
