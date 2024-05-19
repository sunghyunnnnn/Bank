package com.example.demo.controller;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.jpa.EmployeeRepo;
import com.example.demo.jpa.ManagerRepo;
import com.example.demo.jpa.MemberRepo;
import com.example.demo.vo.ManagerVO;
import com.example.demo.vo.MemberVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	
	@Autowired
	MemberRepo memberRepo;
	@Autowired
	ManagerRepo managerRepo;
	@Autowired
	EmployeeRepo employeRepo;
	
	
	
	
	@RequestMapping(value="/")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}
	@RequestMapping(value="/register")
	public ModelAndView register() {
		ModelAndView mav = new ModelAndView();
		List<Integer> employeeNum = employeRepo.selectEnployeeNum();
//		System.out.println("employeeNum >>> " + employeeNum);
		mav.addObject("employeeNum", employeeNum);
		mav.setViewName("member/register");
		return mav;
	}
	@RequestMapping(value="/register_Account")
	public ModelAndView registerAccount(MemberVO member) {
		ModelAndView mav = new ModelAndView();
		memberRepo.save(member);
//		System.out.println("member >> " + member);
		mav.setViewName("forward:/");
		return mav;
	}
	@RequestMapping(value="/manager_Account")
	public ModelAndView manager_Account(ManagerVO manager, HttpSession session) {
		
		ModelAndView mav = new ModelAndView();

		try {
			managerRepo.save(manager);
			mav.setViewName("forward:/");
			return mav;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("부모 키가 없습니다.");
			mav.setViewName("member/register");
			return mav;
		}
	}
	@RequestMapping(value="/login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		List<String> memberId = new ArrayList<>();
		List<String> memberPw = new ArrayList<>();
		List<Map<String,String>> memberidpw = memberRepo.selectMemberIdPw();
		for(Map<String, String> a : memberidpw) {
			 memberId.add(a.get("ID").toString());
			 memberPw.add(a.get("PW").toString());
		}
		mav.addObject("memberId", memberId);
		mav.addObject("memberPw", memberPw);
		
		mav.setViewName("login/login");
		return mav;
	}
	@RequestMapping(value="/loginController")
	public ModelAndView loginControl(MemberVO mem, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		System.out.println(">>>>>>>>>>>>");
		System.out.println("id: "+ mem.getId() + "    pw: "+mem.getPw());
		
		MemberVO dbMem = null;
		
		String id = mem.getId();
		String pw = mem.getPw();
		try {
			dbMem = memberRepo.getById(id);
			System.out.println("DB: >>"+ dbMem);
			System.out.println("로그인 완료");
			session.setAttribute("login", dbMem);
			mav.setViewName("forward:/");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("로그인 실패");
			mav.setViewName("forward:/");
		}
		return mav;
	}
	@RequestMapping(value="/logout")
	public ModelAndView logout(HttpSession session) {
			ModelAndView mav = new ModelAndView();
			session.removeAttribute("login");
			mav.setViewName("forward:/");
			return mav;
		}
}
