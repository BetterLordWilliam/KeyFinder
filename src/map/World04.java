package src.map;

import src.main.GamePanel;

// IMPORT OBJECT TYPES
import src.object.SO_Door;
import src.object.SO_Key;
import src.object.SO_Chest;

public class World04 extends Map {
  GamePanel gp;
  
  // MAP INFO
  public World04(GamePanel gp) {
    this.gp = gp;
    this.mapName = "04";
    this.isEnd = true;
    
    this.mapSizeX = 50;
    this.mapSizeY = 50;
    this.worldWidth = this.mapSizeX * gp.getTileSize();
    this.worldHeight = this.mapSizeY * gp.getTileSize();

    this.mapStartX = 18 * gp.tileSize;
    this.mapStartY = 22 * gp.tileSize;
    this.mapPath = "/res/maps/world04.txt";
  }

  // PLACE OBJECTS
  @Override
  public void setObject() {
    gp.currentMap.object[12] = new SO_Door(2);
    gp.currentMap.object[12].setXY(6 * gp.tileSize, 28 * gp.tileSize);
    gp.currentMap.object[12].isOpen = false;

    gp.currentMap.object[13] = new SO_Door(2);
    gp.currentMap.object[13].setXY(6 * gp.tileSize, 35 * gp.tileSize);
    gp.currentMap.object[13].isOpen = false;

    gp.currentMap.object[14] = new SO_Door(999);
    gp.currentMap.object[14].setXY(21 * gp.tileSize, 22 * gp.tileSize);
    gp.currentMap.object[14].isOpen = false;

    gp.currentMap.object[15] = new SO_Door(999);
    gp.currentMap.object[15].setXY(26 * gp.tileSize, 11 * gp.tileSize);
    gp.currentMap.object[15].isOpen = false;

    gp.currentMap.object[16] = new SO_Door(999);
    gp.currentMap.object[16].setXY(6 * gp.tileSize, 17 * gp.tileSize);
    gp.currentMap.object[16].isOpen = false;

    gp.currentMap.object[17] = new SO_Door(0);
    gp.currentMap.object[17].setXY(6 * gp.tileSize, 9 * gp.tileSize);
    gp.currentMap.object[17].isOpen = false;

    gp.currentMap.object[18] = new SO_Door(1);
    gp.currentMap.object[18].setXY(9 * gp.tileSize, 6 * gp.tileSize);
    gp.currentMap.object[18].isOpen = false;

    gp.currentMap.object[19] = new SO_Door(1);
    gp.currentMap.object[19].setXY(43 * gp.tileSize, 15 * gp.tileSize);
    gp.currentMap.object[19].isOpen = false;

    gp.currentMap.object[111] = new SO_Door(1);
    gp.currentMap.object[111].setXY(31 * gp.tileSize, 6 * gp.tileSize);
    gp.currentMap.object[111].isOpen = false;

    gp.currentMap.object[112] = new SO_Door(2);
    gp.currentMap.object[112].setXY(44 * gp.tileSize, 22 * gp.tileSize);
    gp.currentMap.object[112].isOpen = false;

    gp.currentMap.object[113] = new SO_Door(999);
    gp.currentMap.object[113].setXY(39 * gp.tileSize, 19 * gp.tileSize);
    gp.currentMap.object[113].isOpen = false;

    gp.currentMap.object[2] = new SO_Key(0); 
    gp.currentMap.object[2].setXY(14* gp.tileSize, 2 * gp.tileSize);
    
    gp.currentMap.object[21] = new SO_Key(1);
    gp.currentMap.object[21].setXY(6 * gp.tileSize, 6 * gp.tileSize);

    gp.currentMap.object[22] = new SO_Key(2);
    gp.currentMap.object[22].setXY(42 * gp.tileSize, 5 * gp.tileSize);

    gp.currentMap.object[23] = new SO_Key(3);
    gp.currentMap.object[23].setXY(46 * gp.tileSize, 16 * gp.tileSize);

    gp.currentMap.object[4] = new SO_Chest();
    gp.currentMap.object[4].setXY(6 * gp.tileSize, 45 * gp.tileSize);
    gp.currentMap.object[4].isOpen = false;

    // Object index standardization (For larger maps)
    // 2 - Key
    // 1 - Door
    // 4 - Chest
    // The numbers that follow represent which key, door, chest, etc that the object is.
    // In this file I ordered them by their first numbers (1, 2, 4).
  } 

  // Level Loading
  public void nextLevel() {
    gp.reloadMap(gp.World01);
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