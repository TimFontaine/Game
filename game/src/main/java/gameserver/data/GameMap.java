package gameserver.data;

import java.io.Serializable;

public class GameMap implements Serializable {

	private Node[][] nodes;
	
	public GameMap(int x, int y) {
		nodes = new Node[x][y];
	}
	
	public Node getNode(int x, int y) {
		return nodes[x][y];
	}
	
	public int getSizeX() {
		return nodes.length;
	}
	
	public int getSizeY() {
		return nodes[0].length;
	}

	public void setNode(int x, int y, Node node) {
		nodes[x][y] = node;
	}
	
}
