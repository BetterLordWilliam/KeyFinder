package src.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;
import src.map.EpisodeManager;

/**
 * GamePanel:           the class responsible for drawing everything.
 * 
 * @author              Will Otterbein
 * @version             2024-1
 */
public class GamePanel <Ptr extends Paintable> extends JPanel  {
    private EpisodeManager episodeManager;
   
    public GamePanel(EpisodeManager episodeManager) {
        this.episodeManager = episodeManager;
    }

    /**
     * paintComponent:      will be called once per frame
     */
    @Override
    public  void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        super.paintComponent(g2);   			// window

        // Testing the layering of graphics drawing vs menu drawing.
        Rectangle test = new Rectangle(100, 100, 400, 400);
        Rectangle test1 = new Rectangle(100, 600, 400, 100);

        g2.setColor(Color.red);
        g2.fill(test);
        g2.draw(test);

        g2.setColor(Color.yellow);
        g2.fill(test1);
        g2.draw(test1);

        // Gather the lists of items to paint
        @SuppressWarnings("unchecked")
        List<List<Ptr>> megaList = new LinkedList<>(Arrays.asList(
            (List<Ptr>)episodeManager.getCurrentLevelTiles(),
            (List<Ptr>)episodeManager.getCurrentLevelObjects(),
            (List<Ptr>)episodeManager.getCurrentLevelEntities()
        ));
        // Paint these items in order
        // 1 tiles, 2 objects, 3 entities
        for (List<Ptr> paintables : megaList) {
            if (paintables != null) {
                for (Ptr paintable : paintables) {
                    if (paintable != null) {
                        paintable.paint(g2);
                    }
                }
            }
        }


        g2.dispose();
    }
}
