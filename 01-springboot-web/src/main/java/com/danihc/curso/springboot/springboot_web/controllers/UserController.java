package com.danihc.curso.springboot.springboot_web.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.danihc.curso.springboot.springboot_web.models.User;


@Controller
public class UserController {

    @GetMapping("/details") // Mapea el metodo como componente(beans en el contenedor de spring) pero con el requestmethod en GET
    public String details(Model model){ // Objeto para pasar info a la vista
        User user = new User("Daniel", "Hernandez");
        model.addAttribute("title", "Hola Mundo Spring Boot");
        model.addAttribute("user", user);

        return "details"; // Retorna una vista/template
    }

    @GetMapping("/list")
    public String list(ModelMap model) {
        model.addAttribute("title", "Listado de usuarios");
        return "list";
    }

    /*
     * ModelAttribute retorna en las vistas de cualquier metodo del controlador el objeto de tipo list llamado users, esto
     * es util para solo declarar una vez un objeto y poder reutilizarse en todos los metodos de ese mismo controlador
     */
    @ModelAttribute("users") 
    public List<User> usersModel() {
        return Arrays.asList(new User("pepa", "Gonzalez"),
            new User("Lalo", "Perez", "lalo@correo.com"),
            new User("Juanita", "Roe", "juana@correo.com"),
            new User("Daniel", "Doe"));
    }

    /* 
    Otra opcion usando Map en vez de Model, al ser un parametro de un
    controller se injecta en el destino del return

    @GetMapping("/details") // Mapea el metodo como componente(beans en el contenedor de spring) pero con el requestmethod en GET
    public String details(Map<String, Object> model){ // Objeto para pasar info a la vista
        model.put("title", "Hola Mundo Spring Boot");
        model.put("name", "Daniel");
        model.put("lastname", "Hernandez");

        return "details"; // Retorna una vista/template
    }*/

    /*
     * El controller retorna la vista y no el string porq es un controller
     * y estos no retornan nunca una objeto, solo una vista, para poder
     * pasar valores se usa el parametro inyectado como Model, Map...
     * Si se quiere retornar un objeto, el controller debe de ser 
     * RestController, y si debe de ser Controller, el endpoint debe
     * de ser anotado con ResponseBody para indicar que el retorno sea
     * un objeto hacia en cuerpo de response
     */
}
