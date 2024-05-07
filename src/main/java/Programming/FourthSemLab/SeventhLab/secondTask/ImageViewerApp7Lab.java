package Programming.FourthSemLab.SeventhLab.secondTask;

import javax.swing.JFrame;

public class ImageViewerApp7Lab extends JFrame {
  public ImageViewerApp7Lab() {
    setTitle("Просмотр изображения");
    setSize(300, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    add(new ImageViewerPanel7Lab());
    setVisible(true);
  }
}
