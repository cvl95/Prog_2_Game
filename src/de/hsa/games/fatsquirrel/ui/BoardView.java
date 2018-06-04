package de.hsa.games.fatsquirrel.ui;

import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.core.FlattenedBoard;
import de.hsa.games.fatsquirrel.space.XY;

public interface BoardView {

	public EntityType getEntityType(int x, int y);

	public XY getSize();

	public EntityType getEntityType(XY currPos);

	public FlattenedBoard getflattenedboard();

}
