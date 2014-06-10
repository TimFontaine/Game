/**
 * 
 */
package gameserver.client.graphics;

import gameserver.cdi.CDIHelper;
import gameserver.client.GameClient;
import gameserver.client.panel.MainPanel;
import gameserver.client.panel.NewGamePanel;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseListener;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import org.omg.CORBA.FREE_MEM;

/**
 * @author tfontaine
 * Root for the GUI, contains graphics, listeners and animations
 *
 */
@ApplicationScoped
public class GUI {
	
	GraphicsDevice gd;
	
	
	@Inject private GameCanvas canvas;
	
//	@Inject private GameClient client;

	private FullScreenFrame frame;

	@Inject private MainPanel mainPanel;
	
//	MapControls mapControls;

	/**
	 * 
	 */
	public GUI() {
	}
	
	public static GraphicsDevice getDefaultScreenDevice() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		return ge.getDefaultScreenDevice();
		
	}
	
	 public static Dimension determineFullScreenSize() {
        GraphicsDevice gd = getDefaultScreenDevice();
        Rectangle bounds = gd.getDefaultConfiguration().getBounds();
        return new Dimension(bounds.width - bounds.x,
                             bounds.height - bounds.y);
	 }
	
	public void startGUI(GraphicsDevice gd) {
		gd = getDefaultScreenDevice();
		this.canvas = new GameCanvas();
		CDIHelper.getInstance().manager(canvas);
//		canvas.createKeyBindings();
		frame = new FullScreenFrame(gd);
		frame.setCanvas(canvas);
		frame.setVisible(true);
	
		
		
		
//		canvas.createKeyBindings();
		
//		showMapControls();
	}

	public void startMainPanel() {
//		mainPanel.init();
		mainPanel.setLocation(0, 0);
		mainPanel.setVisible(true);
		canvas.add(mainPanel);
		canvas.validate();//replace with setLocation, set location will invalidate
//		mainPanel.setLocation(100, 100);
//		mainPanel.setOpaque(true);
//		canvas.repaint();
	}
	
	public void closeMainPanel() {
		canvas.removeAll();
	}
	
	
//	public void showMapControls() {
//		if (mapControls == null) {
//			mapControls = new MapControls(roseClient, this);
//			mapControls.addToComponent(canvas);
//		}
//		mapControls.update();
//	}
//	
//	public void updateMapControls() {
//		 if (mapControls != null) {
//	            mapControls.update();
//		 }
//	}
//	
//	public void setupMouseListenersForCanvas() {
//		canvas.addMouseListener(new CanvasMouseListener(roseClient, canvas, mapViewer));
//	}
//
//	/**
//	 * @return
//	 */
//	public Unit getActiveUnit() {
//		return mapViewer.getActiveUnit();
//	}
//	
//	public void setActiveUnit(Unit unit) {
//		mapViewer.setActiveUnit(unit);
//	}
//	
	public void refresh() {
		canvas.refresh();
	}
//
//	/**
//	 * 
//	 */
//	public Canvas getCanvas() {
//		return canvas;
//	}
//
//	/**
//	 * @param mouseListener
//	 */
//	public void close(JPanel panel) {
//		canvas.close(panel);
//	}

	public void showNewGamePanel() {
		closeMainPanel();
		NewGamePanel newGamePanel = new NewGamePanel();
		CDIHelper.getInstance().manager(newGamePanel);
		System.out.println("adding panel to canvas");
		canvas.add(newGamePanel);
		canvas.validate();
		canvas.repaint();
		System.out.println("adding panel done");
	}

	public void startGame() {
		canvas.removeAll();
		canvas.refresh();
	}
	
	

}
