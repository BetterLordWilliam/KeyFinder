package src.tile;

import src.main.Paintable;

public abstract class Tile implements Paintable {
	public static class TileMaker {
		public static Tile makeTile(String type, int tX, int tY) {
			return null;
		}
	}
	
	public int tX, tY;
}
