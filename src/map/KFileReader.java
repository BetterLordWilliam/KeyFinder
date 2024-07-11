package src.map;

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import src.main.Main;
import src.tile.Tile;

import org.w3c.dom.Document; 
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.io.IOException;

/**
 * Loader:			used specifically during the loading of a map.
 * 
 * @author			Will Otterbein
 * @version			2024-1
 */
interface Loader<T1, T2, T3> {
	/**
	 * loadFunction:		loads stuff. Three parameter version
	 * 
	 * @param <T1>			usually a String
	 * @param <T2>			usually an Integer
	 * @param <T3>			usually an Integer
	 * @param parm1			
	 * @param parm2
	 * @param parm3
	 */
	public void loadFunction(T1 parm1, T2 parm2, T3 parm3);
}

/**
 * KFileReader:			reads KeyFinder files (hence 'K' prefix).
 * 
 * @author				Will Otterbein
 * @version				2024-1
 */
public class KFileReader {
	// XML Element Names
	private static final String MAP_PATH_ELEMENT = "Mappath";

	// XML Attribtue Names
	private static final String NAME = "name";
	private static final String DESCRIPTION = "description";
	private static final String EPISODE_DATA_ID = "episodeData";
	private static final String MAP_DATA_ID = "mapData";
	private static final String MAP_TILE_LIST_ID = "mapTiles";
	private static final String MAP_OBJECT_LIST_ID = "mapObjects";
	private static final String MAP_ENTITY_LIST_ID = "mapEntities";
	   
    /**
     * loadStuff:			loads stuff, tiles, objects or entities. Assumed to be CSV format.
     * 
     * @param content		String, content to be processed
     * @param loader		Loader, interface used for loading either tiles, objects or entities
     * @throws IOException
     */
	private static void loadStuff(String content,
    		Loader<String, Integer, Integer> loader) throws IOException {
    	
    	// Parse the content using the loader interface in the parameter.
		// Simply adds tells the map if we are scanning for tiles, obejcts or entities.
		// Creates appropriate thing and adds to the appropriate list.
    	String[] lines = content.split("\n"); 
    	for (int scanY = 0; scanY < lines.length; scanY++) {
    		String[] items = lines[scanY].split(",");
    		for (int scanX = 0; scanX < items.length; scanX++) {
    			String item = items[scanX].trim();
    			if (!item.isBlank())
					loader.loadFunction(item, scanX, scanY);
    		}
    	}
    }
    
	/**
	 * readEpisodeFile:		reads a KeyFinder xml episode file.
	 * 
	 * @param ePath
	 * @param e
	 * @throws ParserConfigurationException 
	 */
	public static void readEpisodeFile(File ePath, Episode e) throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		try {
			// Parse the XML file
			Document document = builder.parse(ePath);
			Element episodeData = document.getElementById(EPISODE_DATA_ID);
			
			// Configure data
			e.setEpisodeName(episodeData.getAttribute(NAME));
			e.setEpisodeDescription(episodeData.getAttribute(DESCRIPTION));
			
			// Initialize the maps
			NodeList nodeList = document.getElementsByTagName(MAP_PATH_ELEMENT);
			for (int i = 0; i < nodeList.getLength(); i++) {
				String content = nodeList.item(i).getTextContent();
				if (!content.isBlank()) {
					Map nmap = new Map(new File(content));
					e.getMapList().add(nmap);
				}
			}
		} catch (SAXException | IOException er) {
			System.err.println("An exception occured while parsing the episodefile: ");
			er.printStackTrace();
			Main.terminate();
		}
	}
	
	/**
	 * readMapFile:				reads the initilisation details of a KeyFinder
	 * 							mapFile. Name, description, player start position. 
	 * 
	 * @param mPath
	 * @param m
	 * @throws ParserConfigurationException
	 */
	public static void readMapFileDetails(File mPath, Map m) throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		try {	
			// Parse the XML file
			Document document = builder.parse(mPath);
			Element mapData = document.getElementById(MAP_DATA_ID);
			
			// Configure data
			m.setMapName(mapData.getAttribute(NAME));
			m.setMapDescription(mapData.getAttribute(DESCRIPTION));
		} catch (SAXException | IOException e) {
			System.err.println("An exception occured while parsing the mapfile: ");
			e.printStackTrace();
			Main.terminate();
		}
	}
	
	/**
	 * readMapFileContents:		reads the contents of the mapFile.
	 * 							Tiles, Objects, Entities.
	 * 
	 * @param m
	 * @throws ParserConfigurationException
	 */
	public static void readMapFileContents(Map m) throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		try {
			// Parse the XML file
			Document document = builder.parse(m.getMapData());
			Element tileData = document.getElementById(MAP_TILE_LIST_ID);
			Element objectData = document.getElementById(MAP_OBJECT_LIST_ID);
			Element entityData = document.getElementById(MAP_ENTITY_LIST_ID);
			
			// Initialize tiles
			loadStuff(tileData.getTextContent(), (string, posx, posy) -> {
				Tile nTile = Tile.TileMaker.makeTile(string, posx, posy);
				m.getTiles().add(nTile);
			});
			
			// Initialize objects
			// ...
			
			// Initialize entities
			// ...
		} catch (SAXException | IOException e) {
			System.err.println("An exception occured while parsing the mapfile: ");
			e.printStackTrace();
			Main.terminate();
		}
	}
}
