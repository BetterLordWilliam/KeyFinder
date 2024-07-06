package src.map;

/**
 * FileFlagConstants:		constants for the flags used in reading KeyFinder files.
 * 
 * @author					Will Otterbein
 * @version					2024-1
 */
public class FileFlagConstants {
	
	/**
	 * FileFlagConstants no-arg constructor.
	 */
	public FileFlagConstants() {}
	
	// GENERIC FLAGS
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	
	// EPISODE SPECIFIC FILE FLAGS
	public static final String MAP_PATHS_START = "mapPathsStart";
	public static final String MAP_PATHS_END = "mapPathsEnd";
	
	// MAP SPECIFIC FILE FLAGS
	public static final String MAP_TILE_START = "mapTiles";
	public static final String MAP_TILE_END = "mapTilesEnd";
	public static final String MAP_ENTITY_START = "mapEntities";
	public static final String MAP_ENTITY_END = "mapEntitiesEnd";
	public static final String MAP_OBJECT_START = "mapObjects";
	public static final String MAP_OBJECT_END = "mapObjectsEnd";
}