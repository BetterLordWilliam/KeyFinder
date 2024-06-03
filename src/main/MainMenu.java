package src.main;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * GamePanel:       The panel where the game happens
 * 
 * @author          Will Otterbein
 * @version         2024-1
 */
public class MainMenu extends JPanel implements State {
	/**
     * required because this is a JPanel
     */
    private static final long serialVersionUID = 1L;

    /**
	 * no-arg constructor
	 */
	public MainMenu () {
		this.setPreferredSize(new Dimension(Main.screenWidth, Main.screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);		// this does
		this.setFocusable(true);
		setupMenu();
	}
	
	/**
	 * setupMenu:      add all the necessary components for the MainMenu
	 */
	private void setupMenu() {
	      JLabel test = new JLabel("This is the MainMenu");
	      this.add(test);
	}
}
