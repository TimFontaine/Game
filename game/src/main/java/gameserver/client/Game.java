package gameserver.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import gameserver.data.GameMap;
import gameserver.data.Map;

//this contains all state data of the game
@ApplicationScoped
public class Game implements Serializable {
	
	private GameMap map;

	private List<Player> players;
	
	public Game() {
		players = new ArrayList<Player>();
	}
	
	public void setMap(GameMap map) {
		this.map = map;
	}
	
	public GameMap getMap() {
		return map;
	}

	public List<Player> getPlayers() {
		return players;
	}
}
