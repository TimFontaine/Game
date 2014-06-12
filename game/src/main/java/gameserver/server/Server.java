package gameserver.server;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import gameserver.Client;
import gameserver.ServerMode;
import gameserver.cdi.CDIHelper;
import gameserver.cdi.qualifiers.GameScope;
import gameserver.client.Game;
import gameserver.data.Connection;
import gameserver.design.ServerConfig;
import gameserver.server.executors.GameController;
import gameserver.server.executors.ModusEnum;
import gameserver.server.executors.UserConnectionController;

@ApplicationScoped
public class Server implements Runnable {
	
	ServerMode serverMode;
	
	private List<Connection> clients;
	
	private Game game;
	
	@Inject private Instance<GameController> gameControllers;
	
	@Inject private UserConnectionController userConnectionController;
	private ServerConfig config;
	private Network network;
	
	public Server() {
		System.out.println("starting server");
		clients = new ArrayList<Connection>();
		
		//define all components for running the game
		
//		commandExecutor = new CommandExecutorImpl(this, clients);
//		game = new Game();
	}

	public void startServer(ServerConfig config) {
		this.serverMode = config.getServerMode();
		switch(serverMode){
		case LOCAL:
			break;
		case NETWORK:
			Network network = new Network(config);
			new Thread(network).start();
		default:
			break;
		
		}
	}
	
	public void addClient(Client client){
		//create new connection
	}

	@Override
	public void run() {
//		new Thread(network).start();
		System.out.println("starting test5 server at:" + System.currentTimeMillis());
		network.waitForNewPlayer();
		System.out.println("a player has connected at:" + System.currentTimeMillis());
		System.out.println(Thread.currentThread().getName());
	}
	
	public void setServerConfig(int maxUsers, ServerConfig config) {
		this.config = config;
		network = new Network(config);
		CDIHelper.getInstance().manager(network);
		for (GameController gameController : gameControllers) {
			gameController.setModus(ModusEnum.NETWORK);
		}
	}

	public void registerClient(Client client) {
		addClient(client);
	}

	public Game getGame() {
		return game;	
	}

	public void startNetwork(int i, ServerConfig config) {
		
	}

//	public void onGameEvent(@Observes GameEvent gameEvent) {
//		System.out.println("the server itsefs receives its game event");
////		handleGameEvent(gameEvent);
//	}
	
}
