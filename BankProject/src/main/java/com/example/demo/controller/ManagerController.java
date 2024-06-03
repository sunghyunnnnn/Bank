package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.jpa.ProductRepo;

import com.example.demo.vo.ProductManagerVO;

import ch.qos.logback.core.model.Model;


@Controller
public class ManagerController {
	
	@Autowired
	ProductRepo pr;
	
	ModelAndView mav = new ModelAndView();
	
	@RequestMapping(value="savingsManager")
	public ModelAndView savings() {
		List<ProductManagerVO> list = pr.showSavings();
		mav.addObject("list", list);
		mav.setViewName("admin/savingsManager");
		return mav;
	}
	
	@RequestMapping(value="depositManager")
	public ModelAndView deposit() {
		List<ProductManagerVO> list = pr.showDeposit();
		mav.addObject("list", list);
		mav.setViewName("admin/depositManager");
		return mav;
	}
	
	@RequestMapping(value="detailSavings")
	public ModelAndView detail(@RequestParam(name="product_num") String num) {
		System.out.println("+++++++++++++++++++++++++++++");
		System.out.println(num);
		Optional<ProductManagerVO> list = pr.findById(num);
		ProductManagerVO savings = list.get();
		System.out.println(">>>>>>>>>>>>>>>"+savings);
		mav.addObject("savings", savings);
		mav.setViewName("admin/selectSavings");
		return mav;
	}
	
	@RequestMapping(value="modifySavings") //수정하기 곧 할거임 나중에,,,,,헤헷
	public ModelAndView modify() {
		return mav;
	}

	@RequestMapping(value="createProduct" )
	public ModelAndView create() {
		mav.setViewName("admin/createProduct");
		return mav;
	}
	
	@RequestMapping(value="createProductComplete")
	public ModelAndView insert(ProductManagerVO pmvo) {
		pr.save(pmvo);
		//mav.addObject("result", "상품이 등록 되었습니다.");
		mav.setViewName("admin/managePage");
		return mav;
	}
	
//	@RequestMapping(value="depositManager")
//	public ModelAndView depositManager() {
//		
//		List<ProductManagerVO> list = pr.showDeposit();
//		mav.addObject("list", list);
//		mav.setViewName("admin/depositManager");
//		return mav;
//	}
	
	@RequestMapping(value="detailDeposit")
	public ModelAndView detailDeposit(@RequestParam(name="product_num") String num) {
		System.out.println("+++++++++++++++++++++++++++++");
		System.out.println(num);
		Optional<ProductManagerVO> list = pr.findById(num);
		ProductManagerVO deposit = list.get();
		System.out.println(">>>>>>>>>>>>>>>"+deposit);
		mav.addObject("deposit", deposit);
		mav.setViewName("admin/selectDeposit");
		return mav;
	}
}
