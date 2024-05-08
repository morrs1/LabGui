package Programming.FourthSemLab.SixthLab.thirdTask;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import javax.swing.JPanel;
import lombok.Setter;
@Setter
public class Axis  {
  private double xLen;
  private double yLen;
  private String label;

  public Axis(int xLen, int yLen) {
    this.xLen = xLen;
    this.yLen = yLen;
    this.label = "Axis";
  }


  public void draw(Path2D path) {
    path.moveTo(0, yLen / 2);
    path.lineTo(xLen, yLen / 2);

    path.moveTo(xLen/2, 0);
    path.lineTo(xLen/2, yLen);

  }
}