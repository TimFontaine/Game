package gameserver.client.actions;

import gameserver.client.GameClient;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import com.tim.game.generated.gui.GameActionProp;

public abstract class GameAction extends AbstractAction {
	
	private final GameClient client;
	
public GameAction() {
	client = null;
}
	
	public GameAction(GameClient client){
		this.client = client;
	}

	GameClient getGameClient() {
		return client;
	}
	
	public KeyStroke getAccelerator() {
		return (KeyStroke) getValue(ACCELERATOR_KEY);
	}
	
	public void setAccelerator(KeyStroke keyStroke) {
		System.out.println("creating accelerator for" + keyStroke);
		putValue(AbstractAction.ACCELERATOR_KEY, keyStroke);
//		putValue(MNEMONIC_KEY, KeyEvent.VK_A);
	}

	public String getId() {
		return (String) getValue("id");
	}
	
}
