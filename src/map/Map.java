package src.map;

import src.object.SuperObject;
import src.main.GamePanel;

public class Map {
  public GamePanel gp;
  public int mapSizeX, mapSizeY;
  public int worldWidth, worldHeight;
  public int mapStartX, mapStartY;
  public SuperObject object[] = new SuperObject[999];
  public String mapPath;  // Directory of the map.txt file.
  public String mapDataPath; 
  public String mapName;
  public Map nextMap;
  public boolean isEnd = false;

  // Level Loading
  public void nextLevel() {
    gp.currentMap = this.nextMap;
    gp.setPlayerPosition();
    gp.setObjects();
  }

  // Reset the Game
  public void resetGame() {
    gp.reloadMap(gp.World01);
    gp.setPlayerPosition();
    gp.setObjects();
  }

  // Empty method
  public void setObject() {
  }
}