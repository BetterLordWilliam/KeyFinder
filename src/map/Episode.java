package src.map;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;

import src.main.Main;
import src.util.KFileReader;

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
    private File episodeData = new File(".\\res\\episodes\\episode1.xml");		// Default to the first episode
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
		mapIndexCap = maps.size();
    }
    
    /**
     * setEpisodeName:				sets the episode name to be the string 
     * 								in parameters.
     * 
     * @param episodeName			String, new episode name
     */
    public void setEpisodeName(String episodeName) {
    	if (episodeName != null)
    		this.episodeName = episodeName;
    }
    
    /**
     * setEpisodeDescription: 		sets the episode description to be the string 
     * 								in parameters.
     * 
     * @param episodeDescription
     */
    public void setEpisodeDescription(String episodeDescription) {
    	if (episodeDescription != null)
    		this.episodeDescription = episodeDescription;
    	
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
		currentMap.loadMap();
		mapIndex++;
    }
    
    /**
     * reloadMap:			reloads the current map.
     */
    public void reloadCurrentMap() {
		currentMap.loadMap();
    }

    /**
     * startEpisode:                begins current episode at first map
     */
    public void startEpisode() {
    	mapIndex = 0;									// Reset the map index
    	loadNextMap();
    }
   
    /**
     * loadEpisode:			initializes current episode.
     */
    private void loadEpisode() {
    	try {
			KFileReader.readEpisodeFile(episodeData, this);
		} catch (ParserConfigurationException e) {
			System.err.println("There was an error reading the episode file: ");
			e.printStackTrace();
			Main.terminate();
		}
    }
    
    /**
     * toString:			returns string representation of the episode.
     * 
     * @return episode		String, episode variables
     */
    @Override
    public String toString() {
    	return (
			episodeName + ": " + episodeDescription + "\n"
			+ "Maps:\n" + maps.toString() + "\n"
		); 
    }
}
