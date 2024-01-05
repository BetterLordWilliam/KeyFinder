package src.map;

import src.main.GamePanel;

// IMPORT OBJECT TYPES
import src.object.SO_Door;
import src.object.SO_Key;

public class TestMap extends Map {
  public TestMap(GamePanel gp) {
    this.gp = gp;
    
    this.mapSizeX = 18;
    this.mapSizeY = 18;
    this.worldWidth = this.mapSizeX * gp.getTileSize();
    this.worldHeight = this.mapSizeY * gp.getTileSize();

    this.mapStartX = 8 * gp.tileSize;
    this.mapStartY = 8 * gp.tileSize;
    this.mapPath = "/res/maps/testMap.txt";
  }

  // PLACE OBJECTS
  // Objects are initialized at an array index.
  // Objects position is set.
  @Override
  public void setObject() {
    gp.currentMap.object[0] = new SO_Key(0);
    gp.currentMap.object[0].setXY(2 * gp.tileSize, 15 * gp.tileSize);

    gp.currentMap.object[1] = new SO_Door(0);
    gp.currentMap.object[1].setXY(5 * gp.tileSize, 6 * gp.tileSize);
  } 
}
