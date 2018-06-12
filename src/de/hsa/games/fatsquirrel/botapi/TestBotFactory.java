package de.hsa.games.fatsquirrel.botapi;

import java.lang.reflect.Proxy;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.hsa.games.fatsquirrel.core.Launcher;
import de.hsa.games.fatsquirrel.proxies.BotInvocationHandler;

public class TestBotFactory implements BotControllerFactory{
	   private static final Logger logger = Logger.getLogger(Launcher.class.getName());

   

	@Override
	public BotController createMasterBotController() {
	   BotController masterBot= new TestBot();
	   
	   BotInvocationHandler handler = new BotInvocationHandler(masterBot);
	   BotController masterBotProxy = (BotController) Proxy.newProxyInstance(BotController.class.getClassLoader(), new Class[] {BotController.class}, handler);
	   logger.log(Level.INFO, "MasterSquirrelBot created");
		return masterBotProxy;
	}

	@Override
	public BotController createMiniBotController() {
	   BotController miniBot = new TestBot();
      
      BotInvocationHandler handler = new BotInvocationHandler(miniBot);
      BotController miniBotProxy = (BotController) Proxy.newProxyInstance(BotController.class.getClassLoader(), new Class[] {BotController.class}, handler);
      logger.log(Level.INFO, "MiniSquirrelBot created");
      return miniBotProxy;

	}

}
