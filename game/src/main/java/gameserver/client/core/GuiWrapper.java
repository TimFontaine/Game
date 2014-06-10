package gameserver.client.core;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import com.tim.game.generated.gui.GameActionProp;
import com.tim.game.generated.gui.GuiData;

@ApplicationScoped
public class GuiWrapper {

	private Map<String, GameActionProp> gameActionProps;
	
	public Map<String, GameActionProp> getGameActionProps() {
		return gameActionProps;
	}

	public GuiWrapper() {
		//transform elements to map
//		transform(data);
		
	}

//	@TODO refactor guiwrapper and guiData
	public void transform(GuiData data) {
		gameActionProps = new HashMap<String, GameActionProp>();
		for (GameActionProp prop: data.getGameActionProps()) {
			gameActionProps.put(prop.getName(), prop);
		}
	}
	
	public GameActionProp  getActionProp(String name) {
		return gameActionProps.get(name);
	}
}
