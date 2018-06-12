package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.space.XY;


public class TestBot implements BotController {

	String name = "FaCh-Bot";
	
	public TestBot() {
		
	}

	
	public void nextStep(ControllerContext view) {
		
		//TODO: Logik für Bot einstellen
		System.out.println("nextStep Testbot");
		System.out.println(view.getEnergy());
		view.move(XY.DOWN);
		
	}
	
}
