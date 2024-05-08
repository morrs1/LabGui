package Programming.FourthSemLab.SixthLab.thirdTask;

import Programming.FourthSemLab.SixthLab.secondTask.Curve;
import Programming.FourthSemLab.SixthLab.secondTask.CurvePanel;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import javax.swing.*;

import lombok.Setter;

public class Graph extends JPanel {

  protected final ArrayList<Curve> curves;
  protected Axis axis;
  protected Grid grid;

  public Graph() {
    this.curves = new ArrayList<Curve>();
    this.grid = new Grid();

  }

  public void addCurve(Curve curve) {
    this.curves.add(curve);
  }

  @Override
  protected void paintComponent(Graphics g) {

    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    Path2D path = new Path2D.Double();
    double scaleX = getWidth() / (4 * Math.PI);
    double scaleY = getHeight() / 4;
    axis = new Axis(getWidth(), getHeight());

    axis.draw(path);
    grid = new Grid();
    grid.draw(path, getWidth(), getHeight());
    for (var c : curves) {
      c.draw(path, scaleX, scaleY, getWidth(), getHeight());
    }
    g2d.draw(path);
  }


}

