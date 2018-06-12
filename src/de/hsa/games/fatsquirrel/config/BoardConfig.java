package de.hsa.games.fatsquirrel.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.hsa.games.fatsquirrel.core.Launcher;
import de.hsa.games.fatsquirrel.space.XY;

public class BoardConfig {


	

	public static final int FIELD_WIDTH=50 ;
	public static final int FIELD_HEIGHT=50;

	private final int NUM_BADBEAST;
	private final int NUM_GOODBEAST;
	private final int NUM_BADPLANT;
	private final int NUM_GOODPLANT;
	private  int NUM_MASTERSQUIRREL;
	private  int NUM_HANDOPERATEDMASTERSQUIRREL;
	private final int NUM_WALL = (FIELD_WIDTH * 2) + (FIELD_HEIGHT * 2) - 4;
	Properties configProps = new Properties();
	private static final Logger logger = Logger.getLogger(Launcher.class.getName());

	private int num_bots = 0;
	private String botName;

	public BoardConfig(int num_bots, int NUM_HANDOPERATEDMASTERSQUIRREL) {
		this.num_bots = num_bots;
		this.NUM_HANDOPERATEDMASTERSQUIRREL = NUM_HANDOPERATEDMASTERSQUIRREL;
		try {
			FileInputStream in =  new FileInputStream("confProps.properties");
			configProps.load(in);
			in.close();
			
		} catch (IOException e) {
			NUM_BADBEAST=1;
			NUM_GOODBEAST=1;
			NUM_BADPLANT=1;
			NUM_GOODPLANT=1;
			logger.log(Level.WARNING, "Board Config couldn't load from prop-file...loading defaults");
			return;	
		}
		NUM_BADBEAST=Integer.parseInt(configProps.getProperty("NUM_BADBEAST"));
		NUM_GOODBEAST=Integer.parseInt(configProps.getProperty("NUM_GOODBEAST"));
		NUM_BADPLANT=Integer.parseInt(configProps.getProperty("NUM_BADPLANT"));
		NUM_GOODPLANT=Integer.parseInt(configProps.getProperty("NUM_GOODPLANT"));	

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
	
	public int getNumberBots() {
		return num_bots;
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
