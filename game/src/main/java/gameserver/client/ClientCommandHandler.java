package gameserver.client;

import gameserver.commands.GameCommand;
import gameserver.server.GameEvent;

public interface ClientCommandHandler {
	
	public void handle(GameCommand command);
	
//	void handleGameEvent(GameEvent gameEvent);
	
	public GameEvent getReturnMessage();

}
