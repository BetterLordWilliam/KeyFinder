package src.main;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 * GamePanel:       The panel where the game happens
 * 
 * @author          Will Otterbein
 * @version         2024-1
 */
public class GamePanel extends JPanel {
	
	/**
	 * this is recommended
	 */
	private static final long serialVersionUID = 1L;
	
	// TILE SIZE
	private final int og_tile_size = 16; 	// 16 x 16, pixels
	private final int size_mod = 4;
	private int tileSize = size_mod * og_tile_size;
	
	// WINDOW SIZE
	private final int max_screen_col = 16;
	private final int max_screen_row = 12;
	private int screenWidth = tileSize * max_screen_col;
	private int screenHeight = tileSize * max_screen_row;
	
	private State currentState = null;
	
	/**
	 * no-arg constructor
	 */
	public GamePanel () {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);		// this does
		this.setFocusable(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);			// window (JFrame)
		Graphics2D g2 = (Graphics2D)g;		// Turn g into g2, graphcis 2D
		
		currentState.paint(g2);				// Paint current state
	}
	
	/**
	 * getTileSize:		returns the tile size
	 * 
	 * @return int		tile size
	 */
	public int getTileSize() {
		return tileSize;
	}
	
	/**
	 * setState:		sets the current game state
	 * 
	 * @param state
	 */
	public void setState(State state) {
		currentState = state;				// Tell the panel what the state is
	}
	
	/**
	 * Getters and Setters for screenWidth and screenHeight.
	 * 
	 * Setters unused for time being, leave as private.
	 * use setters to implement window size option (possibly).
	 * 
	 * @param screenWidth		this is the columns you want
	 * @param screenHeight		this is the rows you want
	 */
	public int getScreenWidth() {
		return screenWidth;
	}
	private void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth * max_screen_col;
	}	// multiple for width size (pixels)
	public int getScreenHeight() {
		return screenHeight;
	}
	private void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight * max_screen_row;
	}	// multiply for height size (pixels)
}
