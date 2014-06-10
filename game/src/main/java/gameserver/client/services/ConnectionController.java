package gameserver.client.services;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import gameserver.Client;
import gameserver.ServerMode;
import gameserver.cdi.CDIHelper;
import gameserver.client.ClientCommandHandler;
import gameserver.client.GameApi;
import gameserver.client.LocalCommandHandler;
import gameserver.client.NetworkCommandHandler;
import gameserver.client.graphics.GUI;
import gameserver.commands.StartGameCommand;
import gameserver.design.ServerConfig;
import gameserver.server.Server;

@ApplicationScoped
public class ConnectionController {

	@Inject private GameApi gameApi;
	@Inject private GUI gui;
	
	public ConnectionController() {
	}

	public void createLocalGame() {
		Server server = new Server();
		CDIHelper.getInstance().manager(server);
//		client.setServer(server);
		//make connection
		ClientCommandHandler handler = createConnection(ServerMode.LOCAL);
		gameApi.makeConnection(handler);
		
		//swithc ui
		System.out.println("crateing new game panel");
		gui.showNewGamePanel();
	}
	
	public void createNetworkGame() {
		System.out.println("starting network game");
		Server server = new Server();
		CDIHelper.getInstance().manager(server);
		ServerConfig config = new ServerConfig(ServerMode.NETWORK);
		config.setAddress(new InetSocketAddress("localhost", 4444));
//		server.startNetwork(2, config);
		server.setServerConfig(1, config);
		System.out.println("starting server");
		Thread thread = new Thread(server);
		thread.start();
		ClientCommandHandler handler = createConnection(ServerMode.NETWORK);
		gameApi.makeConnection(handler);
		System.out.println("making connection done at:" + System.currentTimeMillis());
		handler.handle(new StartGameCommand("my name"));
	}

	private ClientCommandHandler createConnection(ServerMode mode) {
		if (mode == ServerMode.LOCAL) {
			LocalCommandHandler handler = new LocalCommandHandler();
			CDIHelper.getInstance().manager(handler);
			return handler;
		} else if (mode == ServerMode.NETWORK) {
			try {
				Socket socket = new Socket("localhost", 4444);
				System.out.println(socket.getLocalAddress());
				NetworkCommandHandler handler = new NetworkCommandHandler(socket, isThisMyIpAddress(socket.getInetAddress()));
				CDIHelper.getInstance().manager(handler);
				return handler;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		return null;
	}
	
	private boolean isInLocalModus(Socket socket) {
		return "/127.0.0.1".equals(socket.getLocalAddress());
	}
	
	public boolean isThisMyIpAddress(InetAddress addr) {
	    // Check if the address is a valid special local or loop back
	    if (addr.isAnyLocalAddress() || addr.isLoopbackAddress())
	        return true;

	    // Check if the address is defined on any interface
	    try {
	        return NetworkInterface.getByInetAddress(addr) != null;
	    } catch (SocketException e) {
	        return false;
	    }
	}
	
}
