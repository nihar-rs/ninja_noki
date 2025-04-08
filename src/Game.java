package src;

public class Game {
  private Window gameWindow;
  private Panel gamePanel;

  public Game() {
    gamePanel = new Panel();
    gameWindow = new Window(gamePanel);
  }
}
