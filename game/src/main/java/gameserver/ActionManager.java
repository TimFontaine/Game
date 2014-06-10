package gameserver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;

import com.tim.game.generated.gui.GameActionProp;
import com.tim.game.generated.gui.GuiData;

import gameserver.cdi.CDIHelper;
import gameserver.client.GameClient;
import gameserver.client.actions.GameAction;
import gameserver.client.actions.NewLocalGameAction;
import gameserver.client.core.GuiWrapper;
import gameserver.client.graphics.ActionFactory;

@ApplicationScoped
public class ActionManager {
	
//	@Inject private GameClient client;
	@Inject private GuiWrapper guiData;
	
	private Map<String, GameAction> gameActions;
	
	@Inject private Instance<GameAction> actions;
	

	public ActionManager() {
		this.gameActions = new HashMap<String, GameAction>();
	}
	
	public void initGameActions() {
		Map<String, GameAction> actionMap = new HashMap<String, GameAction>();
		
		Iterator<GameAction> it = actions.iterator();
		while(it.hasNext()) {
			GameAction g = it.next();
			actionMap.put(g.getClass().getSimpleName(), g);
		}
		
		
		for (Entry<String, GameActionProp> entry : guiData.getGameActionProps().entrySet()) {
			GameAction action = actionMap.get(entry.getValue().getActionClass());
//			CDIHelper.getInstance().manager(gameActions);
//			add(entry.getKey(), loadGameAction(entry.getValue()));
			ActionFactory.setupGameAction(action, entry.getValue());
			add(entry.getKey(), action);
		}
	}

	private void add(String name, GameAction gameAction) {
		gameActions.put(name, gameAction);
	}

	private GameAction loadGameAction(GameActionProp prop) {
		
		String packag = "gameserver.client.actions.";
		try {
			Class<?> actionClass = this.getClass().getClassLoader().loadClass(packag + prop.getActionClass());
			Constructor<GameAction> actionContructor = (Constructor<GameAction>) actionClass.getDeclaredConstructor();
			GameAction action = actionContructor.newInstance();
			ActionFactory.setupGameAction(action, prop);
			return action;
		} catch (NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public GameAction getAction(String name) {
		return gameActions.get(name);
	}

	public Map<String, GameAction> getActions() {
		return gameActions;
	}
}
