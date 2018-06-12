package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.entities.Entity;

/**
 * Enum for EntityTypes
 * 
 * @author Fatih
 *
 */
public enum EntityType {

	BadBeast, BadPlant, GoodBeast, GoodPlant, HandOperatedMasterSquirrel, MasterSquirrel, MiniSquirrel, Wall, EMPTY;

	public static EntityType getType(Entity entity) {
		if (entity instanceof de.hsa.games.fatsquirrel.entities.Wall)
			return EntityType.Wall;
		else if (entity instanceof de.hsa.games.fatsquirrel.entities.GoodBeast)
			return EntityType.GoodBeast;
		else if (entity instanceof de.hsa.games.fatsquirrel.entities.BadBeast)
			return EntityType.BadBeast;
		else if (entity instanceof de.hsa.games.fatsquirrel.entities.GoodPlant)
			return EntityType.GoodPlant;
		else if (entity instanceof de.hsa.games.fatsquirrel.entities.BadPlant)
			return EntityType.BadPlant;
		else if (entity instanceof de.hsa.games.fatsquirrel.entities.MiniSquirrel)
			return EntityType.MiniSquirrel;
		else if (entity instanceof de.hsa.games.fatsquirrel.core.MasterSquirrelBot)
			return EntityType.MasterSquirrel;
		else if (entity instanceof de.hsa.games.fatsquirrel.entities.MasterSquirrel)
			return EntityType.MasterSquirrel;

		else
			return null;
	}

}
