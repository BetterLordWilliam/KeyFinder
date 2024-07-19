package src.tile;

import java.awt.Graphics2D;

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
public class Tile implements Cloneable, Paintable {	
	/**
	 * TileMaker:			Makes new tiles.
	 *	
	 * @author				Will OTtterbein
	 * @version				2024-1
	 */
	public static class TileMaker {
		/**
		 * makeTile:			Creates a new tile
		 * 
		 * @param type			String, tile type info
		 * @param tX			position of the tile, x-axis
		 * @param tY			position of the tile, y-axis
		 * @return				Tile, a new tile with the specified info
		 */
		public static Tile makeTile(String tileId, int tX, int tY) {	
			Tile newTile = null;
			try {
				newTile = tr.cloneTileWithId(tileId);
				newTile.setTxTy(tX, tY);		// Establish the 'X' and 'Y' positions
			} catch (CloneNotSupportedException e) {
				System.err.println("An exception occurred while reading tile with type: " + tileId);
				e.printStackTrace();
				Main.terminate();
			}
			return newTile;
		}
	}
	
	// Registry reference
	public static TileRegistry tr = new TileRegistry();
	
	protected int tX, tY;
	protected String texturePath;
	protected String tileId;
	
	/**
	 * creates a new Tile object, sets the tileId and texturePath.
	 * Required for Tile objects.
	 * 
	 * @param tileId			String, tileId, the same string that is in the files
	 * @param texturePath		String, texturePath, the path to the resource that will be drawn
	 */
	public Tile(String tileId, String texturePath) {
		this.tileId = tileId;
		this.texturePath = texturePath;
	}
	
	/**
	 * getTileId:			returns the Id of a tile.
	 * 
	 * @return tileId		String, string that is the id of the tile
	 */
	public String getTileId() {
		return tileId;
	}
	
	/**
	 * setTexturePath:		Set the texturePath for this tile
	 * 
	 * @param texturePath	String, the path the the texture
	 */
	public void setTexturePath(String texturePath) {
		this.texturePath = texturePath;
	}
	
	/**
	 * setTxTy:			set the position of the tile
	 * 
	 * @param tX		Integer, x position of the tile
	 * @param tY		Integer, y position of the tile
	 */
	public void setTxTy(int tX, int tY) {
		this.tX = tX;
		this.tY = tY;
	}
	
	/**
	 * clone:			returns a tile with the same details as this one.
	 * 
	 * @return			Tile, returns a tile with the same instance members
	 */
	public Tile clone() {
		return new Tile(this.tileId, this.texturePath);
	}

	/**
	 * paint:			Used to render the tile on the screen.
	 * 
	 * @param g2		Graphics2D, the rendering object
	 */
	@Override
	public void paint(Graphics2D g2) {}
	
	public String toString() {
		return (
				tileId + " " + texturePath 
				+ " X:" + tX + " Y:" + tY + "\n"
		);
	}
}
