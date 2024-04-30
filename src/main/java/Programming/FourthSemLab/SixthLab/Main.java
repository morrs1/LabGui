package Programming.FourthSemLab.SixthLab;

import Programming.FourthSemLab.SixthLab.secondTask.CurveApp;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

  public static String firstTask(String ignoredUnused) {
    var frame = new JFrame("Task 1");
    frame.setSize(320, 340);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    var panel = new JPanel() {
      @Override
      public void paint(Graphics g) {
        // Рисуем оси координат
        g.drawLine(150, 300, 150, 0); // Ось X
        g.drawLine(0, 150, 300, 150); // Ось Y
        //График синуса
        for (double x = -Math.PI; x <= Math.PI; x += 0.01) {
          double y = Math.sin(x);
          int xPos = (int) ((x + Math.PI) * 48);
          int yPos = (int) ((y + 1) * 50 + 101);
          g.drawLine(xPos, yPos, xPos + 1, yPos + 1);
        }
      }
    };
    frame.add(panel);
    frame.setVisible(true);
    return "";
  }

  public static String secondTask(String ignoredUnused) {
    new CurveApp();
    return "";
  }


  public static void main(String[] args) {

  }
}
