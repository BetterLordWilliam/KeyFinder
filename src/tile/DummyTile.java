package src.tile;

import java.awt.Graphics2D;

public class DummyTile extends Tile {
	@Override
	public void paint(Graphics2D g2) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void setTxTy(int tX, int tY) {
		// TODO Auto-generated method stub
		this.tX = tX;
		this.tY = tY;
	}

	@Override
	public void setLocalType(TileType type) {
		// TODO Auto-generated method stub
		this.type = type;
	}
}
