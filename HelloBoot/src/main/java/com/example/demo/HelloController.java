package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@GetMapping("/")
	public String index() {
		return  "Hello ";
	}
	@GetMapping("/hi/{name}")
	public String hello(@PathVariable  String name) {
		return  "Hello "+ name +"~~~";
	}
}
