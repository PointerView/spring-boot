package com.danihc.curso.springboot.di.factura.springboot_difactura.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
@RequestScope
//@JsonIgnoreProperties({"targetSource", "advisors"})
public class Invoice {

    @Autowired
    private Client client;
    
    @Value("${invoice.description.office}")
    private String description;

    @Autowired
    @Qualifier("default")
    private List<Item> items;

    public Invoice(){ // Se ejecuta primero el contructor antes que intanciar nada, incluso antes de dar valor a los atributos
        System.out.println("Creando el componente de la factura desde Invoice");
        System.out.println(client);
        System.out.println(items);

    }

    @PostConstruct // Se ejecuta tras completar el objeto, despues del contructor y de la instanciacion de los atributos
    public void init(){
        System.out.println("Creando el componente de la factura desde init");
        client.setNombre(client.getNombre().concat(" Pepe"));
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destruyendo el componente o bean invoice");
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Integer getTotal(){
        return items.stream()
        .mapToInt(e -> e.getImport())
        .sum();
    }
    /*
     * Muestra el total porq la serializacion de un JSON se realiza mendiante los metodos GET, no accede
     * a los atributos directamente, si se quiere mostrar un atributo que no tenga un getter se puede 
     * usar la anotacion de @JsonProperty sobre el atributo, y si no se quiere añadir algun atributo
     * con getter a la serializacion se puede añadir al metodo getter la anotacion @JsonIgnore
     */
}
