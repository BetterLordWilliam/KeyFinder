package src.tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

import src.main.GamePanel;

public class TileManager {
  GamePanel gp;
  public Tile[] tile;
  public int mapTileNumber[][];

  public TileManager(GamePanel gp) {
    this.gp = gp;

    tile = new Tile[100];
    mapTileNumber = new int[gp.currentMap.mapSizeX][gp.currentMap.mapSizeY];

    getTileImage();
  }

  // LOAD TILE IMAGES AND SET COLLISION STATUS
  public void getTileImage() {
    // Tiles are loaded using a BufferedReader and stream
    try {
      tile[0] = new Tile();
      tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/Dirt.png"));

      tile[1] = new Tile();
      tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/Grass.png"));

      tile[2] = new Tile();
      tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/Wall.png"));
      tile[2].collision = true;

      tile[3] = new Tile();
      tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/Water.png"));
      tile[3].collision = true;

      tile[4] = new Tile();
      tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/Wood.png"));

      tile[5] = new Tile();
      tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/Tree.png"));
      tile[5].collision = true;

      tile[6] = new Tile();
      tile[6].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/KnightStatue.png"));
      tile[6].collision = true;

      tile[7] = new Tile();
      tile[7].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/BannerWall.png"));
      tile[7].collision = true;

      tile[8] = new Tile();
      tile[8].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/Lilypad.png"));
      tile[8].collision = false;
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // LOAD MAP DATA
  public void loadMap() {
    try {
      InputStream inputStream = getClass().getResourceAsStream(gp.currentMap.mapPath);
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

      int col = 0;
      int row = 0;

      // Ensures that the tile reader doesn't go out of bounds
      while (col < gp.currentMap.mapSizeX && row < gp.currentMap.mapSizeY) {
        String line = bufferedReader.readLine();

        while (col < gp.currentMap.mapSizeX) {
          // Split the numbers everytime there is a space character
          String numbers[] = line.split(" ");

          // Store numbers from mapdata txt files into arrays of ints
          int num = Integer.parseInt(numbers[col]);
          mapTileNumber[col][row] = num;
          col++;
        }

        if (col == gp.currentMap.mapSizeX) {
          col = 0;
          row++;
        }
      }
      bufferedReader.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // DRAW MAP
  public void draw(Graphics2D g2) {
    int currentWorldX = 0;
    int currentWorldY = 0;

    // Read ints from mapTileNumber and display corresponding tiles
    while (currentWorldX < gp.currentMap.mapSizeX && currentWorldY < gp.currentMap.mapSizeY) {
      int tileNumber = mapTileNumber[currentWorldX][currentWorldY];

      // Tile x and y coordinates
      int worldX = currentWorldX * gp.getTileSize();
      int worldY = currentWorldY * gp.getTileSize();
      int screenX = worldX - gp.playerInstance.worldX + gp.playerInstance.screenX;
      int screenY = worldY - gp.playerInstance.worldY + gp.playerInstance.screenY;

      // Stop moving camera at the edge of the map
      if (gp.playerInstance.screenX > gp.playerInstance.worldX) {
        screenX = worldX;
      }
      if (gp.playerInstance.screenY > gp.playerInstance.worldY) {
        screenY = worldY;
      }
      int rightOffset = gp.screenWidth - gp.playerInstance.screenX;
      if (rightOffset > gp.worldWidth - gp.playerInstance.worldX) {
        screenX = gp.screenWidth - (gp.worldWidth - worldX);
      }
      int bottomOffset = gp.screenHeight - gp.playerInstance.screenY;
      if (bottomOffset > gp.worldHeight - gp.playerInstance.worldY) {
        screenY = gp.screenHeight - (gp.worldHeight - worldY);
      }

      // Makes sure that tiles are within the players screen before they are drawn
      if ((worldX + gp.tileSize) > (gp.playerInstance.worldX - gp.playerInstance.screenX) &&
          (worldX - gp.tileSize) < (gp.playerInstance.worldX + gp.playerInstance.screenX) &&
          (worldY + gp.tileSize) > (gp.playerInstance.worldY - gp.playerInstance.screenY) &&
          (worldY - gp.tileSize) < (gp.playerInstance.worldY + gp.playerInstance.screenY)) {
        g2.drawImage(tile[tileNumber].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
      } 
      else if 
        (gp.playerInstance.screenX > gp.playerInstance.worldX || 
         gp.playerInstance.screenY > gp.playerInstance.worldY ||
         rightOffset > gp.worldWidth - gp.playerInstance.worldX ||
         bottomOffset > gp.worldHeight - gp.playerInstance.worldY) {
        g2.drawImage(tile[tileNumber].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
      }
      currentWorldX++; // Increase the column position.

      // If we reach the end of the row, move to the next, reset the column position.
      if (currentWorldX == gp.currentMap.mapSizeX) {
        currentWorldX = 0;
        currentWorldY++;
      }
    }
  }
}