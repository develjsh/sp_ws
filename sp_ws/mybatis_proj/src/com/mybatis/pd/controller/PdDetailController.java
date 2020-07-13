package com.mybatis.pd.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mybatis.pd.model.PdDTO;
import com.mybatis.pd.model.PdService;

@Controller
public class PdDetailController {
	private PdService pdService;
	
	public PdDetailController() {
		System.out.println("PdDetailController생성자 호출!");
	}
	
	//DI - setter에 의한 종속객체 주입
	public void setPdService(PdService pdService) {
		this.pdService = pdService;
		
		System.out.println("setter호출, PdDetailController-setPdService()");
	}


	@RequestMapping("/pd/pdDetail.do")
	public ModelAndView pdDetail(@RequestParam(defaultValue = "0") int no) {
		//1
		System.out.println("상품 상세보기, 파라미터  no="+no);
		
		ModelAndView mav = new ModelAndView();
		if(no==0) {
			mav.addObject("msg","잘못된 URL입니다.");
			mav.addObject("url","/pd/pdList.do");
			mav.setViewName("common/message");
			
			return mav;
		}
		
		//2
		PdDTO pdDto=pdService.selectByNo(no);
		System.out.println("상품상세보기 결과, pdDto="+pdDto);
		
		//3
		mav.addObject("pdDto", pdDto);
		mav.setViewName("pd/pdDetail");
		
		//4
		return mav;
	}
	
}







