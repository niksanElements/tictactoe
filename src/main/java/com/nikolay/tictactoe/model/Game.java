package com.nikolay.tictactoe.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.nikolay.tictactoe.model.enums.GameStatus;
import com.nikolay.tictactoe.model.enums.GameType;
import com.nikolay.tictactoe.model.enums.Piece;

import org.hibernate.annotations.Check;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Check(constraints = "first_player_piece = 'O' or first_player_piece = 'X' " +
        "and game_type = 'COMPUTER' or game_type = 'COMPETITION' " +
        "and game_status = 'PROGRESS' or game_status = 'F_PLAYER_WON' or game_status = 'S_PLAYER_WON'" +
        "or game_status = 'TIE' or game_status = 'WAITS_FOR_PLAYER' ")
/**
 * Game entity
 *  id | firstPlayer | secondPlayer | gameType | gameStatus | date   
 */
public class Game{

    public Game(){}

    public Game(Player firstPlayer, Player secondPlayer,
            Piece firstPlayerPiece, GameType gameType, GameStatus gameStatus,
            Date date){
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.firstPlayerPiece = firstPlayerPiece;
        this.gameStatus = gameStatus;
        this.gameType = gameType;
        this.date = date;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "frist_player_id",nullable = false)
    private Player firstPlayer;

    @ManyToOne
    @JoinColumn(name = "second_player_id",nullable = true)
    private Player secondPlayer;

    @Enumerated(EnumType.STRING)
    private Piece firstPlayerPiece;

    @Enumerated(EnumType.STRING)
    private GameType gameType;

    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;

    @Column(name = "date", nullable = false)
    private Date date;
}