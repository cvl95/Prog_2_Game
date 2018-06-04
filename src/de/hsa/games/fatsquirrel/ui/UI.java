package de.hsa.games.fatsquirrel.ui;

import de.hsa.games.fatsquirrel.util.commandscanner.Command;

public interface UI {

	public Command getCommand();

	void render(BoardView view);

	void message(String msg);

}
