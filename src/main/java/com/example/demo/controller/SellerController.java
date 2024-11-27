package com.example.demo.controller;

import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.QnaDTO;
import com.example.demo.entity.Play;
import com.example.demo.entity.Qna;
import com.example.demo.repository.PlayRepository;
import com.example.demo.repository.PlayTimeTableRepository;
import com.example.demo.repository.QnaRepository;
import com.example.demo.repository.TheaterRepository;import com.example.demo.service.BookService;
import com.example.demo.service.PlayService;
import com.example.demo.service.QnaService;

import jakarta.servlet.http.HttpSession;

//Seller = 공연 관계자
@Controller
@RequestMapping(value="/secure/seller")
public class SellerController {
    
	@Autowired
	private PlayService playService;

	@Autowired
	private PlayRepository playRepository;
	
	@Autowired
	private TheaterRepository theaterRepository;
	
	@Autowired
	private QnaRepository qnaRepository;
	
	@Autowired
	private QnaService qnaService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private PlayTimeTableRepository playTimeTableRepository;
	
	@Autowired
	HttpSession httpSession;
	
	@GetMapping("/index")
	public String index(Model model) {
		String role = (String) httpSession.getAttribute("role");
    	String name = (String) httpSession.getAttribute("name");
    	if("SELLER".equals(role) && name != null) {
    		model.addAttribute("name", name);
    		return "seller/index";  // index.html 템플릿을 렌더링
    	}else {
    		return "redirect:/secure/login";
    	}
    }
	
	@GetMapping("/logout")
	public String logout() {
		httpSession.invalidate();
    	return "redirect:/secure/login";
    }
    
    // 공연정보등록
    @GetMapping("/playRegisterForm")
	public String playReg(Model model) {
        return "/seller/body/playRegisterForm";  
    }
    
    // 공연정보수정
    @GetMapping("/playUpdate/{playSeq}")
    public String playUpdate(@PathVariable("playSeq")int playSeq, Model model) {
    	Play play = null;
		try {
			 play = playService.findById(playSeq);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(play != null) {
			model.addAttribute("play", play);
			ZoneId zoneId = ZoneId.systemDefault();
		    Date startTime = Date.from(play.getStartTime().atZone(zoneId).toInstant());
		    Date endTime = Date.from(play.getEndTime().atZone(zoneId).toInstant());
			model.addAttribute("startTime", startTime);
			model.addAttribute("endTime", endTime);
		}
    	return "/seller/body/playUpdate";  
    }
    
    // 공연정보리스트
    @GetMapping("/playList")
    public String playList(Model model) {
    	model.addAttribute("playList", playService.getPlayList());
    	return "/seller/body/playList";  
    }
    
    // 예매정보관리
    @GetMapping("/bookList")
    public String bookList(Model model) {
    	model.addAttribute("bookList", bookService.getBookList());
    	return "/seller/body/bookList";
    }
    
    // QnA 게시판
    @GetMapping("/qnaList")
    public String qnaList(Model model) {
    	model.addAttribute("qnaList", qnaService.getQnaList());
    	return "/seller/body/qnaList";  
    }
    
    // QnA 게시판 - 답변 뷰
    @GetMapping("/qnaDetail/{qnaSeq}")
    @ResponseBody
    public ResponseEntity<QnaDTO> qnaDetail(@PathVariable("qnaSeq") int qnaSeq) {
    	QnaDTO qnaDTO = qnaService.getQnaDetail(qnaSeq);
    	
    	if(qnaDTO != null) {
    		return ResponseEntity.ok(qnaDTO); 
    	} else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    	}
    }
    
    // QnA 게시판 - 답변 등록
    @GetMapping("/qnaWrite")
    public String qnaWrite(@PathVariable("qnaSeq") int qnaSeq, Model model) {
    	return "/seller/body/qnaWrite";
    }
    
    // 공연 시간 등록
    @GetMapping("/timeRegisterForm")
    public String timeRegisterForm(Model model) {
    	model.addAttribute("playList", playRepository.findAll());
    	model.addAttribute("theaterList", theaterRepository.findAll());
    	return "/seller/body/timeRegisterForm";  
    }
    
    // 공연 시간 등록
    @GetMapping("/timeUpdateForm/{seq}")
    public String timeUpdateForm(@PathVariable("seq") int seq, Model model) {
    	
    	model.addAttribute("timetable", playTimeTableRepository.findById(seq).get());
    	model.addAttribute("playList", playRepository.findAll());
    	model.addAttribute("theaterList", theaterRepository.findAll());
    	
    	ZoneId zoneId = ZoneId.systemDefault();
	    Date targetDate = Date.from(playTimeTableRepository.findById(seq).get()
	    		.getTargetDate().atZone(zoneId).toInstant());
		model.addAttribute("targetDate", targetDate);
    	
    	return "/seller/body/timeUpdateForm";  
    }
    
    // 공연 시간 등록
    @GetMapping("/timeList")
    public String timeList(Model model) {
    	model.addAttribute("timeList", playTimeTableRepository.findAll());
    	return "/seller/body/timeList";  
    }
}

/*
    // 공연정보등록
    @GetMapping("/playRegisterForm")
	public String playReg(Model model) {
        return "seller/body/playRegisterForm";  
    }

    // 공연정보수정
    @GetMapping("/playUpdate")
    public String playUdt(Model model) {
    	return "seller/body/playUpdate";  
    }
    
    // 공연정보리스트
    @GetMapping("/playList")
    public String playList(Model model) {
    	return "seller/body/playList";  
    }
    
    // 예매관리
    @GetMapping("/bookList")
    public String bookList(Model model) {
    	return "seller/body/bookList";  
    }
 */