package src.map;

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer; 
import javax.xml.transform.TransformerFactory; 
import javax.xml.transform.dom.DOMSource; 
import javax.xml.transform.stream.StreamResult; 
import org.xml.sax.SAXException;
import org.w3c.dom.Document; 
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.io.IOException;

public class KFileReader {
	// XML Element Names
	private static final String MAP_PATH = "mappath";
	
	// XML Class Instances
	private static DocumentBuilderFactory factory;
	private static DocumentBuilder builder;
	
	/**
	 * readEpisodeFile:		reads a KeyFinder xml episode file.
	 * 
	 * @param ePath
	 * @param e
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public static void readEpisodeFile(File ePath, Episode e) 
			throws ParserConfigurationException, SAXException, IOException {
		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
		
		// Parse the XML file
		Document document = builder.parse(ePath);
		
		// Configure data
		NodeList nodeList = document.getElementsByTagName(MAP_PATH);
		for (int i = 0; i < nodeList.getLength(); i++) {
			System.out.println(nodeList.item(i).getTextContent());
			// e.getMapList().add(null);
		}
	}
	
	/**
	 * readMapFile:			reads a KeyFinder xml mapfile.
	 * 
	 * @param mPath
	 * @param m
	 */
	public static void readMapFile(File mPath, Map m) {
		
	}
}
