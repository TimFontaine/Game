package gameserver;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;

import net.miginfocom.swing.MigLayout;

public class Test {
	
	public static void main(String[] args) {
	    JFrame f = new JFrame("Test");
	    JPanel c = new JPanel(new MigLayout(
	           "",          
	           "[]5[]10[grow]10[]0[]0[]0[]",
	           "[]"
	    ));
	    c.add(new JCheckBox(""));
	    c.add(new JLabel("Name"));
	    c.add(new JSlider());
	    c.add(new JButton("1"));
	    c.add(new JButton("2"));
	    c.add(new JButton("3"));
	    c.add(new JButton("4"));
	    f.getContentPane().add(new JScrollPane(c));
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.pack();
	    f.setVisible(true);
	 }
}
