package src.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * UIsupplier		Supplies customised UI components for KeyFinder.
 * 
 * @author			Will Otterbein
 * @version 		2024-1
 */
public class UIsupplier {
	
	// COMPONENT SIZES
	private static final int MENU_BUTTON_HEIGHT = 30;		// Buttons have max 30px height
	
	// PADDINGS
	public static final Dimension PADDING_SMALL = new Dimension(0,10);
	
	/**
	 * setComponentBgFg:	sets the foreground and background of component
	 * 
	 * Apply the settings in the ColorApplier or default settings
	 * 
	 * @param jc			JComponenet, the componenet we are applying colors to
	 * @param ca			ColorApplier, the color applier with custom color settings
	 */
	private static void setComponentColors(JComponent jc, 
			ComponentApplier<JComponent> ca) {
		if (ca != null) {
			ca.apply(jc);							// Use the ColorApplier interface to apply foreground, background
		} else {									// Apply default colors
			jc.setForeground(Color.white);
			jc.setBackground(Color.darkGray);
			jc.setBorder(BorderFactory.createEtchedBorder(1));
		}
	}
	
	/**
	 * createMenuButton		creates a menu button with.
	 * 
	 * @param buttonName	buttonName,		the title of the button
	 * @param l				ActionListener, the action the button will perform
	 * @param ca			AppearanceApplier, the settings to apply to the component appearance
	 * @return
	 */
	public static JComponent createMenuButton(String buttonName, ActionListener l, 
			ComponentApplier<JComponent> ca) {
		JButton newButton = new JButton(buttonName);
		
		// Styles
		newButton.setFocusable(false);				// disables outline around button text
		setComponentColors(newButton, ca);
		
		// Button actions
		if (l != null)
			newButton.addActionListener(l);		// Pass the action listener
		else
			newButton.setEnabled(false);		// Disabled the button if null action
		
		return newButton;
	}

	/**
	 * createMessage:		Creates a text message
	 * 
	 * @param contents		String, message contents
	 * @param ca			ComponentApplier, customised appreance
	 * @return				text, JComponent
	 */
	public static JComponent createMessage(String contents,
			ComponentApplier<JComponent> ca) {
		JLabel text = new JLabel(contents);
		setComponentColors(text, ca);
		return text;
	}

	/**
	 * createMenuBox:		create the buttons of the main menu
	 * 
	 * @param buttonList	ArrayList<JButton>, list of buttons
	 * @return menuBox		Box, contains menu buttons
	 */
	public static JComponent createMenuBox(List<JComponent> buttons) {
        Box menuBox = new Box(BoxLayout.Y_AXIS);
        int total = buttons.size() - 1;
        int count = 0;
        
        // Add to the box with paddings
        for (JComponent jb : buttons) {
        	JPanel buttonPanel = new JPanel(new BorderLayout());
        	buttonPanel.add(jb);
        	menuBox.add(buttonPanel);
        	if (count < total) 
        		menuBox.add(Box.createRigidArea(PADDING_SMALL));
        	count++;
        }
        
        // Minimum required height
        menuBox.setSize(100, MENU_BUTTON_HEIGHT * count);
        
        /*
        System.out.printf("Width: %d\nHeight: %d\n",
        		menuBox.getWidth(),
        		menuBox.getHeight());
        */
        
        return menuBox;
	}
}