/**
 * 
 */
package gameserver.client.graphics;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.HeadlessException;
import java.awt.Rectangle;

import javax.swing.JFrame;


/**
 * @author tfontaine
 *
 */
public class FullScreenFrame extends JFrame {

	/**
	 * @param gc
	 */
	public FullScreenFrame(GraphicsDevice gd) {
		super(gd.getDefaultConfiguration());
		setUndecorated(true);
		
		gd.setFullScreenWindow(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public void setCanvas(GameCanvas canvas) {
		getContentPane().add(canvas);
	}
	
	

}
