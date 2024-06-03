package src.main;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.Action;
import javax.swing.AbstractAction;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;

public class Game extends JPanel implements Runnable, State {
    /**
     * required because this is a JPanel
     */
    private static final long serialVersionUID = 1L;
    
    // THREAD INFO
    private final int fps = 60;             // I'm not sure why I am saying this, but frames per second
    private Thread gameThread;
    
    public Game() {
        this.setMaximumSize(new Dimension(Main.screenWidth, Main.screenHeight));
        this.setBounds(0,0,Main.screenWidth, Main.screenHeight);
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);       // this does...
        this.setFocusable(true);
        this.setRequestFocusEnabled(true);
        this.setLayout(new BorderLayout());
    }
        
    /**
     * setup:
     * """ invoke the game thread
     * setup the default menu components for Game. """
     */
    @Override
	public void setup() {
        this.grabFocus();        
        
        // Key handling
        this.addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		System.out.println("pressed");
        	}
        }); // doesn't work, likely out of focus
    	startGameThread();							// Game start
	}
    
    /**
     * paused:			stop the game thread, pause menu.
     */
    public void paused() {
    	System.out.println("Game is paused");
    	Main.setState(Main.MAIN_MENU);				// for now,  esc -> back to mm
    }
    
    /**
     * unpaused:		resume gameThread, kill pause menu.
     */
    public void unpaused() {
    	System.out.println("Game is resumed");
    }
    
    /**
     * startGameThread:     begin KeyFinder game thread
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    /**
     * game thread entry.
     * panel update logic, use frames to draw at 60fps.
     */
    @Override
    public void run() {
        // TODO Auto-generated method stub
        double drawInteveral = 1000000000 / fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        
        while (gameThread != null) {
            // game time
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInteveral;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            
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
    
    /**
     * update:              allow the entites to perform their logic
     */
    void update() {
        // System.out.println("This is a frame");
    	// System.out.println(KeyManager.escPressed);
    }

    /**
     * paint:               Used to redraw the game tiles, objects and entities
     * 
     * @param g2            Graphics2D, will get from paintComponent method
     */
    public void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        // Will draw GAME objects and stuff here.
        // Draws the aspects of the game in order
        // 1 the map
        // 2 the objects
        // 3 the player
        // 4 the UI
    	Graphics2D g2 = (Graphics2D)g;
        super.paintComponent(g2);   			// window
        
        g2.dispose();
    }
}
