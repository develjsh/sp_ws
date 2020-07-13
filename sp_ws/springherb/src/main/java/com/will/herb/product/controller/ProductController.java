package com.will.herb.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.will.herb.category.model.CategoryVO;
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
	
	@RequestMapping("/productByCategory.do")
	public String pdCategory(@RequestParam(defaultValue = "0") int categoryNo, @RequestParam String categoryName, Model model ) {
		logger.info("카테고리로 상품 조회, 파라미터 categoryNo={}, categoryName={}", categoryNo, categoryName);
		
		List<ProductVO> list =productService.selectByCategory(categoryNo);
		logger.info("카테고리 상품 list 값,  list.size={}", list.size());
		
		model.addAttribute("list", list);
		
		return "shop/product/productByCategory";
	}
	
	@RequestMapping("/productDetail.do")
	public String pdDetail(@RequestParam(defaultValue = "0") int productNo, Model model) {
		logger.info("상품 상세 조회 화면, 파라미터 productNo={}", productNo);
		
		if(productNo==0) {
			model.addAttribute("msg", "잘못된 URL");
			model.addAttribute("url", "/index.do");
			return "common/message";
		}
		
		ProductVO vo =productService.selectPdByNo(productNo);
		logger.info("상품 상세 조회 결과, 파라미터 vo={}", vo);
		
		model.addAttribute("vo", vo);
		
		return "shop/product/productDetail";
		
	}
	
}
