package gameserver.client.actions;

import gameserver.client.services.ConnectionController;

import java.awt.event.ActionEvent;

import javax.inject.Inject;

public class NewNetworkGameAction extends GameAction {

	@Inject ConnectionController connectionController;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		connectionController.createNetworkGame();
	}

}
