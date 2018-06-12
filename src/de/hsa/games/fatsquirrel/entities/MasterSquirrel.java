package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.space.XY;
import de.hsa.games.fatsquirrel.ui.EntityContext;
import de.hsa.games.fatsquirrel.util.commandscanner.Command;

public class MasterSquirrel extends PlayerEntity {

	private static int energy = 1000;
	private int ID;
	private int stunCounter;
	private Command command;
	
	public MasterSquirrel(XY loc, EntityType type) {
		super(energy, loc, type);

	}
	
	public void createMiniSquirrel(EntityContext entCon) {
		//implement minisquirrel with random xy
		
		updateEnergy(-50);
		
		entCon.getEntitySet().addEntity(new MiniSquirrel(50, ID, new XY(getLoc().getX(), getLoc().getY())));	
	}
	public void getStunned() {
		stunCounter = 3;
	}
	@Override
	public void nextStep(EntityContext entityContext) {
		if (stunCounter > 0) {
			stunCounter--;
			return;
		}
	}
	public void setCommand(Command command) {
      this.command = command;
   }

//	public boolean isMyMini(Entity entity){
//		if (entity.)
//		return true;
//	}
	public String toString() {
		String a = "";
		
		a += "MasterSquirrel | ID " + this.getID() + " | Energy " + this.getEnergy() + " | LOC: X " + this.getLoc().getX() + " | LOC: Y " + this.getLoc().getY();
		return a;
	}

	
}