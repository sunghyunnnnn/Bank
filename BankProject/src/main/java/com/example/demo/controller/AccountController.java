package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.jpa.AccountRepo;
import com.example.demo.vo.AccountVO;
import com.example.demo.vo.RemitVO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AccountController {
	
	@Autowired
	AccountRepo accountrepo;
	
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
		
		
		return mav;
	}
}
