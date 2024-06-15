package src.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import src.ui.UIsupplier;

public class Game extends JPanel implements Runnable, State {
    /**
     * required because this is a JPanel
     */
    private static final long serialVersionUID = 1L;
   
    // THREAD INFO
    private final int FPS = 60;             // I'm not sure why I am saying this, but frames per second
    private Thread gameThread;
    
    // GAME PANEL
    private final GridBagLayout gb = new GridBagLayout();
    private final GridBagConstraints gb_constraints = new GridBagConstraints();

   	// PAUSED MENU
    private final JPanel paused = new JPanel();

    // PAUSED MENU BUTTONS
    private final List<JComponent> pausedButtons = new ArrayList<>(Arrays.asList(
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
        this.setLayout(new GridLayout(1,1));

        createUI();
    }
    
    /**
     * createUI:		create the UI for the game.
     */
    private void createUI() {
        // Configure paused menu box 
        JComponent pausedMenu = UIsupplier.createMenuBox(pausedButtons);
        gb_constraints.ipadx = 10;
        gb_constraints.ipady = 10;
        gb.setConstraints(pausedMenu, gb_constraints);
        
        // Configure the layout of the paused panel
        paused.setLayout(gb);
        paused.setBackground(Color.gray);
        paused.setBorder(BorderFactory.createEtchedBorder(Color.darkGray, Color.gray));
        paused.setVisible(false);
        gb_constraints.ipadx = 10;
        gb_constraints.ipady = 10;
        gb.setConstraints(paused, gb_constraints);

        // Add components to paused
        paused.add(pausedMenu);

        this.add(paused);
    }

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
    @Override
    public void paintComponent(Graphics g) {
    	Graphics2D g2 = (Graphics2D)g;
        super.paintComponent(g2);
       
        // Testing the layering of graphics drawing vs menu drawing.
        Rectangle test = new Rectangle(100, 100, 400, 400);
        Rectangle test1 = new Rectangle(100, 600, 400, 100);

        g2.setColor(Color.red);
        g2.fill(test);
        g2.draw(test);

        g2.setColor(Color.yellow);
        g2.fill(test1);
        g2.draw(test1);

        g2.dispose();
    }
}
