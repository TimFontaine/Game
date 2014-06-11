package gameserver.server.executors;

import java.lang.annotation.Annotation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import javax.inject.Qualifier;

import gameserver.cdi.qualifiers.Server;
import gameserver.client.Game;
import gameserver.client.Player;
import gameserver.commands.GameCommand;
import gameserver.commands.StartGameCommand;
import gameserver.data.GameMap;
import gameserver.events.GameStartedEvent;
import gameserver.server.GameEvent;

@ApplicationScoped
public class UserConnectionController extends GameController {

	@Inject private Game game;
	
	@Inject @Server private Event<GameEvent> event;
	
	@Inject private Instance<WorldBuilder> worldBuilder;
	
	public UserConnectionController() {
	}
	
	public void startNewGame(@Observes StartGameCommand startGameCommand) {
//		AnnotationLiteral<?> a= new AnnotationLiteral<Annotation>() {
//		};
		ServerPlayer player = new ServerPlayer(startGameCommand.getUserName());
		if (game.getPlayers().isEmpty()) {
			player.setMaster();
		}
		
		//create gameworld
		GameMap gameWorld = worldBuilder.get().createWorld();
		game.setMap(gameWorld);
		
		GameEvent gameEvent = new GameStartedEvent(game);
		System.out.println("server component firing event");
		event.select(new AnnotationLiteral<Server>(){}).fire(gameEvent);
	}
}
