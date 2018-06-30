package com.nikolay.tictactoe.repository;

import java.util.List;

import com.nikolay.tictactoe.model.Game;
import com.nikolay.tictactoe.model.enums.GameStatus;
import com.nikolay.tictactoe.model.enums.GameType;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
/**
 * database repository
 * 
 * Contains the games.
 */
public interface GameRepository extends CrudRepository<Game,Long>{
    List<Game> findByGameTypeAndGameStatus(GameType gameType, GameStatus gameStatus);
    List<Game> findByGameStatus(GameStatus gameStatus);
}
