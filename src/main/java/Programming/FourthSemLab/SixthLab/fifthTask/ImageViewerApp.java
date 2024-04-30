package Programming.FourthSemLab.SixthLab.fifthTask;

import javax.swing.JFrame;

public class ImageViewerApp extends JFrame {
  public ImageViewerApp() {
    setTitle("Просмотр изображения");
    setSize(300, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    add(new ImageViewerPanel());
    setVisible(true);
  }

  public static void main(String[] args) {
    new ImageViewerApp();
  }
}
