package com.danihc.curso.springboot.app.interceptor.springboot_interceptor.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("timeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor{

    private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("LoadingTimeInterceptor: preHandle() entrando a " + ((HandlerMethod)handler).getMethod().getName() + "...");
        Long start = System.currentTimeMillis();
        request.setAttribute("start", start);
        Random random = new Random();
        Integer delay = random.nextInt(500);
        Thread.sleep(delay);

        Map<String, String> json = new HashMap<>();
        json.put("error", "no tienes acceso a esta pagina");
        json.put("date", new Date().toString());

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(json);

        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(jsonString);

        /*Un interceptor preHandler retorna true o false, si se quiere retornar lago mas se requiere del uso del HttpServletResponse */

        return false; /*
        * True: continua con los siguientes interceptores o con el metodo destino
        * False: finaliza la ejecucion, no continua con la cadena de metodos ya sean interceptores o cualquier otro metodo.
        */
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Long result = System.currentTimeMillis() - ((Long) request.getAttribute("start"));
        logger.info("Tiempo transcurrido: " + result + " milisegundos!");
        logger.info("LoadingTimeInterceptor: postHandle() saliendo a " + ((HandlerMethod)handler).getMethod().getName() + "...");
    }
}
