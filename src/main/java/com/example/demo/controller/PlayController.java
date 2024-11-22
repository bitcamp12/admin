package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PlayDTO;
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

    @GetMapping("/getPlayList")
    @ResponseBody
    public List<PlayDTO> getPlayList() {
    	return playService.getPlayList();
    }
    
	
}
