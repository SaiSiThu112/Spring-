package com.spring.javaEE.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class Movie {
	
	private Long id;
	
	@NotNull
	@Size(min=5 , message="Name must be at least five characters long")
	private String name;
	
	@NotNull
	@Size(min=5 , message="Director Name must be at least five characters long")
	private String director;
	
	@NotNull
	@Range(min = 1878 , max = 2022 , message="Year must be between 1878-2022")
	private Long year;
	
	private Genres genre;
	
	private Date createAt = new Date();
	
	private Date updateAt = new Date();
}
