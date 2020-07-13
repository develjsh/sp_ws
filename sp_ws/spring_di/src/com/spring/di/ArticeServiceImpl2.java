package com.spring.di;

public class ArticeServiceImpl2 implements ArticleService {
	private ArticleDAO articleDao;
	
	//생성자를 이용한 종속객체 주입 (DI)
	public ArticeServiceImpl2(ArticleDAO articleDao) {
		this.articleDao=articleDao;
	}
	
	@Override
	public void write(ArticleVO vo) {
		System.out.println("ServiceImple2의 write()메서드 호출!!");
		
		articleDao.insert(vo);
	}

}
