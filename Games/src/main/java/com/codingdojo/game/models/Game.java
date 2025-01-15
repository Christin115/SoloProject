package com.codingdojo.game.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "game")
public class Game {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	  
	  @NotEmpty(message = " Name is required!")
	  @Size(min = 2, message = "Name must be at least 2 characters")
	  private String name;
	  
	  @NotEmpty(message = " Genre is required!")
	  @Size(min = 2, message = "Genre must be at least 2 characters")
	  private String genre;

	  @NotEmpty(message = " Info is required!")
	  @Size(min = 2, max = 50,  message = "Info must be between 2 and 50 characters")
	  private String info;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "user_id")
	  private User creator;

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Game() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	

	  
	
}
