package com.example.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.example.demo.dto.MemberDTO;
import com.example.demo.dto.ReplyDTO;

@Mapper
public interface ReplyDAO {

	@Insert("INSERT INTO REPLY(member_seq, qna_seq, content, created_date) VALUES(${memberSeq}, ${qnaSeq}, '${content}', NOW() )")
	public void replyWrite(ReplyDTO replyDTO);

	@Update("UPDATE REPLY SET CONTENT = '${content}' WHERE qna_seq = ${qnaSeq}")
	public void replyUpdate(ReplyDTO replyDTO);

	
}
