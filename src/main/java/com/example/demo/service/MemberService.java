package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDAO;
import com.example.demo.dto.MemberDTO;

@Service
public class MemberService {

	@Autowired
	MemberDAO memberDAO;

	public void signUp(MemberDTO memberDTO) {
		memberDAO.signUp(memberDTO);
	}
	
	//secure/admin/index의 회원관리 첫 화면 표기 (전체 회원 조회)
	public List<MemberDTO> getAllMembers(){
		return memberDAO.getAllMembers();
	}
	
	//secure/admin/index의 회원관리 중 검색 메서드
	public List<MemberDTO> searchMembers(String searchKeyword){
		return memberDAO.searchMembers(searchKeyword);
	}
}
