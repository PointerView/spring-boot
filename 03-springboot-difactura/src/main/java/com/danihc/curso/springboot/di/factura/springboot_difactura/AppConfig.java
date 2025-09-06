package com.danihc.curso.springboot.di.factura.springboot_difactura;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.danihc.curso.springboot.di.factura.springboot_difactura.models.Item;
import com.danihc.curso.springboot.di.factura.springboot_difactura.models.Product;

@Configuration
@PropertySource(value = "classpath:data.properties", encoding = "UTF-8")
public class AppConfig {


    @Bean
    public List<Item> itemsInvoice(){

        return Arrays.asList(
            new Item(new Product("Camara Sony", 800), 2),
            new Item(new Product("Bicicleta Bianchi 26", 1200), 4));
    }

    @Bean("default")
    //@Primary
    public List<Item> itemsInvoiceOficina(){

        return Arrays.asList(
            new Item(new Product("Monitor Asus 24", 700), 4),
            new Item(new Product("Notebook Razer", 2400), 6),
            new Item(new Product("Impresora HP", 800), 1),
            new Item(new Product("Escritorio Oficina", 900), 4));
    }
}
