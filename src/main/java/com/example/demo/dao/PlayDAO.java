package com.example.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.MemberDTO;
import com.example.demo.dto.PlayDTO;
import com.example.demo.dto.SellerDTO;

@Mapper
public interface PlayDAO {
	
	@Insert("INSERT INTO PLAY (NAME, START_TIME, END_TIME, DESCRIPTION, ADDRESS, total_actor)"
			+ " VALUES(#{play_name}, #{start_date}, #{end_date}, #{description}, #{address}, #{total_actor})")
	public void playRegisterWrite(PlayDTO playDTO);
}
