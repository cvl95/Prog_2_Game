package de.hsa.games.fatsquirrel.logging;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Log {
	private static final Logger logger1 = Logger.getLogger(Log.class.getName());
	
//	public static void main(String[] args) {
//		
//		Handler handler = null;
//		try {
//			handler = new FileHandler( "log.txt" );
//		} catch (SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		logger1.addHandler( handler );
//	}
	public static void init() {
        InputStream ins = null;
        try {
            ins = new FileInputStream("src/de/hsa/games/fastsquirrel/logging/loggin_conf.properties");
            LogManager.getLogManager().readConfiguration(ins);
        } catch (SecurityException | IOException e) {
            logger1.log(Level.SEVERE, e.getMessage(), e);
            e.printStackTrace();
        } 
    }
}