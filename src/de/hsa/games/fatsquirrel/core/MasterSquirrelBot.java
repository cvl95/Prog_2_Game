package de.hsa.games.fatsquirrel.core;

import static org.junit.jupiter.api.Assertions.assertThrows;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.entities.MasterSquirrel;
import de.hsa.games.fatsquirrel.space.XY;
import de.hsa.games.fatsquirrel.ui.EntityContext;

public class MasterSquirrelBot extends MasterSquirrel {

	private ControllerContext controllerContext;
	private BotController controller;
	// private final BotController controllerContext;

	public MasterSquirrelBot(XY xy, BotController controller) {
		super(xy, EntityType.MasterSquirrel);
		this.controller = controller;
	}

	private ControllerContext getControllerContext(EntityContext entityContext) {
		if (controllerContext == null) {
			ControllerContextImpl controllerContext = new ControllerContextImpl(entityContext);

		}
		return controllerContext;
	}

	@Override
	public void nextStep(EntityContext entityContext) {
		// entityContext.tryMove(this, XY.RIGHT);
	
		// cci = getControllerContext(entityContext);
		controller.nextStep(new ControllerContextImpl(entityContext));
		
	}

	public class ControllerContextImpl implements ControllerContext {

		private final EntityContext ENTITYCONTEXT;
		private final int MAXSIGHT = 15;

		ControllerContextImpl(EntityContext entityContext) {
			ENTITYCONTEXT = entityContext;
		}

		@Override
		public XY getViewLowerLeft() {
			return new XY(0, ENTITYCONTEXT.getSize().getY());
		}

		@Override
		public XY getViewUpperRight() {
			return new XY(ENTITYCONTEXT.getSize().getX(), 0);
		}

		@Override
		public EntityType getEntityAt(XY xy) {
			XY myPos = MasterSquirrelBot.this.getLoc();
			XY delta = myPos.getVec(myPos, xy);

			// TODO: Throw exception
			if (!inBounds(delta))
				System.out.println("outOufBounds");

			return ENTITYCONTEXT.getEntityType(xy);
		}

		private boolean inBounds(XY delta) {
			int dx = Math.abs(delta.getX());
			int dy = delta.getY();
			return dx <= MAXSIGHT && dy <= MAXSIGHT;
		}

		@Override
		public void move(XY direction) {
			ENTITYCONTEXT.tryMove(MasterSquirrelBot.this, direction);
		}

		@Override
		public void spawnMiniBot(XY direction, int energy) {
			spawnMiniBot(direction, energy);
		}

		@Override
		public int getEnergy() {
			return MasterSquirrelBot.this.getEnergy();
		}

		@Override
		public XY locate() {
			return null;
		}

		@Override
		public boolean isMine(XY xy) {
			if (getEntityAt(xy) == EntityType.MiniSquirrel)
				return true;
			return false;
		}

		@Override
		public void implode(int impactRadius) {
			// TODO implement Implode

		}

		@Override
		public XY directionOfMaster() {
			//TODO Implement directionOfMaster
			return null;
		}

		@Override
		public long getRemainingSteps() {
			//TODO Implement getRemainingSteps
			return 0;
		}
	}

}
