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
    	try {
    		loadEpisode();
    		mapIndexCap = maps.size() - 1;
    	} catch(IOException e) {
    		System.err.println("Episode could not be loaded.");
    		e.printStackTrace();
    		Main.terminate();
    	}
    }
   
    /**
     * loadPaths:		loads the paths for the episode's maps. Creates them as files.
     * 
     * @param br			BufferedReader, the reader for the episode
     * @param stopString	String, the string that serves as the flag for when to stop reading
     * @param load			LoaderSimple<String>, the simple loader which is used to
     * @throws IOException
     */
    private void loadPaths(BufferedReader br, String stopString,
    		LoaderSimple<String> load) throws IOException {
    	String line;
    	while (!((line = br.readLine()).contains(stopString))) {
    		load.loadFunctionSimple(line);
    	}
    }
    
    /**
     * loadEpisode:			initializes current episode.
     * 
     * @throws IOException 
     */
    private void loadEpisode() throws IOException {
    	BufferedReader br = null;
    	
    	try {
    		br = new BufferedReader(new FileReader(episodeData), 256);
    		String line;
    		while ((line = br.readLine()) != null) {
			    /*
				 * Following loadPaths methods use the LoaderSimple functional 
    			 * interface method loadFunctionSimple in their lambda expressions.
    			 * This might be considered overkill for this particular application.
    			 */
    			if (line.contains(MAP_PATHS_START)) {
					loadPaths(br, MAP_PATHS_END,								// Initialize the map Files
    					(path) -> { maps.add(new Map(new File(path))); });
					
    			} else {
					episodeName = (line != null && line.contains(NAME)) 
							? line.split(":")[1] : episodeName;			// Extract the value of name
					episodeDescription = (line != null && line.contains(DESCRIPTION)) 
							? line.split(":")[1] : episodeDescription;	// Extract the value of description
    			}
    		}
    		
    		System.out.printf("name: %s\n", episodeName);
    		System.out.printf("description: %s\n", episodeDescription);
    		
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
    	currentMap = maps.get(mapIndex);
    	reloadMap();
    }
}
