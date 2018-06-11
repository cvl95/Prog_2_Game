package de.hsa.games.fatsquirrel.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.hsa.games.fatsquirrel.entities.BadBeast;
import de.hsa.games.fatsquirrel.entities.BadPlant;
import de.hsa.games.fatsquirrel.entities.Entity;
import de.hsa.games.fatsquirrel.entities.GoodBeast;
import de.hsa.games.fatsquirrel.entities.GoodPlant;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.entities.MasterSquirrel;
import de.hsa.games.fatsquirrel.entities.MiniSquirrel;
import de.hsa.games.fatsquirrel.space.XY;
import de.hsa.games.fatsquirrel.ui.BoardView;
import de.hsa.games.fatsquirrel.ui.EntityContext;

public class FlattenedBoard implements BoardView, EntityContext {
   
   private static final Logger logger = Logger.getLogger(Launcher.class.getName());


	Board board;
	public Entity[][] entArr;
	private EntitySet set;
	private Iterator<Entity> iterator;
	private int counter = 0;
	private Entity tmp;
	public FlattenedBoard(Board board) {
		this.board = board;
//		kill0HPEntities();
		entArr = new Entity[board.getBoardConfig().getFieldWidth()][board.getBoardConfig().getFieldHeight()];// Entity
																												// Array,
																												// Entities
																												// werden
																												// mit
																												// ihren
																												// tatsächlichen
																												// Positionen
																												// im
																												// Array
																												// abgespeichert

//		for (int i = 0; i < board.getEntitySet().getEntityArray().length -1; i++) {
//			if (board.getEntitySet().getEntityArray()[i] != null)
//			entArr[board.getEntitySet().getEntityArray()[i].getLoc().getX()][board.getEntitySet().getEntityArray()[i].getLoc().getY()] = board.getEntitySet().getEntityArray()[i];
//			// Array wird mit Entities an richtiger Position befüllt
		
		 set = board.getEntitySet();
		 iterator = set.getEntityList().iterator();
		 while(iterator.hasNext()) {
			tmp = iterator.next();
			 entArr[tmp.getLoc().getX()][tmp.getLoc().getY()] = tmp;
			 counter++;
		 }
	}

	@Override
	public EntityType getEntityType(int x, int y) {
		return entArr[x][y].getEntityType();
	}

	@Override
	public XY getSize() { // gibt Feld diagonal zum Ursprung aus, "Feldgröße"
		return new XY(board.getBoardConfig().getFieldWidth(), board.getBoardConfig().getFieldHeight());
	}

	public EntityType getMoveDirEnt(XY loc) { // prüft ob und welcher Typ an einer Stelle steht
		if (entArr[loc.getX()][loc.getY()] != null) {
			return entArr[loc.getX()][loc.getY()].getEntityType();
		} else
			return null;
	}

	public boolean collide(Entity entity, XY loc) { // Rückgabewert sagt ob entity auf target bewegen kann
		Entity target = entArr[loc.getX()][loc.getY()];
		if(entity==target)
			return false;
		
		if (entity instanceof MasterSquirrel || entity instanceof MiniSquirrel
				|| entity instanceof HandOperatedMasterSquirrel) { 
		   logger.log(Level.INFO, "Collision Between:" + entity.toString()+" and " + target.toString());
		   // Kollision für Master/MiniSquirrel
			switch (getMoveDirEnt(loc)) {
			case BadBeast:
				if (((BadBeast) target).bite() > 7) {
					killAndReplace(target);
					return true;
				} else {
					entity.updateEnergy(target.getEnergy());
					return false;
				}
				
			case GoodBeast:
				entity.updateEnergy(target.getEnergy());
				killAndReplace(target);
				return true;

			case BadPlant:
				entity.updateEnergy(target.getEnergy());
				killAndReplace(target);
				return true;

			case GoodPlant:
				entity.updateEnergy(target.getEnergy());
				killAndReplace(target);
				return true;

			case Wall:
				entity.updateEnergy(target.getEnergy());
				if (entity instanceof HandOperatedMasterSquirrel)
					((HandOperatedMasterSquirrel) entity).getStunned();
				return false;
			case MiniSquirrel:
				if (entity instanceof HandOperatedMasterSquirrel) {
					entity.updateEnergy(target.getEnergy());
					kill(target);
					return true;
				} else {
					if (((MiniSquirrel) entity).getMasterID() == ((MiniSquirrel) target).getMasterID())
						return false;
					else {
						kill(target);
						kill(entity);
						return true;
					}
				}
			default:
				break;
			}
		}
		if (entity instanceof BadBeast || entity instanceof GoodBeast) {// Kollision für Good/BadBeast
			if (getMoveDirEnt(loc) == EntityType.MasterSquirrel || getMoveDirEnt(loc) == EntityType.MiniSquirrel) {
				if (entity instanceof BadBeast) {
					if (((BadBeast) entity).bite() > 7) {
						killAndReplace(entity);
					}
					else {
						target.updateEnergy(entity.getEnergy());
					}	
				}
				if (entity instanceof  GoodBeast) {
					target.updateEnergy(entity.getEnergy());
					killAndReplace(entity);
				}
				return false;
			}
			if(entity.getEntityType() == EntityType.BadBeast)
				((BadBeast) entity).randStep(this);
			else
				((GoodBeast) entity).randStep(this);
			return false;
			
		}
		return false;

	}

