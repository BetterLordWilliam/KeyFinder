package src.resources.map;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import src.main.Main;
import src.resources.entity.Entity;
import src.resources.object.SObject;
import src.resources.tile.Tile;

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
    private File mapData = new File(".\\res\\maps\\world01.txt");			// Default to map
    
    /**
     * Map:     constructs a map
     * 
     * @param mapName           String, the name of the map
     * @param mapInfoPath       String, the path where to the map file
     */
    public Map(File mapData) {
		if (mapData != null) {
			this.mapData = mapData;
			try {
				// KFileReader.readMapFileDetails(mapData, this);
				throw new ParserConfigurationException();
			} catch (ParserConfigurationException e) {
				System.err.println("There was an error reading the map file contents: ");
				e.printStackTrace();
				Main.terminate();
			}
		} else {
			System.err.println("Mapdata cannot be null!");
			Main.terminate();
		}
    }
  
    /**
     * getMapData:		returns the mapData file.
     * 
     * @return mapData	File, file with the map data in it
     */
    public File getMapData() {
    	return mapData;
    }
   
    /**
     * getMapName:		returns the name of the map
     * 
     * @return mapName 	String, the name of the map
     */
    public String getMapName() {
    	return mapName;
    }
    
    /**
     * setmapName:				sets the map name to be the string 
     * 								in parameters.
     * 
     * @param mapName			String, new map name
     */
    public void setMapName(String mapName) {
    	if (mapName != null)
    		this.mapName = mapName;
    }
    
    /**
     * setmapDescription: 		sets the map description to be the string 
     * 								in parameters.
     * 
     * @param mapDescription
     */
    public void setMapDescription(String mapDescription) {
    	if (mapDescription != null)
    		this.mapDescription = mapDescription;
    	
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

    
    /**
     * loadMap:  populates Tile, Objects and Entities lists.
     * 
     * @throws IOException
     */
    public void loadMap() {
    	try {
    		// KFileReader.readMapFileContents(this);
			throw new ParserConfigurationException();
    	} catch (ParserConfigurationException e) {
    		System.err.println("There was an error reading the map file contents: ");
    		e.printStackTrace();
    		Main.terminate();
    	}
    }
    
    public String toString() {
    	return (
			mapName + "\n" + mapDescription + "\n" +
			mapTiles + "\n" + mapObjects + "\n" + mapEntities + "\n"
		);
    }
}
