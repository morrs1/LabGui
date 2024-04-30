package Programming.FourthSemLab.SixthLab;

import Programming.FourthSemLab.SixthLab.secondTask.Curve;
import Programming.FourthSemLab.SixthLab.secondTask.CurveApp;
import Programming.FourthSemLab.SixthLab.thirdTask.Graph;
import java.awt.Color;
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

  public static String thirdTask(String ignoredUnused) {
    double[] xValues = new double[360];
    double[] yValues = new double[360];
    for (int i = 0; i < 360; i++) {
      double x = i * Math.PI / 180;
      xValues[i] = x;
      yValues[i] = Math.sin(x);
    }

    double[] xValues1 = new double[360];
    double[] yValues1 = new double[360];
    for (int i = 0; i < 360; i++) {
      double x = (i-180) * Math.PI / 180;
      xValues1[i] = x;
      yValues1[i] = Math.sin(x);
    }
    Curve curve1 = new Curve(xValues1, yValues1);
    Curve curve = new Curve(xValues, yValues);
    Graph graph = new Graph();
    curve.setOpaque(false);
    graph.addCurve(curve);
    curve1.setOpaque(false);
    graph.addCurve(curve1);




    graph.draw();
    return "";
  }


  public static void main(String[] args) {

  }
}
