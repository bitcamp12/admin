package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	
	@PostMapping
	public ResponseEntity<ApiResponse<Void>> registerNotice(
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam(value= "image", required = false) MultipartFile image) {
		
		try {
			//DTO 객체 생성
			NoticeDTO noticeDTO = new NoticeDTO();
			noticeDTO.setTitle(title);
			noticeDTO.setContent(content);
			noticeDTO.setHide("N");   // 기본값 설정
			System.out.println("-------------------------------------------------");
			System.out.println(image);
			
			//이미지 처리
			if (image != null && !image.isEmpty()) {
				//이미지 파일 저장 및 파일명 생성
				System.out.println(objectStorageService);
				String imageFileName = objectStorageService.uploadFile(image);
				String originalFilename = image.getOriginalFilename();
				
				System.out.println("-----------------inside image----------------------");
				System.out.println(image);
				
				noticeDTO.setImageFileName(imageFileName);
				noticeDTO.setImageOriginalFileName(originalFilename);
			}
			
			System.out.println("---------------------------ajdfkljasdklfjasdlkfjsdalkf---------------------");
			System.out.println(image);
			
			//공지사항 등록
			noticeService.registerNotice(noticeDTO);
			
			return ResponseEntity.ok(new ApiResponse<>(200, "공지사항 등록 성공", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest()
					.body(new ApiResponse<>(500,"서버 오류", null));
		}
		
		
	}
	
}  
