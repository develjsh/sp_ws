package com.mybatis.pd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mybatis.pd.model.PdDTO;
import com.mybatis.pd.model.PdService;

@Controller
public class PdListController {
	@Autowired
	private PdService pdService;
	
	@RequestMapping("/pd/pdList.do")
	public ModelAndView pdList(@RequestParam(required = false )
		String keyword) {
		//1
		System.out.println("상품 목록, 파라미터 keyword="+keyword);
		
		//2
		Map<String, String> map = new HashMap<String, String>();
		map.put("pdName", keyword);
		List<PdDTO> list=pdService.selectAll(map);
		System.out.println("목록 결과, list.size="+list.size());
		
		//3
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("pd/pdList");
		
		//4
		return mav;
	}
	
}






