package com.meetkiki.aspectj_aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopConfig {

	// 指定的注解
	@Pointcut("@annotation(com.meetkiki.aspectj_aop.LockAction)")
	public void anyTestMethod() {
	}


	@Around("anyTestMethod()&&@annotation(lockAction)")
	public Object around(ProceedingJoinPoint joinPoint, LockAction lockAction) throws Throwable {
		try {
			System.out.println("lock...");
			return joinPoint.proceed();
		} finally {
			System.out.println("unlock...");
		}
	}


}
