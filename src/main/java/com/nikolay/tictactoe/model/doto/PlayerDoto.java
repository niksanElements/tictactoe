package com.nikolay.tictactoe.model.doto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;


@Getter
@Setter
/**
 * The file is used for communication.
 */
public class PlayerDoto {

    @NotNull
    private String userName;
    @NotNull
    private String password;
    @NotNull
    private String email;
}
