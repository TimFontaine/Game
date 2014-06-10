package gameserver.client.actions;

import java.awt.event.ActionEvent;

import javax.inject.Inject;

import gameserver.client.GameClient;
import gameserver.client.services.ConnectionController;

import com.tim.game.generated.gui.GameActionProp;

public class NewLocalGameAction extends GameAction {
	
	@Inject ConnectionController connectionController;
	public NewLocalGameAction() {}

	@Override
	public void actionPerformed(ActionEvent e) {
		connectionController.createLocalGame();
	}
	

}
