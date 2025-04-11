package src.utils;

import java.awt.geom.Rectangle2D;

import src.main.Game;

public class HelperMethods {

  public static boolean canMoveHere(float x, float y, float width, float height, int[][] levelData) {
    if (!isSolid(x, y, levelData))
      if (!isSolid(x + width, y + height, levelData))
        if (!isSolid(x + width, y, levelData))
          if (!isSolid(x, y + height, levelData))
            return true;
    return false;
  }

  private static boolean isSolid(float x, float y, int[][] levelData) {
    if (x < 0 || x >= Game.GAME_WIDTH)
      return true;
    if (y < 0 || y >= Game.GAME_HEIGHT)
      return true;

    float xIndex = x / Game.TILES_SIZE;
    float yIndex = y / Game.TILES_SIZE;

    int value = levelData[(int) yIndex][(int) xIndex];

    if (value >= 48 || value < 0 || value != 11)
      return true;
    return false;
  }

  public static float getEntityXPositionNextToWall(Rectangle2D.Float hitBox, float xSpeed) {
    int currentTile = (int) (hitBox.x / Game.TILES_SIZE);

    if (xSpeed > 0) {
      int tileXposition = currentTile * Game.TILES_SIZE;
      int xOffset = (int) (Game.TILES_SIZE - hitBox.width);
      return tileXposition + xOffset - 1;
    } else {
      return currentTile * Game.TILES_SIZE;
    }
  }

  public static float getEntityYPositionUnderRoofAboveWall(Rectangle2D.Float hitBox, float airSpeed) {
    int currentTile = (int) (hitBox.y / Game.TILES_SIZE);
    if (airSpeed > 0) {
      int tileYposition = currentTile * Game.TILES_SIZE;
      int yOffset = (int) (Game.TILES_SIZE - hitBox.height);
      return tileYposition + yOffset - 1;
    } else {
      return currentTile * Game.TILES_SIZE;
    }
  }

  public static boolean isEntityOnFloor(Rectangle2D.Float hitBox, int[][] levelData) {
    if (!isSolid(hitBox.x, hitBox.y + hitBox.height + 1, levelData))
      if (!isSolid(hitBox.x + hitBox.width, hitBox.y + hitBox.height + 1, levelData))
        return false;

    return true;
  }
}
