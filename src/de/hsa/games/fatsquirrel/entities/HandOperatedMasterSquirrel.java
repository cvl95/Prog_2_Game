package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.space.XY;
import de.hsa.games.fatsquirrel.ui.ConsoleUI;
import de.hsa.games.fatsquirrel.ui.EntityContext;
import de.hsa.games.fatsquirrel.ui.UI;
import de.hsa.games.fatsquirrel.util.commandscanner.Command;
import de.hsa.games.fatsquirrel.util.commandscanner.CommandTypeInfo;
import de.hsa.games.fatsquirrel.util.commandscanner.GameCommandType;

public class HandOperatedMasterSquirrel extends PlayerEntity {
	private static final int energy = 1000;

	private Command command;
	private int stunCounter;
	static EntityType type = EntityType.HandOperatedMasterSquirrel;

	public HandOperatedMasterSquirrel(XY loc, Command command) {
		super(energy, loc, type);
		this.command = command;

	}

	public void getStunned() {
		stunCounter = 3;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public void nextStep(EntityContext entityContext) {

		if (command == null) {
			return;
		}
		if (command.getCommandType() == null) {
			return;
		}
		CommandTypeInfo type = command.getCommandType();
		if (type == GameCommandType.UP)
			entityContext.tryMove(this, XY.UP);
		else if (type == GameCommandType.DOWN)
			entityContext.tryMove(this, XY.DOWN);
		else if (type == GameCommandType.LEFT)
			entityContext.tryMove(this, XY.LEFT);
		else if (type == GameCommandType.RIGHT)
			entityContext.tryMove(this, XY.RIGHT);
		else if (type == GameCommandType.UP_LEFT)
			entityContext.tryMove(this, XY.UP_LEFT);
		else if (type == GameCommandType.UP_RIGHT)
			entityContext.tryMove(this, XY.UP_RIGHT);
		else if (type == GameCommandType.DOWN_LEFT)
			entityContext.tryMove(this, XY.DOWN_LEFT);
		else if (type == GameCommandType.DOWN_RIGHT)
			entityContext.tryMove(this, XY.DOWN_RIGHT);
		else if (type == GameCommandType.STAY)
			entityContext.tryMove(this, XY.STAY);

	}

	@Override
	public String toString() {
		String a = "";

		a += "HandOperatedMasterSquirrel | ID " + this.getID() + " | Energy " + this.getEnergy() + " | LOC: X "
				+ this.getLoc().getX() + " | LOC: Y " + this.getLoc().getY();
		return a;
	}
}
