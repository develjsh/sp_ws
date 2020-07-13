package com.mybatis.pd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mybatis.pd.model.PdService;

@Controller
public class PdDeleteController {

	@Autowired
	private PdService pdService;
		
	@RequestMapping("/pd/pdDelete.do")
	public ModelAndView pdWrite_post(@RequestParam(defaultValue = "0") 
	int no) {
		//1.
		System.out.println("삭제, 파라미터 no="+no);
		ModelAndView mav = new ModelAndView();
		if(no==0) {
			mav.addObject("msg", "잘못된 url");
			mav.addObject("url", "/pd/pdList.do");
			
			return mav;
		}
		
		//2
		int cnt=pdService.deletePd(no);
		System.out.println("삭제 결과, cnt="+cnt);
		
		//3
		mav.setViewName("redirect:/pd/pdList.do");
		
		//4		
		return mav;
	}
}
