package Programming.FourthSemLab.SixthLab.thirdTask;

import Programming.FourthSemLab.SixthLab.secondTask.Curve;
import java.util.ArrayList;

public class Graph {
  private final ArrayList<Curve> curves;
  Axis xAxis;
  Axis yAxis;
  Grid grid;

  public Graph() {
    this.curves = new ArrayList<Curve>();
    this.xAxis = new Axis();
    this.yAxis = new Axis();
    this.grid = new Grid();

  }

  public void addCurve(Curve curve) {
    this.curves.add(curve);
  }

  public void draw() {

  }
}
