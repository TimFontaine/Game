package gameserver.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import gameserver.cdi.CDIHelper;
import gameserver.design.ServerConfig;
import gameserver.server.communication.NetworkConnection;

@ApplicationScoped
public class Network implements Runnable {
	
	private int maxPlayers = 2;
	
	private ServerSocket socket;
	
	private List<NetworkConnection> connections = new ArrayList<NetworkConnection>();

	//allow cdi to find the observers
//	public Network() {}
	
	public Network(ServerConfig config) {
		SocketAddress address = config.getAddress();
		try {
			ServerSocket socket = new ServerSocket();
			socket.bind(address);
//			waitForNewPlayer(socket);
			this.socket = socket;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void waitForNewPlayer() {
		try {
			Socket s = this.socket.accept();
			NetworkConnection c = new NetworkConnection(s);
			CDIHelper.getInstance().manager(c);
			connections.add(c);
//			server.addClient(c);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
	}
	
//	public void onGameEvent2(@Observes GameEvent gameEvent) {
////		handleGameEvent(gameEvent);
//	}
	
}
