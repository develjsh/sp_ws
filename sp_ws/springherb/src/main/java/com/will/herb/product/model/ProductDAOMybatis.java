package com.will.herb.product.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAOMybatis implements ProductDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private String namespace="config.mybatis.mapper.oracle.product.";
	
	
	public List<ProductVO> selectEvent(String eventName) {
		return sqlSession.selectList(namespace +"selectByEventName", eventName);
	}
	
	public List<ProductVO> selectByCategory(int categoryNo){
		return sqlSession.selectList(namespace + "selectByCategory", categoryNo);
	}
	
	public ProductVO selectPdByNo(int productNo){
		return sqlSession.selectOne(namespace + "selectPdByProductNo", productNo);
	}
}
