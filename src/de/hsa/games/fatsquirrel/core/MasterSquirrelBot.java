package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.entities.MasterSquirrel;
import de.hsa.games.fatsquirrel.space.XY;
import de.hsa.games.fatsquirrel.ui.EntityContext;

public class MasterSquirrelBot extends MasterSquirrel {

	ControllerContext context;
	
	
//	public MasterSquirrelBot(XY loc) {
//		super(loc, EntityType.HandOperatedMasterSquirrel);
//		
//	
//	
//	}
	
	public MasterSquirrelBot(XY xy) {
		super(xy, EntityType.HandOperatedMasterSquirrel);	}

	@Override
	public void nextStep(EntityContext entityContext) {
		System.out.println("MasterSquirrelBot Nextstep");
	}
	public class ControllerContextimpl implements ControllerContext{
		public ControllerContextimpl() {
			
		}

		@Override
		public XY getViewLowerLeft() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public XY getViewUpperRight() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public EntityType getEntityAt(XY xy) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void move(XY direction) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void spawnMiniBot(XY direction, int energy) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int getEnergy() {
			// TODO Auto-generated method stub
			return 0;
		}
	}
	
	
	
	
}
