package com.danihc.curso.springboot.app.aop.springboot_aop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(2)
@Component
public class GreetingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    // Sustituir String por * para indicar todo tipo de retorno.
    // Sustituir nombre del metodo por * para ejecutar el aspecto en todos los metodos
    // Sustituir GreetingService por * para ejecutar el aspecto en todos los metodos de todas las clases sevice
    //@Before("execution(* com.danihc.curso.springboot.app.aop.springboot_aop..*.*(..))") dos putos seguidos indica todos los package

    @Before("execution(String com.danihc.curso.springboot.app.aop.springboot_aop.services.GreetingService.*(..))") // Punto de corte, es decir, donde se ejecutara el aspecto
    public void loggerBefore(JoinPoint joinPoint){ // Es un punto de union entre el metodo del aspecto y el metodo a ejecutar

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Antes: " + method + " con los argumentos " + args);
    }

    @AfterReturning("GreetingServicePointcuts.greetingLoogerPointCut()") // Uso de metodo con Pointcut
    public void loggerAfterReturning(JoinPoint joinPoint){ 

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Despues de retornar: " + method + " con los argumentos " + args);
    }
    
    @AfterThrowing("execution(String com.danihc.curso.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    public void loggerAfterThrowing(JoinPoint joinPoint){ 
        
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Despues de lanzar la excepcion: " + method + " con los argumentos " + args);
    }
    
    @After("execution(String com.danihc.curso.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    public void loggerAfter(JoinPoint joinPoint){ 

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Despues: " + method + " con los argumentos " + args);
    }

    @Around("execution(* com.danihc.curso.springboot.app.aop.springboot_aop.services.*.*(..))")
    public Object loggerAround(ProceedingJoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        Object result = null;

        try {
            logger.info("El metodo " + method + "() con los parametros " + args);
            //before
            result = joinPoint.proceed();
            //after/after-returning
            logger.info("El metodo " + method + "() retorla el resultado: " + result);
            return result;
        } catch (Throwable ex) {
            //after-throwing
            logger.error("Error en la llamada del metodo " + method + "()");
        }
        return result;
    }
}
