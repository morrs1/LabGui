package Programming.FourthSemLab.SixthLab.thirdTask;

import javax.swing.JPanel;

public class Grid extends JPanel {
  private int xSpacing;
  private int ySpacing;

  public Grid() {
    this.xSpacing = 50;
    this.ySpacing = 50;
  }

  public void setSpacing(int xSpacing, int ySpacing) {
    this.xSpacing = xSpacing;
    this.ySpacing = ySpacing;
  }

  public void draw() {

  }
}
