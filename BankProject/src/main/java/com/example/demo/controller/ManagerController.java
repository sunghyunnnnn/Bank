package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.jpa.SavingsManagerRepo;
import com.example.demo.vo.SavingsManagerVO;

import ch.qos.logback.core.model.Model;

@Controller
public class ManagerController {
	
	@Autowired
	SavingsManagerRepo smr;
	ModelAndView mav = new ModelAndView();
	
	@RequestMapping(value="savingsManager")
	public ModelAndView savings() {
		List<SavingsManagerVO> list = smr.savings_list();
		mav.addObject("list", list);
		mav.setViewName("admin/savingsManager");
		return mav;
	}
	
	@RequestMapping(value="detailSavings")
	public ModelAndView detail(@RequestParam(name="savings_num") String num) {
		System.out.println("+++++++++++++++++++++++++++++");
		System.out.println(num);
		Optional<SavingsManagerVO> list = smr.findById(num);
		SavingsManagerVO savings = list.get();
		System.out.println(">>>>>>>>>>>>>>>"+savings);
		mav.addObject("savings", savings);
		mav.setViewName("admin/selectSavings");
		return mav;
	}
	
	@RequestMapping(value="modifySavings") //수정하기 곧 할거임 나중에,,,,,헤헷
	public ModelAndView modify() {
		return mav;
	}

	@RequestMapping(value="createSavings")
	public ModelAndView create() {
		mav.setViewName("admin/createSavings");
		return mav;
	}
	
	@RequestMapping(value="createComplete")
	public ModelAndView insert(SavingsManagerVO smv) {
		smr.save(smv);
		//mav.addObject("result", "상품이 등록 되었습니다.");
		mav.setViewName("redirect:/savingsManager");
		return mav;
	}
}
