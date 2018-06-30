package com.nikolay.tictactoe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.nikolay.tictactoe.model.Player;
import com.nikolay.tictactoe.model.doto.PlayerDoto;
import com.nikolay.tictactoe.repository.PlayerRepository;
import com.nikolay.tictactoe.security.ContextUser;


@Service
@Transactional
/**
 * Player connection to the database.
 */
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player createNewPlayer(PlayerDoto playerDTO) {
        Player newPlayer = new Player();
        newPlayer.setUsername(playerDTO.getUserName());
        newPlayer.setPassword(passwordEncoder.encode(playerDTO.getPassword()));
        newPlayer.setEmail(playerDTO.getEmail());
        playerRepository.save(newPlayer);
        return newPlayer;
    }

    public Player getLoggedUser() {
        ContextUser principal = (ContextUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return playerRepository.findOneByUsername(principal.getPlayer().getUsername());
    }

    public List<Player> listPlayers() {
        List<Player> players = (List<Player>) playerRepository.findAll();
        return players;
    }

    public boolean loginPlayer(String username, String password){
        Player p = playerRepository.findOneByUsername(username);
        
        if(p == null){
            return false;
        }

        if(p.isLogged()){
            return false;
        }

        if(p.getPassword().equals(passwordEncoder.encode(username))){
            return false;
        }

        return true;
    }

}

