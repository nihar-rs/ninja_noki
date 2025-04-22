package src.gameStates;

import src.entities.Player;
import src.levels.LevelManager;
import src.main.Game;

import static src.main.Game.SCALE;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Play extends State implements StateMethods {
  private Player player;
  private LevelManager levelManager;

  public Play(Game game) {
    super(game);
    initClasses();
  }

  private void initClasses() {
    levelManager = new LevelManager(game);
    player = new Player(200, 200, (int) (64 * SCALE), (int) (40 * SCALE));
    player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
  }

  @Override
  public void update() {
    levelManager.update();
    player.update();
  }

  @Override
  public void draw(Graphics graphics) {
    levelManager.draw(graphics);
    player.render(graphics);
  }

  @Override
  public void mouseClicked(MouseEvent mouseEvent) {
    if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
      player.setAttacking(true);
    }
    throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
  }

  @Override
  public void mousePressed(MouseEvent mouseEvent) {
  }

  @Override
  public void mouseReleased(MouseEvent mouseEvent) {
  }

  @Override
  public void mouseMoved(MouseEvent mouseEvent) {
  }

  @Override
  public void keyPressed(KeyEvent keyEvent) {
    switch (keyEvent.getKeyCode()) {
      case KeyEvent.VK_A:
        player.setLeft(true);
        break;
      case KeyEvent.VK_D:
        player.setRight(true);
        break;
      case KeyEvent.VK_SPACE:
        player.setJump(true);
        break;
      case KeyEvent.VK_ESCAPE:
        GameState.state = GameState.MENU;
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent keyEvent) {
    switch (keyEvent.getKeyCode()) {
      case KeyEvent.VK_A:
        player.setLeft(false);
        break;
      case KeyEvent.VK_D:
        player.setRight(false);
        break;
      case KeyEvent.VK_SPACE:
        player.setJump(false);
        break;
    }
  }

  public void windowFocusLost() {
    player.resetDirectionalBooleans();
  }

  public Player getPlayer() {
    return player;
  }
}
