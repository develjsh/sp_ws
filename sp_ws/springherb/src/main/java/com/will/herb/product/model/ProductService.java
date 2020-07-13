package com.will.herb.product.model;

import java.util.List;

public interface ProductService {
	public List<ProductVO> selectEvent(String eventName);
	public List<ProductVO> selectByCategory(int categoryNo);
	public ProductVO selectPdByNo(int productNo);
}
