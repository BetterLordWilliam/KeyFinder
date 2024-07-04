package src.map;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import src.entity.Entity;
import src.main.Main;
import src.object.SObject;
import src.tile.Tile;

import static src.map.FileFlagConstants.NAME;
import static src.map.FileFlagConstants.DESCRIPTION;
import static src.map.FileFlagConstants.MAP_TILE_START;
import static src.map.FileFlagConstants.MAP_TILE_END;
import static src.map.FileFlagConstants.MAP_OBJECT_START;
import static src.map.FileFlagConstants.MAP_OBJECT_END;
import static src.map.FileFlagConstants.MAP_ENTITY_START;
import static src.map.FileFlagConstants.MAP_ENTITY_END;

/**
 * Map:         basic details for every map
 * 
 * @author      Will Otterbein
 * @version     2024-1
 */
public class Map {
	// MAP OBJECTS
    private List<Tile> mapTiles = new ArrayList<>();
    private List<SObject> mapObjects = new ArrayList<>();
    private List<Entity> mapEntities = new ArrayList<>();

    private String mapName = null;
    private String mapDescription = null;
    private File mapData = new File("./res/maps/world01.txt");			// Default to map
    private Map currentMap = null;
    
    /**
     * Map:     constructs a map
     * 
     * @param mapName           String, the name of the map
     * @param mapInfoPath       String, the path where to the map file
     */
    public Map(File mapData) {
		if (mapData != null)
			this.mapData = mapData;
		else {
			System.err.println("Map could not be loaded");
			Main.terminate();
		}
    }
    
    /**
     * getTiles:			returns that maps Tiles.
     * 
     * @return mapTiles,	List<Tile>, list of the Tiles
     */
    public List<Tile> getTiles() {
    	return mapTiles;
    }
    
    /**
     * getObjects:			returns the maps Objects.
     * 
     * @return mapObjects,	List<Object>, list of the SObjects
     */
    public List<SObject> getObjects() {
    	return mapObjects;
    }
    
    /**
     * getEntities:			returns the maps Entities.
     * 
     * @return mapEntities,	List<Entity>, list of the Entities
     */
    public List<Entity> getEntities() {
    	return mapEntities;
    }
    
    private void loadTiles() {
    	
    }
    
    private void loadObjects() {
    	
    }
    
    private void loadEntities() {
    	
    }
    
    /**
     * loadMap:  populates Tile, Objects and Entities lists.
     */
    public void loadMap() throws IOException {
    	
    }
}
