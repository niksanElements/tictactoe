package com.nikolay.tictactoe.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
/**
 * Move entity
 *  id | game | row | column | player | date
 */
public class Move{

    public Move(){}

    public Move(Game game, int row, int column, Player player, Date date){
        this.game = game;
        this.row = row;
        this.column = column;
        this.player = player;
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id",nullable = false)
    private Game game;

    @Column(name = "r",nullable = false)
    private int row;

    @Column(name = "c",nullable = false)
    private int column;

    @ManyToOne
    @JoinColumn(name = "player_id",nullable = true)
    private Player player;

    @Column(name = "date",nullable = false)
    private Date date;
}