package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AdminDAO;
import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminService {
	@Autowired
	private AdminDAO adminDAO;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public Admin findByLoginIdAndPassword(String id, String password) {
		Optional<Admin> admin = adminRepository.findById(id);
		System.out.println("관리자 찾음?" + (admin.isPresent() ? "y":"n"));

		if(admin.isPresent()) {
			boolean passwordMatch = passwordEncoder.matches(password, admin.get().getPassword());
			System.out.println("Password 매칭됨? " + passwordMatch);
			
			if(passwordMatch) {
				return admin.get();
			}
		}
//		if(admin.isPresent() && passwordEncoder.matches(password, admin.get().getPassword())) {
//			return admin.get();
//		}
		return null;
	}
}
