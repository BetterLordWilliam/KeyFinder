package src.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
  public boolean upPressed, downPressed, leftPressed, rightPressed, 
    collisionBoxShow, sprint, enterPressed, escPressed, showControlBox;
  // Different controls that player can produce

  int count = 0;

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode(); // Key code references the individual keys
    // Handles the different keys that the player might press
    switch (code) {
      case KeyEvent.VK_W :
        upPressed = true;
        break;
      case KeyEvent.VK_S :
        downPressed = true;
        break;
      case KeyEvent.VK_A :
        leftPressed = true;
        break;
      case KeyEvent.VK_D :
        rightPressed = true;
        break;
      case KeyEvent.VK_L :
        if (count<=0) {
          collisionBoxShow = true;
          count += 1;
        } else {
          count = 0;
          collisionBoxShow = false;
        }
        break;
      // Collision box will behave like a togglable object
      case KeyEvent.VK_SPACE :
        sprint = true;
        break;
      case KeyEvent.VK_ENTER :
        enterPressed = true;
        break;
      case KeyEvent.VK_ESCAPE :
        escPressed = true;
        break;
      case KeyEvent.VK_R :
        if (count<=0) {
          showControlBox = true;
          count += 1;
        } else {
          count = 0;
          showControlBox = false;
        }
        break;
      // Control box will behave like a togglable object
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int code = e.getKeyCode();
    switch (code) {
      case KeyEvent.VK_W :
        upPressed = false;
        break;
      case KeyEvent.VK_S :
        downPressed = false;
        break;
      case KeyEvent.VK_A :
        leftPressed = false;
        break;
      case KeyEvent.VK_D :
        rightPressed = false;
        break;
      case KeyEvent.VK_SPACE :
        sprint = false;
        break;
      case KeyEvent.VK_ENTER :
        enterPressed = false;
        break;
      case KeyEvent.VK_ESCAPE :
        escPressed = false;
        break;
    }
  }
}
