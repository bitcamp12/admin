package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// Admin = 사이트 관리자
@Controller
@RequestMapping(value="/secure/admin")
public class AdminController {
    
    @GetMapping("/index")
	public String index(Model model) {
    	//관리자 정보 추후 추가하기 model 어트리뷰트..//	
        return "admin/index";  // index.html 템플릿을 렌더링
    }
    
    @GetMapping("/memberList")
	public String memberList(Model model) {
    	//관리자 정보 추후 추가하기 model 어트리뷰트..//	
        return "admin/body/memberList";  // index.html 템플릿을 렌더링
    }
    
    @GetMapping("/sellerList")
 	public String sellerList(Model model) {
     	//관리자 정보 추후 추가하기 model 어트리뷰트..//	
         return "admin/body/sellerList";  // index.html 템플릿을 렌더링
     }
    
    @GetMapping("/noticeList")
 	public String noticeList(Model model) {
     	//관리자 정보 추후 추가하기 model 어트리뷰트..//	
         return "admin/body/noticeList";  // index.html 템플릿을 렌더링
     }
    
    @GetMapping("/noticeDetail")
 	public String noticeDetail(Model model) {
     	//관리자 정보 추후 추가하기 model 어트리뷰트..//	
         return "admin/body/noticeDetail";  // index.html 템플릿을 렌더링
     }
    
    @GetMapping("/noticeWriteForm")
 	public String noticeWriteForm(Model model) {
     	//관리자 정보 추후 추가하기 model 어트리뷰트..//	
         return "admin/body/noticeWriteForm";  // index.html 템플릿을 렌더링
     }
}
