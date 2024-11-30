package game;

import environment.BoardPosition;
import environment.Cell;
import environment.LocalBoard;

public class ObstacleMover extends Thread {
	private Obstacle obstacle;
	private LocalBoard board;

	public ObstacleMover(Obstacle obstacle, LocalBoard board) {
		super();
		this.obstacle = obstacle;
		this.board = board;
	}

	@Override
	public void run() {
		while (obstacle.getRemainingMoves() > 0) {

			try {
				// Espera um curto período de tempo (intervalo entre movimentos)
				Thread.sleep(obstacle.getObstacleMoveInterval());

				
				obstacle.setRemainingMoves(obstacle.getRemainingMoves() - 1);
				obstacle.initializePosition();
				
				board.setChanged(); 
				board.notifyObservers();
			}catch (InterruptedException e) {
				// Lidar com a interrupção (pode ser necessário terminar a execução)
				e.printStackTrace();
			}
		}
	}
}
