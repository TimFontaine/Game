package gameserver.server.executors;

import gameserver.data.GameMap;
import gameserver.data.Node;
import gameserver.data.TerrainType;

public class WorldBuilder {

	
	public GameMap createWorld() {
		GameMap gameMap = new GameMap(1000,1000);
		for (int x = 0; x<gameMap.getSizeX();x++) {
			for (int j=0; j<gameMap.getSizeY(); j++) {
				Node node = new Node(TerrainType.SEA);
				gameMap.setNode(x, j, node);
			}
		}
		
		return gameMap;
	}
}