	public void tryMove(Entity entity, XY moveDirection) {
		System.out.println("TryMove FlattenedBoard entity: " + entity.toString());
		int posY = entity.getLoc().addVec(moveDirection).getY();
		int posX = entity.getLoc().addVec(moveDirection).getX();
		if (posX < entArr.length && posY < entArr[1].length && posX >= 0 && posY >= 0) { // überprüft ob
																									// Position noch im
																									// Array ist

			if (getMoveDirEnt(entity.getLoc().addVec(moveDirection)) == null) {
				entity.setXY(entity.getLoc().addVec(moveDirection));
			} else if (collide(entity, entity.getLoc().addVec(moveDirection)))
				entity.setXY(entity.getLoc().addVec(moveDirection));
			else
				return;
		} else
			return;

	}

	// @Override
	// public void tryMove(MiniSquirrel miniSquirrel, XY moveDirection) {
	// if (checkMoveDir(miniSquirrel.getLoc().addVec(moveDirection)) == null) {
	// miniSquirrel.setXY(miniSquirrel.getLoc().addVec(moveDirection));
	// } else
	// collide(miniSquirrel, miniSquirrel.getLoc().addVec(moveDirection));
	// }
	//
	// @Override
	// public void tryMove(MasterSquirrel master, XY moveDirection) {
	// if (checkMoveDir(master.getLoc().addVec(moveDirection)) == null) {
	// master.setXY(master.getLoc().addVec(moveDirection));
	// } else
	// collide(master, master.getLoc().addVec(moveDirection));
	// }
	//
	// @Override
	// public void tryMove(GoodBeast goodBeast, XY moveDirection) {
	// goodBeast.setXY(goodBeast.getLoc().addVec(moveDirection));
	// }
	//
	// @Override
	// public void tryMove(BadBeast badBeast, XY moveDirection) {
	// badBeast.setXY(badBeast.getLoc().addVec(moveDirection));
	// }

