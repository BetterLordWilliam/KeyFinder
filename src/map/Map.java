package src.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
    private File mapData = new File(".\\res\\maps\\world01.txt");			// Default to map
    
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
    
    /**
     * loadStuff:			loads stuff, tiles, objects or entities.
     * 
     * @param br			BufferedReader, should be currently reading the file
     * @param stopString	String, flag that marks the end of the loading process
     * @param loader		Loader, interface used for loading either tiles, objects or entities
     * @throws IOException
     */
    private void loadStuff(BufferedReader br, String stopString,
    		Loader<String, Integer, Integer> loader) throws IOException {
    	int posX = 0, posY = 0;
    	String line;
    	while (!((line = br.readLine()).contains(stopString))) {
    		String[] lits = line.split(",");	// Split items along comma (csv-like assumed)
    		for (String s : lits) {
    			System.out.print(s);
    			loader.loadFunction(s, posX, posY);
    			posX++;
    		}
    		posY++;
    		System.out.print('\n');
    	}
    }
    
    /**
     * loadMap:  populates Tile, Objects and Entities lists.
     * 
     * @throws IOException
     */
    public void loadMap() throws IOException {
    	BufferedReader br = null;
    	
    	try {
    		br = new BufferedReader(new FileReader(mapData), 256);
    		String line;
    		while ((line = br.readLine())!= null) {
    			/*
				 * Following loadStuff methods use the Loader functional 
    			 * interface method loadFunction in their lambda expressions
    			 */
    			if (line.contains(MAP_TILE_START)) {
    				loadStuff(br, MAP_TILE_END, (string, posx, posy) -> {				// Initializes map Tiles
    					mapTiles.add(Tile.TileMaker.makeTile(string, posx, posy));});
    			} else if (line.contains(MAP_OBJECT_START)) {
    				loadStuff(br, MAP_OBJECT_END, (string, posx, posy) -> {				// Initializes map SObjects
						mapObjects.add(SObject.SObjectMaker.makeSObject(string, posx, posy));});
    			} else if (line.contains(MAP_ENTITY_START)) {
    				loadStuff(br, MAP_ENTITY_END, (string, posx, posy) -> {				// Initializes map Entities
    					mapEntities.add(Entity.EntityMaker.makeEntity(string, posx, posy));});
    				
    			} else {
					mapName = (line != null && line.contains(NAME)) 
							? line.split(":")[1].trim() : mapName;			// Extract the value of name
					mapDescription = (line != null && line.contains(DESCRIPTION)) 
							? line.split(":")[1].trim() : mapDescription;	// Extract the value of description
    			}
    		}
    		
    		System.out.printf("name: %s\n", mapName);
    		System.out.printf("description: %s\n", mapDescription);
    		
    	} catch (IOException e) {
    		System.err.println("An error occured while loading the map.");
    		e.printStackTrace();
    		Main.terminate();
    	} finally {
    		br.close();
    	}
    }
}
