package src.gameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import src.main.Game;

public class Menus extends State implements StateMethods {

  public Menus(Game game) {
    super(game);
  }

  @Override
  public void update() {
  }

  @Override
  public void draw(Graphics graphics) {
    graphics.setColor(Color.black);
    graphics.drawString("MENU", Game.GAME_WIDTH / 2, 200);
  }

  @Override
  public void mouseClicked(MouseEvent mouseEvent) {
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
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      GameState.state = GameState.PLAYING;
    }
  }

  @Override
  public void keyReleased(KeyEvent keyEvent) {
  }

}
