package com.nikolay.tictactoe.controller;

import com.nikolay.tictactoe.model.Player;
import com.nikolay.tictactoe.model.doto.PlayerDoto;
import com.nikolay.tictactoe.service.PlayerService;
import com.nikolay.tictactoe.service.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    PlayerService playerService;


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Player createAccount(@RequestBody PlayerDoto newPlayerDTO) {
        Player newPlayer = playerService.createNewPlayer(newPlayerDTO);
        return newPlayer;
    }

    @RequestMapping(value = "/players", method = RequestMethod.GET)
    public void getPlayers() {
        playerService.listPlayers();}

    @RequestMapping(value = "/logged", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getLoggedPlayer(@RequestParam("useranme") String username) {
        System.out.println("request");
        return new Response(playerService.getLoggedUser(), Response.Status.CREATED );
    }
}


