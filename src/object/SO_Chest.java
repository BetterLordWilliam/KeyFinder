package src.object;

import java.io.IOException;
import javax.imageio.ImageIO;

// Colour Legend
// Yellow = 0
// Blue = 1
// Red = 2
// Purple = 3

public class SO_Chest extends SuperObject {
  public SO_Chest() {
    this.name = "Chest";
    try {
      this.image = ImageIO.read(getClass().getResourceAsStream("/res/objects/P_ChestClosed.png"));
      this.image2 = ImageIO.read(getClass().getResourceAsStream("/res/objects/ChestOpened.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }
}
