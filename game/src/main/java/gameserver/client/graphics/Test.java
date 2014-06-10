package gameserver.client.graphics;

import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Test {
	
	  public static void main(String args[]) {
	    JFrame frame = new JFrame("DefaultButton");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JButton button1 = new JButton("Text Button");
	    button1.setMnemonic(KeyEvent.VK_Q);
	    frame.add(button1);
	    frame.setSize(300, 200);
	    frame.setVisible(true);
	}
}
