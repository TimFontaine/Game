package gameserver.client.graphics;

import gameserver.client.Game;
import gameserver.client.GameClient;
import gameserver.client.core.ResourceManager;
import gameserver.data.GameMap;
import gameserver.data.Map;
import gameserver.data.Node;
import gameserver.data.TerrainType;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;



public class MapViewer {

	private static final int tile_size = 50;
	private ResourceManager resourceManager;

	@Inject Game game;
	
	public MapViewer() {
		this.resourceManager = ResourceManager.getInstance();
	}
	
	public void draw(Graphics2D g) {
			System.out.println("redrawing");
			//the game is playing
			
			if (game.getMap() != null) {
				drawMap(g);
			}
			//initial startup fase, paint swing
//			g.drawRect(500, 500, 10, 10);
//			g.dispose();
	}

	private void drawMap(Graphics2D g) {
		GameMap map = game.getMap();
		Rectangle bounds = g.getClipBounds();
		
		int tilesOnScreenX = (bounds.width / tile_size) + 1;
		int tilesOnScreeny = (bounds.height/ tile_size) + 1;

 //		g.setColor(Color.black);
//        g.fillRect(bounds.x, bounds.y,
//                   bounds.width, bounds.height);
		AffineTransform baseTransform = g.getTransform();
		AffineTransform rowTransform = null;
		for (int i= 0; i<tilesOnScreenX; i++) {
			rowTransform = g.getTransform();
			for (int j=0; j<tilesOnScreeny; j++) {
				g.drawRect(0, 0, 50, 50);
				Node node = map.getNode(i, j);
//				boolean explored = ((RosePlayer)roseClient.getPlayer()).isTileExplored(node);
//				if (!explored) {
//					g.drawImage(resourceManager.getImage("unexplored"), 0, 0, null);
//				} else {
					drawTerrain(g, node);
//					drawTileItems(g, node);
//					if (node.getCity() != null) {
//						drawCity(g, node);
//					}
//					Unit unit = getUnitInFront(node);
//					if (unit != null) {
//						drawUnit(g, unit);
//					}
					
//				}
				g.translate(0, tile_size);
				
			}
			g.setTransform(rowTransform);
			g.translate(tile_size, 0);
			
		}
		g.setTransform(baseTransform);
	}

	private void drawTerrain(Graphics2D g, Node node) {
		TerrainType type = node.getTerrainType();
//		if (type != null) {
			String id = type.toString().toLowerCase();
			Image terrainImage = resourceManager.getImage(id);
			g.drawImage(terrainImage, 0, 0, null);
	}
	
}
