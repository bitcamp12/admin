package com.example.demo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.PlayDTO;
import com.example.demo.entity.Play;
import com.example.demo.objectstorage.NCPObjectStorageService;
import com.example.demo.repository.PlayRepository;
import com.example.demo.service.PlayService;
import com.example.demo.util.ApiResponse;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/api/plays")
public class PlayController {

	@Autowired
	private PlayService playService;
	
	@Autowired
	private PlayRepository playRepository;

	@Autowired
	private NCPObjectStorageService objectStorageService;
	
	@Autowired
	HttpSession httpSession;

	@PostMapping("/playRegisterWrite")
	public ResponseEntity<ApiResponse<Void>> playRegisterWrite(@RequestParam("name") String name,
			@RequestParam("startTime") LocalDate startTime, @RequestParam("endTime") LocalDate endTime,
			@RequestParam("description") String description, @RequestParam("totalActor") String totalActor,
			@RequestParam(name = "image" , required = false) MultipartFile image) {

		try {
			PlayDTO playDTO = new PlayDTO();
			playDTO.setName(name);
			System.out.println(name);
			playDTO.setStartTime(startTime);
			playDTO.setEndTime(endTime);
			playDTO.setDescription(description);
			playDTO.setTotalActor(totalActor);
			int seq = (int) httpSession.getAttribute("memberSeq");
			playDTO.setMemberSeq(seq);
			if (image != null && !image.isEmpty()) {
				// 이미지 파일 저장 및 파일명 생성
				System.out.println(objectStorageService);
				String imageFileName = objectStorageService.uploadFile(image);
				playDTO.setImageFileName(imageFileName);
				playDTO.setImageOriginalFileName(image.getOriginalFilename());
			}
			playService.playRegisterWrite(playDTO);
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(500, "ok", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(500, "fail", null));
		}
	}

	@DeleteMapping("{playSeq}")
	public ResponseEntity<ApiResponse<Void>> deletePlay(@PathVariable("playSeq") int playSeq) {
		try {
			playService.deleteById(playSeq);
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(200, "ok", null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(500, "fail", null));
		}
	}

	@PostMapping("/playUpdate/{playSeq}")
	public ResponseEntity<ApiResponse<Void>> playRegisterUpdate(
			@PathVariable("playSeq") int playSeq,
			@RequestParam("name") String name,
			@RequestParam("startTime") LocalDate startTime, @RequestParam("endTime") LocalDate endTime,
			@RequestParam("description") String description, @RequestParam("totalActor") String totalActor,
			@RequestParam(name = "image" , required = false) MultipartFile image) {

		try {
			Play play = playRepository.findById(playSeq).get();
			play.setName(name);
			play.setStartTime(startTime.atStartOfDay());
			play.setEndTime(endTime.atStartOfDay());
			play.setDescription(description);
			play.setTotalActor(totalActor);

			if (image != null && !image.isEmpty()) {
				// 이미지 파일 저장 및 파일명 생성
				System.out.println(objectStorageService);
				objectStorageService.deleteFile(play.getImageFileName());
				String imageFileName = objectStorageService.uploadFile(image);
				play.setImageFileName(imageFileName);
				play.setImageOriginalFileName(image.getOriginalFilename());
			}
			playRepository.save(play);
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(500, "ok", null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(500, "fail", null));
		}
	}
}
