package com.example.demo.jpa;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.vo.AccountVO;

public interface AccountRepo2 extends JpaRepository<AccountVO, String>{
	@Query(value = "select account_num, total from account where id = :na", nativeQuery = true)
	public List<Map<String,Integer>> selectAccount(@Param(value="na") String id);
}
