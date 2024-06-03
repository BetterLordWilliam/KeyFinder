package src.main;

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
    private static JFrame WINDOW = null;
    private static JPanel CURRENT_PANEL = null;
    
    // KEYFINDER GAME RELATED PANELS  (basically states)
    private static JPanel MAIN_MENU = new MainMenu();            // instance of MainMenu state (JPanel)
    private static JPanel GAME = new Game();                     // Instance of GAME state (JPanel)
	
	/**
	 * setPanelToGAME:         changes CURRENT_PANEL to GAME.
	 */
	public static void setPanelToGAME() {
	    CURRENT_PANEL = GAME;
	}
	
	/**
	 * setPanelToMainMenu:     changes CURRENT_PANEL to MAIN_MENU
	 */
	public static void setPanelToMainMenu() {
	    CURRENT_PANEL = MAIN_MENU;
	}
	
    /**
     * main:        program entry
     * 
     * @param args
     */
    public static void main(String[] args) { 
        setPanelToMainMenu();                                // Default start
        
        WINDOW = new JFrame();
        WINDOW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WINDOW.setResizable(false);
        // test
        WINDOW.setTitle("KeyFinder");
        
        WINDOW.getContentPane().add(CURRENT_PANEL);           // Add the default state of the GAME (Main Menu)
        WINDOW.pack();                                        // causes WINDOW size to fit to the GAME panel
        
        WINDOW.setLocationRelativeTo(null);
        WINDOW.setVisible(true);
    }
}