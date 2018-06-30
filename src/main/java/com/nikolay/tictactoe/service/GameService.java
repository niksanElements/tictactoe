package com.nikolay.tictactoe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.nikolay.tictactoe.model.Game;
import com.nikolay.tictactoe.model.Player;
import com.nikolay.tictactoe.model.doto.GameDoto;
import com.nikolay.tictactoe.model.enums.GameStatus;
import com.nikolay.tictactoe.model.enums.GameType;
import com.nikolay.tictactoe.repository.GameRepository;

@Service
@Transactional
/**
 * GameService is direct connection with the database.
 * It manages all database actions.
 */
public class GameService {

    private final GameRepository gameRepository;

    
    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game createNewGame(Player player, GameDoto gameDTO) {
        Game game = new Game();
        game.setFirstPlayer(player);
        game.setGameType(gameDTO.getGameType());
        game.setFirstPlayerPiece(gameDTO.getPiece());
        game.setGameStatus(gameDTO.getGameType() == GameType.COMPUTER ? GameStatus.PROGRESS :
                GameStatus.WAITS_FOR_PLAYER);

        game.setDate(new Date());
        gameRepository.save(game);

        return game;
    }


    public Game updateGameStatus(Game game, GameStatus gameStatus) {
        Game g = getGame(game.getId());
        g.setGameStatus(gameStatus);

        return g;
    }

    /**
     * The application has a future in which players can
     * join any game with just one player. This function
     * gates those games.
     * 
     * @param player
     */
    public List<Game> getGamesToJoin(Player player) {
        return gameRepository.findByGameTypeAndGameStatus(GameType.COMPETITION,
                GameStatus.WAITS_FOR_PLAYER).stream().filter(game -> game.getFirstPlayer() != player).collect(Collectors.toList());

    }

    /**
     *  The action for joining a game. 
     */
    public Game joinGame(Player player, GameDoto gameDTO) {
        Game game =  getGame((long) gameDTO.getId());
        game.setSecondPlayer(player);
        gameRepository.save(game);

        updateGameStatus(game, GameStatus.PROGRESS);

        return game;

    }
    
    public List<Game> getPlayerGames(Player player) {
        List<Game> result = gameRepository.findByFirstPlayerUsername(player.getUsername());
        result.addAll(gameRepository.findBySecondPlayerUsername(player.getUsername()));
        
        return result;
    }


    public Game getGame(Long id) {
        return gameRepository.findById(id).orElse(null);
    }
}
