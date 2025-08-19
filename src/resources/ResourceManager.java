package src.resources;

import src.main.threads.LoaderThread;
import src.resources.map.Episode;
import src.resources.map.Map;
import src.resources.tile.TileRegistry;

public class ResourceManager {
	// PATHS
	public static final String EPISODE_REGISTRY_PATH = "";
	public static final String TILE_REGISTRY_PATH = ".\\res\\tiles\\tileDataRegistry.xml";
	public static final String OBJECT_REGISTRY_PATH = "";
	public static final String ENTITY_REGISTRY_PATH = "";
	
	// REGISTRY OBJECTS, USED DURING MAP LOADING
	private TileRegistry tileRegistry = new TileRegistry();
	// private EntityRegistry entityRegistry = new EntityRegistry();
	// private ObjectRegistry objectRegistry = new ObjectRegistry();

	// WORKING OBJECTS
	private Episode currentEpisode;
	private Map currentMap;
	
	public ResourceManager() {
		loadTileRegistry();
		// loadEntityRegistry();
		// loadObjectRegistry();
	}
	
	public Map getCurrentMap() { return currentMap; };
	public Episode getCurrentEpisode() { return currentEpisode; }
	
	private void loadTileRegistry() {
		System.out.println("Loading tile registry");
	}
	private void loadEntityRegistry() {}
	private void loadObjectRegistry() {}
	
	public void loadEpisode() {}
	public void loadMap() {}
}