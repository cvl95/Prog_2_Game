package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.space.XY;
import de.hsa.games.fatsquirrel.ui.EntityContext;

public class MiniSquirrel extends PlayerEntity {

	private int energy;
	private int ID;
	private int masterID;
	static EntityType type = EntityType.MiniSquirrel;
	private int stunCounter;
	private int waitCounter;

	public MiniSquirrel(int energy, int masterID, XY loc) {
		super(energy, loc, type);
		this.masterID = masterID;

	}

	public void getStunned() {
		stunCounter = 3;
	}

	@Override
	public void nextStep(EntityContext entityContext) {
		
		Entity nearestGoodPlant = entityContext.nearestEntity(this.getLoc(), EntityType.GoodPlant);
		Entity nearestGoodBeast = entityContext.nearestEntity(this.getLoc(), EntityType.GoodBeast);
		XY vecToNearest=XY.genVecfromRand();	
		if(waitCounter<4) {
			waitCounter++;
		}
		else {
			if(XY.getDist(nearestGoodPlant, this.getLoc()) < 20 || (XY.getDist(nearestGoodBeast, this.getLoc()) < 10 )) {
				if(XY.getDist(nearestGoodBeast,this.getLoc()) > XY.getDist(nearestGoodPlant,this.getLoc())) {
					vecToNearest = XY.getVec(this.getLoc(),nearestGoodPlant.getLoc());
				}
				else {
					vecToNearest = XY.getVec(this.getLoc(),nearestGoodBeast.getLoc());
				}
	         //System.out.println("GoodBeast.java's dir to Player:"+XY.vecToDir(vecToNearest).getX() + XY.vecToDir(vecToNearest).getY());
	         entityContext.tryMove(this, XY.vecToDir(vecToNearest));
			} else
				entityContext.tryMove(this,  XY.invertVec(this.getLoc().addVec(vecToNearest)));
			waitCounter=0;
		}
	}

	public String toString() {
		String a = "";

		a += "BadBeast | ID " + this.getID() + " | Energy " + this.getEnergy() + " | LOC: X " + this.getLoc().getX()
				+ " | LOC: Y " + this.getLoc().getY();
		return a;
	}

	public int getMasterID() {
		return masterID;
	}

}