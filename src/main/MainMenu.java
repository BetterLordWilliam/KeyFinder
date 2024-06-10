package src.main;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import src.ui.FontSupplier;
import src.ui.UIsupplier;

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
    
    private GridBagLayout gb = new GridBagLayout();
    private GridBagConstraints gb_constraints = new GridBagConstraints();

	// Creates the Buttons for the menu
	private List<JComponent> menuButtons = new ArrayList<>(Arrays.asList(
			UIsupplier.createMenuButton("Start", 
				(e) -> {Main.setState(Main.GAME);}, null),
			UIsupplier.createMenuButton("Load", null, null),
			UIsupplier.createMenuButton("Settings", null, null),
			UIsupplier.createMenuButton("Controls", null, null),
			UIsupplier.createMenuButton("Quit", 
				(e) -> {Main.terminate();}, null)
	));
    
    /**
	 * no-arg constructor
	 */
	public MainMenu () {
		FontSupplier.loadFonts();
		
		// Settings for the pane
        this.setPreferredSize(new Dimension(Main.screenWidth, Main.screenHeight));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setRequestFocusEnabled(true);
        this.setVisible(false);
        
        createUI();
	}
	
	/**
	 * createUI:       createsUI elements, right now MainMenu elements
	 */
	private void createUI() {
		// Create menu components
        JComponent menuTitle = UIsupplier.createMenuTitle("KeyFinder", 
        	(mt) -> {
        	mt.setForeground(Color.white);
        	mt.setFont(FontSupplier.MENU_FONT_LARGE);});
        JComponent menuBox = UIsupplier.createMenuBox(menuButtons);
       
		// Menu panel creation
        JPanel menuPanel = new JPanel(gb);
        menuPanel.setBackground(Color.black);
        menuPanel.setLayout(gb);
        
        // Configure the constraints for the menu elements
        gb_constraints.ipady = 50;
        gb_constraints.gridwidth = GridBagConstraints.REMAINDER;	// End of row
        gb.setConstraints(menuTitle, gb_constraints); 				// Add title
        gb_constraints.weightx = 0.0;								// Reset to default constraints
        gb_constraints.ipady = 1;
        gb.setConstraints(menuBox, gb_constraints);					// Add box with buttons
        
        // Add the components to the menu panel
        menuPanel.add(menuTitle);
        menuPanel.add(menuBox);
        
		this.add(Box.createVerticalGlue());		// Center vertically
		this.add(Box.createHorizontalGlue());	// Center horizontally
		this.add(menuPanel);
		this.add(Box.createVerticalGlue());
		this.add(Box.createHorizontalGlue());
	}
	
	/**
	 * setup:          set focus, createUI and prepare this state
	 */
	@Override
	public void setup() {
		this.setVisible(true);
        this.grabFocus();
	}
	
	/**
	 * actionPerformed:    required method
	 * 
	 * @param e            ActionEvent, event (but we use lambda).
	 */
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }
}