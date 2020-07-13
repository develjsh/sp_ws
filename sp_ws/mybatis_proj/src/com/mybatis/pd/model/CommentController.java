package com.mybatis.pd.model;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mybatis.pd.model.CommentVO;
import com.mybatis.pd.model.PdService;

@Controller
public class CommentController {
	private PdService pdService;
	
	//DI
	public void setPdService(PdService pdService) {
		this.pdService = pdService;
	}
	
	@RequestMapping("/test/commentList.do")
	public ModelAndView cmtList() {
		//1
		System.out.println("comment 목록");
		
		//2
		List<CommentVO> list=pdService.selectCmt();
		System.out.println("comment 목록 결과 list.size="+list.size());
		
		//3
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("test/commentList");
		
		//4
		return mav;
	}

	
}
