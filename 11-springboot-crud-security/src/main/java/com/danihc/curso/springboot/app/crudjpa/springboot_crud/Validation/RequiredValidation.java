package com.danihc.curso.springboot.app.crudjpa.springboot_crud.Validation;

import org.springframework.util.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


/* Interfaz con el primer generico siendo la anotacion que indique la validacion y 
 * el segundo seria el tipo del campo a validar
 */
public class RequiredValidation implements ConstraintValidator<IsRequired, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //return (value != null && !value.isEmpty() && !value.isEmpty());
        return StringUtils.hasText(value); // Equivalentes
    }

}
