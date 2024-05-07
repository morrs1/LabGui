package Programming.FourthSemLab.SeventhLab.secondTask;

import Programming.FourthSemLab.SixthLab.fifthTask.ImageViewerPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ImageViewerPanel7Lab extends ImageViewerPanel {
  public ImageViewerPanel7Lab() {
    super();
    removeKeyListener(k);
    removeMouseListener(ml);
    JButton buttonR = new JButton("Reverse");
    JButton buttonG = new JButton("Gray");
    JButton buttonB = new JButton("Blur");
    buttonR.addActionListener(e -> {
      image = reverseImage();
      repaint();
    });

    buttonB.addActionListener(e -> {
      image = blurImage(image);
      repaint();
    });

    buttonG.addActionListener(e -> {
      if (!flagG) {
        image = convertToGrayscale(image);
        flagG = true;
      }
      repaint();
    });
    JPanel controlPanel = new JPanel();
    controlPanel.add(buttonR);
    controlPanel.add(buttonG);
    controlPanel.add(buttonB);
    add(controlPanel, BorderLayout.NORTH);
  }
}
