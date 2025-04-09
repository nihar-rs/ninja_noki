package src.main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import src.inputs.KeyboardInputs;
import src.inputs.MouseInputs;

public class GamePanel extends JPanel {
  private MouseInputs mouseInputs = new MouseInputs(this);
  private Game game;

  public GamePanel(Game game) {
    this.game = game;
    addKeyListener(new KeyboardInputs(this) {
    });
    addMouseListener(mouseInputs);
    addMouseMotionListener(mouseInputs);
    // addMouseWheelListener(new MouseWheelInputs() {});

    setPanelSize();
  }

  private void setPanelSize() {
    Dimension size = new Dimension(1280, 800);
    setMinimumSize(size);
    setPreferredSize(size);
    setMaximumSize(size);
  }

  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    game.render(graphics);
  }

  public Game getGame() {
    return game;
  }

}
