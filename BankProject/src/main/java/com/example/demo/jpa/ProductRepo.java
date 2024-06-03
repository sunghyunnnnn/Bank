package com.example.demo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.vo.ProductManagerVO;

public interface ProductRepo extends JpaRepository<ProductManagerVO, String>{
	@Query(value="select * from product_manager where product_num like '#2%'", nativeQuery = true)
	public List<ProductManagerVO> showDeposit();
	@Query(value="select * from product_manager where product_num like '#3%'", nativeQuery = true)
	public List<ProductManagerVO> showSavings();
}
