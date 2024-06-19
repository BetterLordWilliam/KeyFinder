package src.main;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import src.map.EpisodeManager;
import src.ui.UIsupplier;

/**
 * Game:        State where the game happens
 * 
 * @author      Will Otterbein
 * @version     2024-1
 */
public class Game extends JPanel implements State {
    /**
     * required because this is a JPanel
     */
    private static final long serialVersionUID = 1L;
   
    // GAME OBJECTS
    private final GridBagLayout gb = new GridBagLayout();
    private final GridBagConstraints gb_constraints = new GridBagConstraints();

    private final EpisodeManager episodeManager = new EpisodeManager();
    private final JPanel paused = new JPanel();
    private final JPanel game = new GamePanel(episodeManager);
    private final GameThread gameThread = new GameThread(game, episodeManager);

    // PAUSED MENU BUTTONS
    private final List<JComponent> pausedButtons = new ArrayList<>(Arrays.asList(
    		UIsupplier.createMenuButton("Resume Game", 			// Restart the game thread, hide paused
    			(e) -> {unpaused();}, null),
    		UIsupplier.createMenuButton("Save Game", null ,null),
    		UIsupplier.createMenuButton("Quit to Title", 		// Quit to the title
    			(e) -> {
				paused.setVisible(false);
                gameThread.stopGameThread();
				Main.setState(Main.MAIN_MENU);}, null)
    ));
    
    /**
     * no-arg constructor
     */
    public Game() {
    	// Settings for the pane
        this.setPreferredSize(new Dimension(Main.screenWidth, Main.screenHeight));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.setRequestFocusEnabled(true);
        this.setVisible(false);
        this.setLayout(new CardLayout());

        createUI();
    }
    
    /**
     * createUI:		create the UI for the game.
     */
    private void createUI() {
        // Configure paused menu box 
        JComponent pausedMenu = UIsupplier.createMenuBox(pausedButtons);
        pausedMenu.setBorder(BorderFactory.createEtchedBorder(Color.darkGray, Color.gray));
        gb.setConstraints(pausedMenu, gb_constraints);

        // Add components to paused
        paused.add(pausedMenu);

        // Configure the layout of the paused panel
        paused.setLayout(gb);
        paused.setBackground(new Color(0,0,0,0));
        gb_constraints.ipadx = 10;
        gb_constraints.ipady = 10;
        gb.setConstraints(paused, gb_constraints);
        paused.setVisible(false);

        // Configure the layout of the game panel
        game.setLayout(gb);
        game.setBackground(Color.black);
        game.setVisible(false);

        add(game, "GAME");
        add(paused, "PAUSED");
    }

    /**
     * setup:
     * """ invoke the game thread
     * setup the default menu components for Game. """
     */
    @Override
	public void setup() {
    	this.setVisible(true);
    	this.grabFocus();
    	
        setKeyBindings();      				// set the bindings 
        episodeManager.startEpisode();		// Begin the current episode
    	gameThread.startGameThread();     	// start the thread
	}
   
    /**
     * paused:			stops gamethread and shows pause menu
     */
    private void paused() {
        ((CardLayout)this.getLayout()).show(this, "PAUSED");
    	gameThread.stopGameThread();
    }
    
    /**
     * unpaused:		resumes gamethread and hides pause menu
     */
    private void unpaused() {
        ((CardLayout)this.getLayout()).show(this, "GAME");
		gameThread.startGameThread();
    }
    
   /**
     * setKeyBindings:      setup the bindings required for the Game
     */
    private void setKeyBindings() {
    	// Pause the game on "esc" key being pressed
        Action esc = new AbstractAction() {
            private static final long serialVersionUID = 1L;
            @Override
            public void actionPerformed(ActionEvent e) {
            	paused();
            }
        };
        
        // Configure the input map
        this.getInputMap(
            JComponent.WHEN_IN_FOCUSED_WINDOW).put(
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESCAPE"
        );
        this.getActionMap().put("ESCAPE", esc);
    }
}
