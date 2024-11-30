package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import environment.LocalBoard;
import gui.SnakeGui;
import environment.Board;
import environment.BoardPosition;
import environment.Cell;
/** Base class for representing Snakes.
 * Will be extended by HumanSnake and AutomaticSnake.
 * Common methods will be defined here.
 * @author luismota
 *
 */
public abstract class Snake extends Thread implements Serializable{
	private static final int DELTA_SIZE = 10;
	protected LinkedList<Cell> cells = new LinkedList<Cell>();
	protected int size = 5;
	private int id;
	public Board board;
	public Boolean isFinished=false;
	
	public Snake(int id,Board board) {
		this.id = id;
		this.board=board;
		if(board==null) {
			throw new IllegalArgumentException("O board passado snake=null");
		}
	}

	public int getSize() {
		return size;
	}
	
	public void setSize(int x) {
		size=x;
	}
	public int getIdentification() {
		return id;
	}

	public int getLength() {
		return cells.size();
	}
	
	public LinkedList<Cell> getCells() {
		return cells;
	}
	protected void move(Cell cell) throws InterruptedException {
		//System.out.println("Cobra " + getIdentification() + " vai para as coordenadas "+ "x= "+ cell.getPosition().x + " y= " + cell.getPosition().y);




		cells.addFirst(cell);
	

	if (cells.size() > size) {
		cells.getLast().release();
		cells.removeLast();

		
	}
	//cell.request(this);

	}

	public Boolean isProtectedCorners(BoardPosition h) {
		if(h.equals(new BoardPosition(h.x, Board.NUM_ROWS))||
				h.equals(new BoardPosition(Board.NUM_COLUMNS,h.y ))||
				h.equals(new BoardPosition(h.x, 0))||
				h.equals(new BoardPosition(0,h.y ))
				) {
			return false;
		}
		return true;
		
	}
	
	public LinkedList<BoardPosition> getPath() {
		LinkedList<BoardPosition> coordinates = new LinkedList<BoardPosition>();
		for (Cell cell : cells) {
			coordinates.add(cell.getPosition());
		}

		return coordinates;
	}	
	protected void doInitialPositioning() {
		// Random position on the first column. 
		// At startup, snake occupies a single cell
		int posX = 0;
		int posY = (int) (Math.random() * Board.NUM_ROWS);
		BoardPosition at = new BoardPosition(posX, posY);
		
		try {
			board.getCell(at).request(this);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cells.add(board.getCell(at));
		System.err.println("Snake "+getIdentification()+" starting at:"+getCells().getLast());		
	}
	
	public Board getBoard() {
		return board;
	}

	public void EndGame() {
		// TODO Auto-generated method stub
		isFinished=true;
		this.interrupt();
	}
	
	
}
