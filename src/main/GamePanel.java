package src.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import src.inputs.KeyboardInputs;
import src.inputs.MouseInputs;

public class GamePanel extends JPanel {
  private MouseInputs mouseInputs = new MouseInputs(this);
  private float xDelta = 100, yDelta = 100;
  private BufferedImage image, subImage;

  public GamePanel() {
    addKeyListener(new KeyboardInputs(this) {
    });
    addMouseListener(mouseInputs);
    addMouseMotionListener(mouseInputs);
    // addMouseWheelListener(new MouseWheelInputs() {});

    setPanelSize();
    importImage();
  }

  private void importImage() {
    try {
      image = ImageIO.read(getClass().getResource("../images/player_sprites.png"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void setPanelSize() {
    Dimension size = new Dimension(1280, 800);
    setMinimumSize(size);
    setPreferredSize(size);
    setMaximumSize(size);
  }

  public void changeXDelta(int value) {
    this.xDelta += value;
    repaint();
  }

  public void changeYDelta(int value) {
    this.yDelta += value;
    repaint();
  }

  public void setRectPosition(int x, int y) {
    this.xDelta = x;
    this.yDelta = y;
    repaint();
  }

  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);

    if (image != null) {
      subImage = image.getSubimage(1 * 64, 8 * 40, 64, 40);
      graphics.drawImage(subImage, (int) xDelta, (int) yDelta, 128, 80, null);
    }
  }
}
