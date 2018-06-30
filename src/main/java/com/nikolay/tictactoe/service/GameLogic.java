package com.nikolay.tictactoe.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.nikolay.tictactoe.model.Game;
import com.nikolay.tictactoe.model.Position;

import static java.util.Arrays.asList;

import java.util.ArrayList;

/**
 * Game Logic
 * 
 * Tictactoe application has a computer generated opponent but for now it's
 * the logic is very simple(random turn from all possible).
 * 
 * TODO: computer generated opponent intellisase 
 */
public class GameLogic {

    /**
     * checks whether the positions win
     * 
     * @param positions
     */
    public static boolean isWinner(List<Position> positions){
        List<List<Position>> list = getWinnerPos();

        System.out.println("-----------------------");
        for(Position pos: positions){
            System.out.println(pos.getRow()+" "+pos.getColumn());
        }

        for(List<Position> wPos: list){  
            boolean fFlag = false;
            boolean sFlag = false;
            boolean tFlag = false;

            for(Position cPos : positions){
                if(wPos.get(0).getRow() == cPos.getRow()&&
                wPos.get(0).getColumn() == cPos.getColumn()){
                    fFlag = true;
                }

                if(wPos.get(1).getRow() == cPos.getRow()&&
                wPos.get(1).getColumn() == cPos.getColumn()){
                    sFlag = true;
                }

                if(wPos.get(2).getRow() == cPos.getRow()&&
                wPos.get(2).getColumn() == cPos.getColumn()){
                    tFlag = true;
                }
            }

            if(fFlag && sFlag && tFlag){
                return true;
            }
            
        }

        return false;
    }

    /**
     * @return all winning positions
     */
    public static List<List<Position>> getWinnerPos(){
        List<List<Position>> pos = new ArrayList<>();

        pos.add(asList(new Position(1,1),new Position(1,2),new Position(1,3)));
        pos.add(asList(new Position(1,1),new Position(2,1),new Position(3,1)));
        pos.add(asList(new Position(1,1),new Position(2,2),new Position(3,3)));

        pos.add(asList(new Position(1,2),new Position(2,2),new Position(3,2)));

        pos.add(asList(new Position(1,3),new Position(2,3),new Position(3,3)));
        pos.add(asList(new Position(1,3),new Position(2,2),new Position(3,1)));

        pos.add(asList(new Position(2,1),new Position(2,2),new Position(2,3)));
        
        pos.add(asList(new Position(3,1),new Position(3,2),new Position(3,3)));


        return pos;
    }

    /**
     * checks the player turn
     * 
     * {@link Deprecated} 
     */
    public static boolean isPlayerTurn(int numberOfFirstPlayerMovesInGame, int numberOfSecondPlayerMovesInGame) {
        System.out.println(numberOfFirstPlayerMovesInGame);
        System.out.println(numberOfSecondPlayerMovesInGame);
        
        return numberOfFirstPlayerMovesInGame == 0;
    }

    public static boolean isBoardFull(List<Position> takenPositions) {
        return takenPositions.size() == 9;
    }

    public static List<Position> getAllPositions(){
        List<Position> pos = new ArrayList<>();

        for(int i = 1;i <= 3;i++){
            for(int j = 1;j <= 3;j++){
                pos.add(new Position(i,j));
            }
        }

        return pos;
    }

    /**
     * The function gates all open possible positions.
     */
    public  static List<Position> getOpenPositions(List<Position> takenPositions) {

        List<Position> positions = new ArrayList<>();

        for(Position pos: getAllPositions()){
            boolean isUsed = false;

            for(Position taken: takenPositions){
                if(taken.getRow() == pos.getRow() &&
                    taken.getColumn() == pos.getColumn()){
                        isUsed = true;
                        break;
                }
            }

            if(!isUsed){
                positions.add(pos);
            }
        }

        return positions;
    }

    /**
     * auto generated move
     */
    public static Position next(List<Position> takenPositions) {
        Random rand = new Random(System.currentTimeMillis());
        List<Position> list = getOpenPositions(takenPositions);
        int index = rand.nextInt(list.size());
        return list.get(index);
    }
}