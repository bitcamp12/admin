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
	
	@Insert("INSERT INTO PLAY (NAME,MEMBER_SEQ, START_TIME, END_TIME, DESCRIPTION, ADDRESS, total_actor, image_original_file_name, image_file_name, running_time, price, age_limit)"
			+ " VALUES(#{name},#{memberSeq}, #{startTime}, #{endTime}, #{description}, #{address}, #{totalActor}, #{imageOriginalFileName}, #{imageFileName}, #{runningTime}, #{price}, #{ageLimit})")
	public void playRegisterWrite(PlayDTO playDTO);
	
	
	@Select("SELECT * FROM PLAY ORDER BY END_TIME DESC")
	public List<PlayDTO> getPlayList();
}
