package src.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import src.main.Game;
import src.utils.LoadSave;
import src.utils.Constants.PlayerConstants;
import static src.utils.HelperMethods.*;

public class Player extends Entity {
  private BufferedImage[][] animations;
  private int animationTick, animationIndex, animationSpeed = 60;
  private int playerAction = PlayerConstants.IDLE;
  private boolean isMoving = false, isAttacking = false;
  private boolean left, up, right, down, jump;
  private float playerSpeed = 2.0f;
  private int[][] levelData;
  private float xDrawOffset = 21 * Game.SCALE, yDrawOffset = 4 * Game.SCALE;

  private float airSpeed = 0f;
  private float gravity = 0.05f * Game.SCALE;
  private float jumpSpeed = -3.25f * Game.SCALE;
  private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
  private boolean isInAir = false;

  public Player(float x, float y, int width, int height) {
    super(x, y, width, height);
    loadAnimations();
    initHitBox(x, y, 20 * Game.SCALE, 27 * Game.SCALE);
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
    // drawHitBox(graphics);
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

    if (isInAir) {
      if (airSpeed < 0) {
        playerAction = PlayerConstants.JUMP;
      } else {
        playerAction = PlayerConstants.FALLING;
      }
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
    if (jump)
      jump();
    if (!left && !right && !isInAir)
      return;

    float xSpeed = 0;

    if (left)
      xSpeed -= playerSpeed;
    if (right)
      xSpeed += playerSpeed;

    if (!isInAir) {
      if (!isEntityOnFloor(hitBox, levelData)) {
        isInAir = true;
      }
    }

    if (isInAir) {
      if (canMoveHere(hitBox.x, hitBox.y + airSpeed, hitBox.width, hitBox.height, levelData)) {
        hitBox.y += airSpeed;
        airSpeed += gravity;
        updateXPosition(xSpeed);
      } else {
        hitBox.y = getEntityYPositionUnderRoofAboveWall(hitBox, airSpeed);
        if (airSpeed > 0) {
          resetInAir();

        } else {
          airSpeed = fallSpeedAfterCollision;
        }
        updateXPosition(xSpeed);
      }
    } else {
      updateXPosition(xSpeed);
    }
    isMoving = true;
  }

  private void jump() {
    if (isInAir)
      return;
    isInAir = true;
    airSpeed = jumpSpeed;
  }

  private void resetInAir() {
    isInAir = false;
    airSpeed = 0;
  }

  private void updateXPosition(float xSpeed) {
    if (canMoveHere(hitBox.x + xSpeed, hitBox.y, hitBox.width, hitBox.height, levelData)) {
      hitBox.x += xSpeed;
    } else {
      hitBox.x = getEntityXPositionNextToWall(hitBox, xSpeed);
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
    if (!isEntityOnFloor(hitBox, levelData)) {
      isInAir = true;
    }
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

  public void setJump(boolean jump) {
    this.jump = jump;
  }
}
