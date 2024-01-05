package src.map;

import src.main.GamePanel;

// IMPORT OBJECT TYPES
import src.object.SO_Door;
import src.object.SO_Key;
import src.object.SO_Chest;

public class World02 extends Map {
  GamePanel gp;
  
  // MAP INFO
  public World02(GamePanel gp) {
    this.gp = gp;
    this.mapName = "02";
    
    this.mapSizeX = 30;
    this.mapSizeY = 30;
    this.worldWidth = this.mapSizeX * gp.getTileSize();
    this.worldHeight = this.mapSizeY * gp.getTileSize();

    this.mapStartX = 3 * gp.tileSize;
    this.mapStartY = 3 * gp.tileSize;
    this.mapPath = "/res/maps/world02.txt";
  }

  // PLACE OBJECTS
  @Override
  public void setObject() {
    gp.currentMap.object[2] = new SO_Key(0);
    gp.currentMap.object[2].setXY(24 * gp.tileSize, 22 * gp.tileSize);

    gp.currentMap.object[21] = new SO_Key(2);
    gp.currentMap.object[21].setXY(2 * gp.tileSize, 20 * gp.tileSize);

    gp.currentMap.object[22] = new SO_Key(3);
    gp.currentMap.object[22].setXY(14 * gp.tileSize, 1 * gp.tileSize);

    gp.currentMap.object[1] = new SO_Door(0);
    gp.currentMap.object[1].setXY(4 * gp.tileSize, 20 * gp.tileSize);
    gp.currentMap.object[1].isOpen = false;

    gp.currentMap.object[12] = new SO_Door(2);
    gp.currentMap.object[12].setXY(22 * gp.tileSize, 4 * gp.tileSize);
    gp.currentMap.object[12].isOpen = false;

    gp.currentMap.object[13] = new SO_Door(999);
    gp.currentMap.object[13].setXY(6 * gp.tileSize, 13 * gp.tileSize);
    gp.currentMap.object[13].isOpen = false;

    gp.currentMap.object[14] = new SO_Door(999);
    gp.currentMap.object[14].setXY(10 * gp.tileSize, 2 * gp.tileSize);
    gp.currentMap.object[14].isOpen = false;

    gp.currentMap.object[15] = new SO_Door(999);
    gp.currentMap.object[15].setXY(22 * gp.tileSize, 12 * gp.tileSize);
    gp.currentMap.object[15].isOpen = false;

    gp.currentMap.object[16] = new SO_Door(999);
    gp.currentMap.object[16].setXY(11 * gp.tileSize, 20 * gp.tileSize);
    gp.currentMap.object[16].isOpen = false;

    gp.currentMap.object[4] = new SO_Chest();
    gp.currentMap.object[4].setXY(22 * gp.tileSize, 2 * gp.tileSize);
    gp.currentMap.object[4].isOpen = false;

    // Object index standardization (For larger maps)
    // 2 - Key
    // 1 - Door
    // 4 - Chest
    // The numbers that follow represent which key, door, chest, etc that the object is.
  } 

  // Level Loading
  public void nextLevel() {
    gp.reloadMap(gp.World03);
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
