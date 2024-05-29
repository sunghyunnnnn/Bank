package com.example.demo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.vo.DepositProduct;

public interface DepositProductRepo extends JpaRepository<DepositProduct, String>{
	@Query(value="insert into deposit_product(account_num, deposit_num, id, total) values(:account_num, :deposit_num, :id, :total)", nativeQuery = true)
	public void insertDeposit(@Param(value = "account_num") String account_num,@Param(value = "deposit_num") String deposit_num,@Param(value = "id") String id,@Param(value = "total") String total);
}
