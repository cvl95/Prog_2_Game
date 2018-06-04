package de.hsa.games.fatsquirrel.util.commandscanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class CommandScanner {
	private final CommandTypeInfo[] commandTypeInfo;
	// private CommandTypeInfo[] commandTypeInfo;
	private BufferedReader inputReader;
	private PrintStream outputStream;

	public CommandScanner(CommandTypeInfo[] commandTypeInfo, BufferedReader inputReader) {
		this.inputReader = inputReader;
		this.commandTypeInfo = commandTypeInfo;
	}

	public Command next() throws ScanException, IOException, NullPointerException {

		String command = inputReader.readLine(); // liest komplette Zeile ein
		String[] splittedCommand = command.split(" "); // teilt die Zeile nach Leerzeichen auf und speichert die
														// Teilstrings in Array
		String commandName = splittedCommand[0].toLowerCase(); // formatiert die erste Zeile in Kleinbuchstaben
		for (CommandTypeInfo info : commandTypeInfo) { // durchläuft jedes Element in CommandTypeInfo Array und weist
														// dieses info zu
			if (info.getName().equals(commandName.toLowerCase())) { // wenn der Name des info Objekts dem KommandoNamen
																	// (splittedCommand[0]) übereinstimmt
				Object[] params = new Object[splittedCommand.length - 1]; // Objekt-Array params speichert jeglichen
																			// Parameter der hinten am Kommando hängt ab
				for (int i = 1; i < splittedCommand.length; i++) { //
					params[i - 1] = splittedCommand[i]; // Parameter werden um 1 versetzt vom Kommando übertragen
				}

				return new Command(info, params);
			}

		}
		return null;
	}
	
	public Command getCommand(String command) {
		
		 // liest komplette Zeile ein
		String[] splittedCommand = command.split(" "); // teilt die Zeile nach Leerzeichen auf und speichert die
														// Teilstrings in Array
		String commandName = splittedCommand[0].toLowerCase(); // formatiert die erste Zeile in Kleinbuchstaben
		for (CommandTypeInfo info : commandTypeInfo) { // durchläuft jedes Element in CommandTypeInfo Array und weist
														// dieses info zu
			if (info.getName().equals(commandName.toLowerCase())) { // wenn der Name des info Objekts dem KommandoNamen
																	// (splittedCommand[0]) übereinstimmt
				Object[] params = new Object[splittedCommand.length - 1]; // Objekt-Array params speichert jeglichen
																			// Parameter der hinten am Kommando hängt ab
				for (int i = 1; i < splittedCommand.length; i++) { //
					params[i - 1] = splittedCommand[i]; // Parameter werden um 1 versetzt vom Kommando übertragen
				}

				return new Command(info, params);
			}

		}
		return null;
	}
}

