package src.main;

import java.awt.Graphics;

import src.entities.Player;
import src.levels.LevelManager;

public class Game implements Runnable {
  private GameWindow gameWindow;
  private GamePanel gamePanel;
  private Thread gameThread;
  private final int FPS_SET = 120;
  private final int UPS_SET = 200;
  private Player player;
  private LevelManager levelManager;

  public final static int TILES_DEFAULT_SIZE = 32;
  public final static float SCALE = 1.5f;
  public final static int TILES_IN_WIDTH = 26;
  public final static int TILES_IN_HEIGHT = 14;
  public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
  public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
  public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

  public Game() {
    initClasses();
    gamePanel = new GamePanel(this);
    gameWindow = new GameWindow(gamePanel);
    gamePanel.requestFocus();
    startGameLoop();
  }

  private void initClasses() {
    player = new Player(200, 200, (int) (64 * SCALE), (int) (40 * SCALE));
    levelManager = new LevelManager(this);
    player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
  }

  private void startGameLoop() {
    gameThread = new Thread(this);
    gameThread.start();
  }

  private void update() {
    player.update();
    levelManager.update();
  }

  public void render(Graphics graphics) {
    levelManager.draw(graphics);
    player.render(graphics);
  }

  @Override
  public void run() {
    double timePerFrame = 1000000000.0 / FPS_SET;
    double timePerUpdate = 1000000000.0 / UPS_SET;
    double deltaU = 0;
    double deltaF = 0;

    long previousTime = System.nanoTime();
    long lastCheck = System.currentTimeMillis();

    int frames = 0;
    int updates = 0;

    while (true) {
      long currentTime = System.nanoTime();
      deltaU += (currentTime - previousTime) / timePerUpdate;
      deltaF += (currentTime - previousTime) / timePerFrame;
      previousTime = currentTime;

      if (deltaU >= 1) {
        update();
        updates++;
        deltaU--;
      }

      if (deltaF >= 1) {
        gamePanel.repaint();
        deltaF--;
        frames++;
      }

      if (System.currentTimeMillis() - lastCheck >= 1000) {
        lastCheck = System.currentTimeMillis();
        System.out.println("FPS: " + frames + " | UPS: " + updates);
        frames = 0;
        updates = 0;
      }
    }

  }

  public void windowFocusLost() {
    player.resetDirectionalBooleans();
  }

  public Player getPlayer() {
    return player;
  }

}
