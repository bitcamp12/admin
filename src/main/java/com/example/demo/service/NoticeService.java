package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.NoticeDAO;
import com.example.demo.dto.NoticeDTO;

@Service 
public class NoticeService {

	@Autowired
	private NoticeDAO noticeDAO;
	
	
	//공지사항 등록 메서드
	@Transactional
	public void registerNotice(NoticeDTO noticeDTO) {
		
		noticeDTO.setHide("N"); // 기본적으로 숨김 처리 NO
		int result = noticeDAO.insertNotice(noticeDTO);
		
		if(result != 1) {
			throw new RuntimeException("공지사항 등록 실패");
		}
	}
}
