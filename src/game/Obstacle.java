package game;

import environment.Board;
import environment.BoardPosition;
import environment.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Obstacle extends GameElement {
    private static final int NUM_MOVES = 3;
    private static final int OBSTACLE_MOVE_INTERVAL = 400;
    private int remainingMoves = NUM_MOVES;
    private GameElement gameElement=null;
    private Board board;
    public BoardPosition position;
    

    public GameElement getGameElement() {
		return gameElement;
	}
    public Obstacle(Board board) {
        super();
        this.board = board;
        
        
       
    }

    public void initializePosition() throws InterruptedException {
    	BoardPosition old=position; //Guarda a posicao antiga dos 3 obstaculos que vao mexer
        board.addGameElement(this);  //Adiciona os 3 obstaculos em um lugar random
        board.getCell(old).release2();
        board.getCell(old).removeObstacle(); //Remove a posicao antiga dos 3 obstaculos
   
         
        

        System.out.println("Este e o x = " + position.x + "//// " + " Este e o y= " + position.y);
        System.out.println("Existem " + board.getSizeObstacles() + " obstaculos no jogo");
        board.setChanged();
    }

    public int getRemainingMoves() {
        return remainingMoves;
    }

    public void setRemainingMoves(int remainingMoves) {
        this.remainingMoves = remainingMoves;
    }

    public int getObstacleMoveInterval() {
        return OBSTACLE_MOVE_INTERVAL;
    }

    public BoardPosition getPosition() {
        return position;
    }

 


}
