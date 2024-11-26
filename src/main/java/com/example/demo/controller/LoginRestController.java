package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.LoginDTO;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Member;
import com.example.demo.service.AdminService;
import com.example.demo.service.SellerService;
import com.example.demo.util.ApiResponse;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/api/secure")
@Controller
public class LoginRestController {

	@Autowired
	AdminService adminService;
	
	@Autowired
	SellerService sellerService;
	
	@Autowired
	HttpSession httpSession;
	
	@PostMapping("/login/admin")
	public ResponseEntity<ApiResponse<Void>> AdminLogin(@RequestBody LoginDTO loginDTO) {
       
		String id = loginDTO.getId();
		String password = loginDTO.getPassword();
		try {
			Admin result = adminService.findByLoginIdAndPassword(id,password);
			if(result != null) {
				httpSession.setAttribute("role", "ADMIN");
				httpSession.setAttribute("id", id);
				httpSession.setAttribute("name", result.getName());
				return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(200, "success", null));
			}
			else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>(401, "wrong password", null));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(500, "serve error", null));
		}	
    }
	
	@PostMapping("/login/seller")
	public ResponseEntity<ApiResponse<Void>> SellerLogin(@RequestBody LoginDTO loginDTO) {
       
		String id = loginDTO.getId();
		String password = loginDTO.getPassword();
		try {
			Member result = sellerService.findByLoginIdAndPassword(id,password);
			if(result != null) {
				httpSession.setAttribute("role", "SELLER");
				httpSession.setAttribute("name", result.getName());
				httpSession.setAttribute("id", id);
				httpSession.setAttribute("memberSeq", result.getMemberSeq());
				return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(200, "success", null));
			}
			else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>(401, "wrong password", null));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(500, "serve error", null));
		}	
    }

}
