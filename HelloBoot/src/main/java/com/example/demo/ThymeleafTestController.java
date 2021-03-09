package com.example.demo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafTestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ThymeleafTestController.class);
	@GetMapping("/hello")
	public String hello() {
		
		return "thymeleaf1";
	}
	@GetMapping("/hello2")
	public String hello2(Model model) {
		model.addAttribute("userName", "Sung Baeni");
		model.addAttribute("today", new Date());
		LOGGER.info("hjhhj");
		return "thymeleaf1";
	}
	
	
	
	
	
}
