package com.springweb.test.controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		//1. 파라미터 읽어오기
		
		//2. 비즈니스 로직 처리(db작업)
		String res=getGreeting();
		
		//3. ModelAndView에 결과와 뷰 이름 저장
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", res);
		mav.setViewName("test/hello");
		
		//=> /WEB-INF/views/ + 논리적 뷰이름 + .jsp
		//=> /WEB-INF/views/ + test/hello + .jsp
		//=> /WEB-INF/views/test/hello.jsp  
		//=> ViewResolver에 의해 실제 물리적인 뷰이름이 결정됨
		 
		//4. ModelAndView 리턴
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




