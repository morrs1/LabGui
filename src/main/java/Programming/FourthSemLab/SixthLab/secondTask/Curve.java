package Programming.FourthSemLab.SixthLab.secondTask;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import javax.swing.JPanel;

public class Curve extends JPanel {
  private double[] xValues;
  private double[] yValues;

  public Curve(double[] xValues, double[] yValues) {
    this.xValues = xValues;
    this.yValues = yValues;
  }

  public void setData(double[] xValues, double[] yValues) {
    this.xValues = xValues;
    this.yValues = yValues;
  }

  public double[] getXValues() {
    return xValues;
  }

  public double[] getYValues() {
    return yValues;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    Path2D path = new Path2D.Double();

    double scaleX = getWidth() / (4 * Math.PI);
    double scaleY = getHeight() / 4;

    path.moveTo(getXValues()[0] * scaleX + (double) getWidth() / 2,
        -getYValues()[0] * scaleY + (double) getHeight() / 2);
    for (int i = 0; i < getXValues().length; i++) {
      double x = getXValues()[i];
      double y = getYValues()[i];
      int drawX = (int) (x * scaleX + getWidth() / 2);
      int drawY = (int) (-y * scaleY + getHeight() / 2);
//      System.out.println(drawX + " " + drawY);
      path.lineTo(drawX, drawY);
    }

    g2d.draw(path);
  }
}
