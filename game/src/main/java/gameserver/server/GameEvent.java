package gameserver.server;

import java.io.Serializable;

public class GameEvent implements Serializable {
	
	private boolean status;
	
	public GameEvent(boolean b) {
		this.status = true;
	}
	
	public boolean getStatus() {
		return status;
	}
}
