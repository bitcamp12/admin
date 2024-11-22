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
@RequestMapping(value="/api/plays")
public class PlayController {
    
	@Autowired
	private PlayService playService;
	
    @PostMapping("/playRegisterWrite")
    public void playRegisterWrite(@RequestBody PlayDTO playDTO) {
        playService.playRegisterWrite(playDTO);
    }

    @PostMapping("/playListSelect")
    public String playListSelect(@RequestBody PlayDTO playDTO) {
    	playService.playListSelect(playDTO);
    }
}
