package com.spring.springJDBC.controller;

import java.util.List;

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
	
	//회원 메인화면 보기
	@RequestMapping(value = "/userMain", method = RequestMethod.GET)
	public String userMainGet(Model model) {
		//전체 인원수 구하기
		int userCnt = userService.getUserCnt();
		model.addAttribute("userCnt",userCnt);
		return "user/userMain";
	}
	
	//회원등록
	@RequestMapping(value = "/userInput", method = RequestMethod.GET)
	public String userInputGet() {
		return "user/userInput";
	}
	
	//회원등록 폼보기
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
	
	
	//회원 검색폼 보기
	@RequestMapping(value = "/userSearch2", method = RequestMethod.GET)
	public String userSearch2Get() {
		
		return "user/userSearch2";
	}
	
	//회원 완전일치 검색 - 1건 검색
	@RequestMapping(value = "/userSearchList", method = RequestMethod.GET)
	public String userSearch2ListGet(Model model, String mid) {
		UserVo vo3 = userService.getUserIdSearch(mid);
		if(vo3 != null) {
			model.addAttribute("vo", vo3);
			return "user/userSearch2";
		}
		else return "redirect:/message/userSearchNo";
	
	}
	//회원 완전일치 검색 - 1건 검색 - part에 따른 검색( part : mid/name/address)
	@RequestMapping(value = "/userSearchPart", method = RequestMethod.GET)
	public String userSearchPartGet(Model model, String part, String content) {
		UserVo vo3 = userService.getUserSearchPart(part, content);
		if(vo3 != null) {
			model.addAttribute("vo", vo3);
			return "user/userSearch2";
		}
		else return "redirect:/message/userSearchNo";
		
	}
	
	//회원 부분일치 검색 - 1건 이상 검색
	@RequestMapping(value = "/userSearchListOk", method = RequestMethod.GET)
	public String userSearchListOkGet(Model model, String mid) {
		List<UserVo> vos = userService.userSearchListOk(mid);
		model.addAttribute("vos", vos);
		return "user/userSearch2";
		
	}
	
	//전체 리스트 처리
	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	public String userListGet(Model model) {
		List<UserVo> vos = userService.getUserList();
		model.addAttribute("vos",vos);
		return "user/userList";
	}
	
	//회원 삭제처리
	@RequestMapping(value = "/userDeleteOk", method = RequestMethod.GET)
	public String userDeleteOkGet(int idx) {
		int res = userService.setUserDeleteOk(idx);
		if(res!=0) return "redirect:/message/userDeleteOk";
		else return "redirect:/message/userDeleteNo";
	}
	
	//회원 수정 폼 보기
	@RequestMapping(value = "/userUpdate", method = RequestMethod.GET)
	public String userUpdateGet(Model model, int idx) {
		UserVo vo4 = userService.getUserIdxSearch(idx);
		model.addAttribute("vo",vo4);
		return "user/userUpdate";
	}
	//회원 수정처리
	@RequestMapping(value = "/userUpdate", method = RequestMethod.POST)
	public String userUpdatePost(Model model, UserVo vo) {
		int res= userService.getUserUpdate(vo);
		if(res!=0) return "redirect:/message/userUpdateOk";
		else return "redirect:/message/userUpdateNo";
	}
}
