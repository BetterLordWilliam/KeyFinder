package src.main;

// import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
// import java.awt.Rectangle;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;
import src.map.Episode;

/**
 * GamePanel:           the class responsible for drawing everything.
 * 
 * @author              Will Otterbein
 * @version             2024-1
 */
public class GamePanel <Ptr extends Paintable> extends JPanel  {
    /**
     * required because this is a JPanel.
	 */
	private static final long serialVersionUID = 1L;
	
	// CONNECTIONS
	
	private Episode Episode;
   
    public GamePanel(Episode Episode) {
        this.Episode = Episode;
    }

    /**
     * paintComponent:      will be called once per frame
     */
    @SuppressWarnings("unchecked")
	@Override
    public  void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        super.paintComponent(g2);   			// window

        // Testing the layering of graphics drawing vs menu drawing.
        /*Rectangle test = new Rectangle(100, 100, 400, 400);
        Rectangle test1 = new Rectangle(100, 600, 400, 100);

        g2.setColor(Color.red);
        g2.fill(test);
        g2.draw(test);

        g2.setColor(Color.yellow);
        g2.fill(test1);
        g2.draw(test1);

        // Gather the lists of items to paint
        */
        
        List<Ptr> tList, oList, eList;				// some references 
        
        if (Episode.getCurrentMap() != null
        		&& (tList = (List<Ptr>) Episode.getCurrentMap().getTiles()) != null
        		&& (oList = (List<Ptr>) Episode.getCurrentMap().getObjects()) != null
        		&& (eList = (List<Ptr>) Episode.getCurrentMap().getEntities()) != null) {
        	List<List<Ptr>> megaList = new LinkedList<>(Arrays.asList(
        			tList, oList, eList
			));
        	
	        // Paint these items in order
        	// 1 tiles, 2 objects, 3 entities
        }

        g2.dispose();
    }
}
