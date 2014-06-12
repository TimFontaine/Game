package gameserver.client.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import gameserver.cdi.qualifiers.Client;
import gameserver.cdi.qualifiers.NetworkUpdate;
import gameserver.client.Game;
import gameserver.events.GameStartedEvent;

@ApplicationScoped
public class GameUpdater {

	@Inject Game game; 
	
	public void update(@Observes @NetworkUpdate GameStartedEvent event) {
		System.out.println("network update");
		game.setMap(event.getGame().getMap());
	}
}
