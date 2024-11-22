package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Notice;
import com.example.demo.repository.NoticeRepository;
import com.example.demo.service.NoticeService;

// Admin = 사이트 관리자
@Controller
@RequestMapping(value="/secure/admin")
public class AdminController {
    
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private NoticeRepository noticeRepository;
	
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
    	 model.addAttribute("noticeList",noticeService.getNoticeList());
         return "admin/body/noticeList";  // index.html 템플릿을 렌더링
     }
    
    @GetMapping("/noticeDetail/{noticeSeq}")
    public String noticeDetail(@PathVariable("noticeSeq") int noticeSeq, Model model) {
        // noticeSeq를 사용하여 필요한 데이터를 모델에 추가
    	
    	Optional<Notice> notice = noticeRepository.findById(noticeSeq);
    	if(notice.isPresent()) {
    		  model.addAttribute("notice", notice.get());
    	}
    	// 관리자 정보 등 추가적인 데이터 처리 후 뷰 반환
        return "admin/body/noticeDetail";  // noticeDetail.html 템플릿을 렌더링
    }
    
    @GetMapping("/noticeWriteForm")
 	public String noticeWriteForm(Model model) {
     	//관리자 정보 추후 추가하기 model 어트리뷰트..//	
         return "admin/body/noticeWriteForm";  // index.html 템플릿을 렌더링
     }
    
    @GetMapping("/noticeUpdateForm/{noticeSeq}")
  	public String noticeUpdateForm(@PathVariable("noticeSeq") int noticeSeq, Model model) {
      	//관리자 정보 추후 추가하기 model 어트리뷰트..//	
    	Optional<Notice> notice = noticeRepository.findById(noticeSeq);
    	if(notice.isPresent()) {
    		  model.addAttribute("notice", notice.get());
    	}
          return "admin/body/noticeUpdateForm";  // index.html 템플릿을 렌더링
      }
    
    @GetMapping("/reviewAfterList")
  	public String reviewAfterList(Model model) {
      	//관리자 정보 추후 추가하기 model 어트리뷰트..//	
          return "admin/body/reviewAfterList";  // index.html 템플릿을 렌더링
      }
    
    @GetMapping("/reviewBeforeList")
  	public String reviewBeforeList(Model model) {
      	//관리자 정보 추후 추가하기 model 어트리뷰트..//	
          return "admin/body/reviewBeforeList";  // index.html 템플릿을 렌더링
      }
}
