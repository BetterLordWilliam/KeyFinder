package src.main;

import javax.swing.JFrame;

public class Main {
  public static void main(String[] args) {
    // Create JFrame (window) and set title + resize
    JFrame window = new JFrame();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);
    window.setTitle("KeyFinder");

    GamePanel gamePanel = new GamePanel();
    window.add(gamePanel);

    window.pack(); // Causes window to be sized to fit the game panel

    window.setLocationRelativeTo(null);
    window.setVisible(true);

    // Start the game
    gamePanel.setObjects();
    gamePanel.startGameThread();
  }
}