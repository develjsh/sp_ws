package com.spring.di;

public class OracleArticleDAO implements ArticleDAO{

	@Override
	public void insert(ArticleVO vo) {
		System.out.println("oracle DB에 " + vo+ "정보를 insert합니다.");		
	}

}
