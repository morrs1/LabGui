package Programming.FourthSemLab.SeventhLab.fourthTask;

import Programming.FourthSemLab.SixthLab.secondTask.Curve;
import Programming.FourthSemLab.SixthLab.thirdTask.Axis;
import Programming.FourthSemLab.SixthLab.thirdTask.Graph;
import Programming.FourthSemLab.SixthLab.thirdTask.Grid;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class GraphPanel extends Graph {

  private Color color = Color.black;
  private Curve curve;
  private double[] xValues = new double[360];
  private double[] yValues = new double[360];

  public GraphPanel() {
    JTextField textField = new JTextField(10);
    JButton buttonField = new JButton("✔");
    JComboBox<String> comboBox = new JComboBox<>(
        new String[]{"sin(x)", "sin(x*x) + cos(x*x)", "2*sin(x)+cos(2*x)"});
    JButton buttonR = new JButton("Red");
    JButton buttonB = new JButton("Blue");
    JButton buttonG = new JButton("Green");
    add(textField);
    add(buttonField);
    add(comboBox);
    add(buttonR);
    add(buttonB);
    add(buttonG);
    buttonR.addActionListener(e -> {
      color = Color.RED;
      repaint();
    });
    buttonG.addActionListener(e -> {
      color = Color.GREEN;
      repaint();
    });
    buttonB.addActionListener(e -> {
      color = Color.BLUE;
      repaint();
    });

    comboBox.addActionListener(e -> {
      String selectedItem = (String) comboBox.getSelectedItem();
      switch (Objects.requireNonNull(selectedItem)) {
        case "sin(x)" -> {
          for (int i = 0; i < 360; i++) {
            double x = (i-180) * Math.PI / 180;
            xValues[i] = x;
            yValues[i] = Math.sin(x);
          }
          repaint();
        }
        case "sin(x*x) + cos(x*x)" -> {
          for (int i = 0; i < 360; i++) {
            double x = i * Math.PI / 180;
            xValues[i] = x;
            yValues[i] = Math.sin(x * x) + Math.cos(x * x);
          }
          repaint();
        }
        case "2*sin(x)+cos(2*x)" -> {
          for (int i = 0; i < 360; i++) {
            double x = i * Math.PI / 180;
            xValues[i] = x;
            yValues[i] = 2*Math.sin(x) + Math.cos(2 * x);
          }
          repaint();
        }
      }
    });

    curve = new Curve(xValues, yValues);
    addCurve(curve);

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    Path2D path = new Path2D.Double();
    double scaleX = getWidth() / (4 * Math.PI);
    double scaleY = getHeight() / (4*Math.PI);
    axis = new Axis(getWidth(), getHeight());
    axis.draw(path);
    grid = new Grid();
    grid.draw(path, getWidth(), getHeight());
    g2d.draw(path);
    if (color.equals(Color.RED)) {
      g2d.setColor(Color.RED);
    } else if (color.equals(Color.blue)) {
      g2d.setColor(Color.BLUE);
    } else if (color.equals(Color.GREEN)) {
      g2d.setColor(Color.GREEN);
    } else {
      g2d.setColor(Color.BLACK);
    }

    path.reset();
    for (var c : curves) {
      c.draw(path, scaleX, scaleY, getWidth(), getHeight());
    }
    g2d.draw(path);
  }
}
