package src.main;

import javax.swing.JFrame;

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
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		// test
		window.setTitle("KeyFinder");
		
		GamePanel gp = new GamePanel();               // Define the GamePanel (canvas)
		GameManager gm = new GameManager(gp);         // Define the GameManager (Arbiter of logic)
		window.add(gp);                               // ADD GamePanel to the JFrame (window)
		
		window.pack();                                // causes window size to fit to the game panel
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		// Start the game
		gm.startGameThread();                         // Tell the GameManager to start the game
	}
}