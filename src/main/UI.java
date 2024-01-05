package src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import src.object.SO_Key;

public class UI {
  GamePanel gp; 

  // Fonts
  Font pixelFont_6;
  Font pixelFont_8;
  Font pixelFont_16;
  Font pixelFont_24;
  Font pixelFont_32;
  Font pixelFont_64;

  // Key icons
  BufferedImage yellowKey;
  BufferedImage blueKey;
  BufferedImage redKey;
  BufferedImage purpleKey;

  // Other UI images
  BufferedImage uiImage;
  BufferedImage speedIcon;
  BufferedImage controlBoxClosed;
  BufferedImage controlBoxOpen;

  int messageCounter = 0;
  int startPromptCounter = 0;
  public boolean isEnd = false;
  public boolean isLevelEnd = false;
  public boolean messageOn = false;
  public boolean levelStart = false;
  public boolean controlMenu = false;
  
  // Blank message + End game strings
  public String message = "";
  public String startPrompt = "";
  public String endPrompt = "Victory.";
  public String endPrompt2 = "Press enter to restart.";
  public String endPrompt3 = "Press esc to exit.";
  
  // End level strings
  public String endLevelPrompt1 = "Level Complete.";
  public String endLevelPrompt2 = "Press enter to continue.";

  // Control strings
  public String controlHead = "Controls (R)";
  public String control1 = "W = Forward.";
  public String control2 = "A = Left.";
  public String control3 = "S = Down.";
  public String control4 = "D = forward.";
  public String control5 = "SPC = Sprint.";


