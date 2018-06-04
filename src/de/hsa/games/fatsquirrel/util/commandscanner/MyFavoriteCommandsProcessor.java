package de.hsa.games.fatsquirrel.util.commandscanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class MyFavoriteCommandsProcessor {
    private static PrintStream outputStream = new PrintStream(System.out);
    private static BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

    public static void process() {
outputStream.println("process");
    }

    public static void help() {
outputStream.println("help ausgabe");
    }
    public static void addi(Object object, Object object2) {
    	outputStream.println(Integer.parseInt((String) object)+ Integer.parseInt((String) object2));
    }

    public static void main(String[] args) {
        CommandScanner commandScanner = new CommandScanner(GameCommandType.values(), inputReader);
        
        
        
        
        while (true) { // the loop over all commands with one input line for every command
            Command command = null;
			try {
				command = commandScanner.next();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
            Object[] params = command.getParams();
            GameCommandType commandType = (GameCommandType) command.getCommandType();
            switch (commandType) {
                case EXIT:
                    System.exit(0);
                case HELP:
                    help();
                    break;
                case MOVE:
                    process();
                    break;
                case SPAWN_MINI:

                    break;


            }
        }

    }
}
