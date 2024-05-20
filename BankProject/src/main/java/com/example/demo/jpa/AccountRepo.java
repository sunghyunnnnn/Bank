package com.example.demo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.vo.AccountVO;

public interface AccountRepo extends JpaRepository<AccountVO, Integer>{
	@Query(value="insert into account(total) values(:na)", nativeQuery = true)
	public void insertAccount(@Param(value="na") String money);
	
	@Query(value="select account_num from account where id=:na", nativeQuery = true)
	public List<String> selectAccount(@Param(value="na") String id);
}
