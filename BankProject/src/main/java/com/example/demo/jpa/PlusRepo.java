package com.example.demo.jpa;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.vo.PlusVO;

public interface PlusRepo extends JpaRepository<PlusVO, Integer>{
	@Query(value="insert into plus(num, exchange_sign, account_num, exchange_money) values(plus_seq.nextval, '+', :account_num, :exchange_money)", nativeQuery = true)
	public int insertPlus(@Param("account_num") String account_num,@Param("exchange_money") int exchange_money);
}
