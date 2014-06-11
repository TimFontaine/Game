package gameserver.client;

import javax.enterprise.context.ApplicationScoped;

import gameserver.ServerMode;
import gameserver.client.actions.NewLocalGameAction;
import gameserver.client.panel.NewGamePanel;
import gameserver.commands.StartGameCommand;
import gameserver.design.PreGameClientCalls;
import gameserver.server.GameEvent;

@ApplicationScoped
public class GameApi implements PreGameClientCalls {
	
	private ServerMode serverMode;
	
	ClientCommandHandler handler;
	
	public GameApi(ServerMode serverMode) {
		super();
		this.serverMode = serverMode;
	}
	
//	public GameApi(PreGameClientCalls preGame, ServerRootCalls serverRootCalls, ServerMode serverMode) {
//		super();
//		this.preGame = preGame;
//		this.serverMode = serverMode;
//		switch(serverRootCalls.getServerMode()) {
//		case LOCAL:
//			handler = new LocalCommandHandler(serverRootCalls);
//			break;
//		case NETWORK:
//			break;
//		default:
//			break;
//		
//		}
//	}
	
//	public GameApi(ServerRootCalls serverRootCalls) {
//		handler = new LocalCommandHandler(serverRootCalls);
//	}
	
//	public GameApi(InetSocketAddress address) {
//		try {
//			Socket socket = new Socket(address.getAddress(), address.getPort());
//			System.out.println("client: network connection made");
//			handler = new NetworkCommandHandler(socket);
//		} catch (IOException e) {
//			
//		}
//	}

	public GameApi() {
	}

	@Override
	public void createNewGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void test() {
//		TestGameCommand command = new TestGameCommand();
//		handler.handle(command);
//		System.out.println("calling get return message");
//		GameEvent event = handler.getReturnMessage();
//		System.out.println("api has return event");
	}

	public void makeConnection(ClientCommandHandler handler) {
		this.handler = handler;
	}

	
	public void startGame(String userName) {
		handler.handle(new StartGameCommand("userName"));
//		GameEvent event = handler.getReturnMessage();
//		System.out.println("event response is :" + event.getStatus());
	}
}
