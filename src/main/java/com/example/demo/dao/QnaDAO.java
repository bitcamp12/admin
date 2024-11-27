package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.dto.QnaDTO;

@Mapper
public interface QnaDAO {

	@Select("SELECT Q.QNA_SEQ, Q.PLAY_SEQ, Q.MEMBER_SEQ, Q.title, Q.CONTENT, Q.CREATED_DATE, P.NAME AS PLAY_NAME, M.NAME AS MEMBER_NAME "
			+ " FROM QNA Q "
			+ " LEFT JOIN PLAY P "
			+ " ON Q.PLAY_SEQ = P.PLAY_SEQ "
			+ " LEFT JOIN MEMBER M "
			+ " ON Q.MEMBER_SEQ = M.MEMBER_SEQ "
			+ " WHERE Q.QNA_SEQ =  #{qnaSeq}")
	public QnaDTO getQnaDetail(int qnaSeq);
	
}
