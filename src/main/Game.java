package src.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.AbstractAction;

import src.ui.UIsupplier;

public class Game extends JPanel implements Runnable, State {
    /**
     * required because this is a JPanel
     */
    private static final long serialVersionUID = 1L;
   
    // THREAD INFO
    private final int FPS = 60;             // I'm not sure why I am saying this, but frames per second
    private Thread gameThread;
    
   	// PAUSED MENU
    private final JPanel paused = new JPanel();

    // PAUSED MENU BUTTONS
    private List<JComponent> pausedButtons = new ArrayList<>(Arrays.asList(
    		UIsupplier.createMenuButton("Resume Game", 			// Restart the game thread, hide paused
    			(e) -> {unpaused();}, null),
    		UIsupplier.createMenuButton("Save Game", null ,null),
    		UIsupplier.createMenuButton("Quit to Title", 		// Quit to the title
    			(e) -> {
				paused.setVisible(false);
				Main.setState(Main.MAIN_MENU);}, null)
    ));
    
    /**
     * no-arg constructor
     */
    public Game() {
    	// Settings for the pane
        this.setPreferredSize(new Dimension(Main.screenWidth, Main.screenHeight));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.setRequestFocusEnabled(true);
        this.setVisible(false);
        this.setLayout(null);
        this.add(paused);
        
        createPausedUI();
        createUI();
    }
    
    /**
     * createPausedUI:		create the UI for the paused menu.
     */
    private void createPausedUI() {
        JComponent pausedMenu = UIsupplier.createMenuBox(pausedButtons);
        
        paused.add(pausedMenu);
        paused.setSize(new Dimension(
    		pausedMenu.getWidth(),
    		pausedMenu.getHeight()
		));
        paused.setBackground(Color.gray);
        paused.setVisible(false);
    }
    
    /**
     * createUI:		create the UI for the game.
     */
    private void createUI() {}
    
    /**
     * setup:
     * """ invoke the game thread
     * setup the default menu components for Game. """
     */
    @Override
	public void setup() {
    	this.setVisible(true);
    	this.grabFocus();
    	
        setKeyBindings();      // set the bindings 
    	startGameThread();     // start the thread
	}
   
    /**
     * paused:			stops gamethread and shows pause menu
     */
    private void paused() {
    	gameThread = null;
    	paused.setLocation(
    	    ((Main.screenWidth - paused.getWidth())/2),
        	((Main.screenHeight - paused.getHeight())/2));
        paused.setVisible(true);
    }
    
    /**
     * unpaused:		resumes gamethread and hides pause menu
     */
    private void unpaused() {
		startGameThread();
    	paused.setVisible(false);
    }
    
   /**
     * setKeyBindings:      setup the bindings required for the Game
     */
    private void setKeyBindings() {
    	// Pause the game on "esc" key being pressed
        Action esc = new AbstractAction() {
            private static final long serialVersionUID = 1L;
            @Override
            public void actionPerformed(ActionEvent e) {
            	paused();
            }
        };
        
        // Configure the input map
        this.getInputMap(
            JComponent.WHEN_IN_FOCUSED_WINDOW).put(
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESCAPE"
        );
        this.getActionMap().put("ESCAPE", esc);
    }
    
    /**
     * startGameThread:     begin KeyFinder game thread
     */
    private void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    /**
     * game thread entry.
     * panel update logic, use frames to draw at 60FPS.
     */
    @Override
    public void run() {
        // TODO Auto-generated method stub
        double drawInteveral = 1000000000 / FPS;
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
    	System.out.println("Frame");
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
