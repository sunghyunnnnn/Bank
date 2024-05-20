package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.jpa.AccountRepo;
import com.example.demo.vo.AccountVO;

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
}
