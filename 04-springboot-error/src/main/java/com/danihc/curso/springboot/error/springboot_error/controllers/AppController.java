package com.danihc.curso.springboot.error.springboot_error.controllers;

import com.danihc.curso.springboot.error.springboot_error.exceptions.UserNotFoundException;
import com.danihc.curso.springboot.error.springboot_error.models.domain.User;
import com.danihc.curso.springboot.error.springboot_error.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private UserService service;

    @GetMapping("/")
    public String index(){
        //int value = 100/0;
        int value = Integer.parseInt("10c");
        System.out.println(value);
        return "ok 200";
    }

    @GetMapping("/show/{id}")
    public User findById(@PathVariable Long id){
        Optional<User> u = service.findById(id);

        System.out.println(u.orElseThrow(() -> new UserNotFoundException("Error, el usuario no existe"))
                .getName());

        return u.get();
    }

    @GetMapping("/show2/{id}")
    public ResponseEntity<?> findById2(@PathVariable Long id){
        Optional<User> u = service.findById(id);

        if(u.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(u.get());
    }
}