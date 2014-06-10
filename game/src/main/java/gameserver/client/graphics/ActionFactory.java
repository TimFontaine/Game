package gameserver.client.graphics;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import com.tim.game.generated.gui.GameActionProp;

import gameserver.client.actions.GameAction;

public class ActionFactory {
	
	
	public static void setupGameAction(GameAction action, GameActionProp prop) {
		action.putValue("id", prop.getName());
		if (prop.getAccelerator().size() == 1) {
			action.setAccelerator(KeyStroke.getKeyStroke(prop.getAccelerator().get(0)));
		} else {
//			setAccelerator(loadAccelerator(prop));
		}
		
	}
	
	private static void setAccelerator(GameAction action, KeyStroke accelerator) {
		System.out.println("accelerator is:" + accelerator);
		action.putValue(AbstractAction.ACCELERATOR_KEY, KeyEvent.VK_ENTER);
	}
}
