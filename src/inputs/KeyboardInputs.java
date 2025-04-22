package src.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import src.gameStates.GameState;
import src.main.GamePanel;

public class KeyboardInputs implements KeyListener {

  private GamePanel gamePanel;

  public KeyboardInputs(GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyReleased(KeyEvent e) {
    switch (GameState.state) {
      case MENU:
        gamePanel.getGame().getMenus().keyReleased(e);
        break;
      case PLAYING:
        gamePanel.getGame().getPlaying().keyReleased(e);
        break;
      default:
        break;
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (GameState.state) {
      case MENU:
        gamePanel.getGame().getMenus().keyPressed(e);
        break;
      case PLAYING:
        gamePanel.getGame().getPlaying().keyPressed(e);
        break;
      default:
        break;
    }
  }

}
