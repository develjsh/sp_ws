package com.will.herb.category.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryDAO categoryDao;
	public List<CategoryVO> selectCategory(){
		return categoryDao.selectCategory();
	}
	

}