package de.hsa.games.fatsquirrel.ui;

import java.util.List;

import de.hsa.games.fatsquirrel.core.EntitySet;
import de.hsa.games.fatsquirrel.core.EntitySet_old;
import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.core.FlattenedBoard;
import de.hsa.games.fatsquirrel.entities.Entity;
import de.hsa.games.fatsquirrel.space.XY;

public interface EntityContext {
	/**
	 * 
	 * @return will return an XY object with the dimensions of the fild
	 */
	public XY getSize();
/**
 * 
 * @param entity to be moved
 * @param moveDirection XY-Vector for movedirection
 */
	public void tryMove(Entity entity, XY moveDirection);
	/**
	 * 
	 * @param pos to be checked
	 * @return nearest player entity
	 */
	public Entity nearestPlayerEntity(XY pos);
	/**
	 * 
	 * @return returns nearest player entity
	 */
	public Entity nearestEntity(XY pos, EntityType type);
	/**
	 * 
	 * @return returns actual flattened board
	 */
	public FlattenedBoard flatten();
	/**
	 * 
	 * @return returns entityset with entitys
	 */
	public EntitySet getEntitySet();
	
	/**
	 * Kills the given entity
	 * @param entity to be killed
	 */
	public void kill(Entity entity);
	/**
	 * Kills and replace given entity (like Badplants,...)
	 * @param entity
	 */
	public void killAndReplace(Entity entity);
	/**
	 * Returns entitytype of given position
	 * @param xy position to be checked
	 * @return
	 */
	public EntityType getEntityType(XY xy);
	
	
	
}
