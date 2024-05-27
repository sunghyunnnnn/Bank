package com.example.demo.jpa;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.vo.MemberVO;

public interface MemberRepo extends JpaRepository<MemberVO, String>{
	@Query(value = "select id,pw from member", nativeQuery = true)
	public List<Map<String,String>> selectMemberIdPw();
	@Query(value = "select id from member", nativeQuery = true)
	public List<String> selectMemberId();
}
