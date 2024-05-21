package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.jpa.AccountRepo;

import com.example.demo.jpa.PlusRepo;

import com.example.demo.jpa.RemitRepo;

import com.example.demo.vo.AccountVO;

import com.example.demo.vo.PlusVO;

import com.example.demo.vo.RemitVO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AccountController {
	
	@Autowired
	AccountRepo accountrepo;
	@Autowired
	PlusRepo plusRepo;
	@Autowired
	RemitRepo remitrepo;
	
	ModelAndView mav = new ModelAndView();
	
	@RequestMapping(value="accountMake")
	public ModelAndView accountMake() {
		
		mav.setViewName("account/accountMake");
		return mav;
	}

	@RequestMapping(value="accountComplete")
	public ModelAndView accountComplete(AccountVO acvo) {
			
		accountrepo.save(acvo);
		
		mav.addObject("result","계좌 개설 완료");
		mav.setViewName("account/accountComplete");
		return mav;
	}
	
	@RequestMapping(value="remitMoney")
	public ModelAndView remitMoney(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		System.out.println(id);
		List<String> accountnum = new ArrayList<>();
		
		accountnum = accountrepo.selectAccount(id);
		System.out.println(accountnum);
		
		mav.addObject("accountnum", accountnum);
		mav.setViewName("account/remit");
		return mav;
	}
	
	@RequestMapping(value="remitComplete")
	public ModelAndView remitComplete(HttpServletRequest request, RemitVO remitvo) {
		
		String account = request.getParameter("account");
		System.out.println(account);
		String remit_account = request.getParameter("remit_account");
		String remit_text = request.getParameter("remit_text");
		int remit_money = Integer.parseInt(request.getParameter("remit_money"));
		
		try {
			int i = remitrepo.insertRemit(account, remit_account, remit_text, remit_money);
		} catch (Exception e) {
		//왜 오류가 날까요?
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
		
		String account_num = request.getParameter("account");
		System.out.println(account_num);
		try {
			remitrepo.updateRemit(account_num);
		} catch (Exception e) {
			//오류남
		}
		mav.addObject("result","송금완료");
		mav.setViewName("account/remitComplete");
		return mav;
	}
	
	@RequestMapping(value="/accountSearch")
	public ModelAndView accountSearch(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		String id = request.getParameter("id");
		List<Map<String, Integer>> accountList = accountrepo.selectAccount2(id);
		List<String> accountTotal = new ArrayList<>();
		List<String> accountNum = new ArrayList<>();
		
		List<PlusVO> plusList =  plusRepo.findAll();
		
		for(Map<String, Integer> a : accountList) {
			accountNum.add(String.valueOf(a.get("ACCOUNT_NUM")));
			accountTotal.add(a.get("TOTAL").toString());
		}
		
		mav.addObject("accountNum",accountNum);
		mav.addObject("accountTotal", accountTotal);
		mav.addObject("plusList", plusList);
		mav.setViewName("account/searchAccount");
		return mav;
	}
}
