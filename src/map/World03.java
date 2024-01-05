package src.map;

import src.main.GamePanel;

// IMPORT OBJECT TYPES
import src.object.SO_Door;
import src.object.SO_Key;
import src.object.SO_Chest;

public class World03 extends Map {
  GamePanel gp;
  
  // MAP INFO
  public World03(GamePanel gp) {
    this.gp = gp;
    this.mapName = "03";
    
    this.mapSizeX = 30;
    this.mapSizeY = 30;
    this.worldWidth = this.mapSizeX * gp.getTileSize();
    this.worldHeight = this.mapSizeY * gp.getTileSize();

    this.mapStartX = 14 * gp.tileSize;
    this.mapStartY = 15 * gp.tileSize;
    this.mapPath = "/res/maps/world03.txt";
  }

  // PLACE OBJECTS
  @Override
  public void setObject() {
    gp.currentMap.object[1] = new SO_Door(0);
    gp.currentMap.object[1].setXY(8 * gp.tileSize, 15 * gp.tileSize);
    gp.currentMap.object[1].isOpen = false;

    gp.currentMap.object[11] = new SO_Door(2);
    gp.currentMap.object[11].setXY(26 * gp.tileSize, 25 * gp.tileSize);
    gp.currentMap.object[11].isOpen = false;

    gp.currentMap.object[12] = new SO_Door(1);
    gp.currentMap.object[12].setXY(6 * gp.tileSize, 15 * gp.tileSize);
    gp.currentMap.object[12].isOpen = false;

    gp.currentMap.object[13] = new SO_Door(2);
    gp.currentMap.object[13].setXY(4 * gp.tileSize, 15 * gp.tileSize);
    gp.currentMap.object[13].isOpen = false;

    gp.currentMap.object[14] = new SO_Door(999);
    gp.currentMap.object[14].setXY(7 * gp.tileSize, 14 * gp.tileSize);
    gp.currentMap.object[14].isOpen = false;

    gp.currentMap.object[15] = new SO_Door(999);
    gp.currentMap.object[15].setXY(5 * gp.tileSize, 16 * gp.tileSize);
    gp.currentMap.object[15].isOpen = false;

    gp.currentMap.object[16] = new SO_Door(999);
    gp.currentMap.object[16].setXY(14 * gp.tileSize, 9 * gp.tileSize);
    gp.currentMap.object[16].isOpen = false;

    gp.currentMap.object[17] = new SO_Door(999);
    gp.currentMap.object[17].setXY(14 * gp.tileSize, 20 * gp.tileSize);
    gp.currentMap.object[17].isOpen = false;

    gp.currentMap.object[18] = new SO_Door(999);
    gp.currentMap.object[18].setXY(4 * gp.tileSize, 25 * gp.tileSize);
    gp.currentMap.object[18].isOpen = false;

    gp.currentMap.object[19] = new SO_Door(999);
    gp.currentMap.object[19].setXY(24 * gp.tileSize, 23 * gp.tileSize);
    gp.currentMap.object[19].isOpen = false;

    gp.currentMap.object[2] = new SO_Key(0);
    gp.currentMap.object[2].setXY(14 * gp.tileSize, 23 * gp.tileSize);
    
    gp.currentMap.object[22] = new SO_Key(1);
    gp.currentMap.object[22].setXY(9 * gp.tileSize, 5 * gp.tileSize);

    gp.currentMap.object[23] = new SO_Key(2);
    gp.currentMap.object[23].setXY(4 * gp.tileSize, 27 * gp.tileSize);

    gp.currentMap.object[24] = new SO_Key(3);
    gp.currentMap.object[24].setXY(28 * gp.tileSize, 25 * gp.tileSize);

    gp.currentMap.object[4] = new SO_Chest();
    gp.currentMap.object[4].setXY(1 * gp.tileSize, 15 * gp.tileSize);
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
    gp.reloadMap(gp.World04);
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
