package gameserver;

import org.jboss.weld.environment.se.StartMain;

import gameserver.client.GameClient;
import gameserver.server.Server;

public class Root {
	
//	public Root() {
////		Server server = new Server();
//		GameClient client = new GameClient(server);	
//	}

	public static void main(String[] args) {
		StartMain.main(args);
//		new Root();
	}

}
