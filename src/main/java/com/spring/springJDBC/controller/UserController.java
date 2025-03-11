package com.spring.springJDBC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.springJDBC.service.UserService;
import com.spring.springJDBC.vo.UserVo;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/userMain", method = RequestMethod.GET)
	public String userMainGet(Model model) {
		//전체 인원수 구하기
		int userCnt = userService.getUserCnt();
		model.addAttribute("userCnt",userCnt);
		return "user/userMain";
	}
	
	@RequestMapping(value = "/userInput", method = RequestMethod.GET)
	public String userInputGet() {
		return "user/userInput";
	}
	
	@RequestMapping(value = "/userInput", method = RequestMethod.POST)
	public String userInputPost(UserVo vo) {
		//아이디 중복체크
		UserVo vo2 = userService.getUserIdSearch(vo.getMid());
		if(vo2 !=null) return "redirect:/message/userIdDuplication";
		//회원 가입처리
		int res = userService.setUserInput(vo);
		if(res != 0) return "redirect:/message/userInputOk";//controller로 보낼떄는 redirect
		else return "redirect:/message/userInputNo";
	}
	
	@RequestMapping(value = "/userSearch", method = RequestMethod.GET)
	public String userSearchGet() {
		
		return "user/userSearch";
	}
	
	@RequestMapping(value = "/userSearch", method = RequestMethod.POST)
	public String userSearchPost(Model model, String mid) {
		UserVo vo = userService.getUserIdSearch(mid);
		if(vo != null) {
			model.addAttribute("vo",vo);
			return "user/userSearchOk";
		}
		return "redirect:/message/usercantSearch";
	}
	
}
