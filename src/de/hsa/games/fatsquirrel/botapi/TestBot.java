package de.hsa.games.fatsquirrel.botapi;

<<<<<<< HEAD
import de.hsa.games.fatsquirrel.space.XY;
=======
>>>>>>> 416e2aa663db49f23f699881ae88d003e1b086e7

public class TestBot implements BotController {

	public TestBot() {
<<<<<<< HEAD

	
=======
>>>>>>> 416e2aa663db49f23f699881ae88d003e1b086e7
	}

	
	public void nextStep(ControllerContext view) {
		
		//TODO: Logik für Bot einstellen
		System.out.println("nextStep Testbot");
		System.out.println(view.getEnergy());
		view.move(new XY(1,0));
		
	}
	
}
