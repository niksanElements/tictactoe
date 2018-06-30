package com.nikolay.tictactoe.model.doto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * The file is used for communication.
 */
public class CreateMoveDoto {
    @NotNull
    int boardRow;
    @NotNull
    int boardColumn;
}
