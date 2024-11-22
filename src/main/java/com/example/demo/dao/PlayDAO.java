package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.dto.MemberDTO;
import com.example.demo.dto.PlayDTO;
import com.example.demo.dto.SellerDTO;

@Mapper
public interface PlayDAO {
	
	@Insert("INSERT INTO PLAY (NAME, START_TIME, END_TIME, DESCRIPTION, ADDRESS, total_actor)"
			+ " VALUES(#{play_name}, #{start_date}, #{end_date}, #{description}, #{address}, #{total_actor})")
	public void playRegisterWrite(PlayDTO playDTO);
	
	
	@Select("SELECT play_seq as playSeq, name, start_time as startDate, end_time as endDate, description, total_actor as totalActor FROM PLAY ORDER BY END_TIME DESC")
	public List<PlayDTO> getPlayList();
}
