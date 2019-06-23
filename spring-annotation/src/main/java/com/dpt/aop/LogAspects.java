package com.dpt.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 切面类
 * @author 邓鹏涛
 *
 * @Aspect 告诉Spring 当前类是一个切面类
 */
@Aspect
public class LogAspects {
	
	//抽取公共的切入点表达式
	//1、本类引用
	//2、其他类切面引用，就是此方法全类名
	@Pointcut("execution(public int com.dpt.aop.MathCalculator.*(..))")
	public void pointCut() {}
	
	//@Before在目标方法之前切入；切入点表达式（指定在那个方法切入）
	@Before("pointCut()")
	public void logStart(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		System.out.println(joinPoint.getSignature().getName() + "运行。。。@Before：参数列表是：{"+ Arrays.asList(args) +"}");
	}

	@After("com.dpt.aop.LogAspects.pointCut()")
	public void logEnd(JoinPoint joinPoint) {
		System.out.println(joinPoint.getSignature().getName() + "结束。。。@After：参数列表是：{}");
	}

	@AfterReturning(value = "pointCut()", returning="result")
	public void logReturn(Object result) {
		System.out.println("除法正常返回。。。@AfterReturning：运行结果：{" + result + "}");
	}

	//JoinPoint joinPoint 一定写在参数前面
	@AfterThrowing(value = "pointCut()",throwing="exception")
	public void logException(JoinPoint joinPoint, Exception exception) {
		System.out.println(joinPoint.getSignature().getName() + "异常。。。 @AfterThrowing ：异常信息：{"+exception+"}");
	}
	
}
