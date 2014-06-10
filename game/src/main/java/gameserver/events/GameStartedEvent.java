package gameserver.events;

import gameserver.client.Game;
import gameserver.server.GameEvent;

public class GameStartedEvent extends GameEvent {

	private Game game;
	
	public GameStartedEvent(Game game) {
		super(true);
		this.game = game;
	}

	public Game getGame() {
		return game;
	}

}
