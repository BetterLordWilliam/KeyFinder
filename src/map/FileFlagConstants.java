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
	public static final String NAME = "_name:";
	public static final String DESCRIPTION = "_description:";
	
	// EPISODE SPECIFIC FILE FLAGS
	public static final String MAP_PATHS_START = "_map_paths_start";
	public static final String MAP_PATHS_END = "_map_paths_end";
	
	// MAP SPECIFIC FILE FLAGS
	public static final String MAP_TILE_START = "_map_tiles";
	public static final String MAP_TILE_END = "_map_tiles_end";
	public static final String MAP_ENTITY_START = "_map_entities";
	public static final String MAP_ENTITY_END = "_map_entities_end";
	public static final String MAP_OBJECT_START = "_map_objects";
	public static final String MAP_OBJECT_END = "_map_objects_end";
}