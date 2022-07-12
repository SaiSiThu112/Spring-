package com.spring.javaEE.model;


import java.io.Serializable;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Movie implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String director;

	private Long year;
	
	@Enumerated(EnumType.STRING)
	@Column(name="genre")
	private Genres genre;
	
	@Column(name="createAt")
	private Date createAt = new Date();
	
	@Column(name="updateAt")
	private Date updateAt = new Date();
}
