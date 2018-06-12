package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.ui.EntityContext;
import de.hsa.games.fatsquirrel.util.commandscanner.Command;

public class State {

	private int highSchore = 0;
	Board board;
	
	State(Board board){
		this.board=board;
	}
	
	public void update(EntityContext entityContext, Command command) {
		board.update(entityContext, command);
	}

	public FlattenedBoard flattenedBoard() {
		return board.flatten();
		
	}
	
	
	
}
