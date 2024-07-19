package src.tile;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import src.main.Main;
import src.util.KFileReader;

/**
 * TileRegistry:					The class responsible for parsing tile data from the
 * 									registry file and producing a list of tiles which can
 * 									be cloned in the future.
 * 
 * @author							Will Otterbein
 * @version							2024-1
 */
public class TileRegistry {
	public static final String TILE_REGISTRY_PATH = ".\\res\\tiles\\tileDataRegistry.xml";

	private Map<String, Tile> tileRegistry = new HashMap<>();
	private String nullTileId = "T9999";		
		// Id of the 'null' tile, use this instead of terminating the game
	
	/**
	 * no-arg constructor
	 */
	public TileRegistry() {
		try {
			KFileReader.readTileRegistry(this);
				// Upon initalization of class, init the registry
				// Read the tile types from the tile registry file
		} catch (ParserConfigurationException e) {
			System.err.println("An exception occured while attempting to read the Tile Registry file.");
			e.printStackTrace();
			Main.terminate();
		}
	}
	
	/**
	 * addTileToRegistry:			Adds a new tile to the registry.
	 * 
	 * @param tileId				String, the literal value of the tile
	 * @param tileTexturePath		The path that points to the main tile resource
	 */
	public void addTileToRegistry(String tileId, Tile newTile) {
		if (tileId != null && newTile != null) {
			tileRegistry.put(newTile.getTileId(), newTile);
		}
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
		// Return the tile instance matching key and clone it
		Tile result = tileRegistry.get(tileId);
		
		// Otherwise, return a new instance of the null tile
		if (result == null) {
			System.err.println("Bad tileId: " + tileId);
			result = createNullTile();
		} else
			result = result.clone();
			
		return result;
	}
}
