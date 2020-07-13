package com.mybatis.pd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mybatis.pd.model.PdDTO;
import com.mybatis.pd.model.PdService;

@Controller
public class PdWriteController {
	@Autowired
	private PdService pdService;
	
	@RequestMapping(value="/pd/pdWrite.do", method = RequestMethod.GET)
	public ModelAndView pdWrite_get() {
		System.out.println("등록 화면");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pd/pdWrite");
		
		return mav;
	}
	
	@RequestMapping(value="/pd/pdWrite.do", method = RequestMethod.POST)
	public ModelAndView pdWrite_post(@ModelAttribute PdDTO pdDto) {
		//1.
		System.out.println("등록 , dto="+pdDto);
		
		//2
		int cnt=pdService.insertPd(pdDto);
		System.out.println("등록 결과, cnt="+cnt);
		
		//3
		ModelAndView mav = new ModelAndView();
		
		//4
		mav.setViewName("redirect:/pd/pdList.do");
		
		return mav;
	}
	
	
	
}
