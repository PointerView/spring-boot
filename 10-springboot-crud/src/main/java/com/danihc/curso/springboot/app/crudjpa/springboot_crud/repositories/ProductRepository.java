package com.danihc.curso.springboot.app.crudjpa.springboot_crud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.danihc.curso.springboot.app.crudjpa.springboot_crud.models.Product;



public interface ProductRepository extends CrudRepository<Product, Long>{

    boolean existsBySku(String sku);
}
