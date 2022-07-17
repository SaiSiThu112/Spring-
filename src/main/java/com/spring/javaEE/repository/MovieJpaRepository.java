package com.spring.javaEE.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.spring.javaEE.model.Movie;

public interface MovieJpaRepository extends CrudRepository< Movie , Long>{
	
	List<Movie> findAll(Pageable pageable);
	List<Movie> findByName(String name);
	List<Movie> findByNameLike(String name);
	List<Movie> findByNameContaining(String name);
	List<Movie> findByYearGreaterThan(Long year);
	
	//NativeSQL
	@Query(value="SELECT * FROM movie ORDER BY id" , nativeQuery =true) 
	List<Movie> getAllMovie();
	
	//JPQL
	@Query(value="SELECT m FROM Movie m")
	List<Movie> getAllMovieByJPQL();
}
