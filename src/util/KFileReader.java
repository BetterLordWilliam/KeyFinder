package src.util;

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.w3c.dom.Document; 
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.IOException;

import src.main.Main;
import src.resources.ResourceManager;
import src.resources.map.Episode;
import src.resources.map.Map;
import src.resources.object.SObject;
import src.resources.tile.Tile;
import src.resources.tile.TileRegistry;

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
	public KFileReader() {}		// Private constructor, cannot be initialized
	
    /**
     * loadStuff:			loads stuff, tiles, objects or entities. Assumed to be CSV format.
     * 
     * @param content		String, content to be processed
     * @param loader		Loader, interface used for loading either tiles, objects or entities
     * @throws IOException
     */
	private void loadStuff(String content,
    		Loader<String, Integer, Integer> loader) throws IOException {
		
		int x = 0;			// Origin X
		int y = -1;			// Origin Y, I do not know why it seems to count some extra row, but it does
		
    	// Parse the content using the loader interface in the parameter.
		// Simply adds tells the map if we are scanning for tiles, obejcts or entities.
		// Creates appropriate thing and adds to the appropriate list.
    	String[] lines = content.split("\n"); 
    	for (int scanY = 0; scanY < lines.length; scanY++) {
    		String[] items = lines[scanY].split(",");
    		for (int scanX = 0; scanX < items.length; scanX++) {
    			String item = items[scanX].trim();
    			if (!item.isBlank()) {
					// System.out.printf("Literal: %s, X: %d, Y: %d\n", item, x, y);	// For debugging purposes
					loader.loadFunction(item, x, y);
    			}
    			x++;
    		}
			x = 0; y++;		// Reset the column counter after every row
    	}
    }
    
	/**
	 * readEpisodeFile:		reads a KeyFinder xml episode file.
	 * 
	 * @param ePath
	 * @param e
	 * @throws ParserConfigurationException 
	 */
	public void readEpisodeFile(File ePath, Episode e) throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		try {
			// Parse the XML file
			Document document = builder.parse(ePath);
			Element episodeData = document.getElementById(KFileInfo.EPISODE_DATA_ID);
			
			// Configure data
			e.setEpisodeName(episodeData.getAttribute(KFileInfo.NAME));
			e.setEpisodeDescription(episodeData.getAttribute(KFileInfo.DESCRIPTION));
			
			// Initialize the maps
			NodeList nodeList = document.getElementsByTagName(KFileInfo.MAP_PATH_ELEMENT);
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
		System.out.print("Loaded " + e + "\n");
	}
	
	/**
	 * readMapFile:				reads the initialization details of a KeyFinder
	 * 							mapFile. Name, description, player start position. 
	 * 
	 * @param mPath
	 * @param m
	 * @throws ParserConfigurationException
	 */
	public void readMapFileDetails(File mPath, Map m) throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		try {	
			// Parse the XML file
			Document document = builder.parse(mPath);
			Element mapData = document.getElementById(KFileInfo.MAP_DATA_ID);
			
			// Configure data
			m.setMapName(mapData.getAttribute(KFileInfo.NAME));
			m.setMapDescription(mapData.getAttribute(KFileInfo.DESCRIPTION));
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
	public void readMapFileContents(Map m) throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		try {
			// Parse the XML file
			Document document = builder.parse(m.getMapData());
			Element tileData = document.getElementById(KFileInfo.MAP_TILE_LIST_ID);
			Element objectData = document.getElementById(KFileInfo.MAP_OBJECT_LIST_ID);
			Element entityData = document.getElementById(KFileInfo.MAP_ENTITY_LIST_ID);
			
			// Initialize tiles
			loadStuff(tileData.getTextContent(), (string, posx, posy) -> {
				Tile nTile = Tile.TileMaker.makeTile(string, posx, posy);
				m.getTiles().add(nTile);
			});
			// Initialize objects
			loadStuff(objectData.getTextContent(), (string, posx, posy) -> {
				SObject nObject = SObject.SObjectMaker.makeSObject(string, posx, posy);
				m.getObjects().add(nObject);
			});
			
			// Initialize entities
			// ...
		} catch (SAXException | IOException e) {
			System.err.println("An exception occured while parsing the mapfile: ");
			e.printStackTrace();
			Main.terminate();
		}
		System.out.print("Loaded " + m + "\n");
	}
	
	/**
	 * readTileDataChildren:		reads the child nodes of a TileData element.
	 * 
	 * @param childNodes			NodeList, the list of the child nodes
	 * @param tr					TileRegistry, references to the tile registry
	 */
	private void readTileDataChildren(NodeList childNodes, Tile newTile) {
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node node = childNodes.item(i);
			NodeList nodeList = node.getChildNodes();
			if (node.getNodeName().equals(KFileInfo.ANIMATION_FRAMES) && nodeList != null)
				readAnimationFrames(nodeList, newTile);
		}
	}
	
	/**
	 * readAnimationFrames:			reads the data from the individual frames.
	 * 
	 * @param animationFrames		NodeList, the list of the animation frames
	 * @param tr					TileRegistry, references to the tile registry
	 */
	private void readAnimationFrames(NodeList animationFrames, Tile newTile) {
		for (int i = 0; i < animationFrames.getLength(); i++) {
			Node node = animationFrames.item(i);
			if (node.getNodeName().equals(KFileInfo.ANIMATION_FRAME))
				System.out.println(node.getAttributes().getNamedItem(KFileInfo.TEXTURE_PATH).getNodeValue());
		}
	}
	
	/**
	 * readTileRegistry:			reads the tile registry file.
	 * 
	 * @param tr								TileRegistry, the registry object
	 * @throws ParserConfigurationException
	 */
	public void readTileRegistry(TileRegistry tr) throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		try {
			// Parse the XML file
			Document document = builder.parse(ResourceManager.TILE_REGISTRY_PATH);
			NodeList nodeList = document.getElementsByTagName(KFileInfo.TILE_DATA);
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				NodeList nodeSubList = node.getChildNodes();			// Complex node data, if it exists
				
				// Retrieve the data that should be in every tile.
				String tileId = node.getAttributes().getNamedItem(KFileInfo.ID).getNodeValue();
				String tileTexturePath = node.getAttributes().getNamedItem(KFileInfo.TEXTURE_PATH).getNodeValue();
				Tile newTile = new Tile(tileId, tileTexturePath);		// New tile.
				
				// Process the child nodes (if they exist)
				/*if (nodeSubList != null && nodeSubList.getLength() > 0)
					readTileDataChildren(nodeSubList, newTile);*/		// Ignore for the time being
				
				tr.addTileToRegistry(tileId, newTile);					// Add the tile to the registry
				// System.out.printf("%s\n", newTile);
			}
		} catch (SAXException | IOException e) {
			System.err.println("An exception occurred while parsing the mapfile: ");
			e.printStackTrace();
			Main.terminate();
		}
	}
}
