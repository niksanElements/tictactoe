package com.nikolay.tictactoe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
/**
 * board position
 * 
 * !!!!!! It's not used in the database!!!!!!
 */
public class Position{
    int row, column;
}