package src.tile;

import javax.xml.parsers.ParserConfigurationException;

import src.main.Main;
import src.util.KFileReader;

public class TileRegistry {
	public static final String TILE_REGISTRY_PATH = ".\\res\\tiles\\tileDataRegistry.xml";

	
	
	public TileRegistry() {
		try {
			KFileReader.readTileRegistry(this);
		} catch (ParserConfigurationException e) {
			System.err.println("An exception occured while attempting to read the Tile Registry file.");
			e.printStackTrace();
			Main.terminate();
		}
	}
}
