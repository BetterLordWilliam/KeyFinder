package src.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JPanel implements Runnable, State {
    /**
     * required because this is a JPanel
     */
    private static final long serialVersionUID = 1L;
    
    // THREAD INFO
    private final int fps = 60;             // I'm not sure why I am saying this, but frames per second
    private Thread gameThread;

    public Game() {
        this.setPreferredSize(new Dimension(Main.screenWidth, Main.screenHeight));
        this.setBackground(Color.red);
        this.setDoubleBuffered(true);       // this does
        this.setFocusable(true);
        test();                             // for testing state management
    }
    
    private void test() {
        JLabel test = new JLabel("This is the Game state");
        this.add(test);
    }
    
    /**
     * startGameThread:     begin KeyFinder game thread
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    /**
     * game thread entry.
     * panel update logic, use frames to draw at 60fps.
     */
    @Override
    public void run() {
        // TODO Auto-generated method stub
        double drawInteveral = 1000000000 / fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        
        while (gameThread != null) {
            // game time
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInteveral;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
            if (timer >= 1000000000) {
                timer = 0;
            }
        }
    }
    
    /**
     * update:              allow the entites to perform their logic
     */
    void update() {
        // System.out.println("This is a frame");
    }

    /**
     * paint:               Used to redraw the game tiles, objects and entities
     * 
     * @param g2            Graphics2D, will get from paintComponent method
     */
    public void paintComponent(Graphics2D g2) {
        // TODO Auto-generated method stub
        // Will draw GAME objects and stuff here.
        // Draws the aspects of the game in order
        // 1 the map
        // 2 the objects
        // 3 the player
        // 4 the UI
        super.paintComponent(g2);   // window

        g2.dispose();
    }
}
