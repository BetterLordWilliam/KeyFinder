package src.object;

import java.io.IOException;
import javax.imageio.ImageIO;

// Colour Legend
// Yellow = 0
// Blue = 1
// Red = 2
// Purple = 3

public class SO_Door extends SuperObject {
  public SO_Door(int color) {
    this.name = "Door";
    this.color = color;
    try {
      switch (this.color) {
        case 0:
          this.image = ImageIO.read(getClass().getResourceAsStream("/res/objects/Y_Door.png"));
        break;
        case 1:
          this.image = ImageIO.read(getClass().getResourceAsStream("/res/objects/B_Door.png"));
        break;
        case 2:
          this.image = ImageIO.read(getClass().getResourceAsStream("/res/objects/R_Door.png"));
        break;
        case 999:
          this.image = ImageIO.read(getClass().getResourceAsStream("/res/objects/Door.png"));
        break;
      }
      this.image2 = ImageIO.read(getClass().getResourceAsStream("/res/objects/DoorOpened.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }
}
