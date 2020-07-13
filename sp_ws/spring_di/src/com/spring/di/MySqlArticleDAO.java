package com.spring.di;

public class MySqlArticleDAO implements ArticleDAO{

	@Override
	public void insert(ArticleVO vo) {
		System.out.println("MySql~DAO의 insert메서드 호출!!");
		System.out.println("Mysql DB에 " + vo+ "정보를 insert합니다.");		
	}

}
