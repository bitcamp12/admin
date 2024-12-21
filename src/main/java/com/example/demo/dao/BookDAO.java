package com.example.demo.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.MemberDTO;

@Mapper
public interface BookDAO {

	@Delete("DELETE FROM BOOK WHERE book_seq = ${bookSeq}")
	public void bookDelete(int bookSeqInt);
	
}
