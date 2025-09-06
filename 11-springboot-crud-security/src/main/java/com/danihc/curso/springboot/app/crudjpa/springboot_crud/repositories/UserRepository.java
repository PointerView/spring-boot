package com.danihc.curso.springboot.app.crudjpa.springboot_crud.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.danihc.curso.springboot.app.crudjpa.springboot_crud.models.User;

public interface UserRepository extends CrudRepository<User, Long>{

    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
