package org.doit.ik.aop4.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.log4j.Log4j;

@Aspect
@Component
@Log4j
public class LogPrintProfiler4 {

	@Pointcut("execution(* org.doit.ik.aop..*.*(..))")
	private void calcPointCut() {}
	
	@Before(value = "calcPointCut()")
	public void before(JoinPoint joinPoint) throws Throwable {

		String methodName = joinPoint.getSignature().getName();
		log.info(">> " + methodName + "() : LogPrintProfiler4.before 호출됨...");
	
	}
	
	@AfterReturning(pointcut = "calcPointCut()", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) throws Throwable {
		
		String methodName = joinPoint.getSignature().getName();
		log.info("<< " + methodName + "() : LogPrintProfiler4.afterReturning 호출됨...");
		
	}
	
	@Around(value = "calcPointCut()")
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
