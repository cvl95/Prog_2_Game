package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.space.XY;


public class BadPlant extends Entity {
	private final static int ENERGY = -100;
	static EntityType type=EntityType.BadPlant;

	public  BadPlant(XY loc) {
		super(ENERGY, loc, type);
		}
	
	public String toString() {
		String a = "";
		
		a += "BadPlant | ID " + this.getID() + " | Energy " + this.getEnergy() + " | LOC: X " + this.getLoc().getX() + " | LOC: Y " + this.getLoc().getY();
		return a;
	}
}