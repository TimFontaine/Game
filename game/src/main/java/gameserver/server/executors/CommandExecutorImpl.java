package gameserver.server.executors;

import java.util.List;

import gameserver.commands.StartGameCommand;
import gameserver.Client;
import gameserver.commands.GameCommand;
import gameserver.design.CommandExecutor;
import gameserver.design.GameServerCalls;
import gameserver.server.EventObserver;
import gameserver.server.GameEvent;
import gameserver.server.Server;
import gameserver.server.TestAction;
import gameserver.server.communication.Connection;

public class CommandExecutorImpl implements  CommandExecutor, StartGameCommand.Visitor {
	
	List<Connection> clients;
	
	public CommandExecutorImpl(Server server, List<Connection> clients) {
		this.clients = clients;
	}



	public void test() {
		System.out.println("test executor receives test message");
		TestAction action = new TestAction();
		EventObserver eventObserver = new EventObserver();
		System.out.println("operation test in exectuto has clients: " + clients.size());
//		eventObserver.registerAllClients(clients);
		action.addObserver(eventObserver);
		
		action.test();
	}



	@Override
	public GameEvent startNewGame(StartGameCommand startGameCommand) {
		System.out.println("starting new game");
		GameEvent gameEvent = new GameEvent(true);
		return gameEvent;
	}



	@Override
	public GameEvent execute(GameCommand command) {
		return command.accept(this);
	}

}
