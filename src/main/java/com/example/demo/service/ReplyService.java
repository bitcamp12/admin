package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ReplyDAO;
import com.example.demo.dto.ReplyDTO;

@Service
public class ReplyService {

	@Autowired
	private ReplyDAO replyDAO;
	
	public void replyWrite(ReplyDTO replyDTO) {
		replyDAO.replyWrite(replyDTO);
	}

	public void replyUpdate(ReplyDTO replyDTO) {
		replyDAO.replyUpdate(replyDTO);
	}

}
