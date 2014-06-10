package gameserver.client.panel;

import gameserver.cdi.CDIHelper;
import gameserver.client.GameClient;
import gameserver.client.graphics.ButtonFactory;
import gameserver.client.gui.components.GameButton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

//import net.miginfocom.swing.MigLayout;

public class MainPanel extends JPanel implements KeyListener {
	
	private GameButton localGameButton = new GameButton("newLocalGame");
	private GameButton networkGameButton = new GameButton("newNetworkGame");

	public MainPanel() {

		setLayout(new MigLayout());
	}
	
	public void go() {
		System.out.println("go");
	}
	
	@PostConstruct
	public void init() {
		System.out.println("init");
//		this.setLayout(new MigLayout());
		setBackground(Color.BLUE);
//		button = new GameButton();
		CDIHelper.getInstance().manager(localGameButton);
		CDIHelper.getInstance().manager(networkGameButton);
//		button.init("newLocalGame");
//		System.out.println("main" + button.getAction().getValue(Action.ACCELERATOR_KEY));
//		button.setVisible(true);
		
		add(localGameButton);
		add(networkGameButton);
//		button.setSize(getPreferredSize());
		
		setSize(getPreferredSize());
//		validate();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("key pressed");
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("key pressed");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("key pressed");
		
	}

	
//	public void paintComponent(Graphics g) {
//		System.out.println("paint mainpanel");
//		super.paintComponent(g);
//	}
}
