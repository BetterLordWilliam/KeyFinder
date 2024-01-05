package src.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import javax.swing.JPanel;

// IMPORT GAME OBJECTS
import src.entity.Player;
import src.tile.TileManager;
import src.map.Map;

// IMPORT GAME MAPS
import src.map.TestMap;
import src.map.World01;
import src.map.World02;
import src.map.World03;
import src.map.World04;

public class GamePanel extends JPanel implements Runnable {
  // SCREEN SETTINGS
  final int originalTileSize = 16;
  final int scale = 4;
  public int tileSize = originalTileSize * scale; 
  final int maxScreenCol = 16;
  final int maxScreenRow = 12;
  public final int screenWidth = tileSize * maxScreenCol;
  public final int screenHeight = tileSize * maxScreenRow;

  int FPS = 60;

  // MAPS
  Map TestMap = new TestMap(this);
  public Map World01 = new World01(this);
  public Map World02 = new World02(this);
  public Map World03 = new World03(this);
  public Map World04 = new World04(this);

  public Map startMap = World01;
  public Map currentMap = startMap;

  // WORLD SIZE
  public int worldWidth = tileSize * currentMap.mapSizeX;
  public int worldHeight = tileSize * currentMap.mapSizeY;

  // GAME OBJECTS
  KeyHandler keyH = new KeyHandler();
  TileManager tileManager = new TileManager(this);
  
  public CollisionDetection collisionDetector = new CollisionDetection(this);
  public Player playerInstance = new Player(this, keyH);
  public UI ui = new UI(this);
  public Sound sound = new Sound();
  public Sound music = new Sound();

  Thread gameThread;

  public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH); // Set keyhandler
    this.setFocusable(true);
    this.reloadMap(this.startMap); // load the first map
  }

  public int getTileSize() {
    return this.tileSize;
  }

  // LEVEL LOADING
  public void reloadMap(Map nextMap) {
    this.currentMap = nextMap;
    this.worldWidth = tileSize * currentMap.mapSizeX;
    this.worldHeight = tileSize * currentMap.mapSizeY;
    this.tileManager.loadMap();
    this.playerInstance.resetObject();
    this.ui.showMessage(1, "");
    this.ui.isLevelEnd = false;
  }

  // TELEPORTER
  public void setPlayerPosition() {
    this.playerInstance.worldX = currentMap.mapStartX;
    this.playerInstance.worldY = currentMap.mapStartY;
  }

  // RESET LEVEL OBJECTS
  public void setObjects() {
    currentMap.setObject();
  }

  // TERMINATE THE GAME
  public void terminateGame() {
    this.setVisible(false);
    System.exit(0);
  }

  public void startGameThread() {
    gameThread = new Thread(this);
    gameThread.start();
  }

  // GAME LOOP
  @Override
  public void run() {
    double drawInteveral = 1000000000 / FPS;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;
    long timer = 0;

    while (gameThread != null) { 
      // Game time
      currentTime = System.nanoTime();
      delta += (currentTime - lastTime) / drawInteveral;
      timer +=(currentTime - lastTime);
      lastTime = currentTime;

      // Draws game at FPS number
      if (delta >= 1) {
        update();
        repaint();
        delta--;
      }
      if (timer >= 1000000000) {
        timer = 0;
      }
    }
  }

  // UPDATE
  public void update() {
    playerInstance.update();
  }

  // DRAW
  public void paintComponent(Graphics g) {
    // Draws the aspects of the game in order
    // 1 the map
    // 2 the objects
    // 3 the player
    // 4 the UI
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;

    // MAP
    tileManager.draw(g2);
    
    // OBJECT
    for (int index = 0; index < currentMap.object.length; index++) {
      if(currentMap.object[index] != null) {
        currentMap.object[index].draw(g2, this);
      }
    }
    
    // PLAYER
    playerInstance.draw(g2);

    // UI
    this.ui.draw(g2);

    g2.dispose();
  }

  // Game music
  public void playMusic(int i) {
    music.setFile(i);
    music.play();
    music.loop();
  }
  public void stopMusic() {
    music.stop();
  }

  // Sound Effects
  public void playeSE(int i) {
    sound.setFile(i);
    sound.play();
  }
}