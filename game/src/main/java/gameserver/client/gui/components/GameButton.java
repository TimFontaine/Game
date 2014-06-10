package gameserver.client.gui.components;

import java.awt.Image;

import gameserver.ActionManager;
import gameserver.client.actions.GameAction;
import gameserver.client.core.GuiWrapper;
import gameserver.client.core.ResourceManager;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import com.tim.game.generated.gui.GameActionProp;
import com.tim.game.generated.gui.GuiData;

public class GameButton extends JButton {

	@Inject GuiWrapper guiData;
	@Inject ActionManager actionManager;
	@Inject ResourceManager resourceManager;
	
	//TODO initialize with annotationLiteral?
	
	private String name;
	
	public GameButton(String name) {
		this.name = name;
	}

	@PostConstruct
	public void init() {
		GameActionProp prop = guiData.getActionProp(name);
		GameAction action = actionManager.getAction(name);
		setFocusable(true);
		setAction(action);
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(action.getAccelerator(), action.getId());
		getActionMap().put(action.getId(), action);
		
		Image image = resourceManager.getImage(prop.getImage());
		if (image !=null) {
			setIcon(new ImageIcon(image));
		} 
		if (prop.getText() != null) {
			setText(prop.getText());
		}
	}
}
