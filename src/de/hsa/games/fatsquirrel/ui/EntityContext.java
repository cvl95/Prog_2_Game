package de.hsa.games.fatsquirrel.ui;

import de.hsa.games.fatsquirrel.core.EntitySet;
import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.core.FlattenedBoard;
import de.hsa.games.fatsquirrel.entities.Entity;
import de.hsa.games.fatsquirrel.space.XY;

public interface EntityContext {
	
	public XY getSize();
//	public void tryMove(HandOperatedMasterSquirrel handOperatedMaster, XY moveDirection);
//	public void tryMove(MiniSquirrel minisquirrel, XY moveDirection);
//	public void tryMove(GoodBeast goodBeast, XY moveDirection);
//	public void tryMove(BadBeast badBeast, XY moveDirection);
//	public void tryMove(MasterSquirrel master, XY moveDirection);
	public void tryMove(Entity entity, XY moveDirection);
	public Entity nearestPlayerEntity(XY pos);
	public Entity nearestEntity(XY pos, EntityType type);
	public FlattenedBoard flatten();
	public EntitySet getEntitySet();
	
	
	public void kill(Entity entity);
	public void killAndReplace(Entity entity);
	public EntityType getEntityType(XY xy);
	
	
	
}
