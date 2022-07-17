package com.spring.javaEE.controller;

import java.util.List;


import javax.validation.Valid

;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.javaEE.Service.MovieService;
import com.spring.javaEE.dto.MovieDto;
import com.spring.javaEE.model.Movie;
import com.spring.javaEE.model.ShoppingCart;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/movie")
@SessionAttributes("cart")

public class MovieController {
	
	/*
	@Autowired
	MovieJpaRepository movieJpaRepository;
	//MovieRepository movieRepository;
	*/
	
	@Autowired
	MovieService movieService;
	
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
		log.info("New movie controller");
		//List<String> genres = this.getMovieGenres();
		Movie movie = new Movie();
		//movie.setName("Titanic");
		model.addAttribute("movie", movie);
		//model.addAttribute("genres",genres);
		model.addAttribute("message","Welcome to Movie");
		
		return "movie";
	}
	
	@PostMapping
	public String createOrUpdateMovie(@Valid  @ModelAttribute("movie") MovieDto movie , 
							   BindingResult errors
							  /*@SessionAttribute("cart") ShoppingCart cart */) {
		
		if(errors.hasErrors()) {
			log.error("Got error in creating movie "+errors.getErrorCount());
			
			/*
			for(FieldError fe : errors.getFieldErrors()) {
				log.info(fe.getDefaultMessage());
			}
			*/
			//model.addAttribute("movie",movie);
			
			return "movie";
		}
		else {
			this.movieService.saveMovie(movie);
			//cart.addMovie(movie);
			log.info("Post Controller"+ " Name "+movie.getName()+" Director"+movie.getDirector());
			//log.info("No of movie in shoppingcart " + cart.getMovies().size());
			
			return"redirect:movie/movie-list";
		}
		//return "redirect:/";
	}
	
	@GetMapping("update/{movieId}")
	public String getMovieById(@PathVariable Long movieId , Model model) {
		MovieDto movie= this.movieService.getMovieById(movieId);
		model.addAttribute("movie",movie);
		
		return "movie";
	}
	
	@GetMapping("/new")
	public String emptyMovie(Model model) {
		MovieDto movieDto = new MovieDto();
		model.addAttribute("movie",movieDto);
		
		return "movie";
	}
	
	@GetMapping("/movie-list")
	public String movieList(Model model) {
		Iterable<MovieDto> movies = this.movieService.getAllMovie();
		model.addAttribute("movieList",movies);
		
		return "movie-list";
	}	
	
	@GetMapping("delete/{movieId}")
	public String deleteMovie(@PathVariable Long movieId) {
		log.info("Delete movie id "+movieId);
		
		this.movieService.deleteMovieById(movieId);
		
		return "redirect:/movie/movie-list";
	}
	
	@GetMapping("search")
	public String findByName(@RequestParam String name , Model model) {
		log.info("Search movie name "+name);
		
		List<MovieDto> movies = this.movieService.getMovieByName(name);
		model.addAttribute("movieList",movies);
		
		return "movie-list";
	}
	
	@GetMapping("search-like")
	public String findByNameLikeOne(@RequestParam String name , Model model) {
		log.info("Search movie name like "+name);
		
		List<MovieDto> movies = this.movieService.getMovieByNameLike("%"+name+"%");
		model.addAttribute("movieList",movies);
		
		return "movie-list";
	}
	
	@GetMapping("search-like-contain")
	public String findByNameLike(@RequestParam String name, Model model) {
		log.info("Search movie name contain "+name);
		
		List<MovieDto> movies = this.movieService.getMovieByNameContain(name);
		model.addAttribute("movieList",movies);
		
		return "movie-list";
	}
	
	@GetMapping("search-year-greater-than")
	public String findByYearGreaterThan(@RequestParam Long year , Model model) {
		log.info("Search year greaterthan "+year);
		
		List<MovieDto> movies = this.movieService.getMovieByYearGreaterThan(year);
		model.addAttribute("movieList",movies);
		
		return "movie-list";
	}
	
	@GetMapping("/movie-list-by-page")
	public String movieListByPage(@RequestParam int pageNo, @RequestParam int pageSize, Model model) {
		
		List<MovieDto> movies = this.movieService.getAllMovieByPage(pageNo, pageSize);
		model.addAttribute("movieList",movies);
		
		return "movie-list";
	}
	
}
