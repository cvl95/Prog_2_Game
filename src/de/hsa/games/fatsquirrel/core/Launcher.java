package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.botapi.TestBotFactory;
import de.hsa.games.fatsquirrel.config.BoardConfig;
import de.hsa.games.fatsquirrel.gameimpl.BotGameImpl;
import de.hsa.games.fatsquirrel.gameimpl.SinglePlayerGameImpl;
import de.hsa.games.fatsquirrel.space.XY;
import de.hsa.games.fatsquirrel.ui.ConsoleUI;
import de.hsa.games.fatsquirrel.ui.FxUI;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Launcher extends Application {
	/**
	 * switcher between console-mode or fx-mode
	 * switcher between botGame or Handoperated
	 */
	private static boolean console = false;
	private static boolean botGame = true;
	/*
	 * loglevel 1 = ???
	 */
	private static int logLevel = 1;

	/*
	 * start gameimpl based on switcher above
	 */
	public static void main(String[] args) {
		if (!console) {
		
			if(botGame) {
				
				FxUI fxUI = FxUI.createInstance(new XY(BoardConfig.FIELD_WIDTH, BoardConfig.FIELD_HEIGHT));
				Game game = new BotGameImpl(fxUI, new TestBotFactory());
				startGame(game);
			}
			
		launch(args);
		}
		
		else  {
			 Game game = new SinglePlayerGameImpl(new ConsoleUI());
			startGame(game);
		
	}
	}
	/*
	 * javaFX 
	 * (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		FxUI fxUI = FxUI.createInstance(new XY(BoardConfig.FIELD_WIDTH, BoardConfig.FIELD_HEIGHT));
		final Game game = new SinglePlayerGameImpl(fxUI);

		primaryStage.setScene(fxUI);
		primaryStage.setTitle("SquirrelGame");
		fxUI.getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				System.exit(-1);

			}
		});
		primaryStage.show();

		startGame(game);
	}
/*
 * starter for the gameLoop
 */
	private static void startGame(Game game) {
		game.setPause(false);
		game.gameLoopRun();
	}

}
