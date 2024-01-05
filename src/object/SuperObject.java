package src.object;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import src.main.GamePanel;

public class SuperObject {
  public BufferedImage image;
  public BufferedImage image2;
  public String name;
  public boolean collision = false;
  public boolean isOpen = false;
  public boolean isHit = false;
  public int color;
  public int worldX, worldY;
  public Rectangle solidArea = new Rectangle(0, 0, 64, 64);
  public int solidAreaDefaultX = 0;
  public int solidAreaDefaultY = 0;

  // Set objects world coordinates
  public void setXY(int X, int Y) {
    isHit = false;
    this.worldX = X;
    this.worldY = Y;
  }

  public void draw(Graphics2D g2, GamePanel gp) {
    int screenX = worldX - gp.playerInstance.worldX + gp.playerInstance.screenX;
    int screenY = worldY - gp.playerInstance.worldY + gp.playerInstance.screenY;

    // Handles the drawing of tiles near the edge of the world
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

    // Makes sure that objects are within the players screen before they are drawn
    if ((worldX + gp.tileSize) > (gp.playerInstance.worldX - gp.playerInstance.screenX) &&
        (worldX - gp.tileSize) < (gp.playerInstance.worldX + gp.playerInstance.screenX) &&
        (worldY + gp.tileSize) > (gp.playerInstance.worldY - gp.playerInstance.screenY) &&
        (worldY - gp.tileSize) < (gp.playerInstance.worldY + gp.playerInstance.screenY)) {
      g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    } 
    else if 
      (gp.playerInstance.screenX > gp.playerInstance.worldX || 
        gp.playerInstance.screenY > gp.playerInstance.worldY ||
        rightOffset > gp.worldWidth - gp.playerInstance.worldX ||
        bottomOffset > gp.worldHeight - gp.playerInstance.worldY) {
      g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

    // Object and tile drawing is handled in a similar fashion.
    // The code here is not to different to the tile code that handles the drawing of tiles
    
  }

  public void switchImage(GamePanel gp) {
    this.image = this.image2;
  }
}
