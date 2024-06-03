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
    // TILE SIZE
    static final int og_tile_size = 16;    // 16 x 16, pixels
    static final int size_mod = 4;
    static int tileSize = size_mod * og_tile_size;
    
    // WINDOW SIZE
    static final int max_screen_col = 16;
    static final int max_screen_row = 12;
    static int screenWidth = tileSize * max_screen_col;
    static int screenHeight = tileSize * max_screen_row;
    
    private JFrame window;
    private JPanel state;
    
    // GAME STATES
    private JPanel main_menu = new MainMenu();            // instance of MainMenu state (JPanel)
    private JPanel game = new Game();                     // Instance of Game state (JPanel)
    
	public static void main(String[] args) {
	    Main m = new Main();                              // Instance of main
	    
		m.window = new JFrame();
		m.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.window.setResizable(false);
		// test
		m.window.setTitle("KeyFinder");
		
		m.window.getContentPane().add(m.state);           // Add the default state of the game (Main Menu)
		m.window.pack();                                  // causes window size to fit to the game panel
		
		m.window.setLocationRelativeTo(null);
		m.window.setVisible(true);
	}
	
	/**
	 * no-arg constructor for Main
	 * 
	 * sets the default state to MainMenu
	 */
	Main() {
	    state = main_menu;
	}
	
	/**
	 * changeState:    Change the current state of the game
	 * 
	 * Change from MainMenu to Game, current states
	 * 
	 * @param state
	 */
	public void changeState(JPanel newPanel) {
	    window.getContentPane().add(newPanel);              // Switch to the new state
	}
}