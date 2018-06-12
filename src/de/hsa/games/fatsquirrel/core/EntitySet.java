package de.hsa.games.fatsquirrel.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import de.hsa.games.fatsquirrel.entities.Character;

import de.hsa.games.fatsquirrel.entities.BadBeast;
import de.hsa.games.fatsquirrel.entities.BadPlant;
import de.hsa.games.fatsquirrel.entities.Entity;
import de.hsa.games.fatsquirrel.entities.GoodBeast;
import de.hsa.games.fatsquirrel.entities.GoodPlant;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.entities.MasterSquirrel;
import de.hsa.games.fatsquirrel.space.XY;
import de.hsa.games.fatsquirrel.ui.EntityContext;
import de.hsa.games.fatsquirrel.util.commandscanner.Command;

public class EntitySet {

	private List<Entity> set;

	EntitySet() {
		set = new CopyOnWriteArrayList<>();
	}

	public void addEntity(Entity entity) {
		set.add(entity);
	}

	public void deleteEntity(Entity entity) {
		set.remove(entity);
	}

	public void entitiesNextStep(EntityContext entityContext, Command command) {
		Iterator<Entity> iterator = set.iterator();
		Entity tmp;
		
		while (iterator.hasNext()) {
			tmp = iterator.next();
			if (tmp instanceof Character) {
			
				if(tmp instanceof MasterSquirrelBot) {
					((MasterSquirrelBot)tmp).nextStep(entityContext);	
//					continue;
				}
				if(tmp instanceof HandOperatedMasterSquirrel) {
					((HandOperatedMasterSquirrel)tmp).setCommand(command);
//					continue;
				}
				((Character)tmp).nextStep(entityContext);
				
			}		
		}
		
		
//		while (iterator.hasNext()) {
//			tmp = iterator.next();
//			System.out.println(tmp.toString());
//			if (tmp instanceof Character) {
//				System.out.println("EntitySet instanceof Character");
//				if (tmp instanceof HandOperatedMasterSquirrel) {
//					System.out.println("EntitySet instanceof Character.Handoperated");
////					System.out.println(tmp.toString());
//					if (command != null)
//						System.out.println(command.toString());
//					if (tmp instanceof MasterSquirrel && !(tmp instanceof MasterSquirrelBot)) {
//						System.out.println("EntitySet Instanceof master/bot");
//
//						((MasterSquirrel) tmp).setCommand(command);
//					}
//					System.out.println("Entityset nextstep character all");
//					((Character) tmp).nextStep(entityContext);
//				}
//			}
//
//		}
	}

	public boolean checkPosFree(XY xy) {
		Iterator<Entity> iterator = set.iterator();
		Entity tmp;
		while (iterator.hasNext()) {
			tmp = iterator.next();
			if (tmp == null)
				break;
			else if (xy.getX() == tmp.getLoc().getX() && xy.getY() == tmp.getLoc().getY())
				return false;

		}
		return true;
	}

	public Object[] getEntityArray() {
		return set.toArray();
	}

	public List<Entity> getEntityList() {
		return this.set;
	}

	@Override
	public String toString() {
		Iterator<Entity> iterator = set.iterator();
		Entity tmp;
		int counter = 0;
		String a = "";

		while (iterator.hasNext()) {
			tmp = iterator.next();
			a += "Type " + tmp.getEntityType() + " |ID " + tmp.getID() + " | Energy " + tmp.getEnergy() + " | LOC: X "
					+ tmp.getLoc().getX() + " | LOC: Y " + tmp.getLoc().getY() + "\r";
			counter++;
		}
		return a;
	}

}
