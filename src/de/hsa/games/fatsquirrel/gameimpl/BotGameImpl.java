package de.hsa.games.fatsquirrel.gameimpl;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.config.BoardConfig;
import de.hsa.games.fatsquirrel.core.EntitySet_old;
import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.core.Game;
import de.hsa.games.fatsquirrel.core.MasterSquirrelBot;
import de.hsa.games.fatsquirrel.entities.Entity;
import de.hsa.games.fatsquirrel.entities.MasterSquirrel;
import de.hsa.games.fatsquirrel.space.XY;
import de.hsa.games.fatsquirrel.ui.UI;

public class BotGameImpl extends Game {

	private Entity botPlayer;
	private BotControllerFactory factory;
	static BoardConfig boardConfig = new BoardConfig(0, 0);
	private BotController botController;
	
	public BotGameImpl(UI ui, BotControllerFactory factory) {
		super(ui, boardConfig);
	
		boardConfig.setMasterSquirrel(1);
		this.factory = factory;
		botController = factory.createMasterBotController();
		botPlayer = new MasterSquirrelBot(XY.getRndFreePos(this.getBoard().getEntitySet(), boardConfig), botController);
		this.getBoard().getEntitySet().addEntity(botPlayer);
		
	}

	// TODO: better tostring
	public String toString() {
		String s = "";

		s += this.botPlayer.getEntityType();
		

		return s;

	}
}
