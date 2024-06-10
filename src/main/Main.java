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
    public static final int OG_TILE_SIZE = 16;    // 16 x 16, pixels
    public static final int SIZE_MOD = 4;
    public static int tileSize = SIZE_MOD * OG_TILE_SIZE;
    
    // window SIZE (accessible elsewhere)
    public static final int MAX_SCREEN_COL = 16;
    public static final int MAX_SCREEN_ROW = 12;
    public static int screenWidth = tileSize * MAX_SCREEN_COL;
    public static int screenHeight = tileSize * MAX_SCREEN_ROW;
    
    // window RELATED OBJECTS 
    // (the actual frame of the game and the current panel pointer)
    private static JFrame window = new JFrame();
    private static State currentPanel = null;
    
    // KEYFINDER GAME RELATED PANELS  (basically states)
    public static final State MAIN_MENU = new MainMenu();            // instance of MainMenu state (JPanel)
    public static final State GAME = new Game();                     // Instance of GAME state (JPanel)
    
	/**
	 * setPanelToGAME:         changes currentPanel to GAME.
	 */
	public static void setState(State newState) {
		window.getContentPane().removeAll();
	    
		currentPanel = newState;
	    currentPanel.setup();
	    
	    window.getContentPane().add((JPanel) currentPanel);
	    window.pack();
        window.setVisible(true);
        window.revalidate();
		window.repaint();
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
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setPreferredSize(new Dimension(screenWidth, screenHeight));
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setTitle("KeyFinder");
        setState(MAIN_MENU);						// Enter the main menu
    }
}