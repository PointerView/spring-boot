package com.danihc.curso.springboot.di.factura.springboot_difactura.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/*
 * Singleton: Un solo objeto en una sola aplicacion de spring de las muchas que pueden haber en un servidor
 * ApplicationScope: Un objeto para todas las aplicaciones de spring, alcance mas aplio que el Singleton
 * SessionScope: Un objeto para toda la aplicacion pero asignada a un unico cliente.
 * RequestScope: Un objeto para un cliente para una sola request
 */

@Component
@RequestScope
//@JsonIgnoreProperties({"targetSource", "advisors"})
public class Client {

    @Value("${client.name}")
    private String nombre;
    @Value("${client.lastname}")
    private String lastname;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }   
}

/*
🧠 ¿Por qué Spring usa proxies en algunos scopes?
✅ Problema:
Supón que tienes un bean @Singleton que depende de un bean @RequestScoped. Si Spring inyectara directamente una instancia del bean request,
este moriría al terminar la petición, dejando el singleton con una referencia inválida.

✅ Solución:
Spring inyecta un proxy en lugar de la instancia real. Este proxy actúa como intermediario: cada vez que llamas a un método del
bean, Spring redirige la llamada a la instancia correcta según el scope actual.

El problema es que si se quiere retornar 
 */