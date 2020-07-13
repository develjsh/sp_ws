package com.will.herb.member.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.api.client.googleapis.auth.clientlogin.ClientLogin.Response;
import com.will.herb.member.model.MemberService;
import com.will.herb.member.model.MemberVO;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/login/login.do", method = RequestMethod.GET)
	public String login_get() {
		logger.info("로그인 화면 보여주기 ");
		
		
		return "login/login";
	}
	
	@RequestMapping(value = "/login/login.do", method = RequestMethod.POST)
	public String login_post(@RequestParam String userid, @RequestParam String pwd, @RequestParam(required = false) String saveId,
			Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("로그인 결과 확인, userid={}, pwd={}", userid, pwd);
		logger.info("saveId={}", saveId);
		
		int result = memberService.loginCheck(userid, pwd);
		
		logger.info("로그인 처리결과, result={}",result);
		
		String msg="로그인 처리 실패", url="/login/login.do";
		if(result==MemberService.LOGIN_OK) {
			//상세정보 조회
			MemberVO vo = memberService.selectMember(userid);
			
			//session에 저장
			HttpSession session = request.getSession();
			session.setAttribute("userid", userid);
			session.setAttribute("userName", vo.getName());
			
			//쿠키에 저장
			if(saveId!=null) {
				Cookie ck = new Cookie("ck_userid", userid);
				ck.setPath("/");
				ck.setMaxAge(1000*24*60*60);
				response.addCookie(ck);
			}else {
				Cookie ck = new Cookie("ck_userid", userid);
				ck.setPath("/");
				ck.setMaxAge(0);
				response.addCookie(ck);
			}
			
			msg=vo.getName()+"님 로그인 되었습니다";
			url="/index.do";
		}else if(result==MemberService.PWD_DISAGREE) {
			msg="비밀번호가 일치하지 않습니다";
		}else if(result==MemberService.ID_NONE) {
			msg="아이디가 존재하지 않습니다";
			
		}
		
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	@RequestMapping("/login/logout.do")
	public String logout(HttpSession session) {
		logger.info("로그 아웃 처리");
		session.invalidate();
		
		return "redirect:/index.do";
	}
	
	
}
