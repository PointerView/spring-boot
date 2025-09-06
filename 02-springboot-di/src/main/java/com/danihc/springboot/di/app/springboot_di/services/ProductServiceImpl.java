package com.danihc.springboot.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.danihc.springboot.di.app.springboot_di.models.Product;
import com.danihc.springboot.di.app.springboot_di.repositories.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    @Qualifier("productJson") /* Especifica cual es el Bean que se desea inyectar mediante el nombre del componente/bean mismo empezando por minusculas,
    si se modifica el nombre del componente se debera de usar ese nombre personalizado*/ 
    private ProductRepository repository;
    /*
     * Se usa Autowired para injectar, tambien se puede aplicar la anotacion al setter del atributo lo cual seria equivalente
     * a inyectarlo directamente en el atributo, tambien se puede hacer mediante constructor pero en este caso hace el
     * @Autowired de manera implicita por lo que no seria necesario poner de forma explicita la anotacion @Autowired
     */

     @Value("${config.price.tax}")
     private Double tax;

    @Override
    public List<Product> findAll(){
        return repository.findAll().stream().map(p -> {
            Double priceTax = p.getPrice() * tax;
            //Product newProd = new Product(p.getId(), p.getName(), priceImp.longValue()); // Retornar nuevo producto que no moodifique el objeto principal mutable

            //Product newP = (Product) p.clone(); // Usando la interefaz clonable para duplicar el objeto
            //newP.setPrice(priceTax.longValue());
            
            p.setPrice(priceTax.longValue());

            return p;
        }).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id){
        return repository.findById(id);
    }
}
