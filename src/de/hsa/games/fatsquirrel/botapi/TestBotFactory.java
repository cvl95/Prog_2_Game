package de.hsa.games.fatsquirrel.botapi;

public class TestBotFactory implements BotControllerFactory{

	@Override
	public BotController createMasterBotController() {
		return new TestBot();
	}

	@Override
	public BotController createMiniBotController() {
		// TODO Auto-generated method stub
		return new TestBot();
	}

}
