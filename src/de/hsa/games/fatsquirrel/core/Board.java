package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.config.BoardConfig;
import de.hsa.games.fatsquirrel.entities.BadBeast;
import de.hsa.games.fatsquirrel.entities.BadPlant;
import de.hsa.games.fatsquirrel.entities.GoodBeast;
import de.hsa.games.fatsquirrel.entities.GoodPlant;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.entities.Wall;
import de.hsa.games.fatsquirrel.space.XY;
import de.hsa.games.fatsquirrel.ui.BoardView;
import de.hsa.games.fatsquirrel.ui.EntityContext;
import de.hsa.games.fatsquirrel.util.commandscanner.Command;

public class Board {

	private EntitySet entitySet = new EntitySet();
	private BoardConfig boardConfig;
	private XY xy = new XY(0, 0);
	private Command command = new Command(null, null);

	public Board(BoardConfig boardConfig) {
		this.boardConfig = boardConfig;
//System.out.println(boardConfig.getMasterSquirrel());
		for (int i = 0; i < boardConfig.getFieldHeight(); i++) {

			for (int j = 0; j < boardConfig.getFieldWidth(); j++) {
				if (i == 0 || i == boardConfig.getFieldHeight() - 1) {
					entitySet.addEntity(new Wall(new XY(j, i)));
				} else if (j == 0 || j == boardConfig.getFieldWidth() - 1) {
					entitySet.addEntity(new Wall(new XY(j, i)));
				}
			}
		}

		// init BadBeasts
		for (int i = 0; i < boardConfig.getBadBeast(); i++) { // do x times
			xy = XY.getRndFreePos(entitySet, boardConfig); // xy = new random free position
			entitySet.addEntity(new BadBeast(xy));

		}

		// init BadPlant
		for (int i = 0; i < boardConfig.getBadPlant(); i++) {
			xy = XY.getRndFreePos(entitySet, boardConfig);
			entitySet.addEntity(new BadPlant(xy));

		}

		// init GoodBeast
		for (int i = 0; i < boardConfig.getGoodBeast(); i++) {
			xy = XY.getRndFreePos(entitySet, boardConfig);
			entitySet.addEntity(new GoodBeast(xy));

		}

		// init GoodPlant
		for (int i = 0; i < boardConfig.getGoodPlant(); i++) {
			xy = XY.getRndFreePos(entitySet, boardConfig);
			entitySet.addEntity(new GoodPlant(xy));

		}

		// init HandOperatedMaster
		for (int i = 0; i < boardConfig.getHandOperatedMaster(); i++) {
			xy = XY.getRndFreePos(entitySet, boardConfig);
			entitySet.addEntity(new HandOperatedMasterSquirrel(xy, command));

		}
		// init Mastersquirrels
//		for (int i = 0; i < boardConfig.getMasterSquirrel(); i++) {
//			xy = XY.getRndFreePos(entitySet, boardConfig);
//			entitySet.addEntity(new MasterSquirrel(xy, EntityType.MasterSquirrel));
//		}
	}

	// führt Spieldaten des aktuellen Spielbretts in 2d Array Darstellung zusammen

	public FlattenedBoard flatten() {
		return new FlattenedBoard(this);
	}

	// getter Boardview
	public BoardView getBoardView() {
		return flatten();
	}

	// toString
	@Override
	public String toString() {
		return entitySet.toString();

	}

	// getter EntitySet
	public EntitySet getEntitySet() {
		return entitySet;
	}

	// getter BoardConfig
	public BoardConfig getBoardConfig() {

		return boardConfig;
	}

	public void update(EntityContext entityContext, Command command) {
		entitySet.entitiesNextStep(entityContext, command);
	}

}
