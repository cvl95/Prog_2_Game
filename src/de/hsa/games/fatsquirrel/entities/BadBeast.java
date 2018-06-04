package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.core.FlattenedBoard;
import de.hsa.games.fatsquirrel.space.XY;
import de.hsa.games.fatsquirrel.ui.EntityContext;

public class BadBeast extends Character {
	private static final int ENERGY = -150;
	private int byteCounter = 0;
	private int waitCounter=1;

	static EntityType type = EntityType.BadBeast;

	public BadBeast(XY loc) {
		super(ENERGY, loc, type);
	}

	public int bite() {
		byteCounter++;
		return byteCounter;
	}

	public void nextStep(EntityContext entityContext) {	
	//TODO: Fix behavior
		
		Entity nearestPlayer = entityContext.nearestEntity(this.getLoc(), EntityType.MasterSquirrel);
		if(nearestPlayer==null)
		   nearestPlayer = entityContext.nearestEntity(this.getLoc(), EntityType.HandOperatedMasterSquirrel);
		if(waitCounter<3) {
			waitCounter++;
		}
		else {
	
			if (XY.getDist(nearestPlayer, this.getLoc()) <6) {
				XY vecToNearest = XY.getVec(nearestPlayer.getLoc(), this.getLoc());
				//System.out.println("BadBeast.java's dir to Player:"+XY.vecToDir(vecToNearest).getX() + XY.vecToDir(vecToNearest).getY());
				entityContext.tryMove(this,XY.vecToDir(vecToNearest));
			
			} else
				entityContext.tryMove(this, XY.genVecfromRand());
			waitCounter=0;
		}
	}

	public void randStep(EntityContext entityContext) {
		if(waitCounter<3) {
			waitCounter++;
		}
		else {
				entityContext.tryMove(this, this.getLoc().addVec(XY.genVecfromRand()));
				waitCounter=0;
		}
	}

	public String toString() {
		String a = "";

		a += "BadBeast | ID " + this.getID() + " | Energy " + this.getEnergy() + " | LOC: X " + this.getLoc().getX()
				+ " | LOC: Y " + this.getLoc().getY();
		return a;
	}
}