package com.danihc.curso.springboot.app.aop.springboot_aop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class GreetingFooAspect {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    

    @Before("GreetingServicePointcuts.greetingFooAspectPointCut()")
    public void loggerBefore(JoinPoint joinPoint){

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Antes Foo: " + method + "  invocado con los parametros " + args);
    }

    @After("GreetingServicePointcuts.greetingFooAspectPointCut()")
    public void loggerAfter(JoinPoint joinPoint){ 

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Despues Foo: " + method + " invocado con los parametros " + args);
    }
}

/*
 * Order forma un orden de ejecucion de los aspects pero en modo de anidacion, el order(1) sera el primero en
 * ejecutar el Before y el ultimo en ejecutar el After anidando dentro de su contexto los demas aspectos y 
 * ejecuciones de metodos
 */