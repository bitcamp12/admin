package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.dto.PlayTimeTableDTO;

@Mapper
public interface PlayTimeTableDAO {

	@Insert("INSERT INTO PLAY_TIME_TABLE (play_seq, theater_seq, start_time, end_time, start_dis_time, end_dis_time, min_rate, max_rate, target_date)"
							+ " VALUES(#{play_seq}, #{theater_seq}, #{start_time}, #{end_time}, #{start_dis_time}, #{end_dis_time}, #{min_rate}, #{max_rate}, #{target_date})")
	public void timeRegisterWrite(PlayTimeTableDTO playTimeTableDTO);

	@Select("SELECT play_seq, name, start_time, end_time FROM PLAY ORDER BY START_TIME")
	public List<Map<String, Object>> timeRegisterFormSelect();
	
}
