package com.nikolay.tictactoe.repository;

import java.util.List;

import com.nikolay.tictactoe.model.Game;
import com.nikolay.tictactoe.model.Move;
import com.nikolay.tictactoe.model.Player;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
/**
 * database repository
 * 
 * Contains all Moves.
 */
public interface MoveRepository extends CrudRepository<Move,Long>{
    List<Move> findByGame(Game game);
    List<Move> findByGameAndPlayer(Game game, Player player);
    int countByGameAndPlayer(Game game, Player player);
}