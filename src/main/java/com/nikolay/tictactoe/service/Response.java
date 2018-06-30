package com.nikolay.tictactoe.service;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
/** response object */
public class Response<T> {
    private T object;
    private Status status;

    public Response(){

    }

    public Response(T object, Status status) {
        this.object = object;
        this.status = status;
    }

    public enum Status {
        CREATED,
        NOT_CREATED,
        RECEIVED,
        NOT_RECEIVED,
        LOGGED;
    }

}
