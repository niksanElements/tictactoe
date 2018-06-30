package com.nikolay.tictactoe.security;

import com.nikolay.tictactoe.model.Player;
import com.nikolay.tictactoe.repository.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{

    private final PlayerRepository playerRepository;
    
    @Autowired
    public UserDetailsServiceImpl(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        checkNotNull(username);

        if(isEmpty(username)){
            throw new UsernameNotFoundException("User can't be empty!");
        }

        Player player = playerRepository.findOneByUsername(username);
        if(player == null){
            throw new UsernameNotFoundException("A user with "+username+"doesn't exists!");
        }

        return new ContextUser(player);
    }
}