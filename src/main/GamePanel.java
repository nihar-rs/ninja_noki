package src.main;

import java.awt.Graphics;
import javax.swing.JPanel;

import src.inputs.KeyboardInputs;
import src.inputs.MouseInputs;

public class GamePanel extends JPanel {
  private MouseInputs mouseInputs = new MouseInputs(this);
  private int xDelta = 100, yDelta = 100;

  public GamePanel() {
    addKeyListener(new KeyboardInputs(this) {
    });
    addMouseListener(mouseInputs);
    addMouseMotionListener(mouseInputs);
    // addMouseWheelListener(new MouseWheelInputs() {});
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
    graphics.fillRect(xDelta, yDelta, 250, 100);
  }
}
