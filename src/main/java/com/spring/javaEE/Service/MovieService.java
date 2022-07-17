package com.spring.javaEE.Service;

import java.util.List;
import com.spring.javaEE.dto.MovieDto;

public interface MovieService {
	
	List<MovieDto> getAllMovieByPage(int pageNo , int size);
	List<MovieDto> getAllMovie();
	MovieDto getMovieById(Long id);
	List<MovieDto> getMovieByName(String name);
	List<MovieDto> getMovieByNameLike(String name);
	List<MovieDto> getMovieByNameContain(String name);
	List<MovieDto> getMovieByYearGreaterThan(Long year);
	MovieDto saveMovie(MovieDto movie);
	void deleteMovieById(Long movieId);
}
