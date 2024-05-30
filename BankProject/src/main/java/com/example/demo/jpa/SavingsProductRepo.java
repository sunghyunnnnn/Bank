package com.example.demo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.vo.SavingsProductVO;

public interface SavingsProductRepo extends JpaRepository<SavingsProductVO, String>{

	@Query(value="insert into savings_product(account_num, id, savings_num, total) values(:account_num, :id, :savings_num, :total)", nativeQuery = true)
	public String join(@Param(value="account_num") String account_num, @Param(value="id") String id,
			@Param(value="savings_num")String savings_num, @Param(value="total") int total);
	
	@Query(value="update account a set total = total - :total where account_num=:account", nativeQuery = true)
	public void updateSavings(@Param("total")int total, @Param("account") String account);
}
