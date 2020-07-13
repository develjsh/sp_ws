package com.spring.di;

public class ArticeServiceImpl3 implements ArticleService{
	private ArticleDAO articleDao;
	
	//DI - setter를 이용한 종속객체 주입
	public void setArticleDao(ArticleDAO articleDao) {
		this.articleDao = articleDao;
	}

	@Override
	public void write(ArticleVO vo) {
		System.out.println("ServiceImpl3의 write() 메서드 호출!");
		
		articleDao.insert(vo);
	}

}


