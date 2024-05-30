package com.example.demo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.vo.DepositVO;
import com.example.demo.vo.SavingsManagerVO;

public interface DepositRepo extends JpaRepository<DepositVO, String>{
	@Query(value = "select * from deposit_manager", nativeQuery = true)
	public List<DepositVO> deposit_list();
}
