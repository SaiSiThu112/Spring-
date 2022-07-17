package com.spring.javaEE.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.javaEE.Service.MovieService;
import com.spring.javaEE.dto.MovieDto;

@RequestMapping("/api/movies")
@RestController
public class RestMovieConroller {
	
	@Autowired
	MovieService movieService;
	
	@GetMapping
	List<MovieDto> getAllMovie() {
		return movieService.getAllMovie();
	}
	
	@GetMapping("/{id}")
	ResponseEntity<MovieDto> getMovieById(@PathVariable Long id){
		MovieDto movie = this.movieService.getMovieById(id);
		if(movie.getId()!=null) {
			return ResponseEntity.ok().body(movie);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(movie);
		}
		
	}
	
	private ResponseEntity<Object> saveOrUpdateMovie(MovieDto movie, BindingResult result) {
		
		if(!result.hasErrors()) {
			MovieDto movieResult = this.movieService.saveMovie(movie);
			return ResponseEntity.ok().body(movieResult);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
		}
	}

	@PostMapping
	ResponseEntity<Object> saveMovie(@Valid @RequestBody MovieDto movie , BindingResult result){
		return saveOrUpdateMovie(movie, result);
		
	}

	@PutMapping("/{id}")
	ResponseEntity<Object> updateMovie(@PathVariable Long id ,@Valid @RequestBody MovieDto movie , BindingResult result){
		return saveOrUpdateMovie(movie , result);
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<MovieDto> deleteMovie(@PathVariable Long id){
		MovieDto movie = this.movieService.getMovieById(id);
		if(movie.getId()!=null) {
			this.movieService.deleteMovieById(movie.getId());
			return ResponseEntity.ok().body(movie);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(movie);
		}
	}
				
}
