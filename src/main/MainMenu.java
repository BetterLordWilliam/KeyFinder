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
    
    private final Dimension menuPanelDim_max = new Dimension(200,400);
    private final Dimension menuPanelDim_min = new Dimension(200,200);
    private final Dimension menuPanelDim_preferred = menuPanelDim_min;	// Preferred dimension is min

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
        this.setPreferredSize(new Dimension(Main.screenWidth, Main.screenHeight));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setRequestFocusEnabled(true);
        createUI();
	}
	
	/**
	 * createUI:       createsUI elements, right now MainMenu elements
	 */
	private void createUI() {
		// Menu panel
		JPanel menuPanel = new JPanel(gb);
        menuPanel.setMaximumSize(menuPanelDim_max);
        menuPanel.setMinimumSize(menuPanelDim_min);
        menuPanel.setPreferredSize(menuPanelDim_preferred);
        menuPanel.setBackground(Color.black);
        menuPanel.setLayout(gb);
        
        // Make Menu panel parts
        JComponent menuTitle = UIsupplier.createMenuTitle("KeyFinder", 
        	(mt) -> {mt.setForeground(Color.white);});
        JComponent menuBox = UIsupplier.createMenuBox(menuButtons);
        
        // Add menu panel parts to menu panel
        gb_constraints.gridwidth = GridBagConstraints.REMAINDER;	// End of row
        gb_constraints.weighty = 1.0;								// Padding after the title
        gb.setConstraints(menuTitle, gb_constraints); 				// Add title
        gb_constraints.weightx = 0.0;								// Reset to default constraints
        gb.setConstraints(menuBox, gb_constraints);					// Add box with buttons
        
        menuPanel.add(menuTitle);
        menuPanel.add(menuBox);
        
        // Add to this main_menu panel
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