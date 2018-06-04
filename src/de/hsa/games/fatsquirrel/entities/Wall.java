package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.space.XY;

public class Wall extends Entity {

	private static final int ENERGY = -10;
	static EntityType type=EntityType.Wall;

	public Wall(XY loc) {
		super(ENERGY, loc, type);
	
	}
	public String toString() {
		String a = "";
		
		a += "Wall | ID " + this.getID() + " | Energy " + this.getEnergy() + " | LOC: X " + this.getLoc().getX() + " | LOC: Y " + this.getLoc().getY();
		return a;
	}

}