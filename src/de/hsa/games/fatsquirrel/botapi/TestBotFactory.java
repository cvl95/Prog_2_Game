package de.hsa.games.fatsquirrel.botapi;

import java.lang.reflect.Proxy;

import de.hsa.games.fatsquirrel.proxies.BotInvocationHandler;

public class TestBotFactory implements BotControllerFactory{
   

	@Override
	public BotController createMasterBotController() {
	   BotController masterBot= new TestBot();
	   
	   BotInvocationHandler handler = new BotInvocationHandler(masterBot);
	   BotController masterBotProxy = (BotController) Proxy.newProxyInstance(BotController.class.getClassLoader(), new Class[] {BotController.class}, handler);
	   
		return masterBotProxy;
	}

	@Override
	public BotController createMiniBotController() {
<<<<<<< HEAD
		return new TestBot();
=======
	   BotController miniBot = new TestBot();
      
      BotInvocationHandler handler = new BotInvocationHandler(miniBot);
      BotController miniBotProxy = (BotController) Proxy.newProxyInstance(BotController.class.getClassLoader(), new Class[] {BotController.class}, handler);
      
      return miniBotProxy;
>>>>>>> 416e2aa663db49f23f699881ae88d003e1b086e7
	}

}
