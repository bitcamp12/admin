package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.dto.MemberDTO;

@Mapper
public interface MemberDAO {

	public void signUp(MemberDTO memberDTO);
	
	@Select("SELECT * FROM member ORDER BY member_seq DESC")
	public List<MemberDTO> getAllMembers();
	
	@Select("SELECT * FROM member WHERE " +
			"id LIKE CONCAT('%', #{searchKeyword}, '%') OR " +
			"name LIKE CONCAT('%', #{searchKeyword}, '%') OR " +
			"email LIKE CONCAT('%', #{searchKeyword}, '%') OR " + 
			"phone LIKE CONCAT('%', #{searchKeyword}, '%') OR " + 
			"address LIKE CONCAT('%', #{searchKeyword}, '%') OR " +
			"gender LIKE CONCAT('%', #{searchKeyword}, '%') OR " +
			"register_date LIKE CONCAT('%', #{searchKeyword}, '%')")
	public List<MemberDTO> searchMembers(String searchKeyword);	
}
