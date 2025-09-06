package com.danihc.springboot.di.app.springboot_di.repositories;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.danihc.springboot.di.app.springboot_di.models.Product;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ProductRepositoryJson implements ProductRepository {

    private List<Product> list;

    public ProductRepositoryJson() {

        Resource resource = new ClassPathResource("json/product.json"); // Obtenemos el recurso, este debe estar en resources donde es la ruta inicial a indicar.
        ObjectMapper objectMapper = new ObjectMapper(); // Objeto para serializar el JSON
        try {
            list = Arrays.asList(objectMapper.readValue(resource.getFile(), Product[].class));// se lee el fichero y se indica el tipo de retorno
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public List<Product> findAll() {
        return list;
    }

    @Override
    public Product findById(Long id) {
        return list.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow();
    }

}
