package com.example.demo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.vo.RemitVO;

public interface RemitRepo extends JpaRepository<RemitVO, Integer>{
	
	@Query(value="insert into remit values(remit_seq.nextval, '-', :account_num, :remit_account, :remit_text, :remit_money, sysdate)", nativeQuery = true)
	public int insertRemit(@Param("account_num") String account, @Param("remit_account") String remit_account,
							@Param("remit_text") String remit_text, @Param("remit_money") int remot_money);
	
	@Query(value="update account a set total = total - (select remit_money from remit r where r.num = (select max(num)"
				+ "from remit) and a.account_num=:account_num)", nativeQuery = true)
	public void updateRemit(@Param("account_num") String account_num);
}
