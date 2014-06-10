package gameserver.client.panel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gameserver.client.GameApi;
import gameserver.client.GameClient;

import javax.inject.Inject;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class NewGamePanel extends JPanel implements ActionListener {

	private GameClient client;
	private JTextField userName;
	
	@Inject GameApi gameApi;
	
	
	public NewGamePanel() {
		super(new MigLayout());
		
		this.setBackground(Color.GREEN); 
		JLabel lUserName = new JLabel("userName");
		userName = new JTextField(10);
		JButton button = new JButton("start game");
		button.setActionCommand("start game");
		button.addActionListener(this);
		this.add(lUserName);
		this.add(userName, "wrap");
		add(button);
		
		
		setSize(getPreferredSize());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("new game panel action performed");
		String actionCommand = e.getActionCommand();
		if ("start game".equals(actionCommand)) {
			gameApi.startGame(userName.getText());
		}
	}
}
