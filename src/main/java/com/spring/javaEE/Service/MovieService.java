package com.spring.javaEE.Service;

import java.util.List;
import com.spring.javaEE.dto.MovieDto;

public interface MovieService {
	
	List<MovieDto> getAllMovie();
	MovieDto getMovieById(Long id);
	MovieDto saveMovie(MovieDto movie);
	void deleteMovieById(Long movieId);
}
