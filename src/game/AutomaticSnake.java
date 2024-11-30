package game;

import java.util.LinkedList;
import java.util.Random;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

import javax.swing.text.Position;


import environment.LocalBoard;
import gui.SnakeGui;
import environment.Cell;
import environment.Board;
import environment.BoardPosition;

public class AutomaticSnake extends Snake {
	BoardPosition next;
	private Random random=new Random();

	public AutomaticSnake(int id, LocalBoard board) {
		super(id,board);
		if(board==null) {
			throw new IllegalArgumentException("Board nao pode ser null");
		}

	}


	@Override
	public void run() {
		doInitialPositioning();
		System.err.println("Cobra " + getIdentification() + " inicializada com tamanho: " + cells.size());

		while (!isInterrupted()) {
			Cell currentCell = cells.getFirst();
			List<BoardPosition> possibleMoves = board.getNeighboringPositions(currentCell);
			
			try {
				BoardPosition next = chooseBestPosition(possibleMoves);
				Cell NextCell= board.getCell(next);
				sleep(Board.PLAYER_PLAY_INTERVAL);
				NextCell.request(this);
				
				
				
				if(NextCell.isOcupiedByGoal()) {
					
					
					Goal goal=NextCell.getGoal();  //Ir buscar o goal
					if(goal.getValue()==9) {
						setSize(getSize()+goal.getValue());  //Incrementar o tamanho da cobra com o tamanho do goal
						cells.addFirst(NextCell);
						NextCell.release2();
						board.endGame();
					
					}else {
						setSize(getSize()+goal.getValue());  //Incrementar o tamanho da cobra com o tamanho do goal
						cells.addFirst(NextCell);  //Mover a cobra para a coordenada do goal (cabeca para a coordenada)
						goal.incrementValue(); //Incrementar 1 no goal
						NextCell.release2();  //Desaparecer o goal da tabela
						goal.getBoard().addGameElement(goal); // Colocar em outras coordenadas o goal
					}

				}else {
					
					move(board.getCell(next));
					//System.out.println("A proxima posicao é " + next.x + " " + next.y);
				}
					board.setChanged();
					
				
					 // Pausa entre movimentos para tornar a movimentação visível.
			}
			catch (InterruptedException e) {
				if(board.isFinished()==false) {
				try {
					
					BoardPosition next1 = chooseOtherPosition(possibleMoves);
					Cell NextCell= board.getCell(next1);
					sleep(Board.PLAYER_PLAY_INTERVAL);
					NextCell.request(this);

					
				
					move(board.getCell(next1));
					
					board.setChanged();
					}
					
					catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
				System.out.println("Cobra " + getIdentification() + " interrompida.");
				e.printStackTrace();
		}
	}
}

	private BoardPosition chooseBestPosition(List<BoardPosition> possibleMoves) throws InterruptedException {
		while (!possibleMoves.isEmpty()) {
			BoardPosition menorValor=possibleMoves.get(0);
			for(int i =0;i<possibleMoves.size();i++) {
				if(menorValor.distanceTo(board.getGoalPosition()) > possibleMoves.get(i).distanceTo(board.getGoalPosition()) && isNotHimself(possibleMoves.get(i))) {
					menorValor=possibleMoves.get(i);
				}
				
			}


			return menorValor;
		}


		return null; // Não há movimentos possíveis.
	}


	private BoardPosition chooseOtherPosition(List<BoardPosition> possibleMoves) throws InterruptedException {
		// TODO Auto-generated method stub
		List<BoardPosition> possibleMoves1=new LinkedList<BoardPosition>();
		
		for(int i = 0; i<possibleMoves.size();i++) {
			if(!(board.getCell(possibleMoves.get(i)).isOcupied()) && isNotHimself(possibleMoves.get(i))) {
				possibleMoves1.add(possibleMoves.get(i));
			}
		}
		if(possibleMoves1.size()==0) {
			System.out.println("Nao tem movimentos");
		}
		
		return chooseBestPosition(possibleMoves1);
	}
	
	


	
	private boolean isNotHimself(BoardPosition boardPosition) {
		// TODO Auto-generated method stub
		for(int i =0;i<cells.size();i++) {
			if(boardPosition.equals(cells.get(i).getPosition())) {
				return false;
			}
		}
		return true;
	}


	public Board getBoard() {
		return board;
	}
	
	
	private BoardPosition chooseRandomPosition(List<BoardPosition> possibleMoves) {
		while (!possibleMoves.isEmpty()) {
			int index = random.nextInt(possibleMoves.size());
			BoardPosition position = possibleMoves.remove(index);
			Cell cell = board.getCell(position);
			if (!cell.isOcupied()){
				return position;
			}
		}
		return null; // Não há movimentos possíveis.
	}

}
