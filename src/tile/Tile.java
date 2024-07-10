package src.tile;

import java.util.Map;

import static java.util.Map.entry;

import src.main.Main;
import src.main.Paintable;

interface TileFactory <T> {
	T newInstance();
}

/**
 * Tile:		The abstract implementaiton for Tile classes.
 * 				Contains methods, data and factories necessary for tile construction.
 * 				Additionally, the class has information pertaining to the TileTypes.
 * 
 * @author		Will Otterbein
 * @version		2024-1
 */
public abstract class Tile implements Paintable {
	
	/**
	 * TileType:			defines the tile types in association with the
	 * 						literals read from map files.
	 */
	protected static enum TileType {
		DIRT(0), GRASS(1), WALL(2), WATER(3), WOOD(4),
		TREE(5), KNIGHT_STATUE(6), BANNER_WALL(7), LILYPAD(8);
		
		private final int typeId;
		
		/**
		 * TileType:		Constructs TileType.
		 * 
		 * @param typeId	int, assigns typeId
		 */
		TileType(int typeId) {
			this.typeId = typeId;
		}
		
		/**
		 * getTypeId:		returns the typeId.
		 * 
		 * @return typeId	int, numeric representation
		 */
		public int getTypeId() {
			return typeId;
		}
	}
	
	/*
	 * Tile factory map 
	 */
	private static final Map<TileType, TileFactory<? extends Tile>> tileFactories = Map.ofEntries(
		entry(TileType.DIRT, () -> new DummyTile()),
		entry(TileType.GRASS, () -> new DummyTile()),
		entry(TileType.WALL, () -> new DummyTile()),
		entry(TileType.WATER, () -> new DummyTile()),
		entry(TileType.WOOD, () -> new DummyTile()),
		entry(TileType.TREE, () -> new DummyTile()),
		entry(TileType.KNIGHT_STATUE, () -> new DummyTile()),
		entry(TileType.BANNER_WALL, () -> new DummyTile()),
		entry(TileType.LILYPAD, () -> new DummyTile())
	);
	
	/**
	 * TileMaker:			Makes new tiles.
	 * 
	 * @param type			String, tile type info
	 * @param tX			position of the tile, x-axis
	 * @param tY			position of the tile, y-axis
	 * @return				Tile, a new tile with the specified info
	 */
	public static class TileMaker {
		public static Tile makeTile(String type, int tX, int tY) {
			TileType ty = null;
			try {
				ty = TileType.values()[Integer.parseInt(type.trim())];
				// Retrieve the TileType based of raw data, use as index
			} catch (NumberFormatException e) {
				System.err.println("Invalid tileType encountered ("+ type + ") :");
				e.printStackTrace();
				Main.terminate();
			} catch (IndexOutOfBoundsException e) {
				System.err.println("Unknown tileType encountered ("+ type +") :");
				e.printStackTrace();
				Main.terminate();
			}
			
			Tile newTile = tileFactories.get(ty).newInstance();
			newTile.setLocalType(ty);		// Establish the type locally
			newTile.setTxTy(tX, tY);		// Establish the 'X' and 'Y' positions
			return newTile;
		}
	}

	
	protected int tX, tY;
	protected TileType type;
	
	public abstract void setTxTy(int tX, int tY);
	public abstract void setLocalType(TileType type);
	
	// For debugging purposes
	public String toString() {
		return (
			"[ " + type + " | " 
			+ tX + "," + tY + " ]"
		);
	}
}
