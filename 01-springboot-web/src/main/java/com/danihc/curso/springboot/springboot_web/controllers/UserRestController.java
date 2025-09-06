package com.danihc.curso.springboot.springboot_web.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danihc.curso.springboot.springboot_web.models.User;
import com.danihc.curso.springboot.springboot_web.models.dto.UserDto;


@RestController // Retorna la respuesta como JSON al body
@RequestMapping("/api") // Mapea el controler completo con una ruta
public class UserRestController {

    @GetMapping("/details")
    public Map<String, Object> details(){
        Map<String, Object> responseBody = new HashMap<>();
        User user = new User("Daniel", "Hernandez");
        responseBody.put("title", "Hola Mundo Spring Boot");
        responseBody.put("user", user);

        return responseBody;
    }

    @GetMapping("/list")    
    public List<User> list(){
        User user = new User("Daniel", "Hernandez");
        User user2 = new User("Pepe", "Doe");
        User user3 = new User("Jhon", "Doe");
        
        List<User> users = Arrays.asList(user, user2, user3);

        return users;
    }

    @GetMapping("/details2")
    public UserDto details2(){
        User user = new User("Daniel", "Hernandez");
        
        UserDto userDto = new UserDto();
        userDto.setUser(user);
        userDto.setTitle("Hola Mundo Spring Boot");

        return userDto;
    }
    
}
/*
 * @RestController = @Controller + @ResponseBody
 * @GetMapping = @RequestMapping(path="..." method=RequestMethod.GET)
 */