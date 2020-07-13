package com.spring.di;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class LoggingAspect {
	private Log log = LogFactory.getLog(getClass());
		
	public Object logging(ProceedingJoinPoint joinPoint) 
			throws Throwable {
		log.info("기록 시작");
		
		StopWatch stopWatch = new StopWatch();
		try {
			stopWatch.start();
			Object obj=joinPoint.proceed();
			
			return obj;
		}catch(Throwable e) {
			throw e;
		}finally {
			stopWatch.stop();
			log.info("기록 종료!");
			log.info(joinPoint.getSignature().getName() 
				+ " 메서드 실행시간 : " + stopWatch.getTotalTimeMillis()
				+"밀리초");
		}

		/*
		 JoinPoint : 공통 관심 사항이 적용될 수 있는 지점
         	(ex: 메서드 호출 시, 객체 생성 시 등)
		 ProceedingJoinPoint : JoinPoint 의 하위클래스로서 
		 	proceed() 메서드를 가지고 있다. 
		 		
		 proceed() 메서드를 사용하여 실제 타켓 메서드를 호출하게 된다
		*/
	}
}
