package com.spring.javaEE.repository;

import java.util.List;
import java.util.Optional;

import com.spring.javaEE.model.Movie;

public interface MovieRepository {
	
	List<Movie> findAll();
	Optional<Movie> findById(Long id);
	Movie save(Movie movie);
}
