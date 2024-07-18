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
	// TILE REGISTRY INFORMATION
	public static final String TILE_REGISTRY_PATH = ".\\res\\tiles\\tileDataRegistry.xml";

	// TILE REGISTRY OBJECTS
	private Map<String, Tile> tileRegistry = new HashMap<>();
	
	/**
	 * no-arg constructor
	 */
	public TileRegistry() {
		try {
			KFileReader.readTileRegistry(this);
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
	public void addTileToRegistry(String tileId, String tileTexturePath) {
		if (tileId != null && tileTexturePath != null) {
			Tile newTile = new Tile(tileId, tileTexturePath);
			tileRegistry.put(tileId, newTile);
		}
	}
}
