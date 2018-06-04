package de.hsa.games.fatsquirrel.config;

import de.hsa.games.fatsquirrel.space.XY;

public class BoardConfig {

	public static final int FIELD_WIDTH = 50;
	public static final int FIELD_HEIGHT = 50;

	private final int NUM_BADBEAST = 1;
	private final int NUM_GOODBEAST = 1;
	private final int NUM_BADPLANT = 1;
	private final int NUM_GOODPLANT = 5;
	private  int NUM_MASTERSQUIRREL;
	private  int NUM_HANDOPERATEDMASTERSQUIRREL;
	private final int NUM_WALL = (FIELD_WIDTH * 2) + (FIELD_HEIGHT * 2) - 4;

	public BoardConfig(int NUM_MASTERSQUIRREL, int NUM_HANDOPERATEDMASTERSQUIRREL) {
		this.NUM_MASTERSQUIRREL = NUM_MASTERSQUIRREL;
		this.NUM_HANDOPERATEDMASTERSQUIRREL = NUM_HANDOPERATEDMASTERSQUIRREL;
	}
	public void setMasterSquirrel(int masterSquirrel) {
		NUM_MASTERSQUIRREL = masterSquirrel;
	}
	
	public int getFieldWidth() {
		return FIELD_WIDTH;
	}

	public int getFieldHeight() {
		return FIELD_HEIGHT;
	}

	public int getBadBeast() {
		return NUM_BADBEAST;
	}

	public int getGoodBeast() {
		return NUM_GOODBEAST;
	}

	public int getBadPlant() {
		return NUM_BADPLANT;
	}

	public int getGoodPlant() {
		return NUM_GOODPLANT;
	}

	public int getMasterSquirrel() {
		return NUM_MASTERSQUIRREL + NUM_HANDOPERATEDMASTERSQUIRREL;
	}

	public int getHandOperatedMaster() {
		return NUM_HANDOPERATEDMASTERSQUIRREL;
	}

	public int getWall() {
		return NUM_WALL;
	}

	public int getEntityNumber() {
		return NUM_BADBEAST + NUM_GOODBEAST + NUM_BADPLANT + NUM_GOODPLANT + NUM_MASTERSQUIRREL + NUM_WALL;
	}

	public XY getSize() {
		return new XY(FIELD_WIDTH, FIELD_HEIGHT);
	}
}
