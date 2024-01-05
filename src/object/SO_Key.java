package src.object;

import java.io.IOException;
import javax.imageio.ImageIO;

// Colour Legend
// Yellow = 0
// Blue = 1
// Red = 2
// Purple = 3

public class SO_Key extends SuperObject {
  public SO_Key(int color) {
    this.name = "Key";
    this.color = color;
    try {
      switch (this.color) {
        case 0:
          this.image = ImageIO.read(getClass().getResourceAsStream("/res/objects/Y_Key.png"));
        break;
        case 1:
          this.image = ImageIO.read(getClass().getResourceAsStream("/res/objects/B_Key.png"));
        break;
        case 2:
          this.image = ImageIO.read(getClass().getResourceAsStream("/res/objects/R_Key.png"));
        break;
        case 3:
          this.image = ImageIO.read(getClass().getResourceAsStream("/res/objects/P_Key.png"));
        break;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
