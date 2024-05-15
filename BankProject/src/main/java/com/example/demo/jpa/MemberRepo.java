package com.example.demo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.vo.MemberVO;

public interface MemberRepo extends JpaRepository<MemberVO, Integer>{

}
