package src.inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import src.gameStates.GameState;
import src.main.GamePanel;

public class MouseInputs implements MouseListener, MouseMotionListener {
  private GamePanel gamePanel;

  public MouseInputs(GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    switch (GameState.state) {
      case MENU:
        gamePanel.getGame().getMenus().mouseClicked(e);
        break;
      case PLAYING:
        gamePanel.getGame().getPlaying().mouseClicked(e);
        break;
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

  @Override
  public void mouseDragged(MouseEvent e) {

  }

  @Override
  public void mouseMoved(MouseEvent e) {
  }

}
