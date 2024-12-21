package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.SellerDAO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

@Service
public class SellerService {

	@Autowired
	private SellerDAO sellerDAO;
	
	@Autowired
	private MemberRepository memberRepository;

	public Member findByLoginIdAndPassword(String id, String password) {
		// TODO Auto-generated method stub
		return memberRepository.findByLoginIdAndPassword(id,password);
	}

	public long getTotalCount() {
		// TODO Auto-generated method stub
		return memberRepository.countByRole(Member.Role.SELLER);
	}

	public List<Member> getSellerPaging(Pageable pageable) {
	    // role이 "SELLER"인 회원을 페이징하여 반환
	    Page<Member> pageResult = memberRepository.findByRole(Member.Role.SELLER, pageable);
	    return pageResult.getContent();  // 페이징된 회원 리스트를 반환
	}


	public List<Member> getSellerPagingKeyword(Pageable pageable, String value) {
	    // role이 "SELLER"이고 이름에 "value"를 포함하는 회원을 페이징하여 반환
	    Page<Member> pageResult = memberRepository.findByRoleAndNameContaining(Member.Role.SELLER, value, pageable);
	    return pageResult.getContent();  // 페이징된 회원 리스트를 반환
	}

	public long getTotalCountKeyword(String value) {
		// TODO Auto-generated method stub
		return memberRepository.countByRoleAndNameContaining(Member.Role.SELLER, value);
	}

}
