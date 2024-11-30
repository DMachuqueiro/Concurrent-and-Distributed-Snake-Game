package game;

import environment.Board;
import environment.Cell;
import environment.LocalBoard;
import gui.Main;
import gui.SnakeGui;

public class Goal extends GameElement  {
	private int value=1;
	private Board board;
	public static final int MAX_VALUE=10;
	public boolean max=false;
	
	
	public Goal( Board board2) {
		this.board = board2;
	}
	public Goal( Board board2, Cell cell) {
		this.value=cell.getGoal().getValue();
		this.board = board2;
	}
	
	public int getValue() {
		return value;
	}
	public void incrementValue() throws InterruptedException {
		//TODOv
		if(this.value < MAX_VALUE -1 ) {
			value=value+1;
			//System.out.println(value);
			
		}
		else {
		
		}
		
		
		
		
	}

	public int captureGoal() {
//		TODO
		return -1;
	}
	
	public Board getBoard() {
		return board;
	}
}
