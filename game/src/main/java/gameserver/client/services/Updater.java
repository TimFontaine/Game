package gameserver.client.services;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import gameserver.cdi.qualifiers.Client;
import gameserver.cdi.qualifiers.ClientUpdate;
import gameserver.client.graphics.GUI;
import gameserver.events.GameStartedEvent;

@ClientUpdate
public class Updater {
	
	@Inject GUI gui;
	
	public void update(@Observes @Client GameStartedEvent event) {
		//redraw
		System.out.println("simple updater: starting game refreshing gui");
		gui.startGame();;
	}

}
