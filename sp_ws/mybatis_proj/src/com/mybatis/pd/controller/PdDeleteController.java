package com.mybatis.pd.controller;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mybatis.pd.model.CommentVO;
import com.mybatis.pd.model.PdService;

@Controller
public class PdDeleteController {
	private PdService pdService;
	
	public PdDeleteController() {
		System.out.println("PdDeleteController생성자 호출!");		
	}
	
	//DI
	public void setPdService(PdService pdService) {
		this.pdService = pdService;
		
		System.out.println("setter호출,PdDeleteController-setPdService()");
	}


	@RequestMapping("/pd/pdDelete.do")
	public ModelAndView pdDelete(@RequestParam(defaultValue = "0") int no) {
		//1
		System.out.println("상품 삭제, 파라미터  no="+no);
		
		//2
		ModelAndView mav = new ModelAndView();
		if(no==0) {
			mav.addObject("msg", "잘못된  url입니다.");
			mav.addObject("url", "/pd/pdList.do");
			mav.setViewName("common/message");
			
			return mav;
		}
		
		//3
		
		int cnt=pdService.deletePd(no);
		System.out.println("상품 삭제 처리 결과 cnt= "+cnt);
		
		//4
		mav.setViewName("redirect:/pd/pdList.do");
		
		return mav;
	}
	

	
}
