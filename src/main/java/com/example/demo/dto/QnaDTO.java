package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.entity.Qna;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QnaDTO {

	private int qnaSeq;
	private int playSeq;
	private int memberSeq;
	private String memberName;
	private String memberId;
	private String playName;
	private String title;
	private String content;
	private String replyYn; 
	private String replyContent; 
	private LocalDateTime createdDate;
	
	public QnaDTO(Qna qna) {
		this.qnaSeq = qna.getQnaSeq();
		this.playSeq = qna.getPlay().getPlaySeq();
		this.memberName = qna.getMember().getName();
		this.memberId = qna.getMember().getId();
		this.playName = qna.getPlay().getName();
		this.content = qna.getContent(); // Q&A 내용
		this.title = qna.getTitle();
		this.replyContent = (qna.getReply() != null && qna.getReply().getContent() != null) ? qna.getReply().getContent() : "";// 답변 내용
		this.replyYn = (qna.getReply() != null && qna.getReply().getContent() != null) ? "checked" : "";
		this.createdDate = qna.getCreatedDate();
	}
}
