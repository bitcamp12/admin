package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.dto.NoticeDTO;

@Mapper
public interface NoticeDAO {
	
	 @Insert("INSERT INTO notice (title, content, created_date, updated_date, hide, image_file_name, image_original_file_name) " +
	            "VALUES (#{title}, #{content}, NOW(), NOW(), 'N', #{imageFileName}, #{imageOriginalFileName})")
     public int insertNotice(NoticeDTO noticeDTO);
	 
	@Select("select * from notice") 
	public List<NoticeDTO> getNoticeList();

}