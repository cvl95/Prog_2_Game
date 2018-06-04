package de.hsa.games.fatsquirrel.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Scanner;

import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.entities.Entity;
import de.hsa.games.fatsquirrel.util.commandscanner.*;

public class ConsoleUI implements UI {
	private CommandScanner comScan = new CommandScanner(GameCommandType.values(),new BufferedReader(new InputStreamReader(System.in)));
	private Command command;

	@Override
	public Command getCommand() {
		try {
			command = comScan.next();
		} catch (ScanException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return command;
	}

	@Override
	public void render(BoardView view) {
		Entity[][] entArr = new Entity[view.getflattenedboard().entArr[0].length][view
				.getflattenedboard().entArr.length];

		for (int i = 0; i < entArr.length; i++) {

			for (int j = 0; j < entArr[0].length; j++) {
				entArr[i][j] = view.getflattenedboard().entArr[j][i];
			}

		}

		for (int y = 0; y < entArr.length; y++) {

			for (int x = 0; x < entArr[0].length; x++) {

				if (entArr[y][x] == null)
					System.out.print(" ");
				else {
					EntityType entType = entArr[y][x].getEntityType();
					switch (entType) {
					case Wall:
						System.out.print("#");
						break;
					case BadBeast:
						System.out.print("x");
						break;
					case GoodBeast:
						System.out.print("+");
						break;
					case BadPlant:
						System.out.print("v");
						break;
					case GoodPlant:
						System.out.print("w");
						break;
					case MasterSquirrel:
						System.out.print("S");
						break;
					case HandOperatedMasterSquirrel:
						System.out.print("H");
						break;
					default:
						System.out.print("ERROR");
						break;

					}
				}

			}
			System.out.println();
		}

	}

	@Override
	public void message(String msg) {
	}

}
