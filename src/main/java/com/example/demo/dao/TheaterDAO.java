package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.dto.MemberDTO;

@Mapper
public interface TheaterDAO {

	@Select("SELECT theater_seq, name FROM THEATER")
	public List<Map<String, Object>> theaterListSelect();

	
}
