package com.springweb.test.controller;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController2 {

	@RequestMapping("/hello2.do")
	public ModelAndView hello() {
		//1		
		//2
		String result=getGreeting();
		
		//3
		ModelAndView mav = new ModelAndView();
		mav.addObject("greeting", result);
		mav.setViewName("test/hello2");
		
		//4
		return mav;
	}
	
	public String getGreeting() {
		Calendar cal = Calendar.getInstance();
		int hour=cal.get(Calendar.HOUR_OF_DAY);

		String result="";
		if(hour >=6 && hour<=10 ) {
			result="좋은 아침입니다.";
		}else if(hour >=12 && hour<=15 ) {
			result="점심식사는 하셨나요";
		}else if(hour >=18 && hour<=22 ) {
			result="좋은 밤 되세요";
		}else {
			result="안녕하세요";			
		}
		
		return result;		
	}
	
	
}
