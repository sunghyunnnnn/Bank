package com.example.demo.jpa;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.vo.AccountVO;

public interface AccountRepo extends JpaRepository<AccountVO, String>{
	@Query(value="insert into account(account_num, id, account_pw, total) values(:account_num, :id, :account_pw, :total)", nativeQuery = true)
	public void insertAccount(@Param(value="account_num") String account_num, @Param(value="id") String id,@Param(value="account_pw") int account_pw,@Param(value="total") int total);
	
	@Query(value="select account_num from account where id=:na", nativeQuery = true)
	public List<String> selectAccount(@Param(value="na") String id);
	
	@Query(value = "select account_num, total from account where id = :na", nativeQuery = true)
	public List<Map<String,Integer>> selectAccount2(@Param(value="na") String id);
	
	@Query(value="select account_num, total from account where id = :na", nativeQuery = true)
	public List<String> selectAll(@Param(value="na") String id);
	
	@Query(value="select total from account where account_num=:account_num", nativeQuery = true)
	public String selectTotal(@Param(value="account_num") String account);
//	UPDATE account SET product_num = '#201' WHERE account_num = '41212';
	@Query(value="update account set product_num = :product_num, total = total - :money where account_num = :account_num", nativeQuery = true)
	public void updateAccount(@Param(value="product_num") String product_num,@Param(value="money") int money ,@Param(value="account_num") String account_num);
	
}
