package com.spring.javaEE.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.javaEE.model.Movie;
import com.spring.javaEE.repository.MovieRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	@Autowired
	MovieRepository movieRepository;
	
	@GetMapping("/")
	public String Home() {
		System.out.println("HomeController");
		List<Movie> movies = this.movieRepository.findAll(); 
		log.info("Movie "+movies.get(0));
		log.info("No of movie "+movies.size());
		this.movieRepository.findAll();
		Optional<Movie> movieId = this.movieRepository.findById(1L);
		log.info("Movie id "+movieId);
		return "home";
	}
	
}
