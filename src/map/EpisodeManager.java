package src.map;

import java.util.LinkedList;
import java.util.List;
import src.entity.Entity;
import src.object.SObject;
import src.tile.Tile;

/**
 * EpisodeManager:      Will be responsible for controlling the sequence through episodes.
 * 
 * @author              Will Otterbein
 * @version             2024-1
 */
public class EpisodeManager {
    private List<Map> maps = new LinkedList<>(); 
    private Map currentMap = null;

    /**
     * no-arg constructor
     */
    public EpisodeManager() {
        linkMaps();
    }

    /**
     * linkMaps:        adds all the game maps to a list (LinkedList)
    */ 
    private void linkMaps() {
        maps.add(new Map("map01", "../../res/maps/world1.txt"));
        maps.add(new Map("map02", "../../res/maps/world2.txt"));
        maps.add(new Map("map03", "../../res/maps/world3.txt"));
        maps.add(new Map("map04", "../../res/maps/world4.txt"));
    }

    /**
     * getCurrentLevelTiles:        retrieves current map tiles.
     */
    public List<Tile> getCurrentLevelTiles() {
        if (currentMap != null)
            return currentMap.mapTiles;
        else    
            return null;
    }

    /**
     * getCurrentLevelObjects:      retrieves current map objects.
     */
    public List<SObject> getCurrentLevelObjects() {
         if (currentMap != null)
            return currentMap.mapObjects;
        else    
            return null;
    }

    /**
     * getCurrentLevelEntities:     retrieves the entities.
     * 
     * @return
     */
    public List<Entity> getCurrentLevelEntities() {
        if (currentMap != null)
            return currentMap.mapEntities;
        else    
            return null;
    }

    /**
     * startEpisode:                begins current episode at first map
     */
    public void startEpisode() {

    }
}
