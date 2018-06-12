package de.hsa.games.fatsquirrel.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.hsa.games.fatsquirrel.config.BoardConfig;
import de.hsa.games.fatsquirrel.ui.BoardView;
import de.hsa.games.fatsquirrel.ui.EntityContext;
import de.hsa.games.fatsquirrel.ui.UI;
import de.hsa.games.fatsquirrel.util.commandscanner.Command;
import de.hsa.games.fatsquirrel.util.commandscanner.CommandTypeInfo;
import de.hsa.games.fatsquirrel.util.commandscanner.GameCommandType;


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
	private static final Logger logger = Logger.getLogger(Launcher.class.getName());
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
				CommandTypeInfo type = command.getCommandType();
				if(type==GameCommandType.ESCAPE)
					
					System.exit(0);
			}
		});
		gameLoopThread.start();

	}

	public boolean getPause() {
		return pause;
	}
	public void saveGame() {
		File file = new File("highscores.txt");
		try {
			if (file.exists()) { 
				FileWriter fw = new FileWriter("highscores.txt",true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("Botname: "+);
			}
			else{ 
                file.createNewFile(); 
                saveGame();
            }
			

		}
		catch (IOException e) { 
			logger.log(Level.WARNING, "Cant save Game, highscores are lost");
        } 
		

            	
		
		
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}
public Board getBoard() {
	return board;
}
	
}
