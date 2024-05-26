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
		window.setTitle("KeyFinder");
		
		GamePanel gp = new GamePanel();
		window.add(gp);
		
		window.pack(); // causes window size to fit to the game panel
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		// Start the game
		gp.startGameThread();
	}
}