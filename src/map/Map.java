package src.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import src.entity.Entity;
import src.object.SObject;
import src.tile.Tile;

/**
 * Map:         basic details for every map
 * 
 * @author      Will Otterbein
 * @version     2024-1
 */
public class Map {

    List<Tile> mapTiles = new ArrayList<>();
    List<SObject> mapObjects = new ArrayList<>();
    List<Entity> mapEntities = new ArrayList<>();

    private String mapName = null;
    private File mapInfo = null;

    /**
     * Map:     constructs a map
     * 
     * @param mapName           String, the name of the map
     * @param mapInfoPath       String, the path where to the map file
     */
    public Map(String mapName, String mapInfoPath) {
        this.mapName = mapName;
        try {
            mapInfo = new File(mapInfoPath);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * loadMap:  populates Tile, Objects and Entities lists.
     */
    public void loadMap() {
    	BufferedReader br;
        if (mapInfo != null) {
        	try {
				br = new BufferedReader(new FileReader(mapInfo));
				String line;
				while ((line = br.readLine()) != null) {
					String[] lines = line.split(" ");	// Split line along default regex " "
					for (String c : lines) {
						System.out.printf("%s ", c);
					}
					System.out.print('\n');
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }
}
