package environment;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import game.GameElement;
import game.Goal;
import game.Obstacle;
import game.Snake;
/** Main class for game representation. 
 * 
 * @author luismota
 *
 */
public class Cell {
	private BoardPosition position;
	private Snake ocuppyingSnake = null;
	private GameElement gameElement=null;
	private Lock lock=new ReentrantLock();
	private Condition notOcupied = lock.newCondition();
	private Condition notEmpty = lock.newCondition(); 
	private Condition notOcupiedOb = lock.newCondition();
	private Condition notEmptyOb = lock.newCondition();


	public GameElement getGameElement() {
		return gameElement;
	}


	public Cell(BoardPosition position) {
		super();
		this.position = position;
	}

	public BoardPosition getPosition() {
		return position;
	}

	public void request(Snake snake)throws InterruptedException {
		lock.lock();

		try {
			while(isOcupied()) {
				notOcupied.await();

			}
	
			ocuppyingSnake=snake;
			notEmpty.signalAll();

		}finally {
			lock.unlock();
		}

	}

	public void release() throws InterruptedException {

		//TODO
		lock.lock();
		try {
			while(!isOcupied()) {
				notEmpty.await();
			}
			
			this.ocuppyingSnake=null;
			notOcupied.signalAll();
		}finally {
			lock.unlock();
		}
	}
	public void release2() throws InterruptedException {

		//TODO
		lock.lock();
		try {
			while(!isOcupied()) {
				notEmpty.await();
			}
			this.gameElement=null;
			notOcupied.signalAll();
		}finally {
			lock.unlock();
		}
	}

	public boolean isOcupiedBySnake() {
		return ocuppyingSnake!=null;
	}


	public  void setGameElement(GameElement element) throws InterruptedException {
		// TODO coordination and mutual exclusion
		lock.lock();

		try {
			while(isOcupied()) {
				notOcupied.await();

			}
	
			gameElement=element;
			notEmpty.signalAll();

		}finally {
			lock.unlock();
		}
		

	}

	public boolean isOcupied() {
		return isOcupiedBySnake() || (gameElement!=null && gameElement instanceof Obstacle);
	}


	public Snake getOcuppyingSnake() {
		return ocuppyingSnake;
	}


	public  Goal removeGoal() {
		// TODO
		return null;
	}

	
	public void removeObstacle() throws InterruptedException {
			
			this.setGameElement(null);
			
	}


	public Goal getGoal() {
		return (Goal)gameElement;
	}


	public boolean isOcupiedByGoal() {
		return (gameElement!=null && gameElement instanceof Goal);
	}



}
