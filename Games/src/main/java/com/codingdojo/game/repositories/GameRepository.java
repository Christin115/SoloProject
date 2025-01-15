package com.codingdojo.game.repositories;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.game.models.Game;

@Repository
public interface GameRepository extends ListCrudRepository<Game, Long> {

}
