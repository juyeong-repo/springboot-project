package com.security.bit.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.security.bit.dao.MemberRepository;
import com.security.bit.model.Member;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberRepository memberRepository;

	private final PasswordEncoder passwordEncoder;

	// 인덱스 페이지
	@GetMapping("/")
	public String index() {
	
		return "/index";
	}

	// 로그인 페이지 호출
	@GetMapping("/member/login")
	public String dispLogin() {
		return "/login";
	}

	@GetMapping("/member/login/result")
	public String dispLoginResult() {
		return "/loginSuccess";
	}

	@GetMapping("/member/logout/result")
	public String dispLogout() {
		return "/logout";
	}

	// 접근 거부 페이지
	@GetMapping("/member/denied")
	public String dispDenied() {
		return "/denied";
	}

	// 접근 거부 페이지
	@GetMapping("/loginfail")
	public String loginfail() {
		return "/loginfail";
	}



	@GetMapping("/main") //언제호출되어지는지 
	public String mainPage(@AuthenticationPrincipal User user, Map<String, Object> model) {
		// System.out.println("mainPage" + );
		List<Member> members = memberRepository.findAll();
		model.put("members", members);
		model.put("currentMemberId", user.getUsername());
		return "/homepage";
	}

	@GetMapping("/admin")
	public String adminPage(@AuthenticationPrincipal User user, Map<String, Object> model) {
		
		model.put("currentAdminId", user.getUsername());
		return "/admin";
	}

	@GetMapping("/member/info")
	public String dispMyInfo(@AuthenticationPrincipal User user,Model model) {

		model.addAttribute("currentMemberId", user.getUsername());
		return "/myinfo";
	}


	@GetMapping("/member/signup")
	public String memberJoinForm(Member memberForm) {
		return "/memberJoinForm";
	}
	@PostMapping({"/member/new","/member/signup"})
	public String execSignup(Member member) {
		System.out.println("execSignup:"+member);
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		memberRepository.save(member);

		return "redirect:/member/login";
	}
}