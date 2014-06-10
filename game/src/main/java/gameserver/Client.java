package gameserver;

import gameserver.server.GameEvent;

public interface Client {

	void handleGameEvent(GameEvent gameEvent);

	void receiveEvent(GameEvent gameEvent);

}
