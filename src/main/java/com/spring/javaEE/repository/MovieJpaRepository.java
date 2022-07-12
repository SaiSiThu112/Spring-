package com.spring.javaEE.repository;

import org.springframework.data.repository.CrudRepository;

import com.spring.javaEE.model.Movie;

public interface MovieJpaRepository extends CrudRepository< Movie , Long>{

}
