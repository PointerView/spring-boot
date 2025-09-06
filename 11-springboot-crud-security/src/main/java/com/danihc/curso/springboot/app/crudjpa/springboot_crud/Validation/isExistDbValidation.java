/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.danihc.curso.springboot.app.crudjpa.springboot_crud.Validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.danihc.curso.springboot.app.crudjpa.springboot_crud.services.ProductService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;



@Component
public class isExistDbValidation implements ConstraintValidator<IsExistDb, String>{

    @Autowired
    private ProductService service;
    
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        
        if(service == null){
            return true;
        }

        return !service.existsBySku(value);
    }

}
