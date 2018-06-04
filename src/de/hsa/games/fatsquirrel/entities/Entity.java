package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.core.FlattenedBoard;
import de.hsa.games.fatsquirrel.space.XY;
import de.hsa.games.fatsquirrel.ui.EntityContext;

public abstract class Entity {

	private int ID;
	private int energy;
	private XY loc;
	static int idCounter;
	private EntityType type;


	public Entity(int energy, XY loc, EntityType type) {
		this.ID = idCounter;
		idCounter++;
		this.energy = energy;
		this.loc = loc;
		this.type=type;

	}

	public void setXY(XY loc) {
		this.loc = loc;
	}

	public int getID() {
		return ID;
	}

	public XY getLoc() {
		return loc;
	}

	public int getEnergy() {
		return energy;
	}
	
	
	

	public void updateEnergy(int energy) {
		this.energy += energy;
	}
	
	public EntityType getEntityType() {
		
		return type;
	}
	

}