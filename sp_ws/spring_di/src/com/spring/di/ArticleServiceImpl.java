package com.spring.di;

public class ArticleServiceImpl implements ArticleService{
	private ArticleDAO articleDao;
	
	//기존 방식
	public ArticleServiceImpl() {
		articleDao = new MySqlArticleDAO();
	}
	
	@Override
	public void write(ArticleVO vo) {
		System.out.println("ServiceImpl의 write()메서드 호출");
		articleDao.insert(vo);
	}

}

