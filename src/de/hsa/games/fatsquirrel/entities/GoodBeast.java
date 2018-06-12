package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.core.FlattenedBoard;
import de.hsa.games.fatsquirrel.space.XY;
import de.hsa.games.fatsquirrel.ui.EntityContext;

public class GoodBeast extends Character {

	private final static int ENERGY = 200;
	public int waitCounter=0;

	static EntityType type = EntityType.GoodBeast;

	public GoodBeast(XY loc) {
		super(ENERGY, loc, type);

	}

	

	
	public void nextStep(EntityContext entityContext) {
		//TODO Wechselnde Instanzen vom MS/HOMS im Entity Array von Board, übergeben in entity Context 
		Entity nearestPlayer = entityContext.nearestEntity(this.getLoc(), EntityType.MasterSquirrel);
		if(nearestPlayer==null)
         nearestPlayer = entityContext.nearestEntity(this.getLoc(), EntityType.HandOperatedMasterSquirrel);
      
		if(waitCounter<4) {
			waitCounter++;
		}
		else {
			if(XY.getDist(nearestPlayer, this.getLoc()) < 100) {
				XY vecToNearest = XY.getVec(this.getLoc(), nearestPlayer.getLoc());
	         entityContext.tryMove(this, XY.vecToDir(vecToNearest));
			} else
				entityContext.tryMove(this,  XY.invertVec(this.getLoc().addVec(XY.genVecfromRand())));
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

		a += "GoodBeast | ID " + this.getID() + " | Energy " + this.getEnergy() + " | LOC: X " + this.getLoc().getX()
				+ " | LOC: Y " + this.getLoc().getY();
		return a;
	}
}