package org.doit.ik.aop3.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
public class LogPrintProfiler3 {

	// p.217(219) [Before Advice]
	public void before(JoinPoint joinPoint) throws Throwable {

		String methodName = joinPoint.getSignature().getName();
		log.info(">> " + methodName + "() : LogPrintProfiler3.before 호출됨...");
	
	}
	
	// p.218(220) [After Retruning Advice]
	public void afterReturning(JoinPoint joinPoint, Object result) throws Throwable {
		
		String methodName = joinPoint.getSignature().getName();
		log.info("<< " + methodName + "() : LogPrintProfiler3.afterReturning 호출됨...");
		
	}
	
	// p.222 [Around Advice]
	public Object trace (ProceedingJoinPoint joinPoint) throws Throwable {
		
		StopWatch sw = new StopWatch();
		sw.start();
		
		String methodName = joinPoint.getSignature().getName();
		log.info("> " + methodName + "() start...");
		
		Object result = joinPoint.proceed(); // target (실제 핵심 기능)
		
		log.info("> " + methodName + "() stop...");
		sw.stop();
		log.info("> " + methodName + "() 처리 시간 : " + sw.getTotalTimeMillis() + "ms");
		
		return result;
	}
	
}
