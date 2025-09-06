package com.danihc.curso.springboot.springboot_web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danihc.curso.springboot.springboot_web.models.dto.ParamDto;
import com.danihc.curso.springboot.springboot_web.models.dto.ParamMixDto;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/params")
public class RequestParamsController {

    @GetMapping("/foo")
    public ParamDto foo(@RequestParam(required = false, defaultValue = "Hola que tal", name="mensaje") String message){
        ParamDto param = new ParamDto();
        param.setMessage(message);
        return param;
        /*
         * RequestParam permite obtener un parametro enviado por queryParam, es decir, en el path/ruta/enlace
         * 
         * -required: permite acceder al endpoint sin pasar el param.
         * -defaultValue
         * -name: por defecto el nombre del param al cual se le manda el valor es el nombre del paramentro del metodo, con
         *        name se le puede dar un nombre personalizado, mas recomendable usar el nombre del parametro por default
         */
    }

    @GetMapping("/bar")
    public ParamMixDto bar(@RequestParam String message, @RequestParam Integer code){
        ParamMixDto params = new ParamMixDto();
        params.setMessage(message);
        params.setCode(code);
        return params;
    }


    @GetMapping("/request")
    public ParamMixDto request(HttpServletRequest req){
        ParamMixDto params = new ParamMixDto();
        params.setCode(Integer.valueOf(req.getParameter("code")));
        params.setMessage(req.getParameter("message"));
        return params;
    }
}
