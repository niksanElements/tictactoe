package com.nikolay.tictactoe.model.doto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import com.nikolay.tictactoe.model.enums.GameStatus;
import com.nikolay.tictactoe.model.enums.Piece;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * The file is used for communication.
 */
public class MoveDoto {

    private int boardColumn;
    private int boardRow;
    private Date created;
    private String userName;
    private GameStatus gameStatus;
    private Piece playerPieceCode;
}
