package de.hsa.games.fatsquirrel.gameimpl;

import de.hsa.games.fatsquirrel.config.BoardConfig;
import de.hsa.games.fatsquirrel.core.Game;
import de.hsa.games.fatsquirrel.entities.Entity;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.space.XY;
import de.hsa.games.fatsquirrel.ui.UI;
import de.hsa.games.fatsquirrel.util.commandscanner.Command;

public class SinglePlayerGameImpl extends Game {

//	private Entity humanPlayer;
	static BoardConfig boardConfig = new BoardConfig(0, 1);
//	private Command command = new Command(null, null);

	public SinglePlayerGameImpl(UI ui) {
		super(ui, boardConfig);
//		humanPlayer = new HandOperatedMasterSquirrel(XY.getRndFreePos(this.getBoard().getEntitySet(), boardConfig),
//				command);
//		this.getBoard().getEntitySet().addEntity(humanPlayer);

	}

}
