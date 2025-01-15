package com.codingdojo.game.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.game.models.Game;
import com.codingdojo.game.repositories.GameRepository;

@Service
public class GameService {
	@Autowired
	GameRepository tRepo;
	
	public List<Game> allGames(){
		return tRepo.findAll();
	} 
	
	public Game createGame(Game b){
	    return tRepo.save(b);
	  }
	
	public Game findGame(Long id){
	    Optional<Game> optionalGame =tRepo.findById(id);
	    if(optionalGame.isPresent()){
	      return optionalGame.get();
	    }
	    else{
	      return null;
	    }
	    
	}
	
	public void deleteGame(Long id) {
		tRepo.deleteById(id);
	}
	
	public Game updateGame(Game game) {
		return tRepo.save(game);
	}
}
