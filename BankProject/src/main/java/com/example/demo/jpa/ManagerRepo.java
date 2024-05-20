package com.example.demo.jpa;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.vo.ManagerVO;

public interface ManagerRepo extends JpaRepository<ManagerVO, String>{
	@Query(value = "select manager_id,manager_pw from manager", nativeQuery = true)
	public List<Map<String,String>> selectManagerIdPw();
}
