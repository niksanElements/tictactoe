package com.nikolay.tictactoe.model.doto;

import com.nikolay.tictactoe.model.enums.GameType;
import com.nikolay.tictactoe.model.enums.Piece;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * The file is used for communication.
 */
public class GameDoto {

    private int id;
    private GameType gameType;
    private Piece piece;
}


