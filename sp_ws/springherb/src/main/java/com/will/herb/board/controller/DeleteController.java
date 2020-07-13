package com.will.herb.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.will.herb.board.model.BoardService;
import com.will.herb.board.model.BoardVO;

@Controller
@RequestMapping("/board/delete.do")
public class DeleteController {
	private static final Logger logger
		=LoggerFactory.getLogger(DeleteController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String delete_get(@RequestParam(defaultValue = "0") int no, ModelMap model) {
		//1
		logger.info("삭제 화면, 파라미터 no={}", no);
		
		
		if(no==0) {
			model.addAttribute("msg", "잘못된 URL");
			model.addAttribute("url", "/board/list.do");
			
			return "common/message";
		}
		
		return "board/delete";
		
	}
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(method = RequestMethod.POST)
	public String delete_post(@ModelAttribute BoardVO boardVo, Model model) {
		
		//1
		logger.info("삭제 처리, 파라미터 no={no}", boardVo.getNo());
		
		//2
		
		String msg="글 삭제 실패", url="/board/detail.do?no="+boardVo.getNo();
		if(boardService.checkPwd(boardVo.getNo(), boardVo.getPwd())) {
			int cnt= boardService.deleteBoard(boardVo.getNo());
			if(cnt>0) {
				msg="글 삭제 처리 완료";
				url="/board/list.do";
			}
			
		}else {
			msg= "비밀번호 불일치";
		}
		
		//3
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		//4
		return "common/message";
	}
}
