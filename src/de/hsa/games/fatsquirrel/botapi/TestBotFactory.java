package de.hsa.games.fatsquirrel.botapi;

public class TestBotFactory implements BotControllerFactory{

	@Override
	public BotController createMasterBotController() {
		return new TestBot();
	}

	@Override
	public BotController createMiniBotController() {
		return new TestBot();
	}

}
