package src.main;


/**
 * GameManager:     Controls the game, initiates states, etc.
 * 
 * @author          Will Otterbein
 * @version         2024-1
 */
public class GameManager implements Runnable {
    private GamePanel gp;
    private Thread gameTread;
    
    // GAME OBJECTS
    // KeyHandler, AudioHandler, etc.
    
    // THREAD INFO
    private final int fps = 60;
    private Thread gameThread;
    
    public GameManager(GamePanel gp) {
        this.gp = gp;       // Connect the GamePanel
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
                gp.repaint();
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
}
