import javax.swing.JFrame;

public class Window {
  private JFrame jFrame;

  public Window(Panel gamePanel) {
    jFrame = new JFrame();
    jFrame.setSize(1280, 720);
    jFrame.setResizable(false);
    jFrame.setLocationRelativeTo(null);
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jFrame.setTitle("ninja-noki");

    jFrame.add(gamePanel);
    jFrame.setVisible(true);
  }
}
