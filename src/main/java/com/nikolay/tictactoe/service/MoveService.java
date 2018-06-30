package com.nikolay.tictactoe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import com.nikolay.tictactoe.model.Game;
import com.nikolay.tictactoe.model.Move;
import com.nikolay.tictactoe.model.Player;
import com.nikolay.tictactoe.model.Position;
import com.nikolay.tictactoe.model.doto.CreateMoveDoto;
import com.nikolay.tictactoe.model.doto.MoveDoto;
import com.nikolay.tictactoe.model.enums.GameStatus;
import com.nikolay.tictactoe.model.enums.GameType;
import com.nikolay.tictactoe.model.enums.Piece;
import com.nikolay.tictactoe.repository.MoveRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
/**
 * The service is connected with move table in the
 * database. It has direct connection with the database
 * plus additional functionality:
 * - check current game status
 * - is player turn
 * 
 * Those functionalities are not directly connected to the table
 * but they use moveRepository that is way for simplicity and faster code
 * execution.
 */
public class MoveService {

    private final MoveRepository moveRepository;


    @Autowired
    public MoveService(MoveRepository moveRepository) {
        this.moveRepository = moveRepository;
    }

    public Move createMove(Game game, Player player, CreateMoveDoto createMoveDTO) {
        Move move = new Move();
        move.setColumn(createMoveDTO.getBoardColumn());
        move.setRow(createMoveDTO.getBoardRow());
        move.setDate(new Date());
        move.setPlayer(player);
        move.setGame(game);

        moveRepository.save(move);

        return move;
    }

    public Move autoCreateMove(Game game) {
        Move move = new Move();
        move.setColumn(GameLogic.next(getTakenMovePositionsInGame(game)).getColumn());
        move.setRow(GameLogic.next(getTakenMovePositionsInGame(game)).getRow());
        move.setDate(new Date());
        move.setPlayer(null);
        move.setGame(game);

        moveRepository.save(move);

        return move;
    }

    public GameStatus checkCurrentGameStatus(Game game) {
        if (GameLogic.isWinner(getPlayerMovePositionsInGame(game, game.getFirstPlayer()))) {
            return GameStatus.FIRST_PLAYER_WON;
        } else if (GameLogic.isWinner(getPlayerMovePositionsInGame(game, game.getSecondPlayer()))) {
            return GameStatus.SECOND_PLAYER_WON;
        } else if (GameLogic.isBoardFull(getTakenMovePositionsInGame(game))) {
            return GameStatus.TIE;
        } else if (game.getGameType() == GameType.COMPETITION && game.getSecondPlayer() == null ) {
            return GameStatus.WAITS_FOR_PLAYER;
        } else {
            return GameStatus.PROGRESS;
        }

    }


    public List<MoveDoto> getMovesInGame(Game game) {

        List<Move> movesInGame = moveRepository.findByGame(game);
        List<MoveDoto> moves = new ArrayList<>();
        Piece currentPiece = game.getFirstPlayerPiece();

        for(Move move :  movesInGame) {
            MoveDoto moveDTO = new MoveDoto();
            moveDTO.setBoardColumn(move.getColumn());
            moveDTO.setBoardRow(move.getRow());
            moveDTO.setCreated(move.getDate());
            moveDTO.setGameStatus(move.getGame().getGameStatus());
            moveDTO.setUserName(move.getPlayer() == null ? GameType.COMPUTER.toString() : move.getPlayer().getUsername() );
            moveDTO.setPlayerPieceCode(currentPiece);
            moves.add(moveDTO);

            currentPiece = currentPiece == Piece.X ? Piece.O : Piece.X;
        }

        return moves;
    }

    public List<Position> getTakenMovePositionsInGame(Game game) {
        return moveRepository.findByGame(game).stream()
                .map(move -> new Position(move.getRow(), move.getColumn()))
                .collect(Collectors.toList());
    }

    public List<Position> getPlayerMovePositionsInGame(Game game, Player player) {

        return moveRepository.findByGameAndPlayer(game, player).stream()
                .map(move -> new Position(move.getRow(), move.getColumn()))
                .collect(Collectors.toList());
    }

    public int getTheNumberOfPlayerMovesInGame(Game game, Player player) {
        return moveRepository.countByGameAndPlayer(game, player);
    }

    public long isPlayerTurn(Game game, Player firstPlayer, Player secondPlayer) {
        List<Move> moves = moveRepository.findByGame(game);
        int index = 0;

        if(moves.size() == 0){
            return firstPlayer.getId();
        }

        Date date = moves.get(0).getDate();


        for(int i = 0;i < moves.size();i++){
            Move move = moves.get(i);
            if(move.getDate().compareTo(date)> 0){
                date = move.getDate();
                index = i;
            }
        }

        Move move = moves.get(index);

        if(move.getPlayer().getId() == firstPlayer.getId()){
            return secondPlayer.getId();
        }

        return firstPlayer.getId();
        
    }




}
