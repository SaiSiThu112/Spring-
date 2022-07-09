package com.spring.javaEE.controller;

import java.util.ArrayList
;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.javaEE.model.Movie;
import com.spring.javaEE.model.ShoppingCart;
import com.spring.javaEE.repository.MovieRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/movie")
@SessionAttributes("cart")

public class MovieController {
	
	@Autowired
	MovieRepository movieRepository;
	
	//Create Session Attribute
	@ModelAttribute("cart")
	public ShoppingCart getCart() {
		log.info("create new shoppingcart");
		return new ShoppingCart();
	}
	
	/*
	 * Just for example, 
	 * In real word scenarios , we use service -> dao
	 * 
	List<String> getMovieGenres(){
		List<String> genres = new ArrayList<>();
		genres.add("Action");
		genres.add("Anime");
		genres.add("Comedy");
		
		return genres;
	}
	*/
	@GetMapping
	public String getMovie(Model model) {
		//List<String> genres = this.getMovieGenres();
		Movie movie = new Movie();
		//movie.setName("Titanic");
		model.addAttribute("movie", movie);
		//model.addAttribute("genres",genres);
		model.addAttribute("message","Welcome to Movie");
		return "movie";
	}
	
	@PostMapping
	public String createMovie(@Valid Movie movie , Errors errors ,
							  @SessionAttribute("cart") ShoppingCart cart) {
		
		if(errors.hasErrors()) {
			log.error("Error in creating movie");
			return "movie";
		}
		else {
			this.movieRepository.save(movie);
			cart.addMovie(movie);
			log.info("Post Controller"+ " Name "+movie.getName()+" Director"+movie.getDirector());
			log.info("No of movie in shoppingcart " + cart.getMovies().size());
			return"redirect:movie/movie-list";
		}
		//return "redirect:/";
	}
	@GetMapping("/movie-list")
	public String movieList(Model model) {
		List<Movie> movies = this.movieRepository.findAll();
		model.addAttribute("movieList",movies);
		return "movie-list";
	}
	
}
