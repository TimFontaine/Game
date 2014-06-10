package gameserver.design;

import gameserver.commands.GameCommand;
import gameserver.server.GameEvent;

public interface CommandExecutor  {
	
	public GameEvent execute(GameCommand command);
}
