package com.will.herb.product.model;

import java.util.List;

public interface ProductService {
	public List<ProductVO> selectEvent(String eventName);
}
