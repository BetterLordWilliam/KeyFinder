package src.main.threads;

import java.util.List;
import javax.swing.JPanel;

import src.resources.entity.Entity;
import src.resources.map.Episode;

/**
 * GameThread:      The thread which will update and repaint during game execution.abstract 
 * 
 * @author          Will Otterbein
 * @version         2024-1
 */
public class GameThread implements Runnable {
    // THREAD INFO
    private static final int FPS = 60;
    private Thread gameThread = new Thread();

    // CONNECTIONS
    private JPanel rootPanel = null;        // Where to draw things
    private Episode Episode = null;       	// What to draw, what to update

    /**
     * constructor, requires that you supply pointers to the root panel
     * as well as the Episode where entity data will come from.
     * 
     * @param rootPanel         JPanel, where graphical items are to be drawn
     * @param Episode    Episode, where the list of entities is to be retrieved
     */
    public GameThread(JPanel rootPanel) {
		this.rootPanel = rootPanel;					// Get panel reference from Game
    }

    /**
     * setEpisode:       establishes pointer to the episode manager.
     * 
     * @param Episode
     */
    public void setEpisode(Episode Episode) {
        this.Episode = Episode;
    }

    /**
     * startGameThread:     begin KeyFinder game thread.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    /**
     * stopGameThread:      sets the gameThread to null.
     */
    public void stopGameThread() {
        gameThread = null;
    }

    /**
     * update:              called once per-frame.
     */
    public void update() {
    	List<Entity> eList;			// eList reference
    	
        if (Episode.getCurrentMap() != null 
        		&& (eList = Episode.getCurrentMap().getEntities()) != null){
			for (Entity thing : eList)
				if (thing != null)
					thing.update();
        }
        // System.out.println("Frame");
    }

    /**
     * game thread entry.
     * panel update logic, use frames to draw at 60FPS.
     */
    @Override
    public void run() {
        double drawInteveral = 1000000000 / FPS;
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
                if (rootPanel != null && Episode != null) {
                    update();
                    rootPanel.repaint();
                }
                delta--;
            }
            if (timer >= 1000000000) {
                timer = 0;
            }
        }
    }
}