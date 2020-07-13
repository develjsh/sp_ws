package com.mybatis.pd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mybatis.pd.model.PdDTO;
import com.mybatis.pd.model.PdService;

@Controller
public class PdDetailController {
	@Autowired private PdService pdService;
	
	@RequestMapping("/pd/pdDetail.do")
	public ModelAndView pdDetail(@RequestParam(defaultValue = "0") 
		int no) {
		//1
		System.out.println("상세보기,파라미터 no="+no);
		
		ModelAndView mav = new ModelAndView();
		if(no==0) {
			mav.addObject("msg", "잘못된 url");
			mav.addObject("url", "/pd/pdList.do");
			
			return mav;
		}
		
		//2
		PdDTO dto=pdService.selectByNo(no);
		System.out.println("상세보기 결과 dto="+dto);
		
		//3
		mav.addObject("pdDto", dto);
		mav.setViewName("pd/pdDetail");
		
		//4
		return mav;
	}
	
}
