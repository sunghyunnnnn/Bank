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
import com.example.demo.jpa.SavingsProductRepo;
import com.example.demo.vo.SavingsManagerVO;
import com.example.demo.jpa.AccountRepo;
import com.example.demo.jpa.DepositProductRepo;
import com.example.demo.jpa.DepositRepo;
import com.example.demo.jpa.RemitRepo;
import com.example.demo.vo.AccountVO;
import com.example.demo.vo.DepositVO;
import com.example.demo.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {
	ModelAndView mav = new ModelAndView();
	
	@Autowired
	SavingsManagerRepo smr;
	@Autowired
	DepositRepo depositRepo;
	@Autowired
	AccountRepo accountRepo;
	@Autowired
	DepositProductRepo depositProductRepo;
	@Autowired
	RemitRepo remitRepo;
	@Autowired
	SavingsProductRepo spr;
	
	
	@RequestMapping(value="/depositList")
	public ModelAndView deposit() {
		ModelAndView mav = new ModelAndView();
		List<DepositVO> depositVO =  depositRepo.findAll();
		System.out.println(">>> " + depositVO);
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
	public ModelAndView depositController(HttpServletRequest request, AccountVO acvo) {
		ModelAndView mav = new ModelAndView();
		String deposit_num = request.getParameter("deposit_num");
		String account_num = request.getParameter("account_num");
		String my_account_num = request.getParameter("my_account_num");
		String id = request.getParameter("id");
		String total = request.getParameter("total");
		accountRepo.save(acvo);
		int exchange_money = Integer.parseInt(total);
		
		try {
			depositProductRepo.insertDeposit(account_num, deposit_num, id, total);
			
		} catch (Exception e) {
			
		}
		try {
			remitRepo.insertRemit(my_account_num, "----", "----", exchange_money);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			remitRepo.updateRemit(exchange_money, my_account_num);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		mav.setViewName("index");
		return mav;
		
		
		
	}
	
	@RequestMapping(value="detailProduct")
	public ModelAndView detail(@RequestParam(name="savings_num") String num, HttpSession session) {
		Optional<SavingsManagerVO> list = smr.findById(num);
		SavingsManagerVO savings = list.get();
		System.out.println(">>>>>>>>>>>>>>>"+savings);
		//DB에 저장하기 위해 session으로 넘겨줌.
		session.setAttribute("savings", savings);
		mav.addObject("savings", savings);
		mav.setViewName("products/detailSavings");
		return mav;
	}
	
	@RequestMapping(value="joinSavings")
	public ModelAndView join(HttpSession session) {
		MemberVO getid = (MemberVO) session.getAttribute("login");
		String id = getid.getId();
		List<String> account = new ArrayList<>();
		List<String> total = new ArrayList<>();
		List<Map<String, Integer>> selectNumTotal = remitRepo .selectNumTotal(id);
		
		for(Map<String, Integer> a : selectNumTotal) {
			account.add(String.valueOf(a.get("ACCOUNT_NUM")));
			total.add(a.get("TOTAL").toString());
		}
		
		mav.addObject("account", account);
		mav.addObject("total", total);
		mav.setViewName("products/joinSavings");
		return mav;
	}
	
	@RequestMapping(value="joinComplete")
	public String join(HttpServletRequest request, HttpSession session) {
		MemberVO getid = (MemberVO) session.getAttribute("login");
		SavingsManagerVO num = (SavingsManagerVO) session.getAttribute("savings");
		//넣을 값
		String account_num = request.getParameter("account_num");
		String account = request.getParameter("account");
		int total = Integer.parseInt(request.getParameter("total"));
		String savings_num = num.getSavings_num();
		String id = getid.getId();
		System.out.println(total +">>>>>>>>>"+ account);
		try {
			spr.updateSavings(total, account);			
		} catch (Exception e) {
			System.out.println("반영되긴 했는데,,,,");
		}
		try {
			remitRepo.insertRemit2(account, account_num, "적금", total);
		} catch (Exception e) {
		}
		try {			
			String i = spr.join(account_num, id, savings_num, total);
			System.out.println(i);
		}catch (Exception e) {
			System.out.println("나오지 말아");
		}
		return "redirect:/savings?id="+id;
	}
	
	
	
}
