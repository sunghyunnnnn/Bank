package com.example.demo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.vo.SavingsManagerVO;

public interface SavingsManagerRepo extends JpaRepository<SavingsManagerVO, String> {

	@Query(value = "select * from savings_manager", nativeQuery = true)
	public List<SavingsManagerVO> savings_list();
	
	@Query(value = "select * from savings_manager where savings_num = :num", nativeQuery = true)
	public List<SavingsManagerVO> savings_num(@Param(value="num") String num);

}
