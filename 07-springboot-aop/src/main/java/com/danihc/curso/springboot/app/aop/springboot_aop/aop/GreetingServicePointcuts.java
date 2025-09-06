package com.danihc.curso.springboot.app.aop.springboot_aop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class GreetingServicePointcuts {

    @Pointcut("execution(String com.danihc.curso.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    public void greetingLoogerPointCut(){}

    @Pointcut("execution(* com.danihc.curso.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    public void greetingFooAspectPointCut(){}
}
