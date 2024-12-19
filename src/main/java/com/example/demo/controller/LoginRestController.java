/*
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.AdminRepository;
import com.example.demo.service.AdminService;
import com.example.demo.service.SellerService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/secure")
public class LoginRestController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private HttpSession httpSession;

	@Autowired
	SellerService sellerService;
	

	@PostMapping("/login/admin")
	public ResponseEntity<ApiResponse<Void>> AdminLogin(@RequestBody LoginDTO loginDTO) {
		try {
			System.out.println("Login 시도 1 - ID: " + loginDTO.getId());
			
			Admin admin = adminService.findByLoginIdAndPassword(loginDTO.getId(),loginDTO.getPassword());
			
			if(admin != null) {
				System.out.println("Login 성공 for ID: " + loginDTO.getId());
				Authentication authentication= new UsernamePasswordAuthenticationToken(
					admin.getId(),
					admin.getPassword(),
					Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
				);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
				//세션에 필요한 정보 저장
				httpSession.setAttribute("role", "ADMIN");
				httpSession.setAttribute("id", admin.getId());
				httpSession.setAttribute("name", admin.getName());

				return ResponseEntity.ok(new ApiResponse<>(200, "success", null));
			}else {
				System.out.println("Login 실패 - 유효하지 않은 credentials");
			}
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ApiResponse<>(401, "wrong password", null));
		} catch(Exception e) {
			System.out.println("Login 에러: " + e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse<>(500, "server error", null));
				
		}
	}

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
	
	@PostMapping("/test")
	public ResponseEntity<String> aa() {
		  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	        String rawPassword = "aa";
	        String encryptedPassword = encoder.encode(rawPassword);
		Admin admin = new Admin();
		admin.setId("aa");
		admin.setPassword(encryptedPassword);
		admin.setName("aa");
		admin.setAdminSeq(10000);
		
		adminRepository.save(admin);
		
		return null;
    }

}
*/
