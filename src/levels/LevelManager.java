package src.levels;

import static src.main.Game.TILES_SIZE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import src.main.Game;
import src.utils.LoadSave;

public class LevelManager {
  private Game game;
  private BufferedImage[] levelSprite;
  private Level levelOne = new Level(LoadSave.getLevelData());

  public LevelManager(Game game) {
    this.game = game;
    // levelSprite = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
    importOutsideSprite();
  }

  private void importOutsideSprite() {
    BufferedImage image = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
    levelSprite = new BufferedImage[48];
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 12; j++) {
        int index = i * 12 + j;
        levelSprite[index] = image.getSubimage(j * 32, i * 32, 32, 32);
      }
    }
  }

  public void draw(Graphics graphics) {
    for (int i = 0; i < Game.TILES_IN_HEIGHT; i++) {
      for (int j = 0; j < Game.TILES_IN_WIDTH; j++) {
        int index = levelOne.getSpriteIndex(j, i);
        graphics.drawImage(levelSprite[index], TILES_SIZE * j, TILES_SIZE * i, TILES_SIZE, TILES_SIZE, null);
      }
    }
  }

  public void update() {

  }

  public Level getCurrentLevel() {
    return levelOne;
  }
}
