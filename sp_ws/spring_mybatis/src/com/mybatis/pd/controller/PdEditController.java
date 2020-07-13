package com.mybatis.pd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mybatis.pd.model.PdDTO;
import com.mybatis.pd.model.PdService;

@Controller
@RequestMapping("/pd/pdEdit.do")
public class PdEditController {
	@Autowired private PdService pdService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView pdEdit(@RequestParam(defaultValue = "0") 
		int no) {
		//1
		System.out.println("수정화면, 파라미터 no="+no);
		ModelAndView mav = new ModelAndView();
		if(no==0) {
			mav.addObject("msg", "잘못된 url");
			mav.addObject("url", "/pd/pdList.do");
			
			return mav;
		}
		
		//2
		PdDTO dto=pdService.selectByNo(no);
		System.out.println("수정화면 조회 결과, dto="+dto);
		
		//3
		mav.addObject("dto", dto);
		mav.setViewName("pd/pdEdit");
		
		//4
		return mav;
	}

	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView pdEdit_post(@ModelAttribute PdDTO pdDto) {
		//1
		System.out.println("수정처리, 파라미터 dto="+pdDto);
		
		//2
		int cnt=pdService.updatePd(pdDto);
		System.out.println("수정 결과, cnt="+cnt);
		
		String msg="수정 실패", url="/pd/pdEdit.do?no="+pdDto.getNo();
		if(cnt>0) {
			msg="수정 성공";
			url="/pd/pdDetail.do?no="+pdDto.getNo();
		}
		
		//3
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		mav.setViewName("common/message");
		
		//4
		return mav;
	}
	
}








