package com.will.herb.zipcode.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.will.herb.zipcode.model.ZipcodeService;
import com.will.herb.zipcode.model.ZipcodeVO;

@Controller
@RequestMapping("/zipcode")
public class ZipcodeController {
	private static final Logger logger
		=LoggerFactory.getLogger(ZipcodeController.class);
	
	@Autowired
	private ZipcodeService zipcodeService;
	
	@RequestMapping("/zipcode.do")
	public String zipcode(@RequestParam(required = false) String dong,
			Model model) {
		//1
		logger.info("우편번호 검색, 파라미터 dong={}", dong);
		
		//2
		List<ZipcodeVO> list=null;
		if(dong!=null && !dong.isEmpty()) {
			list=zipcodeService.selectZipcode(dong);
			logger.info("우편번호 검색 결과, list.size={}", list.size());
		}
		
		//3
		model.addAttribute("list", list);
		
		//4
		return "zipcode/zipcode";
	}
	
	
}




