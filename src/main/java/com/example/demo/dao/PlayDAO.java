package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.MemberDTO;
import com.example.demo.dto.PlayDTO;
import com.example.demo.dto.SellerDTO;

@Mapper
public interface PlayDAO {
	public void playRegister(PlayDTO playDTO);
}
