package gameserver.design;

import gameserver.Client;
import gameserver.ServerMode;

public interface ServerRootCalls {

	public void startServer(ServerConfig config);
	
	public ServerMode getServerMode();
	
	public CommandExecutor getCommandExecutor();

	public void addClient(Client client);
	
}
