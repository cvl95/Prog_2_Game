package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.core.FlattenedBoard;
import de.hsa.games.fatsquirrel.entities.Entity;
import de.hsa.games.fatsquirrel.space.XY;
import de.hsa.games.fatsquirrel.ui.EntityContext;

public abstract class Character extends Entity{
   
   public Character(int energy,  XY loc, EntityType type) {
      super(energy, loc, type);
   }

   public abstract void nextStep(EntityContext entityContext);
   
}
