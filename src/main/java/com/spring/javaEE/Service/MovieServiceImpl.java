package com.spring.javaEE.Service;

import java.util.ArrayList;


import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		//Iterable<Movie> movies = this.movieJpaRepository.findAll();
		Iterable<Movie> movies = this.movieJpaRepository.getAllMovie();
		//Iterable<Movie> movies = this.movieJpaRepository.getAllMovieByJPQL();
		return entityListToDto(movies);
	}	
		
	@Override
	public MovieDto getMovieById(Long id) {
		// TODO Auto-generated method stub
		Optional<Movie> movieOpt = this.movieJpaRepository.findById(id);
		if(movieOpt.isPresent()) {
			Movie movie=movieOpt.get();
			MovieDto dto = mapper.map(movie, MovieDto.class);
			
			return dto;
		}
		else {
			return new MovieDto();
		}
	}
	

	@Override
	public List<MovieDto> getMovieByName(String name) {
		// TODO Auto-generated method stub
		Iterable<Movie> movie = this.movieJpaRepository.findByName(name);
		return entityListToDto(movie);
		
	}
	
	@Override
	public List<MovieDto> getMovieByNameLike(String name) {
		// TODO Auto-generated method stub
		Iterable<Movie> movies = this.movieJpaRepository.findByNameLike(name);
		return entityListToDto(movies);
	}
	
	@Override
	public List<MovieDto> getMovieByNameContain(String name) {
		// TODO Auto-generated method stub
		Iterable<Movie> movies = this.movieJpaRepository.findByNameContaining(name);
		return entityListToDto(movies);
	}
	
	@Override
	public List<MovieDto> getMovieByYearGreaterThan(Long year) {
		// TODO Auto-generated method stub
		Iterable<Movie> movies = this.movieJpaRepository.findByYearGreaterThan(year);
		return entityListToDto(movies);
	}
	
	private List<MovieDto> entityListToDto(Iterable<Movie> movie) {
		List<MovieDto> movieDto = new ArrayList<>();		
		for(Movie movies : movie) {
			MovieDto dto = mapper.map(movies,MovieDto.class);
			movieDto.add(dto);
		}
		
		return movieDto;
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

	@Override
	public List<MovieDto> getAllMovieByPage(int pageNo, int size) {
		// TODO Auto-generated method stub
		Iterable<Movie> movies = this.movieJpaRepository.findAll(PageRequest.of(pageNo, size , Sort.by(Sort.Direction.ASC,"name")));
		return entityListToDto(movies);
	}

}