  public UI(GamePanel gp) {
    this.gp = gp;

    // Load UI Images & Font
    loadUI();
    try {
      pixelFont_8 = loadFont(8f);
      pixelFont_16 = loadFont(16f);
      pixelFont_24 = loadFont(24f);
      pixelFont_32 = loadFont(32f);
      pixelFont_64 = loadFont(64f);
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Create dummy key objects and use their images
    SO_Key yellow = new SO_Key(0);
    SO_Key blue = new SO_Key(1);
    SO_Key red = new SO_Key(2);
    SO_Key purple = new SO_Key(3);
    yellowKey = yellow.image;
    blueKey = blue.image;
    redKey = red.image;
    purpleKey = purple.image;
  }

  // Loads the font based off of float size
  public Font loadFont(float size) throws Exception {  
    String fName = "/res/UI/Text/FFFFORWA.TTF";
    InputStream is = Main.class.getResourceAsStream(fName);
    Font font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(size);
    return font;
  }

  public void loadUI() {
    // Load the image icons for the UI components
    try {
      this.uiImage = ImageIO.read(getClass().getResourceAsStream("/res/UI/KeyFinderUI.png"));
      this.speedIcon = ImageIO.read(getClass().getResourceAsStream("/res/UI/SpeedIcon.png"));
      this.controlBoxClosed = ImageIO.read(getClass().getResourceAsStream("/res/UI/ShowBoxClosed.png"));
      this.controlBoxOpen = ImageIO.read(getClass().getResourceAsStream("/res/UI/ShowBoxOpen.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Handles the different message types.
  public void showMessage(int type, String text) {
    switch (type) {
      case 0:
        messageOn = true;
        message = text;
      break;
      case 1:
        levelStart = true;
        startPrompt = "Unlock the chest to escape.";
      break;
    }
  }

  public void regularMessage(Graphics2D g2) {
    // REGULAR MESSAGE
    g2.setFont(pixelFont_24);
    g2.setColor(Color.white);
    g2.drawString(message, 20, gp.tileSize * 11);

    messageCounter++;
    if (messageCounter > 120) {
      messageCounter = 0;
      messageOn = false;
    }
  }

  public void bigMessage(Graphics2D g2) {
    // BIG MESSAGE
    g2.setFont(pixelFont_32);
    g2.setColor(Color.white);
    g2.drawString(startPrompt, 400, 75);

    startPromptCounter++;
    if (startPromptCounter > 240) {
      startPromptCounter = 0;
      levelStart = false;
    }
  }
  
  public void endLevelMessage(Graphics2D g2) {
    // END LEVEL MESSAGE
    g2.setFont(pixelFont_32);
    g2.setColor(Color.white);

    int textLength = (int)g2.getFontMetrics().getStringBounds(this.endLevelPrompt1, g2).getWidth();
    int x2 = gp.screenWidth/2 - textLength/2;
    g2.drawString(endLevelPrompt1, x2, 200);

    // Continue.
    int textLength2 = (int)g2.getFontMetrics().getStringBounds(this.endLevelPrompt2, g2).getWidth();
    int x3 = gp.screenWidth/2 - textLength2/2;
    g2.drawString(endLevelPrompt2, x3, 300);
  }

  public void endWorldMessage(Graphics2D g2) {
    // END MESSAGE
    g2.setFont(pixelFont_64);
    g2.setColor(Color.yellow);

    int textLength = (int)g2.getFontMetrics().getStringBounds(this.endPrompt, g2).getWidth();
    int x2 = gp.screenWidth/2 - textLength/2;
    g2.drawString(endPrompt, x2, 200);

    // Continue / End game prompts.
    g2.setFont(pixelFont_24);
    g2.setColor(Color.white);

    // Continue.
    int textLength2 = (int)g2.getFontMetrics().getStringBounds(this.endPrompt2, g2).getWidth();
    int x3 = gp.screenWidth/2 - textLength2/2;
    g2.drawString(endPrompt2, x3, gp.screenHeight - 175);
    
    // End.
    int textLength3 = (int)g2.getFontMetrics().getStringBounds(this.endPrompt3, g2).getWidth();
    int x4 = gp.screenWidth/2 - textLength3/2;
    g2.drawString(endPrompt3, x4, gp.screenHeight - 75);
  }

  public void controlBox(Graphics2D g2) {
    // CONTROL BOX
    g2.setColor(Color.gray);

    // Re-draws header for the control head box
    g2.setFont(pixelFont_8);
    g2.drawString(controlHead, gp.screenWidth - 130, 27);
    
    // Draws the control menu.
    g2.setFont(pixelFont_16);
    g2.drawString(control1, gp.screenWidth - 170, 52);
    g2.drawString(control2, gp.screenWidth - 170, 74);    
    g2.drawString(control3, gp.screenWidth - 170, 96);    
    g2.drawString(control4, gp.screenWidth - 170, 118);    
    g2.drawString(control5, gp.screenWidth - 170, 140);        
  }

  public void draw(Graphics2D g2) {
    if (this.isEnd) {
      endWorldMessage(g2);
    } else if (this.isLevelEnd) {
      endLevelMessage(g2);
    } else {
      // UI
      g2.drawImage(uiImage, 0, 0, gp.tileSize * 16, gp.tileSize * 12, null);
      g2.drawImage(controlBoxClosed, 0, 0, gp.tileSize * 16, gp.tileSize * 12, null);

      // Switch the font for the headers for the control head box
      g2.setFont(pixelFont_8);
      g2.setColor(Color.gray);
      g2.drawString(controlHead, gp.screenWidth - 130, 27);

      // UI Icons / Menus
      if (this.controlMenu) {
        g2.drawImage(controlBoxOpen, 0, 0, gp.tileSize * 16, gp.tileSize * 12, null);
        controlBox(g2);
      }
      // If the player possesses any of the keys, the icons will appear
      // They are spaced 64 pixels apart from one another (x-axis)
      if (gp.playerInstance.hasYellowKey) {
        g2.drawImage(yellowKey, 20, 20, gp.tileSize, gp.tileSize, null);
      }
      if (gp.playerInstance.hasBlueKey) {
        g2.drawImage(blueKey, 84, 20, gp.tileSize, gp.tileSize, null);
      }
      if (gp.playerInstance.hasRedKey) {
        g2.drawImage(redKey, 148, 20, gp.tileSize, gp.tileSize, null);
      }
      if (gp.playerInstance.hasPurpleKey) {
        g2.drawImage(purpleKey, 212, 20, gp.tileSize, gp.tileSize, null);
      }
      if (gp.playerInstance.speed == 7) {
        g2.drawImage(speedIcon, 0, 0, gp.tileSize * 16, gp.tileSize * 12, null);
      }
      if (messageOn) {
        regularMessage(g2);
      }
      if (levelStart) {
        bigMessage(g2);
      }
      
      // WORLD NUMBER
      g2.setFont(pixelFont_32);
      g2.setColor(Color.gray);
      g2.drawString(gp.currentMap.mapName, 300, 75);
    }
  }
}