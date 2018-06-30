package com.nikolay.tictactoe.repository;

import com.nikolay.tictactoe.model.Player;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 *  Repository interface. Used by spring for connection
 *  to the database.
 */
@Repository
public interface PlayerRepository extends CrudRepository<Player,Long>{
    Player findOneByUsername(String username);
}