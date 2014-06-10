package gameserver.data.events;

import gameserver.data.Connection;

public class ConnectionEvent {

	private Connection connection;
	
	public ConnectionEvent(Connection connection) {
		this.connection = connection;
	}
	
	public Connection getConnection() {
		return connection;
	}
}
