package com.will.herb.product.model;

import java.util.List;

public interface ProductDAO {
	
	public List<ProductVO> selectEvent(String eventName);
}
