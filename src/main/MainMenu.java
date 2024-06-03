package src.main;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.Component;
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
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));    
		    // Shows contents in a single row / column
		setupMenu();
	}
	
	/**
	 * setupMenu:      add all the necessary components for the MainMenu
	 */
	private void setupMenu() {
	    // For the buttons I guess
	    JPanel smaller = new JPanel();
	    smaller.setMaximumSize(new Dimension(400, 100));       // test
	    smaller.setBorder(new TitledBorder("Main Menu"));
	    smaller.setBackground(Color.black);
	    
	    JButton start = new JButton("Start");
	    JButton settings = new JButton("Settings");
	    JButton controls = new JButton("Controls");
	    JButton quit = new JButton("quit");
	    
	    smaller.add(start);
	    smaller.add(controls);
	    smaller.add(settings);
	    smaller.add(quit);
	    smaller.setAlignmentY(JComponent.CENTER_ALIGNMENT);
	    smaller.setAlignmentX(JComponent.CENTER_ALIGNMENT);     // Make buttons stay in the middle
	    
	    this.add(smaller);
	}
}
