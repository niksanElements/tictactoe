package com.nikolay.tictactoe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
/**
 * player entity
 *  id | username | email | password | isLogged
 */
public class Player {

    public Player(){}

    public Player(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
        this.isLogged = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "is_loggen",nullable = false)
    private boolean isLogged;
}


