package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.entities.Entity;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.entities.MasterSquirrel;
import de.hsa.games.fatsquirrel.space.XY;
import de.hsa.games.fatsquirrel.ui.EntityContext;
import de.hsa.games.fatsquirrel.util.commandscanner.Command;
import de.hsa.games.fatsquirrel.entities.Character;

public class EntitySet {

	private final int maxEntity = 1000;
	// Entityarray
	private Entity[] entities;
/**
 * generate Entityarray
 */
	public EntitySet() {
		entities = new Entity[maxEntity];
	}
/**
 * Add Entity to next free pos in array
 * @param entity
 */
	public void addEntity(Entity entity) {
		for (int i = 0; i < entities.length; i++) {
			if (entities[i] == null) {
				entities[i] = entity;
				break;
			}
		}
	}
/**
 * delete Entity from array
 * @param entity
 */
	public void deleteEntity(Entity entity) {
		for (int i = 0; i < entities.length; i++) {
			if (entities[i] != null && entities[i].getID() == entity.getID()) {
				entities[i] = null;
				break;
			}
		}
	}
/**
 * Delete Entity from ID
 * @param id
 */
	public void deleteEntityFromID(int id) {
		System.out.println("deleteentityfromid" + id);

		
		for (int i = 0; i < entities.length; i++) {
			System.out.println(entities[i].getID());
			if (entities[i].getID() == id) {
				System.out.println(entities[i].getID() + id);
				entities[i] = null;	
				break;
			}
	}
	}
/**
 * Call the nextstep method from every Entity that is an instace of 
 * Character
 * @param entityContext
 */
	public void entitiesNextStep(EntityContext entityContext, Command command) {
		for (int i = 0; i < entities.length; i++) {

			if (entities[i] != null && entities[i] instanceof Character) {
				if (entities[i] instanceof HandOperatedMasterSquirrel) 
					((HandOperatedMasterSquirrel) entities[i]).setCommand(command);
				if(entities[i] instanceof MasterSquirrel) 
				   ((MasterSquirrel) entities[i]).setCommand(command);	
				((Character) entities[i]).nextStep(entityContext);
				
			}

		}
	}
/**
 * search and return Entity of type Handoperatedmaster
 * @return
 */
	public HandOperatedMasterSquirrel getHandOperatedMaster() {
		for (int i = 0; i < entities.length; i++) {
			if (entities[i] != null && entities[i].getEntityType() == EntityType.HandOperatedMasterSquirrel)
				return (HandOperatedMasterSquirrel) entities[i];
		}
		return null;
	}
	/**
	 * return entity from ID
	 * @param ID
	 * @return
	 */
	public Entity getEntity(int ID) {
		for (int i = 0; i < entities.length; i++) {
			if (entities[i].getID() == ID) {
				return entities[i];
			}
		}

		return null;
	}
	
	

/**
 * check if given position is a free position
 * @param xy
 * @return
 */
	public boolean checkPosFree(XY xy) {
		for (int i = 0; i < entities.length; i++) {
			if (entities[i] == null)
				break;
			else if (xy.getX() == entities[i].getLoc().getX() && xy.getY() == entities[i].getLoc().getY()) {
				return false;
			}

		}
		return true;
	}
/**
 * getter entity array
 * @return
 */
	public Entity[] getEntityArray() {
		return entities;
	}
/**
 * to-string
 */
	@Override
	public String toString() {
		String a = "";

		for (int i = 0; i < entities.length; i++) {

			if (entities[i] != null) {
				a += "EntitySet " + i + " |ID " + entities[i].getID() + " | Energy " + entities[i].getEnergy()
						+ " | LOC: X " + entities[i].getLoc().getX() + " | LOC: Y " + entities[i].getLoc().getY()
						+ "\r";
			}
		}
		return a;
	}

}
