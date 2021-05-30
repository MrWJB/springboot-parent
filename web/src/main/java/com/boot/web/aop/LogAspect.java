package com.boot.web.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class LogAspect {

    /**
     * 切入点表达式
     */
    @Pointcut("execution(* com.boot.web.service.*.*(..))")
    public void pcl(){
    }

    /**
     * 前置通知
     * @param jp
     */
    @Before(value = "pcl()")
    public void before(JoinPoint jp){
        String name = jp.getSignature().getName();
        System.out.println(name + "方法开始执行...");
    }

    /**
     * 后置通知
     * @param jp
     */
    @After(value = "pcl()")
    public void after(JoinPoint jp){
        String name = jp.getSignature().getName();
        System.out.println(name + "方法执行结束...");
    }

    /**
     * 返回通知
     * 在该方法中可以获取目标方法的返回值。
     * returning参数是指返回值的变量名，对应方法的参数。
     * @param jp
     * @param result
     */
    @AfterReturning(value = "pcl()", returning = "result")
    public void afterReturing(JoinPoint jp ,Object result){
        String name = jp.getSignature().getName();
        System.out.println(name + "方法返回值..." + result);
    }

    /**
     * 异常通知
     * @param jp
     * @param e
     */
    @AfterThrowing(value = "pcl()", throwing = "e")
    public void AfterThrowing(JoinPoint jp, Exception e){
        String name = jp.getSignature().getName();
        System.out.println(name + "方法抛异常了，异常是: " + e.getMessage());
    }

    /**
     * 环绕通知
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around(value = "pcl()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        return pjp.proceed();
    }
}
