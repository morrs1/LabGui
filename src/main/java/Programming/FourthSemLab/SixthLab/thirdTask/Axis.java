package Programming.FourthSemLab.SixthLab.thirdTask;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import javax.swing.JPanel;
import lombok.Setter;
@Setter
public class Axis extends JPanel {
  private double minValue;
  private double maxValue;
  private String label;

  public Axis() {
    this.minValue = 0.0;
    this.maxValue = 1.0;
    this.label = "Axis";
  }

  public void setRange(double minValue, double maxValue) {
    this.minValue = minValue;
    this.maxValue = maxValue;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    Path2D path = new Path2D.Double();

    double scaleX = getWidth() / (4 * Math.PI);
    double scaleY = getHeight() / 4;

    path.moveTo(0, getHeight() / 2);
    path.lineTo(getWidth(), getHeight() / 2);

    path.moveTo(getWidth()/2, 0);
    path.lineTo(getWidth()/2, getHeight());

    g2d.draw(path);
  }
}