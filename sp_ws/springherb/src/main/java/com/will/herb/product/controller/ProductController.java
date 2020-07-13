package com.will.herb.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.will.herb.product.model.ProductService;
import com.will.herb.product.model.ProductVO;

@Controller
@RequestMapping("/shop/product")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/productEvent.do")
	public String pdEvet(@RequestParam String eventName, Model model) {
		logger.info("메인 이벤트 상품 조회 파라미터 eventName={}", eventName);
		
		List<ProductVO> list = productService.selectEvent(eventName);
		logger.info("메인 이벤트 상품 결과 값 list.size={} ", list.size());
		
		model.addAttribute("list", list);
		
		return "shop/product/productEvent";
	}
	
}
