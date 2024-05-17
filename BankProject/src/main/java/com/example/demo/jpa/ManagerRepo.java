package com.example.demo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.vo.ManagerVO;

public interface ManagerRepo extends JpaRepository<ManagerVO, String>{

}
