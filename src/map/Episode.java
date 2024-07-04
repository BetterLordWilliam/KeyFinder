package src.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
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
    private File episodeData = new File("./res/episodes/episode1.txt");		// Default to the first episode
    private String episodeName = null;
    private String episodeDescription = null;
    
    /**
     * no-arg constructor
     */
    public Episode(File episodeData) {
    	if (episodeData != null)
    		this.episodeData = episodeData;
    	try {
    		loadEpisode();
    		mapIndexCap = maps.size() - 1;
    	} catch(IOException e) {
    		System.err.println("Episode could not be loaded.");
    		e.printStackTrace();
    		Main.terminate();
    	}
		startEpisode();													// Temporary episode start, need to create episode selector
    }
    
    /**
     * loadEpisode:			initializes current episode.
     * 
     * @throws IOException 
     */
    private void loadEpisode() throws IOException {
    	BufferedReader br = null;
    	int mapList = 0;
    	
    	try {
    		br = new BufferedReader(new FileReader(episodeData));
    		String line = br.readLine();
    		
    		while (line != null) {
    			if (line != null && line.equals(MAP_PATHS_END))
    				mapList = 0;
    			if (mapList == 1)
    				maps.add(new Map(new File(line)));
    			if (line != null && line.equals(MAP_PATHS_START))
    				mapList = 1;
    			episodeName = (line != null && line.contains(NAME)) 
    					? line.split(":")[1].trim() : episodeName;			// Extract the value of name
    			episodeDescription = (line != null && line.contains(DESCRIPTION)) 
    					? line.split(":")[1].trim() : episodeDescription;	// Extract the value of description
    			line = br.readLine();						// Advance to next line
    		}
    		
    	} catch (FileNotFoundException e) {
    		System.err.println("An exception occured while loading the episode (likely incorrect episode path): ");
    		e.printStackTrace();
    		Main.terminate();
    	} catch (IOException e) {
    		System.err.println("An exception occured while loading the episode (episode data likely bad): ");
    		e.printStackTrace();
    		Main.terminate();
    	} finally {
    		br.close();
    	}
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
    public void loadAmap() {
    	if (mapIndex >= mapIndexCap) {
    		System.out.println("End");
    		Main.terminate();				// End the game at last level for know.
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
     * startEpisode:                begins current episode at first map
     */
    public void startEpisode() {
    	currentMap = maps.get(0);
    	loadAmap();
    }
}
