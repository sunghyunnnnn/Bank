package com.example.demo.controller;

import java.io.IOException;
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
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AccountController {
	
	@Autowired
	AccountRepo accountrepo;
	@Autowired
	PlusRepo plusRepo;
	@Autowired
	RemitRepo remitrepo;
	
	ModelAndView mav = new ModelAndView();
	private ModelAndView modelAndView;
	
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
		List<String> accountAll = new ArrayList<>();
		List<String> account = new ArrayList<>();
		List<String> total = new ArrayList<>();
		
		List<Map<String, Integer>> selectNumTotal = remitrepo.selectNumTotal();
		accountnum = accountrepo.selectAccount(id);
		//확인용:System.out.println(selectNumTotal.get(0).keySet());
		for(Map<String, Integer> a : selectNumTotal) {
			account.add(String.valueOf(a.get("ACCOUNT_NUM")));
			total.add(a.get("TOTAL").toString());
		}
		System.out.println(accountnum);
		
		accountAll = accountrepo.selectAll(id);
		System.out.println(accountAll);
		mav.addObject("account", account);
		mav.addObject("total", total);
		mav.addObject("accountnum", accountnum);
		mav.addObject("accountAll", accountAll);
		mav.setViewName("account/remit");
		return mav;
	}
	
	@RequestMapping(value="remitComplete")
	public ModelAndView remitComplete(HttpServletRequest request, RemitVO remitvo, HttpServletResponse response) throws IOException {
		
		String account_num = request.getParameter("account_num");
		String total = accountrepo.selectTotal(account_num);
		System.out.println(account_num);
		String remit_account = request.getParameter("remit_account");
		String remit_text = request.getParameter("remit_text");
		int remit_money = Integer.parseInt(request.getParameter("remit_money"));
		
		if(Integer.parseInt(total) > remit_money) {
			try {
				int i = remitrepo.insertRemit(account_num, remit_account, remit_text, remit_money);
			} catch (Exception e) {
			//왜 오류가 날까요?
			}
			
			try {
				remitrepo.updateRemit(remit_money,account_num);
				System.out.println("원래 나와야하는 부분");
			} catch (Exception e) {
				//오류남
				System.out.println("되면 안되는데;;");
			} 
			mav.addObject("result","송금완료");
			mav.setViewName("account/remitComplete");
			return mav;
		} else {
			response.sendRedirect(request.getHeader("referer"));
			return mav;
			} 
		} 
		
		//String money = remitrepo.selectMoney();
		//System.out.println(total);
		//System.out.println(money);

	
	@RequestMapping(value="/accountSearch")
	public ModelAndView accountSearch(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		String id = request.getParameter("id");
		List<Map<String, Integer>> accountList = accountrepo.selectAccount2(id);
		
		List<String> accountTotal = new ArrayList<>();
		List<String> accountNum = new ArrayList<>();
		
		List<PlusVO> plusList =  plusRepo.findAll();
		List<RemitVO> remitList = remitrepo.findAll();
		
		for(Map<String, Integer> a : accountList) {
			accountNum.add(String.valueOf(a.get("ACCOUNT_NUM")));
			accountTotal.add(a.get("TOTAL").toString());
		}
		
		mav.addObject("accountNum",accountNum);
		mav.addObject("accountTotal", accountTotal);
		mav.addObject("plusList", plusList);
		mav.addObject("remitList",remitList);
		mav.setViewName("account/searchAccount");
		return mav;
	}
}
