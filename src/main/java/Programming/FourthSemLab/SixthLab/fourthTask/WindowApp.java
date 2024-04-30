package Programming.FourthSemLab.SixthLab.fourthTask;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class WindowApp extends JFrame {

  public WindowApp() {
    setTitle("График функции");
    setSize(600, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    add(new WindowPanel());
    setVisible(true);


  }


}
