package src.main;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Main.
 * 
 * Welcome to the source code of the KeyFinder re-write.
 * After analysing the code of my original project, it is clear that there
 * were some serious improvements to be made.
 * 
 * The code you are about to read is the beginning of this effort.
 * 
 * @author Will Otterbein
 * @version 2024-1
 */
public class Main {
    // TILE SIZE (accessible elsewhere)
    public static final int og_tile_size = 16;    // 16 x 16, pixels
    public static final int size_mod = 4;
    public static int tileSize = size_mod * og_tile_size;
    
    // WINDOW SIZE (accessible elsewhere)
    public static final int max_screen_col = 16;
    public static final int max_screen_row = 12;
    public static int screenWidth = tileSize * max_screen_col;
    public static int screenHeight = tileSize * max_screen_row;
    
    // WINDOW RELATED OBJECTS 
    // (the actual frame of the game and the current panel pointer)
    private static JFrame WINDOW = new JFrame();
    private static State CURRENT_PANEL = null;
    
    // KEYFINDER GAME RELATED PANELS  (basically states)
    public static final State MAIN_MENU = new MainMenu();            // instance of MainMenu state (JPanel)
    public static final State GAME = new Game();                     // Instance of GAME state (JPanel)
    
	/**
	 * setPanelToGAME:         changes CURRENT_PANEL to GAME.
	 */
	public static void setState(State newState) {
		WINDOW.getContentPane().removeAll();
	    
		CURRENT_PANEL = newState;
	    CURRENT_PANEL.setup();
	    
	    WINDOW.getContentPane().add((JPanel) CURRENT_PANEL);
	    WINDOW.pack();
        WINDOW.setVisible(true);
        WINDOW.revalidate();
		WINDOW.repaint();
	}
	    
    /**
     * terminate:		kill the KeyFinder process
     * 
     * testing purposes
     */
    public static void terminate() {
    	System.exit(0);
    }
    
    /**
     * main:        program entry
     * 
     * @param args
     */
    public static void main(String[] args) { 
    	// Basic window setup
        WINDOW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WINDOW.setPreferredSize(new Dimension(screenWidth, screenHeight));
        WINDOW.setResizable(false);
        WINDOW.pack();
        WINDOW.setVisible(true);
        WINDOW.setLocationRelativeTo(null);
        WINDOW.setTitle("KeyFinder");
        setState(MAIN_MENU);						// Enter the main menu
    }
}