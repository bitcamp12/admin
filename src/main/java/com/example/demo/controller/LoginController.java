//package com.example.demo.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/secure")
//public class LoginController {
//
//    @GetMapping("/login")
//    public String login() {
//        return "login";  // 템플릿 경로에 맞게 수정
//    }
//
//    @GetMapping("/login-error")
//    public String loginError(Model model) {
//        model.addAttribute("loginError", true);
//        return "login";
//    }
//}
