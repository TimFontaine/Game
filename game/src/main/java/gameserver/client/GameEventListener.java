package gameserver.client;

import gameserver.client.graphics.GUI;
import gameserver.events.GameStartedEvent;
import gameserver.server.executors.Modus;
import gameserver.server.executors.ModusEnum;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

public class GameEventListener {
	
	@Inject GUI gui;

	
	public void gameStarted(@Observes @Modus(ModusEnum.DIRECT) GameStartedEvent event) {
		gui.startGame();
	}
}
