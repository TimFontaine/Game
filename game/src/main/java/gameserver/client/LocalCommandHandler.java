package gameserver.client;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import gameserver.Client;
import gameserver.commands.GameCommand;
import gameserver.server.GameEvent;

public class LocalCommandHandler implements ClientCommandHandler, Client {
	
	private GameEvent responseEvent;
	
	@Inject private Event<GameCommand> event;
	
	
//	public LocalCommandHandler(GameClient client) {
//		this.executor = client.getCommandExecutor();
////		calls.addClient(this);
//		System.out.println("command handler added client via calls");
//	}
	
	public LocalCommandHandler() {
	}

	@Override
	public void handle(GameCommand command) {
		System.out.println("sending command");
		event.fire(command);
//		responseEvent = executor.execute(command);
	}

	@Override
	public void handleGameEvent(GameEvent gameEvent) {
		System.out.println("handle game event called");
	}

	@Override
	public GameEvent getReturnMessage() {
		System.out.println("get retur message called");
		return responseEvent;
	}

	@Override
	//should be called from server localconnection
	public void receiveEvent(GameEvent gameEvent) {
		responseEvent = gameEvent;
	}

}
