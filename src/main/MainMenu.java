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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

/**
 * GamePanel:       The panel where the game happens
 * 
 * @author          Will Otterbein
 * @version         2024-1
 */
public class MainMenu extends JPanel implements ActionListener, State {
	/**
     * required because this is a JPanel
     */
    private static final long serialVersionUID = 1L;

    /**
	 * no-arg constructor
	 */
	public MainMenu () {
        this.setMaximumSize(new Dimension(Main.screenWidth, Main.screenHeight));
        this.setBounds(0,0,Main.screenWidth, Main.screenHeight);
        this.setBackground(Color.black);
        this.setFocusable(true); 
        this.setRequestFocusEnabled(true);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        	// Shows contents in a single row / column        
	}
	
	@Override
	public void setup() {
        this.grabFocus();
       
		JPanel smaller = new JPanel();
	    smaller.setBorder(new TitledBorder("Main Menu"));
	    smaller.setBackground(Color.black);
	    smaller.setLayout(new BoxLayout(smaller, BoxLayout.Y_AXIS));
	   
	    // Main Menu container components
	    JButton start = new JButton("Start");
	    start.addActionListener(e -> {
	    	System.out.println("Start");
	    	Main.setState(Main.GAME);			// Begin
	    });
	    start.setFocusable(false);
	    
	    JButton settings = new JButton("Settings");
	    settings.setFocusable(false);
	    settings.setEnabled(false);
	    JButton controls = new JButton("Controls");
	    controls.setFocusable(false);
	    controls.setEnabled(false);
	    
	    JButton quit = new JButton("quit");
	    quit.addActionListener(e -> {
	    	Main.terminate();
	    });
	    quit.setFocusable(false);
	    
	    smaller.add(start);
	    smaller.add(controls);
	    smaller.add(settings);
	    smaller.add(quit);
	    
	    // Add to this panel
	    this.add(smaller);
	    this.setAlignmentX(CENTER_ALIGNMENT);
	    this.setAlignmentY(CENTER_ALIGNMENT);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
