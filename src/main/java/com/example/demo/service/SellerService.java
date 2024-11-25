package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.SellerDAO;
import com.example.demo.entity.Admin;
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

}
