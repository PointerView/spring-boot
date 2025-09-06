package com.danihc.curso.springboot.jpa.springboot_jpa_relationship.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.danihc.curso.springboot.jpa.springboot_jpa_relationship.entities.Client;


@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

    @Query("select c from Client c join fetch c.addresses") // Busca los campos de la FK addresses en la misma consulta(Fecht Eagle) y asi no es Fetch Lazy
    Optional<Client> findOne(Long id);
}
