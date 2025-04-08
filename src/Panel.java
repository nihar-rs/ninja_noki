package src;

import java.awt.Graphics;
import javax.swing.JPanel;

public class Panel extends JPanel {
  public Panel() {
  }

  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);

    graphics.fillRect(100, 100, 200, 50);
  }
}
