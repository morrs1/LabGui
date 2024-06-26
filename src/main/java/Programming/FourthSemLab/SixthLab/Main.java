package Programming.FourthSemLab.SixthLab;

import Programming.FourthSemLab.SixthLab.eightTask.RunningText;
import Programming.FourthSemLab.SixthLab.fifthTask.ImageViewerApp;
import Programming.FourthSemLab.SixthLab.fourthTask.WindowApp;
import Programming.FourthSemLab.SixthLab.secondTask.Curve;
import Programming.FourthSemLab.SixthLab.secondTask.CurveApp;
import Programming.FourthSemLab.SixthLab.seventhTask.BouncingBallApp;
import Programming.FourthSemLab.SixthLab.thirdTask.Graph;
import Programming.FourthSemLab.SixthLab.thirdTask.GraphDrawer;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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
      yValues1[i] = Math.pow(x,2);
    }
    Curve curve1 = new Curve(xValues1, yValues1);
    Curve curve = new Curve(xValues, yValues);
    Graph graph = new Graph();

    graph.addCurve(curve);
    graph.addCurve(curve1);


    var gd = new GraphDrawer(graph);
    gd.draw();
    return "";
  }

  public static String fourthTask(String ignoredUnused) {
    new WindowApp();
    return "";
  }

  public static String fifthTask(String ignoredUnused) {
    new ImageViewerApp();
    return "";
  }
  public static String sixthTask(String ignoredUnused) {
    new ImageViewerApp();
    return "";
  }

  public static String seventhTask(String ignoredUnused) {
    SwingUtilities.invokeLater(() -> {
      JFrame frame = new JFrame("Bouncing Balls");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(new BouncingBallApp());
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
    });
    return "";
  }
  public static String eighthTask(String ignoredUnused) {
    SwingUtilities.invokeLater(() -> {
      JFrame frame = new JFrame("Бегущая строка");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(new RunningText());
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
    });
    return "";
  }


  public static void main(String[] args) {

  }
}
