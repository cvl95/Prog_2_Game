package de.hsa.games.fatsquirrel.gameimpl;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.config.BoardConfig;
import de.hsa.games.fatsquirrel.core.Game;
import de.hsa.games.fatsquirrel.core.MasterSquirrelBot;
import de.hsa.games.fatsquirrel.space.XY;
import de.hsa.games.fatsquirrel.ui.UI;

public class MultiBotGameImpl extends Game{
	public BotControllerFactory factory;
	public BotController botController;
	static BoardConfig boardConfig = new BoardConfig(2,0);
	
	
	public MultiBotGameImpl(UI ui, BotControllerFactory factory) {
		super(ui, boardConfig);
		
		
		
//		for (int i = 0; i < boardConfig.getNumberBots(); i++) {
//			
//			this.factory = factory;
//			botController = factory.createMasterBotController();
//			this.getBoard().getEntitySet().addEntity(new MasterSquirrelBot(XY.getRndFreePos(this.getBoard().getEntitySet(), boardConfig), botController));
			
//		}
	}
}