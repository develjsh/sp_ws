package com.spring.di;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LoggingDAOAspect {
	private Log log = LogFactory.getLog(getClass());
	
	public void before(JoinPoint joinPoint){
		log.info("[ 기록시작 target = "+ joinPoint.getTarget() +"]");
		log.info("메서드 : " + joinPoint.getSignature().getName());
		
		Object[] argArr=joinPoint.getArgs();
		for(int i=0;i<argArr.length;i++) {
			log.info(i + "번째 매개변수 : " + argArr[i]);
		}		
	}
	
	public void afterReturning(JoinPoint joinPoint) {
		log.info("[ 기록 끝  target = "+ joinPoint.getTarget()+" ]");
	}
	
	public void afterError(JoinPoint joinPoint) {
		log.info("[DAO Error]");
		
		for(Object args : joinPoint.getArgs()) {
			log.info("DAO Error 매개변수 : " + args);
		}
	}
	
	
	
	
}





