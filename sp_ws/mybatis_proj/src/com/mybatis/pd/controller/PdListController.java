package com.mybatis.pd.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mybatis.pd.model.PdDTO;
import com.mybatis.pd.model.PdService;

@Controller
public class PdListController {
	private PdService pdService;
	
	public PdListController() {
		System.out.println("PdListController생성자 호출!");
	}
	
	//DI - setter에 의한 종속객체 주입
	public void setPdService(PdService pdService) {
		this.pdService = pdService;
		
		System.out.println("setter호출, PdListController-setPdService()");
	}

	@RequestMapping("/pd/pdList.do")
	public ModelAndView pdList(@RequestParam(required = false) String keyword) {
		//1
		System.out.println("상품 목록,keyword = "+ keyword);
		
		//2
		Map<String, String> map = new HashMap<String, String>();
		map.put("pdName", keyword);
		
		List<PdDTO> list=null;
		list=pdService.selectAll(map);
		System.out.println("상품 목록 결과 , list= " + list.size());
		
		//3
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("pd/pdList");
		
		//4
		return mav;
	}
	
}







