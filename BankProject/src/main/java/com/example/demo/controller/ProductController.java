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


import com.example.demo.jpa.AccountRepo;
import com.example.demo.jpa.PlusRepo;
import com.example.demo.jpa.ProductRepo;
import com.example.demo.jpa.RemitRepo;
import com.example.demo.vo.AccountVO;
import com.example.demo.vo.MemberVO;
import com.example.demo.vo.ProductManagerVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {
	ModelAndView mav = new ModelAndView();
	
	@Autowired
	AccountRepo accountRepo;

	@Autowired
	RemitRepo remitRepo;
	
	@Autowired
	ProductRepo pr;
	@Autowired
	PlusRepo plueRepo;
	
	
	@RequestMapping(value="/depositList")
	public ModelAndView deposit() {
		ModelAndView mav = new ModelAndView();
		List<ProductManagerVO> depositVO =  pr.showDeposit();
		System.out.println(">>> " + depositVO);
		mav.addObject("depositVO", depositVO);
		mav.setViewName("products/depositList");
		return mav;
	}
	@RequestMapping(value="/savings")
	public ModelAndView savings() {
		List<ProductManagerVO> list = pr.showSavings();
		System.out.println(list);
		mav.addObject("list", list);
		mav.setViewName("products/savings");
		return mav;
	}
	@RequestMapping(value="/depositProduct")
	public ModelAndView depositProduct(HttpServletRequest request) {
		String product_num = request.getParameter("product_num");
		String id = request.getParameter("id");
		List<Map<String, Integer>> accountList = accountRepo.selectAccount2(id);
		List<Integer> accountPW = accountRepo.selectAccountPW(id);
		
		List<String> accountTotal = new ArrayList<>();
		List<String> accountNum = new ArrayList<>();
		
		for(Map<String, Integer> a : accountList) {
			accountNum.add(String.valueOf(a.get("ACCOUNT_NUM")));
			accountTotal.add(a.get("TOTAL").toString());
		}
		
		ModelAndView mav = new ModelAndView();
		ProductManagerVO deposit = pr.getById(product_num);
		
		mav.addObject("accountNum",accountNum);
		mav.addObject("accountTotal", accountTotal);
		mav.addObject("accountPW", accountPW);
		mav.addObject("deposit", deposit);
		mav.setViewName("products/depositPage");
		return mav;
	}
	@RequestMapping(value="depositController")
	public ModelAndView depositController(HttpServletRequest request, AccountVO acvo) {
		ModelAndView mav = new ModelAndView();
		
		try {			
			accountRepo.updateAccount(acvo.getProduct_num(), acvo.getTotal(), acvo.getAccount_num());
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {			
			remitRepo.insertRemit(acvo.getAccount_num(), "----", "----", acvo.getTotal());
		} catch (Exception e) {
			// TODO: handle exception
		}
		mav.setViewName("index");
		return mav;
	
		
	}
	
	@RequestMapping(value="detailProduct")
	public ModelAndView detail(@RequestParam(name="product_num") String num, HttpSession session) {
		Optional<ProductManagerVO> list = pr.findById(num);
		ProductManagerVO savings = list.get();
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
		List<Map<String, Integer>> selectNumTotal = remitRepo.selectNumTotal(id);
		List<Integer> accountPW = accountRepo.selectAccountPW(id);
		System.out.println(">>> " + accountPW);
		for(Map<String, Integer> a : selectNumTotal) {
			account.add(String.valueOf(a.get("ACCOUNT_NUM")));
			total.add(a.get("TOTAL").toString());
		}
		
		mav.addObject("account", account);
		mav.addObject("accountPW", accountPW);
		mav.addObject("total", total);
		mav.setViewName("products/joinSavings");
		return mav;
	}
	
	@RequestMapping(value="joinComplete")
	public String join(HttpServletRequest request, HttpSession session, AccountVO acvo) {
		
		MemberVO getid = (MemberVO) session.getAttribute("login");

		String id = getid.getId();
		
		try {			
			accountRepo.updateAccount(acvo.getProduct_num(), acvo.getTotal(), acvo.getAccount_num());
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {			
			remitRepo.insertRemit(acvo.getAccount_num(), "----", "----", acvo.getTotal());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/savings?id="+id;
	}
	@RequestMapping(value="Termination")
	public ModelAndView Termination(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String product_num = request.getParameter("product_num");
		String account_num = request.getParameter("account_num");
		
		MemberVO member = (MemberVO)session.getAttribute("login");
		ProductManagerVO product = pr.showProduct(product_num);
		AccountVO account = accountRepo.getById(account_num);
		mav.addObject("account", account);
		mav.addObject("member", member);
		mav.addObject("product", product);
		
		mav.setViewName("products/Termination");
		return mav;
	}
	@RequestMapping(value="Termination_controller")
	public ModelAndView Termination_controller(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String account_num = request.getParameter("account_num");
		int addMoney = Integer.parseInt(request.getParameter("addMoney"));
		try {
			accountRepo.updateAccountMoney(account_num, addMoney);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			plueRepo.insertPlus(account_num, addMoney);
		} catch (Exception e) {
			// TODO: handle exception
		}
		mav.setViewName("index");
		return mav;
	}
}
