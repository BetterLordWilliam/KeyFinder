package src.entity;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Entity {
  public int worldX, worldY;
  public int speed;
  public int defaultSpeed;
  public int sprintSpeed;
  public Rectangle solidArea;
  public boolean showCollision;
  public int solidAreaDefaultX, solidAreaDefaultY;
  public boolean collisionOn = false;

  public BufferedImage idle, up1, up2, down1, down2, left1, left2, right1, right2;
  public String direction;
  
  public int spriteCounter = 0;
  public int spriteNumber = 1;
}