	@Override
	//TODO: Problems regarding multiple HandOperated and their distance i think...
	public Entity nearestPlayerEntity(XY pos) { // gibt mir die näheste Player-Entity zurück

		Entity[] players = new Entity[board.getBoardConfig().getMasterSquirrel() ];
		int k = 0;
		
		set =  board.getEntitySet();
		iterator = set.getEntityList().iterator();
		while (iterator.hasNext()) {
			tmp = iterator.next();
			if (tmp instanceof MasterSquirrel) {
				players[k] = tmp;
				k++;
			}
		}
//		for (int j = 0; j < board.getEntitySet().getEntityArray().length - 1; j++) {
//			if (board.getEntitySet().getEntityArray()[j] != null) {
//				if (board.getEntitySet().getEntityArray()[j] instanceof MasterSquirrel) { //.getEntityType() == EntityType.MasterSquirrel) {
//					players[k] = board.getEntitySet().getEntityArray()[j]; //Befüllt Entity Array players mit HoMS
//					k++;
//				}
//			}
//		}
		Entity nearest = null;

		int dist = XY.pyth(board.getBoardConfig().getFieldWidth(), board.getBoardConfig().getFieldHeight());
		for (int i = 0; i < players.length; i++) {
			int c = XY.getDist(players[i], pos);
			if (c < dist) 
				nearest = players[i];
		}

		return nearest;
	}
	public Entity nearestEntity(XY pos, EntityType type) { // gibt mir die näheste Player-Entity zurück
		
		Entity[] players = new Entity[100];
		int k = 0;
		
//		 set = (List<Entity>) board.getEntitySet();
//		 iterator = set.iterator();
//		 while(iterator.hasNext()) {
//			tmp = iterator.next();
//			 entArr[tmp.getLoc().getX()][tmp.getLoc().getY()] = tmp;
//			 counter++;
		
		set = board.getEntitySet();
		iterator = set.getEntityList().iterator();
		
		while(iterator.hasNext()) {
			tmp = iterator.next();
			
			if(tmp.getEntityType() == type) {
				players[k] = tmp;
				k++;
			}
		}
		
		
//		for (int j = 0; j < board.getEntitySet().getEntityArray().length - 1; j++) {
//			if (board.getEntitySet().getEntityArray()[j] != null) {
//				if (board.getEntitySet().getEntityArray()[j].getEntityType() == type) {
//					players[k] = board.getEntitySet().getEntityArray()[j]; //Befüllt Entity Array players mit HoMS
//					k++;
//				}
//			}
//		}
		Entity nearest = null;

		int dist = XY.pyth(board.getBoardConfig().getFieldWidth(), board.getBoardConfig().getFieldHeight());
		for (int i = 0; i < players.length; i++) {
			if(players[i]==null) 
				return nearest;
			int c = XY.getDist(players[i], pos);
			if (c < dist) 
				nearest = players[i];
		}

		return nearest;
	}
	//
	@Override
	public void killAndReplace(Entity entity) { // ?! Wie kann ich eine neue Entity einfügen
	   logger.log(Level.INFO, "K.I.A.: "+ entity.toString());
		XY loc = XY.getRndFreePos(board.getEntitySet(), board.getBoardConfig());
		switch (entity.getEntityType()) {
		case BadBeast:
			board.getEntitySet().deleteEntity(entity);
			board.getEntitySet().addEntity(new BadBeast(loc));
			break;
		case GoodBeast:
			board.getEntitySet().deleteEntity(entity);
			board.getEntitySet().addEntity(new GoodBeast(loc));
			break;
		case BadPlant:
			board.getEntitySet().deleteEntity(entity);
			board.getEntitySet().addEntity(new BadPlant(loc));
			break;
		case GoodPlant:
			board.getEntitySet().deleteEntity(entity);
			board.getEntitySet().addEntity(new GoodPlant(loc));
			break;
		default:
			break;
		}

	}
	public void kill(Entity entity) {
	   logger.log(Level.INFO, "K.I.A.: "+ entity.toString());
	   board.getEntitySet().deleteEntity(entity);
	}

	public void kill0HPEntities() {
		
		set =  board.getEntitySet();
		iterator = set.getEntityList().iterator();
		
		while(iterator.hasNext()) {
			tmp = iterator.next();
			if(tmp instanceof HandOperatedMasterSquirrel) {
				if (tmp.getEnergy() <= 0)
					set.getEntityList().remove(tmp);
			}
		}
		
		
		
//		Entity entities[]=board.getEntitySet().getEntityArray();	
//		for (int i = 0; i < entities.length; i++) {
//		   if(entities[i]!=null)
//		      if(entities[i] instanceof HandOperatedMasterSquirrel)
//		         if(entities[i].getEnergy()<=0) 
//		            board.getEntitySet().deleteEntity(entities[i]);
//		}
		
	}
	
	
	@Override
	public EntityType getEntityType(XY xy) {
		
	   if(entArr[xy.getX()][xy.getY()]==null) {
	      return EntityType.EMPTY;
	   }
	   else
		return entArr[xy.getX()][xy.getY()].getEntityType();
	}

	@Override
	public FlattenedBoard flatten() {
		
		return null;
	}

	@Override
	public List<Entity> getEntitySet() {
	
		return (List<Entity>) set;
	}
	@Override
	public FlattenedBoard getflattenedboard() {
		
		return this;
	}

}
