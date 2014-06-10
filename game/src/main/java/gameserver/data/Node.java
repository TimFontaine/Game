package gameserver.data;

import java.io.Serializable;

public class Node implements Serializable {

	private TerrainType terrainType;
	
	public Node(TerrainType terrainType) {
		this.terrainType = terrainType;
	}

	public TerrainType getTerrainType() {
		return terrainType;
	}

	public void setTerrainType(TerrainType terrainType) {
		this.terrainType = terrainType;
	}
}
