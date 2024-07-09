package src.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import src.main.Main;

import static src.map.FileFlagConstants.NAME;
import static src.map.FileFlagConstants.DESCRIPTION;
import static src.map.FileFlagConstants.MAP_PATHS_START;
import static src.map.FileFlagConstants.MAP_PATHS_END;

/**
 * Episode:      Will be responsible for controlling the sequence through episodes.
 * 
 * @author              Will Otterbein
 * @version             2024-1
 */
public class Episode {
	private int mapIndex = 0;
	private int mapIndexCap = 0;
	
	// EPISODE OBJECTS
	private List<Map> maps = new LinkedList<>(); 
    private Map currentMap = null;
    private File episodeData = new File(".\\res\\episodes\\episode1.txt");		// Default to the first episode
    private String episodeName = null;
    private String episodeDescription = null;
    
    /**
     * Episode:				constructs an Episode.
     * 
     * @param episodeData	File, file with the episode data
     */
    public Episode(File episodeData) {
    	if (episodeData != null)
    		this.episodeData = episodeData;
    	
    	// TEMPORARY
		loadEpisode();
		mapIndexCap = maps.size() - 1;
    }
    
    /**
     * loadEpisode:			initializes current episode.
     */
    private void loadEpisode() {
    	try {
			KFileReader.readEpisodeFile(episodeData, this);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * getMapList:			returns the list of maps.
     * 
     * @return maps			List<Map>, the list of maps
     */
    public List<Map> getMapList() {
    	return maps;
    }
    
    /**
     * getCurrentMap:		returns the current map.
     * 
     * @return currentMap	Map, the reference to the current map object.
     */
    public Map getCurrentMap() {
    	return currentMap;
    }
    
    /**
     * loadNextMap:			loads the next map.
     */
    public void loadNextMap() {
    	if (mapIndex >= mapIndexCap) {
    		System.out.println("End");
    		mapIndex = 0;
    		Main.setState(Main.MAIN_MENU);				// End the game at last level for know.
    		return;
    	}
    	currentMap = maps.get(mapIndex);
    	try {
    		currentMap.loadMap();
    	} catch (IOException e) {
    		System.err.println("Cannot load map");
    		e.printStackTrace();
    		Main.terminate();
    	}
    	mapIndex++;
    }
    
    /**
     * reloadMap:			reloads the current map.
     */
    public void reloadMap() {
    	try {
    		currentMap.loadMap();
    	} catch (IOException e) {
    		System.err.println("Cannot load map");
    		e.printStackTrace();
    		Main.terminate();
    	}
    	mapIndex++;
    }
    
    /**
     * startEpisode:                begins current episode at first map
     */
    public void startEpisode() {
    	mapIndex = 0;
    	// currentMap = maps.get(mapIndex);
    	// reloadMap();
    }
}
