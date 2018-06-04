package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.space.XY;

public class GoodPlant extends Entity {

	private static final int ENERGY = 100;
	static EntityType type=EntityType.GoodPlant;

	public GoodPlant(XY loc) {
		super(ENERGY, loc, type);
	}

	public String toString() {
		String a = "";

		a += "GoodPlant | ID " + this.getID() + " | Energy " + this.getEnergy() + " | LOC: X " + this.getLoc().getX()
				+ " | LOC: Y " + this.getLoc().getY();
		return a;
	}


}