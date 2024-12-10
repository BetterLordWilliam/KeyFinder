package src.resources.tile;

import java.util.HashMap;
import java.util.Map;

/**
 * TileRegistry:					The class responsible for parsing tile data from the
 * 									registry file and producing a list of tiles which can
 * 									be cloned in the future.
 * 
 * @author							Will Otterbein
 * @version							2024-1
 */
public class TileRegistry {
	private Map<String, Tile> tileRegistry = new HashMap<>();
	
	// Id of the 'null' tile, use this instead of terminating the game
	private String nullTileId = "T9999";		
	
	/**
	 * addTileToRegistry:			Adds a new tile to the registry.
	 * 
	 * @param tileId				String, the literal value of the tile
	 * @param tileTexturePath		The path that points to the main tile resource
	 */
	public void addTileToRegistry(String tileId, Tile newTile) {
		if (tileId != null && newTile != null)
			tileRegistry.put(newTile.getTileId(), newTile);
	}
	
	/**
	 * createNullTile:				Creates a new instance of a null tile.
	 * 
	 * @return a new null tile		Tile, new instance of Tile with a null texture
	 */
	public Tile createNullTile() {
		return tileRegistry.get(nullTileId).clone();
	}
	
	/**
	 * cloneTileWithId:				Creates a new instance of the tile based off the Id.
	 * 
	 * @param tileId				String, tile Id
	 * @return						Tile, new tile
	 * @throws CloneNotSupportedException
	 */
	public Tile cloneTileWithId(String tileId) throws CloneNotSupportedException {
		// Return the tile instance matching key and
		// Clone it if it is not null. Otherwise, return a new instance of the null tile
		Tile result = tileRegistry.get(tileId);
		return result = (result == null) ? createNullTile() : result.clone();
	}
}
