package com.example.demo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.vo.DepositVO;

public interface DepositRepo extends JpaRepository<DepositVO, String>{
	
}
