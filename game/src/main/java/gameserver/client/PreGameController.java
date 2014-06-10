package gameserver.client;

import gameserver.ServerMode;

public class PreGameController {

	private final GameClient client;
	
	private ServerConnectionManager connnectionManager;
	
	public PreGameController(GameClient gameClient) {
		this.client = gameClient;
	}

	public void createNewGame(ServerMode mode) {
		
	}
}
