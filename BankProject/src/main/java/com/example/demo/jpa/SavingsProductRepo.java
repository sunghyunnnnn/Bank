package com.example.demo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.vo.SavingsProductVO;

public interface SavingsProductRepo extends JpaRepository<SavingsProductVO, String>{

}
