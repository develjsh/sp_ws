package com.spring.di;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Main {

	public static void main(String[] args) {
		//1. 기존 방식 이용  - 직접 종속객체 생성
		ArticleService articleService = new ArticleServiceImpl();
		ArticleVO vo = new ArticleVO();
		vo.setNo(1);
		vo.setTitle("[1] 기존 방식 이용한 경우");
		articleService.write(vo);
		
		//2. DI 이용 - 생성자를 이용한 종속객체 주입
		//작성한 설정 파일로부터 BeanFactory 를 생성하고, 
		//BeanFactory로부터 필요한 빈 객체를 가져와서 사용
		Resource resource
			=new ClassPathResource("applicationContext.xml");
		BeanFactory beanFactory=new XmlBeanFactory(resource);
		ArticleService articleService2
			= (ArticleService)beanFactory.getBean("articleService2");
		ArticleVO vo2 = new ArticleVO();
		vo2.setNo(2);
		vo2.setTitle("[2] DI - 생성자를 이용한 종속객체 주입");
		articleService2.write(vo2);
		
		//3. DI 이용 - setter를 이용한 종속객체 주입
		//ClassPathXmlApplicationContext 를 사용해 applicationContext.xml 
		//을 로드하고 ArticleService 에 대한 레퍼런스를 얻는다
		ApplicationContext context
		  =new ClassPathXmlApplicationContext("applicationContext.xml");
		ArticleService service3
			=(ArticleService) context.getBean("articleService3");
		ArticleVO vo3 = new ArticleVO();
		vo3.setNo(3);
		vo3.setTitle("[3] DI - setter를 이용한 종속객체 주입 이용한 경우");
		service3.write(vo3);
		
	}

}




