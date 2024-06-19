package src.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * KeyManager:      Reports key presses / events
 * 
 * @author          Will Otterbein
 * @version         2024-1
 */
public class KeyManager implements KeyListener {
    public static boolean upPressed, downPressed, leftPressed, rightPressed, 
    showCollisionBox, sprint, enterPressed, escPressed, showControlBox;     // Don't think there is a better way

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
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
            showCollisionBox = (showCollisionBox) ? false : true;
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
            showControlBox = (showControlBox) ? false : true;
            break;
          // Control box will behave like a togglable object
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
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
