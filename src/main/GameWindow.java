package src.main;

import javax.swing.JFrame;

public class GameWindow {
  private JFrame jFrame;

  public GameWindow(GamePanel gamePanel) {
    jFrame = new JFrame();
    jFrame.setResizable(false);
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jFrame.setTitle("ninja-noki");

    jFrame.add(gamePanel);
    jFrame.pack();
    jFrame.setLocationRelativeTo(null);
    jFrame.setVisible(true);
  }
}
