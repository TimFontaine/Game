package gameserver.design;

import java.net.InetSocketAddress;

import gameserver.ServerMode;

public class ServerConfig {

	public ServerConfig(ServerMode serverMode) {
		super();
		this.setServerMode(serverMode);
	}
	
	public ServerConfig(ServerMode serverMode, InetSocketAddress address) {
		super();
		this.setServerMode(serverMode);
		this.address = address;
	}
	
	private ServerMode serverMode;
	private InetSocketAddress address;
	public InetSocketAddress getAddress() {
		return address;
	}
	public void setAddress(InetSocketAddress address) {
		this.address = address;
	}

	public ServerMode getServerMode() {
		return serverMode;
	}

	public void setServerMode(ServerMode serverMode) {
		this.serverMode = serverMode;
	}

}
