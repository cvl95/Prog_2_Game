package de.hsa.games.fatsquirrel.proxies;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.core.Launcher;

public class BotInvocationHandler implements InvocationHandler {
   
   private static final Logger logger = Logger.getLogger(Launcher.class.getName());
   private BotController botController;
   
   public BotInvocationHandler(BotController botController) {
      this.botController=botController;  
   }

   @Override
   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
	   String s="";
      for (int i = 0; i < args.length; i++) 
          s+=(" " + args[i]);
      logger.log(Level.INFO,"Method called: " + method + " with params: "+s);
      Object result = null;
      try  {
          result = method.invoke(botController, args);
      } catch(InvocationTargetException ex)  {
          logger.log(Level.WARNING, "* exception:" + ex.getTargetException());
          throw ex.getTargetException();
      }
      logger.log(Level.FINE,"Methodresult of "+ method +": "+ result);
      return result;
   }

}
