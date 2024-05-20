package com.example.demo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.vo.AccountVO;

public interface AccountRepo extends JpaRepository<AccountVO, Integer>{

}
