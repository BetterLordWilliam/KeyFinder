package src.map;

import src.main.GamePanel;

// IMPORT OBJECT TYPES
import src.object.SO_Door;
import src.object.SO_Key;
import src.object.SO_Chest;

public class World01 extends Map {
  GamePanel gp;
  
  // MAP INFO
  public World01(GamePanel gp) {
    this.gp = gp;
    this.mapName = "01";
    
    this.mapSizeX = 52;
    this.mapSizeY = 52;
    this.worldWidth = this.mapSizeX * gp.getTileSize();
    this.worldHeight = this.mapSizeY * gp.getTileSize();

    this.mapStartX = 27 * gp.tileSize;
    this.mapStartY = 40 * gp.tileSize;
    this.mapPath = "/res/maps/world01.txt";
  }

  // PLACE OBJECTS
  @Override
  public void setObject() {
    gp.currentMap.object[2] = new SO_Key(3);
    gp.currentMap.object[2].setXY(40 * gp.tileSize, 36 * gp.tileSize);

    gp.currentMap.object[21] = new SO_Key(0);
    gp.currentMap.object[21].setXY(23 * gp.tileSize, 30 * gp.tileSize);

    gp.currentMap.object[1] = new SO_Door(0);
    gp.currentMap.object[1].setXY(28 * gp.tileSize, 36 * gp.tileSize);
    gp.currentMap.object[1].isOpen = false;

    gp.currentMap.object[4] = new SO_Chest();
    gp.currentMap.object[4].setXY(28 * gp.tileSize, 34 * gp.tileSize);
    gp.currentMap.object[4].isOpen = false;
  } 

  // Level Loading
  public void nextLevel() {
    gp.reloadMap(gp.World02);
    gp.setPlayerPosition();
    gp.setObjects();
  }

  // Reset the Game
  public void resetGame() {
    gp.reloadMap(gp.World01);
    gp.setPlayerPosition();
    gp.setObjects();
  }
}