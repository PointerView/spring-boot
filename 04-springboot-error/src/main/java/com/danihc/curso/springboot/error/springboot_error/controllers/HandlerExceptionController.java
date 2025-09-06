package com.danihc.curso.springboot.error.springboot_error.controllers;

import com.danihc.curso.springboot.error.springboot_error.exceptions.UserNotFoundException;
import com.danihc.curso.springboot.error.springboot_error.models.Error;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice/* Se usa la anotacion @RestControllerAdvice para indicar que es un controlador rest(@ResponseBody
que contrendra manejadores de excepciones(@ontrollerAdvice))*/
public class HandlerExceptionController {

    @ExceptionHandler(ArithmeticException.class) // Indicar a que excepcion se mapeara este metodo
    public ResponseEntity<Error> divisionByZero(ArithmeticException ex){
        Error error = new Error();
        error.setDate(new Date());
        error.setError("Error division por cero!");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        //return ResponseEntity.internalServerError().body(error);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
    }

    /*El error 404 not found lo maneja por defecto spring aunq creemos el handler, para cambiarlo se debera
    * de realizar en el .properties y cambiar esa configuracion*/
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Error> noFoundEx(NoHandlerFoundException e){
        Error error = new Error();
        error.setDate(new Date());
        error.setError("Api rest no encontrada!");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
        /*return ResponseEntity.notFound().build();
        * Se retorna de este modo si solo se quiere mandar el error por defecto, sin modificar el cuerpo
        * de la excepción
        * */
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)/* En vez de enviar el status directamente en el return
    se puede indicar que tipo de status retornar con esta anotacion*/
    public Map<String, Object> numberFormatException(NumberFormatException e){
        Map<String, Object> error = new HashMap<>();

        error.put("message", e.getMessage());
        error.put("error", "No se pudo convertir ese input a Integer");
        error.put("date", new Date());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return error;
    }

    @ExceptionHandler({UserNotFoundException.class,
            HttpMessageNotWritableException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)/* En vez de enviar el status directamente en el return
    se puede indicar que tipo de status retornar con esta anotacion*/
    public Map<String, Object> userNotFoundException(Exception e){
        Map<String, Object> error = new HashMap<>();

        error.put("message", e.getMessage());
        error.put("error", "El user o role no existe");
        error.put("date", new Date());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return error;
    }
}
