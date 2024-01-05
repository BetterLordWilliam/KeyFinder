package src.entity;

import src.main.GamePanel;
import src.main.KeyHandler;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Entity {
  GamePanel gp;
  KeyHandler keyH;

  public final int screenX;
  public final int screenY;
  public boolean hasYellowKey = false;
  public boolean hasRedKey = false;
  public boolean hasBlueKey = false;
  public boolean hasPurpleKey = false;

  public boolean imageLoadTest = false;

  public Player(GamePanel gp, KeyHandler keyH) {
    this.gp = gp;
    this.keyH = keyH;

    screenX = gp.screenWidth/2 - (gp.tileSize/2);
    screenY = gp.screenHeight/2 - (gp.tileSize/2);

    // Collision box
    this.solidArea = new Rectangle();
    solidArea.x = 16;
    solidArea.y = 40;
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;
    solidArea.width = 32;
    solidArea.height = 24;

    setDefaultValues();
    getPlayerSprite();
  }

  public void setDefaultValues() {
    worldX = gp.currentMap.mapStartX;
    worldY = gp.currentMap.mapStartY;
    defaultSpeed = 5;
    sprintSpeed = 7;
    speed = defaultSpeed;
    direction = "idle";
  }

  public void getPlayerSprite() {
    try {
      // Load player sprites (for movement)
      // BufferedImage and stream
      idle = ImageIO.read(getClass().getResourceAsStream("/res/player/movement/Player_IDL.png"));
      up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/movement/Player_U1.png"));
      up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/movement/Player_U2.png"));
      down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/movement/Player_D1.png"));
      down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/movement/Player_D2.png"));
      right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/movement/Player_R1.png"));
      right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/movement/Player_R2.png"));
      left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/movement/Player_L1.png"));
      left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/movement/Player_L2.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void update() {
    // Reveal/hide player collision
    if (keyH.collisionBoxShow) this.showCollision = true;
    else if (!keyH.collisionBoxShow) this.showCollision = false;
    
    // Reveal/hide control box
    if (keyH.showControlBox) {
      gp.ui.controlMenu = true;
    } else if (!keyH.showControlBox) {
      gp.ui.controlMenu = false;
    }

    // Level End Selection
    if (keyH.enterPressed && gp.ui.isLevelEnd) {
      gp.currentMap.nextLevel();
      gp.ui.isLevelEnd = false;
    }

    // Restart Seletion
    if (keyH.enterPressed && gp.ui.isEnd) {
      gp.stopMusic();
      gp.currentMap.nextLevel();
      gp.ui.isEnd = false;
    }

    // Quit (Anytime)
    if (keyH.escPressed) {
      gp.ui.isEnd = false;
      gp.terminateGame();
    }

    // Check collision if the player is going to be moving
    if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
      // Determine direction based off of key input
      if (keyH.upPressed) direction = "up";
      else if (keyH.downPressed) direction = "down";
      else if (keyH.leftPressed) direction = "left";
      else if (keyH.rightPressed) direction = "right";

      if (keyH.sprint) speed = sprintSpeed;
      if (!keyH.sprint) speed = defaultSpeed;
  
      // Check tile collision
      collisionOn = false;
      gp.collisionDetector.checkTile(this);

      // Check object collision
      int objectIndex = gp.collisionDetector.checkObject(this, true);
      pickUpObject(objectIndex);

      // If player isn't colliding, movement is possible
      if(collisionOn == false) {
        switch(direction) {
        case "up":  worldY -= speed; break;
        case "down":  worldY += speed; break;
        case "left":  worldX -= speed; break;
        case "right":  worldX += speed; break;
        }
      }
  
      // Handle animation frames and play walking noise
      spriteCounter ++;
      if(spriteCounter > 14) {
        if (spriteNumber == 1) {
          spriteNumber = 2;
          gp.playeSE(0);
        } else if (spriteNumber == 2) {
          spriteNumber = 1;
        }
        spriteCounter = 0;
      }
    } else {
      speed = 5;
    }
  }

  // Resetable objects
  public void resetObject() {
    hasYellowKey = false;
    hasRedKey = false;
    hasBlueKey = false;
    hasPurpleKey = false;
  }

  // Player-object Interactions
  public void pickUpObject(int index) {
    String keyFound = "Key found.";
    String locked = "Locked.";

    // The different possible actions that might occur when a player interects with a given object
    if (index != 9999) {
      String objectName = gp.currentMap.object[index].name;
      switch(objectName) {
        case "Key":
          // Simple interactions here
          // Player touches the key, he gets the key, the key disappears and the jingle is played
          if (gp.currentMap.object[index].color == 0) hasYellowKey = true;
          else if (gp.currentMap.object[index].color == 1) hasBlueKey = true;
          else if (gp.currentMap.object[index].color == 2) hasRedKey = true;
          else if (gp.currentMap.object[index].color == 3) hasPurpleKey = true;
          gp.currentMap.object[index] = null;
          gp.ui.showMessage(0, keyFound);
          gp.playeSE(2);
        break;
        case "Door":
          // A little more complex
          // First, we check if the door isOpen, we don't want to unescessarily open doors that are already opened.
          // If it doesn't have a color, the door is now open, it's collision is set to false, and we switch the sprite
          if (gp.currentMap.object[index].isOpen) {
            gp.currentMap.object[index].isOpen = true;
            gp.currentMap.object[index].collision = false;
            gp.currentMap.object[index].switchImage(gp);
          }
          // If the door is locked, the story changes.
          // We check the type of lock that it was and if the player has the associated key then he can open the door.
          else if (!gp.currentMap.object[index].isOpen && 
          gp.currentMap.object[index].color == 999) {
            gp.currentMap.object[index].isOpen = true;
            gp.currentMap.object[index].collision = false;
            gp.currentMap.object[index].switchImage(gp);
            gp.playeSE(3);
          } else if
          (!gp.currentMap.object[index].isOpen && 
            gp.currentMap.object[index].color == 0 && 
            hasYellowKey) {
              gp.currentMap.object[index].isOpen = true;
              gp.currentMap.object[index].collision = false;
              gp.currentMap.object[index].switchImage(gp);
              gp.playeSE(3);
          } else if
          (!gp.currentMap.object[index].isOpen && 
            gp.currentMap.object[index].color == 1 && 
            hasBlueKey) {
              gp.currentMap.object[index].isOpen = true;
              gp.currentMap.object[index].collision = false;
              gp.currentMap.object[index].switchImage(gp);
              gp.playeSE(3);
          } else if
          (!gp.currentMap.object[index].isOpen && 
            gp.currentMap.object[index].color == 2 && 
            hasRedKey) {
              gp.currentMap.object[index].isOpen = true;
              gp.currentMap.object[index].collision = false;
              gp.currentMap.object[index].switchImage(gp);
              gp.playeSE(3);
          // If he doesn't have any of the necessary keys, he cannot pass through so the collision remains.
          // We say that the door is his and an error message plays.
          // The isHit variable ensures that if the player remains touching the door the interaction noise doesn't endlessly loop.
          } else if (!gp.currentMap.object[index].isOpen && gp.currentMap.object[index].collision == true && !gp.currentMap.object[index].isHit) {
            gp.ui.showMessage(0, locked);
            gp.playeSE(1);
            gp.currentMap.object[index].isHit = true;
          }
        break;
        case "Chest":
          if (hasPurpleKey && !gp.currentMap.object[index].isOpen) {
            gp.currentMap.object[index].isOpen = true;
            gp.currentMap.object[index].switchImage(gp);
            // End game or end level scenario checker
            if (!gp.currentMap.isEnd) {
              gp.playeSE(4);
              gp.ui.isLevelEnd = true;
            } else {
              gp.playMusic(5);
              gp.ui.isEnd = true;
            }
          // Play the error message and show that the chest is locked
          } else if (!gp.currentMap.object[index].isOpen && !gp.currentMap.object[index].isHit) {
            gp.ui.showMessage(0, locked);
            gp.playeSE(1);
            gp.currentMap.object[index].isHit = true;
          }
        break;
      }
    }
  }
  
  public void draw(Graphics2D g2) {
    BufferedImage image = null;

    // Draws the different player sprites based on his direction
    switch(direction) {
      case "idle": image = idle; break;
      case "up":
        if (spriteNumber == 1) image = up1;
        if (spriteNumber == 2) image = up2;
      break;
      case "down":
        if (spriteNumber == 1) image = down1;
        if (spriteNumber == 2) image = down2;
      break;
      case "right":
        if (spriteNumber == 1) image = right1;
        if (spriteNumber == 2) image = right2;
      break;
      case "left":
        if (spriteNumber == 1) image = left1;
        if (spriteNumber == 2) image = left2;
      break;
    }
    
    int meX = screenX;
    int meY = screenY;

    // Basically, if the player gets to close to the edge, his screenX and screenY are no longer updated with his movement. 
    if (screenX > worldX) {
      meX = worldX;
    }
    if (screenY > worldY) {
      meY = worldY;
    }
    int rightOffset = gp.screenWidth - screenX;
    if (rightOffset > gp.worldWidth - worldX) {
      meX = gp.screenWidth - (gp.worldWidth - worldX);
    }
    int bottomOffset = gp.screenHeight - screenY;
    if (bottomOffset > gp.worldHeight - worldY) {
      meY = gp.screenHeight - (gp.worldHeight - worldY);
    }

    // update character
    g2.drawImage(image, meX, meY, gp.tileSize, gp.tileSize, null);
    if (showCollision) g2.drawRect(meX + this.solidArea.x, meY + this.solidArea.y, this.solidArea.width, this.solidArea.height);
  }
} 