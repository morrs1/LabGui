package Programming.FourthSemLab.SixthLab.thirdTask;

import Programming.FourthSemLab.SixthLab.secondTask.Curve;
import Programming.FourthSemLab.SixthLab.secondTask.CurvePanel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Graph extends JFrame {

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
    setTitle("График функции sin(x)");
    setSize(600, 600);
    setLayout(new BorderLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    for(var c : curves){
      add(c, BorderLayout.CENTER);
    }
    add(new Axis());
    setVisible(true);

  }
}
