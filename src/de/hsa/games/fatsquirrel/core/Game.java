package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.config.BoardConfig;
import de.hsa.games.fatsquirrel.gameimpl.BotGameImpl;
import de.hsa.games.fatsquirrel.ui.BoardView;
import de.hsa.games.fatsquirrel.ui.EntityContext;
import de.hsa.games.fatsquirrel.ui.UI;
import de.hsa.games.fatsquirrel.util.commandscanner.Command;

public abstract class Game {
	UI ui;
	private BoardConfig boardConfig;
	protected Board board;// = new Board(boardConfig);
	private FlattenedBoard flatBoard;// = new FlattenedBoard(newboard);
	private State state;// = new State(newboard);
	private Command command;
	private int roundCounter = 0;
	EntityContext entityContext;
	private final int FPS = 20;
	private boolean pause;
	public Game(UI ui, BoardConfig boardConfig) {
		this.ui = ui;
		this.boardConfig = boardConfig;
		board = new Board(boardConfig);
		flatBoard =  new FlattenedBoard(board);
		entityContext = flatBoard;
		state = new State(board);
	}

	public void render(BoardView boardView) {
		flatBoard = board.flatten();
		ui.render(boardView);
	}

	public void processInput() {
		command = ui.getCommand();
	}

	public void update() {
		if (!pause)
			state.update(board.flatten(), command);
	}
	
	

	public void gameLoopRun() {
		Thread gameLoopThread = new Thread(() -> {
			while (true) {
				BoardView boardView = board.getBoardView();
				try {
					Thread.sleep((long) (1 / (double) FPS * 5000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				render(boardView);
				update();
				roundCounter++;
				try {
					Thread.sleep((long) (1 / (double) FPS * 5000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				processInput();
			}
		});
		gameLoopThread.start();

	}

	public boolean getPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}
public Board getBoard() {
	return board;
}
	
}
