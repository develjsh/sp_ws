package com.will.herb.product.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDAO productDao;
	
	@Override
	public List<ProductVO> selectEvent(String eventName) {
		return productDao.selectEvent(eventName);
	}
	public List<ProductVO> selectByCategory(int categoryNo){
		return productDao.selectByCategory(categoryNo);
	}
	@Override
	public ProductVO selectPdByNo(int productNo) {
		return productDao.selectPdByNo(productNo);
	}
	
}
