package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.space.XY;


public class TestBot implements BotController {

	String name = "FaCh-Bot";
	
	public TestBot() {
		
	}

	
	public void nextStep(ControllerContext view) {
		

		view.move(XY.DOWN);
		
	}
	
}
