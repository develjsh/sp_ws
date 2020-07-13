package com.mybatis.pd.controller;

import java.sql.SQLException;

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
	private PdService pdService;
		
	public PdEditController() {
		System.out.println("PdEditController 생성자 호출");
	}

	//DI-setter에 의한 종속객체 주입
	public void setPdService(PdService pdService) {
		this.pdService = pdService;
		
		System.out.println("setter호출, PdEditController-setPdService()");
	}

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView pdEdit_get(@RequestParam(defaultValue = "0") int no) {
		//1
		System.out.println("상품 수정 화면, 파라미터 no="+no);
		
		ModelAndView mav = new ModelAndView();
		if(no==0) {
			mav.addObject("msg", "잘못된 url입니다.");
			mav.addObject("url", "/pd/pdList.do");
			mav.setViewName("common/message");
			
			return mav;
		}
		
		//2
		PdDTO pdDto=null;
		
			pdDto=pdService.selectByNo(no);
		System.out.println("상품 수정 화면 조회 결과, dto="+pdDto);
		
		mav.addObject("dto", pdDto);
		mav.setViewName("pd/pdEdit");
		
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView pdEdit_post(@ModelAttribute PdDTO pdDto) {
		//1
		System.out.println("상품 수정 처리, 파라미터  pdDto="+pdDto);
		
		//2		
		
		int cnt=pdService.updatePd(pdDto);
		System.out.println("상품 수정 처리 결과 = "+ cnt);
		
		//3
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/pd/pdDetail.do?no="+pdDto.getNo());
		
		//4
		return mav;
	}
	
	
}






