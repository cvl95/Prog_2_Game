package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.space.XY;


public class TestBot implements BotController {

	public TestBot() {
	}

	
	public void nextStep(ControllerContext view) {
		
		//TODO: Logik f�r Bot einstellen
		System.out.println("nextStep Testbot");
		System.out.println(view.getEnergy());
		view.move(new XY(1,0));
		
	}
	
}
