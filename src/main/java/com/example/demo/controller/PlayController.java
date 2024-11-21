package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PlayDTO;
import com.example.demo.service.MemberService;
import com.example.demo.service.PlayService;

@RestController
@RequestMapping(value="/api/play")
public class PlayController {
    
	@Autowired
	private PlayService playService;
	
	@Autowired
	MemberService memberService;
	
    @PostMapping("/playRegisterWrite")
    public void playRegisterWrite(@RequestBody PlayDTO playDTO) {
        playService.playRegister(playDTO);
    }
}
