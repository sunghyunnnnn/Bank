package com.example.demo.jpa;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.vo.AccountVO;

public interface AccountRepo extends JpaRepository<AccountVO, String>{
	@Query(value="insert into account(total) values(:na)", nativeQuery = true)
	public void insertAccount(@Param(value="na") String money);
	
	@Query(value="select account_num from account where id=:na", nativeQuery = true)
	public List<String> selectAccount(@Param(value="na") String id);
	
	@Query(value = "select account_num, total from account where id = :na", nativeQuery = true)
	public List<Map<String,Integer>> selectAccount2(@Param(value="na") String id);
	
	@Query(value="select account_num, total from account where id = :na", nativeQuery = true)
	public List<String> selectAll(@Param(value="na") String id);
	
	@Query(value="select total from account where account_num=:account_num", nativeQuery = true)
	public String selectTotal(@Param(value="account_num") String account);
	
}
