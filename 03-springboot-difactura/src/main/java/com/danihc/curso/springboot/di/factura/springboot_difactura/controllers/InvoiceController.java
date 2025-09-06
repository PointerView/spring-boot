package com.danihc.curso.springboot.di.factura.springboot_difactura.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danihc.curso.springboot.di.factura.springboot_difactura.models.Client;
import com.danihc.curso.springboot.di.factura.springboot_difactura.models.Invoice;


@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private Invoice invoice;

    @GetMapping("/show")
    public Invoice show() {
        Invoice i = new Invoice();
        Client c = new Client();

        c.setNombre(invoice.getClient().getNombre());
        c.setLastname(invoice.getClient().getLastname());
        i.setClient(c);
        i.setDescription(invoice.getDescription());
        i.setItems(invoice.getItems());

        return i;
        /*
         * Aqui en vez de retornar el objeto creado mediante un proxi, creamos un nuevo objeto con los
         * mismos valores y lo retornamos
         */
    }
    
}
