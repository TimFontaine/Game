package gameserver.client.graphics;

import gameserver.ActionManager;
import gameserver.client.GameClient;
import gameserver.client.actions.GameAction;
import gameserver.client.core.ResourceManager;

import java.awt.Image;

import javax.inject.Inject;
import javax.swing.ImageIcon;
import javax.swing.JButton;






import javax.swing.JComponent;

import com.tim.game.generated.gui.GameActionProp;

public class ButtonFactory {
	
	@Inject private static ActionManager actionManager;
	
	private ButtonFactory() {}
	
	public static JButton createButtonFromActionName(String name, GameClient client) {
		ResourceManager resourceManager = ResourceManager.getInstance();
		GameActionProp prop = client.getGuiData().getActionProp(name);
		GameAction action = actionManager.getAction(name);
		JButton button = new JButton();
		button.setFocusable(true);
		button.setAction(action);
		System.out.println("setting accelerator in factory to : " + action.getAccelerator());
		button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(action.getAccelerator(), action.getId());
		button.getActionMap().put(action.getId(), action);
		
		Image image = resourceManager.getImage(prop.getImage());
		if (image !=null) {
			button.setIcon(new ImageIcon(image));
		} 
		if (prop.getText() != null) {
			button.setText(prop.getText());
		}
		return button;
	}
}
