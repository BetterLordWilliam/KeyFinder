package src.main;

import src.entity.Entity;

public class CollisionDetection {
  GamePanel gp;

  public CollisionDetection(GamePanel gp) {
    this.gp = gp;
  }

  // Set collision true for player
  public void setCollision(Entity entity, int number1, int number2) {
    if (gp.tileManager.tile[number1].collision == true ||
        gp.tileManager.tile[number2].collision == true) entity.collisionOn = true;
  }

  // Check the tiles around the collision box for collisions
  public void checkTile(Entity entity) {
    int entityLeftWorldX = entity.worldX + entity.solidArea.x;
    int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
    int entityTopWorldY = entity.worldY + entity.solidArea.y;
    int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

    // Coordinates of the collision box corners
    int entityLeftX = entityLeftWorldX/gp.tileSize;
    int entityRightX = entityRightWorldX/gp.tileSize;
    int entityTopY = entityTopWorldY/gp.tileSize;
    int entityBottomY = entityBottomWorldY/gp.tileSize;

    int tileNumber1, tileNumber2;

    switch(entity.direction) {
      case "up" :
        // Check collision for the top of the collision box
        entityTopY = (entityTopWorldY - entity.speed)/gp.tileSize;
        tileNumber1 = gp.tileManager.mapTileNumber[entityLeftX][entityTopY];
        tileNumber2 = gp.tileManager.mapTileNumber[entityRightX][entityTopY];
        setCollision(entity, tileNumber1, tileNumber2);
      break;
      case "down" :
        // Check collision for the bottom of the collision box
        entityBottomY = (entityBottomWorldY + entity.speed)/gp.tileSize;
        tileNumber1 = gp.tileManager.mapTileNumber[entityLeftX][entityBottomY];
        tileNumber2 = gp.tileManager.mapTileNumber[entityRightX][entityBottomY];
        setCollision(entity, tileNumber1, tileNumber2);
      break;
      case "left" :
        // Check collision for the left of the collision box
        entityLeftX = (entityLeftWorldX - (entity.speed + 3))/gp.tileSize;
        tileNumber1 = gp.tileManager.mapTileNumber[entityLeftX][entityTopY];
        tileNumber2 = gp.tileManager.mapTileNumber[entityLeftX][entityBottomY];
        setCollision(entity, tileNumber1, tileNumber2);
      break;
      case "right" :
        // Check collision for the right of the collision box
        entityRightX = (entityRightWorldX + (entity.speed + 3))/gp.tileSize;
        tileNumber1 = gp.tileManager.mapTileNumber[entityRightX][entityTopY];
        tileNumber2= gp.tileManager.mapTileNumber[entityRightX][entityBottomY];
        setCollision(entity, tileNumber1, tileNumber2);
      break;
    }
  }

  // Entity / Object collision detection
  public int checkObject(Entity entity, boolean player) {
    int index = 9999; // Default return value

    for (int i = 0; i < gp.currentMap.object.length; i++) {
      if (gp.currentMap.object[i] != null) {
        // Get player's collision
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        // Get object's solid area position
        gp.currentMap.object[i].solidArea.x = gp.currentMap.object[i].worldX + gp.currentMap.object[i].solidArea.x;
        gp.currentMap.object[i].solidArea.y = gp.currentMap.object[i].worldY + gp.currentMap.object[i].solidArea.y;

        switch(entity.direction) {
          // Check if the players collision box interacts 
          // with an objects collision box in a given direction.
          case "up":
            entity.solidArea.y -= entity.speed;
            if (entity.solidArea.intersects(gp.currentMap.object[i].solidArea)) {
              if (gp.currentMap.object[i].collision == true) entity.collisionOn = true;
              if (player == true) index = i;
            }
          break;
          case "down":
            entity.solidArea.y += entity.speed;
            if (entity.solidArea.intersects(gp.currentMap.object[i].solidArea)) {
              if (gp.currentMap.object[i].collision == true) entity.collisionOn = true;
              if (player == true) index = i;
            }
          break;
          case "left":
            entity.solidArea.x -= entity.speed;
            if (entity.solidArea.intersects(gp.currentMap.object[i].solidArea)) {
              if (gp.currentMap.object[i].collision == true) entity.collisionOn = true;
              if (player == true) index = i;
            }
          break;
          case "right":
            entity.solidArea.x += entity.speed;
            if (entity.solidArea.intersects(gp.currentMap.object[i].solidArea)) {
              if (gp.currentMap.object[i].collision == true) entity.collisionOn = true;
              if (player == true) index = i;
            }
          break;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.currentMap.object[i].solidArea.x = gp.currentMap.object[i].solidAreaDefaultX;
        gp.currentMap.object[i].solidArea.y = gp.currentMap.object[i].solidAreaDefaultY;
      }
    }
    return index; // Returns the index of the object with which the player is colliding
  }
}
