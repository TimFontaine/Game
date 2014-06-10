package gameserver.server.communication;

import gameserver.Client;
import gameserver.design.CommandExecutor;
import gameserver.server.GameEvent;

public class LocalConnection implements Connection {

	private CommandExecutor commandExecutor;
	
	private Client client;
	
	public LocalConnection (Client client, CommandExecutor commandExecutor) {
		this.client = client;
	}

	@Override
	public void handleGameEvent(GameEvent gameEvent) {
		client.receiveEvent(gameEvent);
	}
}
