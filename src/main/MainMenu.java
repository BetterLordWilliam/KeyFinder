package src.main;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
			createMenuButton("Start", e -> {Main.setState(Main.GAME);}),
			createMenuButton("Load", null),
			createMenuButton("Settings", null),
			createMenuButton("Controls", null),
			createMenuButton("Quit", e -> {Main.terminate();})
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
	}

	/**
	 * createMenuTitle		creates main menu title
	 * 
	 * @return JLabel		menuTitle component
	 */
	private JLabel createMenuTitle() {
		JLabel menuTitle = new JLabel("KeyFinder");
		menuTitle.setForeground(Color.white);
		return menuTitle;
	}
	
	/**
	 * createMenuButton		creates a menu button with.
	 * 
	 * @param buttonName	buttonName,		the title of the button
	 * @param l				ActionListener, the action the button will perform
	 * @return
	 */
	private JComponent createMenuButton(String buttonName, ActionListener l) {
		JButton newButton = new JButton(buttonName);
		// Styles
		newButton.setFocusable(false);				// disables outline around button text
		newButton.setForeground(Color.white);
		newButton.setBackground(Color.darkGray);
		newButton.setBorder(BorderFactory.createEtchedBorder(1));
		
		// Button Action
		if (l != null)
			newButton.addActionListener(l);		// Pass the action listener
		else
			newButton.setEnabled(false);		// Disabled the button if null action
		return newButton;
	}
	
	/**
	 * createMenuBox:	create the buttons of the main menu
	 */
	private JComponent createMenuBox() {
        Box menuBox = new Box(BoxLayout.Y_AXIS);
        
        // Add to the box with paddings
        for (JComponent jb : menuButtons) {
        	JPanel buttonPanel = new JPanel(new BorderLayout());
        	buttonPanel.add(jb);
        	menuBox.add(buttonPanel);
        	menuBox.add(Box.createRigidArea(new Dimension(0,10)));
        }
	
        return menuBox;
	}
	
	/**
	 * createUI:       createsUI elements, right now MainMenu elements
	 */
	private void createUI() {
		// Menu panel
		JPanel smaller = new JPanel(gb);
        smaller.setMaximumSize(menuPanelDim_max);
        smaller.setMinimumSize(menuPanelDim_min);
        smaller.setPreferredSize(menuPanelDim_preferred);
        smaller.setBackground(Color.black);
        smaller.setLayout(gb);
        
        // Make Menu panel parts
        JComponent menuTitle = createMenuTitle();
        JComponent menuBox = createMenuBox();
        
        // Add menu panel parts to menu panel
        gb_constraints.gridwidth = GridBagConstraints.REMAINDER;	// End of row
        gb_constraints.weighty = 1.0;								// Padding after the title
        gb.setConstraints(menuTitle, gb_constraints); 				// Add title
        gb_constraints.weightx = 0.0;								// Reset to default constraints
        gb.setConstraints(menuBox, gb_constraints);					// Add box with buttons
        
        smaller.add(menuTitle);
        smaller.add(menuBox);
        
        // Add to this main_menu panel
		this.add(Box.createVerticalGlue());		// Center vertically
		this.add(Box.createHorizontalGlue());	// Center horizontally
		this.add(smaller);
		this.add(Box.createVerticalGlue());
		this.add(Box.createHorizontalGlue());
	}
	
	/**
	 * setup:          set focus, createUI and prepare this state
	 */
	@Override
	public void setup() {
	    this.removeAll();
        this.grabFocus();
        createUI();
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