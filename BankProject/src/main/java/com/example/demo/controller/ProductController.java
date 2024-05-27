package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {
	
	@RequestMapping(value="/deposit")
	public ModelAndView deposit() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("products/deposit");
		return mav;
	}
	@RequestMapping(value="/savings")
	public ModelAndView savings() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("products/savings");
		return mav;
	}
	
}
