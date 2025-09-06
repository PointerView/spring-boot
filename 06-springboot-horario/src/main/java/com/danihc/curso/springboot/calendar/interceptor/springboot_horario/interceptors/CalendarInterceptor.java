package com.danihc.curso.springboot.calendar.interceptor.springboot_horario.interceptors;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CalendarInterceptor implements HandlerInterceptor{
    
    @Value("${config.calendar.open}")
    private Integer open;

    @Value("${config.calendar.close}")
    private Integer close; 

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
     throws Exception {

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println(hour);

        if(hour > open && hour < close){
            StringBuilder message = new StringBuilder("Bienvenidos al horario de atencion al cliente")
            .append(", atendemos desde las ")
            .append(open)
            .append("hrs. hasta las ")
            .append(close)
            .append("hrs. Gracias por su visita");

            request.setAttribute("message", message.toString());
            return true;
        }
        
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = new HashMap<>();
        StringBuilder message = new StringBuilder("Cerrado, fuera del horario de atencion, ")
        .append("por favor visitenos desde las ")
        .append(open)
        .append(" y las ")
        .append(close)
        .append(" hrs. Gracias!");

        data.put("message", message);
        data.put("date", new Date());

        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(mapper.writeValueAsString(data));

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
     throws Exception {

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
