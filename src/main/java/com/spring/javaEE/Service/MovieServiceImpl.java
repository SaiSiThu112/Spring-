package com.spring.javaEE.Service;

import java.util.ArrayList;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.spring.javaEE.dto.MovieDto;
import com.spring.javaEE.model.Movie;
import com.spring.javaEE.repository.MovieJpaRepository;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	MovieJpaRepository movieJpaRepository;
	
	@Autowired
	ModelMapper mapper;

	@Override
	public List<MovieDto> getAllMovie() {
		// TODO Auto-generated method stub
		List<MovieDto> movieDto = new ArrayList<>();		
		Iterable<Movie> movies = this.movieJpaRepository.findAll();
		for (Movie movie : movies ) {
			
			MovieDto dto = mapper.map(movie, MovieDto.class);
			movieDto.add(dto);
		}
			
		return movieDto;		
	}	
	
	
	@Override
	public MovieDto getMovieById(Long id) {
		// TODO Auto-generated method stub
		Movie movie = this.movieJpaRepository.findById(id).get();
		MovieDto dto = mapper.map(movie, MovieDto.class);
		
		return dto;
	}
		
	@Override
	public MovieDto saveMovie(MovieDto dto) {
		// TODO Auto-generated method stub
		Movie movie = mapper.map(dto, Movie.class);
		movie = this.movieJpaRepository.save(movie);
		
		return mapper.map(movie, MovieDto.class);
	}

	@Override
	public void deleteMovieById(Long movieId) {
		// TODO Auto-generated method stub
		this.movieJpaRepository.deleteById(movieId);
	}

}
