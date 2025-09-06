package com.danihc.curso.springboot.backend.backendproducts.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.danihc.curso.springboot.backend.backendproducts.entities.Product;


@RepositoryRestResource(path = "products")/* En vez de crear controller-service-repo, se crea solo el repo
y se anota con  RepositoryRestResource(path = "products") donde el path es como el RequestMapping general
del controller, en este caso si se hacen request por ejemplo de get al enpoint se hara un findAll, pero si
se indica una id como PathVariable, no como queryParam, se hara el findById, lo mismo para un post el cual
haria un save. Esto es util para crear un api restfull muy generica de modo rapido y evitando codigo*/
public interface ProductRepository extends CrudRepository<Product, Long> {

}
