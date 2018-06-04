package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.core.FlattenedBoard;
import de.hsa.games.fatsquirrel.space.XY;
import de.hsa.games.fatsquirrel.ui.EntityContext;

public abstract class PlayerEntity extends Character {



   public PlayerEntity(int energy, XY loc, EntityType type) {
      
      super(energy, loc, type);
   }
//public abstract void nextStep(EntityContext entityContext);
}
