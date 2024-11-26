package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.NoticeDTO;
import com.example.demo.objectstorage.NCPObjectStorageService;
import com.example.demo.service.NoticeService;
import com.example.demo.util.ApiResponse;

@RestController
@RequestMapping(value = "/api/notices")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private NCPObjectStorageService objectStorageService;

	//공지 사항 등록
	@PostMapping("")
	public ResponseEntity<ApiResponse<Void>> registerNotice(
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam(value = "image", required = false) MultipartFile image) {

		try {
			// DTO 객체 생성
			NoticeDTO noticeDTO = new NoticeDTO();
			noticeDTO.setTitle(title);
			noticeDTO.setContent(content);
			noticeDTO.setHide("N"); // 기본값 설정

			// 이미지 처리
			if (image != null && !image.isEmpty()) {
				// 이미지 파일 저장 및 파일명 생성
				System.out.println(objectStorageService);
				String imageFileName = objectStorageService.uploadFile(image);
				System.out.println("-----------------inside image----------------------");
				System.out.println(image);
				noticeDTO.setImageFileName(imageFileName);
				noticeDTO.setImageOriginalFileName(image.getOriginalFilename());
			};

			// 공지사항 등록
			noticeService.registerNotice(noticeDTO);

			return ResponseEntity.ok(new ApiResponse<>(200, "공지사항 등록 성공", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ApiResponse<>(400, "서버 오류: " + e.getMessage(), null));
		}
	}

	//공지 사항 수정
	//@PutMapping("{noticeSeq}")
	// 원래 PUT이 맞는데 , form데이터는 put 지원하지 않음
	@PostMapping("/update/{noticeSeq}")
	public ResponseEntity<ApiResponse<Void>> updateNotice(
			@PathVariable("noticeSeq") int noticeSeq,
			@RequestParam("title") String title, 
			@RequestParam("content") String content,
			@RequestParam(value = "image", required = false) MultipartFile image) {

		try { // 공지 사항 수정
			noticeService.updateNotice(noticeSeq, title, content, image);
			return ResponseEntity.ok().body(new ApiResponse<>(200, "공지 사항이 수정에 성공했습니다.", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ApiResponse<>(400, "공지 사항이 수정에 실패했습니다.", null));
		}
	}

	//공지 사항 삭제
	@DeleteMapping("{noticeSeq}")
	public ResponseEntity<ApiResponse<Void>> deleteNotice(
			@PathVariable("noticeSeq") int noticeSeq) {

		try { // 공지 사항 수정
			noticeService.deleteNotice(noticeSeq);
			return ResponseEntity.ok().body(new ApiResponse<>(200, "공지 사항 삭제에 성공했습니다.", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ApiResponse<>(400, "공지 사항이 삭제가 실패했습니다.", null));
		}
	}
	
	@PutMapping("/updateHideStatus/{noticeSeq}")
	public ResponseEntity<ApiResponse<Void>> updateHideStatus(
			@PathVariable("noticeSeq") int noticeSeq,
			@RequestParam(name = "hide", required = true) String hide) {
		try {
			noticeService.updateHideStatus(noticeSeq, hide);
			return ResponseEntity.ok().body(new ApiResponse<>(200, "숨김여부 수정 성공", null));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}	
	}
	
}
