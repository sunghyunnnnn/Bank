package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.jpa.DepositRepo;
import com.example.demo.jpa.SavingsManagerRepo;
import com.example.demo.vo.DepositVO;
import com.example.demo.vo.SavingsManagerVO;

import ch.qos.logback.core.model.Model;

@Controller
public class ManagerController {
	
	@Autowired
	SavingsManagerRepo smr;
	@Autowired
	DepositRepo depositRepo;
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
	
	@RequestMapping(value="depositManager")
	public ModelAndView depositManager() {
		List<DepositVO> list = depositRepo.deposit_list();
		mav.addObject("list", list);
		mav.setViewName("admin/depositManager");
		return mav;
	}
	@RequestMapping(value="detailDeposit")
	public ModelAndView detailDeposit(@RequestParam(name="deposit_num") String num) {
		System.out.println("+++++++++++++++++++++++++++++");
		System.out.println(num);
		Optional<DepositVO> list = depositRepo.findById(num);
		DepositVO deposit = list.get();
		System.out.println(">>>>>>>>>>>>>>>"+deposit);
		mav.addObject("deposit", deposit);
		mav.setViewName("admin/selectDeposit");
		return mav;
	}
	@RequestMapping(value="createDeposit")
	public ModelAndView createDeposit() {
		mav.setViewName("admin/createDeposit");
		return mav;
	}
	@RequestMapping(value="createDepositComplete")
	public ModelAndView createDepositComplete(DepositVO depositVO) {
		depositRepo.save(depositVO);
		//mav.addObject("result", "상품이 등록 되었습니다.");
		mav.setViewName("redirect:/depositManager");
		return mav;
	}
}
