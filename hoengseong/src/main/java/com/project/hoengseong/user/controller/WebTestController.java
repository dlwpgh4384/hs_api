package com.project.hoengseong.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class WebTestController {
	
	@GetMapping("web/index")
    public String index(Model model) {
		System.out.println("home controller start");
		model.addAttribute("data", "web/index 입니다.");
        return "web/index";
    }
	
	//ResponseBody
	@GetMapping("web/login")
    public String loginpage() {
		System.out.println("login page start");
        return "web/login";
    }

}