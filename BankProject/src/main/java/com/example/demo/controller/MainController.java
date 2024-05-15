package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.jpa.MemberRepo;
import com.example.demo.vo.MemberVO;

@Controller
public class MainController {
	
	@Autowired
	MemberRepo memberRepo;
	
	
	@RequestMapping(value="/")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}
	@RequestMapping(value="/register")
	public ModelAndView register() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/register");
		return mav;
	}
	@RequestMapping(value="/register_Account")
	public ModelAndView registerAccount(MemberVO member) {
		ModelAndView mav = new ModelAndView();
		memberRepo.save(member);
//		System.out.println("member >> " + member);
		mav.setViewName("index");
		return mav;
	}
	
}
