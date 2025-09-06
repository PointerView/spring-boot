package com.danihc.curso.springboot.springboot_web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danihc.curso.springboot.springboot_web.models.User;
import com.danihc.curso.springboot.springboot_web.models.dto.ParamDto;


@RestController
@RequestMapping("/api/var")
public class PathVariableController {

    @Value("${config.username}") // Se usa config porq estan agregadas dentro del config al ser propiedades de configuracion
    private String username;

    //@Value("${config.message}")
    //private String message;

    @Value("${config.listOfValues}")
    private List<String> listOfValues;

    @Value("${config.code}")
    private Integer code;

    @Value("#{'${config.listOfValues}'.split(',')}")
    private List<String> valueList;

    @Value("#{'${config.listOfValues}'}")
    private String value;

    @Value("#{'${config.listOfValues}'.toUpperCase().split(',')}")
    private List<String> valuesUpper;

    @Value("#{${config.valuesMap}}") /* Para injectar clases complejas(no primitivos) se usa SpEL para que haga un cast
    al tipo de dato del atributo, si se usa sin SpEL, se importa un string y no funciona, por ello si se inyectan valores
    simples/primitivos se usa ${} pero si se usan estructuras de datos o logica dinamica se usa #{}*/
    private Map<String, Object> valuesMap;

    @Value("#{${config.valuesMap}.product}")
    private String product;

    @Value("#{${config.valuesMap}.price}")
    private Long price;

    @Autowired /* Busca un Component en el contenedor de Spring y lo instancia por injeccion de dependencias */
    private Environment env;

    @GetMapping("/baz/{message}") // El nombre de la variable del path y del parametro deben de ser siempre iguales, si se quiere cambiar, usar name en PathVariable
    public ParamDto baz(@PathVariable String message){

        ParamDto param = new ParamDto();
        param.setMessage(message);

        return param;
        /*
         * PathVariable es diferente de RequestParam, este ultimo se envia especificando una variable y su valor pero no forma
         * parte de el endpoint en si, sino que es como una especie de extension del mismo, en cambio con esta opcion se aplica
         * el valor directamente como parte de la misma ruta, se le da el valor directamente y el controlador es el encargado de
         * recuperarlo, ademas, esa variable es parte del endpoint y siempre se debera de indicar para poder acceder al mismo.
         * 
         * Este ultimo modo es mas recomendable por la facilidad que tendran los navegadores para anexar los endpoints aunq cada
         * uno es muy util para dependiendo que casos
         */
    }


    @GetMapping("/mix/{product}/{id}")
    public Map<String, Object> mixPathVariable(@PathVariable String product, @PathVariable Long id) {
        
        Map<String, Object> json = new HashMap<>();
        json.put("product", product);
        json.put("id", id);


        return json;
    }
    
    @PostMapping("/create")
    public User create(@RequestBody User user){
        // hacer algo con el usuario save en la bbdd
        user.setName(user.getName().toUpperCase());
        return user;
    }

    @GetMapping("/values")
    public Map<String, Object> values(@Value("${config.message}") String message){
        Map<String, Object> json = new HashMap<>();
        json.put("username", username);
        json.put("code", code);
        json.put("code2", env.getProperty("config.code", Integer.class)); // Cast del retorno del property de String a Integer
        json.put("message", message);
        json.put("message2", env.getProperty("config.message")); /*Alternativa al uso de @Value mediante Enviroment */
        json.put("listOfValues", listOfValues);
        json.put("valueList", valueList);
        json.put("value", value);
        json.put("valuesUpper", valuesUpper);
        json.put("valuesMap", valuesMap);
        json.put("product", product);
        json.put("price", price);

        return json;
    }
}
