package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.space.XY;

public class TestBot implements BotController {

	public TestBot() {

	
	}

	@Override
	public void nextStep(ControllerContext view) {
		
		//TODO: Logik für Bot einstellen
		System.out.println("nextStep Testbot");
		System.out.println(view.getEnergy());
		view.move(new XY(1,0));
		
	}
	
}
