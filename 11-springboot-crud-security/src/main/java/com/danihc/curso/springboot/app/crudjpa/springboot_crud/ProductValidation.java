package com.danihc.curso.springboot.app.crudjpa.springboot_crud;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.danihc.curso.springboot.app.crudjpa.springboot_crud.models.Product;


@Component
public class ProductValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) { /* el Object es el objeto a validar y el Errors es un
        generico del BindigResult, contiene la info de la validacion */

        Product product = (Product) target;
        ValidationUtils.rejectIfEmpty(errors, "name", null, "es requerido");
        //ValidationUtils.rejectIfEmpty(errors, "description", null, "es requerido");
        if(product.getDescription() == null || product.getDescription().isBlank()){
            errors.rejectValue("description", null,  "es requerido, por favor");/*
            En caso de usar rejectValue no es compatible con el uso de properties por lo que se tiene que
            usar literales como defaultMessage*/
        }

        if(product.getPrice() == null){
            errors.rejectValue("price", null, "no puede ser nulo, ok!");
        }else if(product.getPrice() < 500){
            errors.rejectValue("price", null, "debe ser un valor numerico mayor o igual de 500!");
        }
    }

}
