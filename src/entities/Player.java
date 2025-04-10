package src.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import src.main.Game;
import src.utils.LoadSave;
import src.utils.Constants.PlayerConstants;
import static src.utils.HelperMethods.CanMoveHere;

public class Player extends Entity {
  private BufferedImage[][] animations;
  private int animationTick, animationIndex, animationSpeed = 60;
  private int playerAction = PlayerConstants.IDLE;
  private boolean isMoving = false, isAttacking = false;
  private boolean left, up, right, down;
  private float playerSpeed = 2.0f;
  private int[][] levelData;
  private float xDrawOffset = 21 * Game.SCALE, yDrawOffset = 4 * Game.SCALE;

  public Player(float x, float y, int width, int height) {
    super(x, y, width, height);
    loadAnimations();
    initHitBox(x, y, 20 * Game.SCALE, 28 * Game.SCALE);
  }

  public void update() {
    updatePosition();
    updateAnimationTick();
    setAnimation();
  }

  public void render(Graphics graphics) {
    graphics.drawImage(animations[playerAction][animationIndex], (int) (hitBox.x - xDrawOffset),
        (int) (hitBox.y - yDrawOffset), width,
        height, null);
    drawHitBox(graphics);
  }

  private void updateAnimationTick() {
    animationTick++;
    if (animationTick >= animationSpeed) {
      animationTick = 0;
      animationIndex++;
      if (animationIndex >= PlayerConstants.getSpriteAmount(playerAction)) {
        animationIndex = 0;
        isAttacking = false;
      }
    }
  }

  private void setAnimation() {
    int setAnimation = playerAction;

    if (isMoving) {
      playerAction = PlayerConstants.RUNNING;
    } else {
      playerAction = PlayerConstants.IDLE;
    }

    if (isAttacking) {
      playerAction = PlayerConstants.ATTACK_1;
    }

    if (setAnimation != playerAction) {
      resetAnimationTick();
    }
  }

  private void resetAnimationTick() {
    animationTick = 0;
    animationIndex = 0;
  }

  private void updatePosition() {
    isMoving = false;

    if (!left && !right && !up && !down)
      return;

    float xSpeed = 0, ySpeed = 0;

    if (left && !right)
      xSpeed = -playerSpeed;
    else if (right && !left)
      xSpeed = playerSpeed;

    if (up && !down)
      ySpeed = -playerSpeed;
    else if (down && !up)
      ySpeed = playerSpeed;

    if (CanMoveHere(hitBox.x + xSpeed, hitBox.y + ySpeed, hitBox.width, hitBox.height, levelData)) {
      hitBox.x += xSpeed;
      hitBox.y += ySpeed;
      isMoving = true;
    }

  }

  private void loadAnimations() {
    BufferedImage image = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);

    animations = new BufferedImage[9][6];
    for (int i = 0; i < animations.length; i++) {
      for (int j = 0; j < animations[i].length; j++) {
        animations[i][j] = image.getSubimage(j * 64, i * 40, 64, 40);
      }
    }
  }

  public void loadLevelData(int[][] levelData) {
    this.levelData = levelData;
  }

  public void resetDirectionalBooleans() {
    left = false;
    up = false;
    down = false;
    right = false;
  }

  public boolean isLeft() {
    return left;
  }

  public void setAttacking(boolean isAttacking) {
    this.isAttacking = isAttacking;
  }

  public void setLeft(boolean left) {
    this.left = left;
  }

  public boolean isUp() {
    return up;
  }

  public void setUp(boolean up) {
    this.up = up;
  }

  public boolean isRight() {
    return right;
  }

  public void setRight(boolean right) {
    this.right = right;
  }

  public boolean isDown() {
    return down;
  }

  public void setDown(boolean down) {
    this.down = down;
  }

}
