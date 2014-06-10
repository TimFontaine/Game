package gameserver.commands;

import gameserver.client.GameClient;
import gameserver.client.actions.GameAction;
import gameserver.design.GameServerCalls;
import gameserver.server.GameEvent;

import java.awt.event.ActionEvent;

public class StartGameCommand extends GameCommand {
	
	private String userName;
	
	public StartGameCommand(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

}
