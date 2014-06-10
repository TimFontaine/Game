package gameserver.client.graphics;

import gameserver.ActionManager;
import gameserver.client.GameClient;
import gameserver.client.actions.GameAction;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.xml.ws.Action;

//handle swing stuff, paints and components, responable for swing drawing
public class GameCanvas extends JPanel {
	
	@Inject private ActionManager actionManager;
	
	@Inject private MapViewer mapViewer;
	
	public GameCanvas() {
//		this.mapViewer = client.getGui().mapViewer;
		
        setOpaque(false);
        setDoubleBuffered(true);
        setLayout(null);
        
        setLocation(0, 0);
        setSize(1000,1000);
        
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
//        this.mapViewer = mapViewer;
        
//        JButton button = new JButton("qsdf");
//        add(button);
//        button.setSize(100, 100);
//        button.setLocation(50, 50);
	}

	@Override
	public void paintComponent(Graphics g) {
		System.out.println("paint component");
		Graphics2D g2 = (Graphics2D) g;
		mapViewer.draw(g2);
//		super.paintComponent(g);
//		super.paintChildren(g);
//		repaint();
		
	}

	public void refresh() {
		System.out.println("refresh");
		repaint();
	}

	public void createKeyBindings() {
		for (GameAction action : actionManager.getActions().values()) {
			getInputMap(WHEN_IN_FOCUSED_WINDOW).put(action.getAccelerator(), action.getId());
			getActionMap().put(action.getId(), action);
			System.out.println("key binding created for" + action.getAccelerator());
		}
	}
}
