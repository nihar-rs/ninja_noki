package src.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import src.main.Game;

public class LoadSave {
  public static final String PLAYER_ATLAS = "player_sprites.png";
  public static final String LEVEL_ATLAS = "outside_sprites.png";
  public static final String LEVEL_ONE_DATA = "level_one_data.png";

  public static BufferedImage getSpriteAtlas(String fileName) {
    BufferedImage image = null;

    try {
      image = ImageIO.read(LoadSave.class.getResource("../images/" + fileName));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return image;
  }

  public static int[][] getLevelData() {
    int[][] levelData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
    BufferedImage image = getSpriteAtlas(LEVEL_ONE_DATA);

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        Color color = new Color(image.getRGB(j, i));
        int value = color.getRed();
        if (value >= 48) {
          value = 0;
        }
        levelData[i][j] = value;
      }
    }
    return levelData;
  }
}
