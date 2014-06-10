package gameserver.client;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.swing.SwingUtilities;

import org.jboss.weld.environment.se.bindings.Parameters;
import org.jboss.weld.environment.se.events.ContainerInitialized;

import com.tim.game.generated.gui.GuiData;

import gameserver.ActionManager;
import gameserver.ServerMode;
import gameserver.client.core.GuiWrapper;
import gameserver.client.core.SpecLoader;
import gameserver.client.graphics.GUI;
import gameserver.client.services.ConnectionController;
import gameserver.design.ServerConfig;
import gameserver.design.ServerRootCalls;
import gameserver.loader.Specification;
import gameserver.server.Server;

@ApplicationScoped
public class GameClient {
	
//	@Inject private GameApi gameApi;
	
	@Inject private GuiWrapper guiData;
	@Inject private ActionManager actionManager;
	SpecLoader loader;
//	private PreGameController preGameController;
	@Inject private ConnectionController connectionController;
	
	@Inject private GameEventListener gameEventListener;
	private Server server;
	@Inject private GUI gui;

	Game game;
	
	@Inject 
	public GameClient(SpecLoader loader, GuiWrapper guiData, ActionManager actionManager ) {
		this.guiData = guiData;
		this.loader = loader;
		this.actionManager = actionManager;
		loadGuiData();
		actionManager.initGameActions();
	}
	
	public void start(@Observes ContainerInitialized event, 
			@Parameters List<String> parameters) {
//		this.gui = new GUI(this);
		
//		this.actionManager = new ActionManager(this);
//		this.preGameController = new PreGameController(this);
//		this.gameApi = new GameApi(this);
		this.connectionController = new ConnectionController();
		//startup screen
		this.gui.startGUI(GUI.getDefaultScreenDevice());
		
		//start the gui with swing
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				gui.startMainPanel(); 
			}
		});
		
	}
	
	private void loadGuiData() {
		guiData = loader.getGuiData();
		loader.loadGUI();
	}
	
//	public void startNetworkServer(ServerConfig config) {
//		serverRootCalls.startServer(config);
//		gameApi = new GameApi(config.getAddress());
//	}
	
//	public void startLocalServer() {
//		ServerConfig config = new ServerConfig(ServerMode.LOCAL);
//		serverRootCalls.startServer(config);
//		gameApi = new GameApi(serverRootCalls);
// 	}

	public Game getGame() {
		return game;
	}

	public GuiWrapper getGuiData() {
		return guiData;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

}
