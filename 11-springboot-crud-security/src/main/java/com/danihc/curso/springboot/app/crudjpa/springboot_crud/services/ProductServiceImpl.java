package com.danihc.curso.springboot.app.crudjpa.springboot_crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danihc.curso.springboot.app.crudjpa.springboot_crud.models.Product;
import com.danihc.curso.springboot.app.crudjpa.springboot_crud.repositories.ProductRepository;



@Service
public class ProductServiceImpl implements ProductService{


    @Autowired
    ProductRepository repository;

    @Override
    @Transactional(readOnly=true)
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    @Transactional
    public Optional<Product> update(Long id, Product product) {

        Optional<Product> productDb = repository.findById(id);
        
        if(productDb.isPresent()){
            Product p = productDb.orElseThrow();
            p.setName(product.getName());
            p.setDescription(product.getDescription());
            p.setPrice(product.getPrice());
            p.setSku(product.getSku());
            return Optional.of(repository.save(p));
        }

        return productDb;
    }

    @Override
    @Transactional
    public Optional<Product> delete(Long id) {
        Optional<Product> productDb = repository.findById(id);
        productDb.ifPresent(repository::delete);
        return productDb;
    }

    @Override
    @Transactional(readOnly=true)
    public boolean existsBySku(String sku) {
        return repository.existsBySku(sku);
    }
}
