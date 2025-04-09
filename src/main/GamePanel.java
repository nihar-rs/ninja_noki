package src.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import src.inputs.KeyboardInputs;
import src.inputs.MouseInputs;
import static src.utils.Constants.PlayerConstants.*;

public class GamePanel extends JPanel {
  private MouseInputs mouseInputs = new MouseInputs(this);
  private float xDelta = 100, yDelta = 100;
  private BufferedImage image;
  private BufferedImage[][] animations;
  private int animationTick, animationIndex, animationSpeed = 15;
  private int playerAction = IDLE;

  public GamePanel() {
    addKeyListener(new KeyboardInputs(this) {
    });
    addMouseListener(mouseInputs);
    addMouseMotionListener(mouseInputs);
    // addMouseWheelListener(new MouseWheelInputs() {});

    setPanelSize();
    importImage();
    loadAnimations();
  }

  private void loadAnimations() {
    animations = new BufferedImage[9][6];
    for (int i = 0; i < animations.length; i++) {
      for (int j = 0; j < animations[i].length; j++) {
        animations[i][j] = image.getSubimage(j * 64, i * 40, 64, 40);
      }
    }
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

  private void updateAnimationTick() {
    animationTick++;
    if (animationTick >= animationSpeed) {
      animationTick = 0;
      animationIndex++;
      if (animationIndex >= getSpriteAmount(playerAction)) {
        animationIndex = 0;
      }
    }
  }

  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);

    updateAnimationTick();
    if (image != null) {
      graphics.drawImage(animations[playerAction][animationIndex], (int) xDelta, (int) yDelta, 128, 80, null);
    }
  }

}
