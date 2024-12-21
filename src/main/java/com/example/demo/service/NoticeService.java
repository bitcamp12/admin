package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.NoticeDAO;
import com.example.demo.dto.NoticeDTO;
import com.example.demo.entity.Notice;
import com.example.demo.entity.Notice.HideStatus;
import com.example.demo.objectstorage.NCPObjectStorageService;
import com.example.demo.repository.NoticeRepository;

@Service
public class NoticeService {

	@Autowired
	private NoticeDAO noticeDAO;

	@Autowired
	private NoticeRepository noticeRepository;

	@Autowired
	private NCPObjectStorageService objectStorageService;

	// 공지사항 등록 메서드
	@Transactional
	public void registerNotice(NoticeDTO noticeDTO) {

		noticeDTO.setHide("N"); // 기본적으로 표기되도록 - 숨김NO 상태

		int result = noticeDAO.insertNotice(noticeDTO);

		if (result != 1) {
			throw new RuntimeException("공지사항 등록 실패");
		}
	}

	public List<Notice> getNoticeList() {
		return noticeRepository.findAll();
	}

	@Transactional
	public void updateNotice(int noticeSeq, String title, String content, MultipartFile image) throws Exception {
		// TODO Auto-generated method stub
		Optional<Notice> optionalNotice = noticeRepository.findById(noticeSeq);
		// 존재하지 않으면
		if (!optionalNotice.isPresent()) {
			// seq로 조회가 안된다면
			throw new Exception("공지 사항이 없습니다.");
		}
		Notice notice = optionalNotice.get();

		if (image != null) {
			// Object Storage(NCP) 이미지 삭제
			objectStorageService.deleteFile(notice.getImageFileName());

			String imageOriginalFileName = image.getOriginalFilename();
			// Object Storage(NCP) 새로운 이미지 올리기
			String imageFileName = objectStorageService.uploadFile(image);
			notice.setTitle(title);
			notice.setContent(content);
			notice.setImageFileName(imageFileName);
			notice.setImageOriginalFileName(imageOriginalFileName);
			notice.setUpdatedDate(LocalDateTime.now());
			noticeRepository.save(notice);
		} else { // 이미지 변경 안하면
			notice.setTitle(title);
			notice.setContent(content);
			notice.setUpdatedDate(LocalDateTime.now());
			noticeRepository.save(notice);
		}
	}

	public void deleteNotice(int noticeSeq) {
		noticeRepository.deleteById(noticeSeq);
	}

	public void updateHideStatus(int noticeSeq, String hide) throws Exception {
		Notice notice = noticeRepository.findById(noticeSeq).orElseThrow(
				()-> new Exception("공지찾기 실패")
		);
		
		if(hide.equals("true"))
			notice.setHide(HideStatus.Y);
		else
			notice.setHide(HideStatus.N);
		noticeRepository.save(notice);
	}

	// 공지사항 페이징 처리 (검색 없이)
    public List<Notice> getNoticePaging(Pageable pageable) {
        Page<Notice> pageResult = noticeRepository.findAll(pageable); // 모든 공지사항 페이징
        return pageResult.getContent(); // 페이징된 결과 목록 반환
    }

    // 전체 공지사항 개수 반환
    public long getTotalCount() {
        return noticeRepository.count();
    }

    // 검색 키워드를 기준으로 전체 공지사항 개수 반환
    public long getTotalCountKeyword(String value) {
        return noticeRepository.countByTitleContaining(value); // 제목에 키워드가 포함된 공지사항 개수 반환
    }

    // 검색 키워드를 기준으로 공지사항 페이징 처리
    public List<Notice> getNoticePagingKeyword(Pageable pageable, String value) {
        Page<Notice> pageResult = noticeRepository.findByTitleContaining(value, pageable); // 키워드로 검색하여 페이징 처리
        return pageResult.getContent(); // 페이징된 결과 목록 반환
    }
    
}
