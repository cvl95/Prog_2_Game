package de.hsa.games.fatsquirrel.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

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
	 * switcher between console-mode or fx-mode switcher between botGame or
	 * Handoperated
	 */
	private static boolean console = false;
	private static boolean botGame = true;
	private static final Logger logger = Logger.getLogger(Launcher.class.getName());

	public static void main(String[] args) {
		initLogger();

		if (!console) {

			if (botGame) {

				FxUI fxUI = FxUI.createInstance(new XY(BoardConfig.FIELD_HEIGHT, BoardConfig.FIELD_HEIGHT));
				Game game = new BotGameImpl(fxUI, new TestBotFactory());
				startGame(game);
				logger.log(Level.INFO, "BotGame launching");

			}
			launch(args);

		}

		else {
			Game game = new SinglePlayerGameImpl(new ConsoleUI());
			logger.log(Level.INFO, "SinglePlayerGame launching");
			startGame(game);

		}
	}

	/*
	 * javaFX (non-Javadoc)
	 * 
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

	public static void initLogger() {
		InputStream ins = null;
		Handler handler = null;
		try {
			ins = new FileInputStream("src/de/hsa/games/fatsquirrel/logging_conf.properties");
			LogManager.getLogManager().readConfiguration(ins);// TODO Logfile wird nicht geladen, über logging
																// nachvollziehen
			handler = new FileHandler("log.xml");
			logger.addHandler(handler);
			handler = new ConsoleHandler();
			logger.addHandler(handler);
		} catch (SecurityException | IOException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			e.printStackTrace();
		}
	}

}
