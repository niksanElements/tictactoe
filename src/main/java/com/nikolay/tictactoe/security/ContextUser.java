package com.nikolay.tictactoe.security;

import com.google.common.collect.ImmutableSet;
import com.nikolay.tictactoe.model.Player;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class ContextUser extends User{
    private final Player player;

    public ContextUser(Player player){
        super(player.getUsername(), // username
            player.getPassword(),   // password
            true,                   // user enabled
            true,                   // account not expired
            true,                   // credential have not expired
            true,                   // account not locked
            ImmutableSet.of(new SimpleGrantedAuthority("create"))); // authority
        
            this.player = player;
    }

    public Player getPlayer(){
        return player;
    }
}