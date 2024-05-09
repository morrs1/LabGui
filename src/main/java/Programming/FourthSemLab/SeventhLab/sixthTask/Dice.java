package Programming.FourthSemLab.SeventhLab.sixthTask;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Dice extends JPanel {

  private static final long serialVersionUID = 1L;

  private static final int SPOT_DIAMETER = 4;

  private int myFaceValue;
  private Color spotColor = Color.BLACK;
  private Color faceColor = Color.WHITE;

  public void setSpotColor(Color spotColor) {
    this.spotColor = spotColor;
  }

  public void setFaceColor(Color faceColor) {
    this.faceColor = faceColor;
  }

  public Dice() {
    setBackground(faceColor);
    setPreferredSize(new Dimension(15, 15));
    roll();
  }
  public Dice(int w) {
    setBackground(faceColor);
    setPreferredSize(new Dimension(w, w));
    roll();
  }

  public int roll() {
    int val = (int) (6 * Math.random() + 1);
    setValue(val);
    return val;
  }

  public int getValue() {
    return myFaceValue;
  }

  public void setValue(int spots) {
    myFaceValue = spots;
    repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    int w = getWidth();
    int h = getHeight();

    g.drawRect(0, 0, w - 1, h - 1);

    switch (myFaceValue) {
      case 1:
        drawSpot(g, w / 2, h / 2);
        break;
      case 3:
        drawSpot(g, w / 2, h / 2);

      case 2:
        drawSpot(g, w / 4, h / 4);
        drawSpot(g, 3 * w / 4, 3 * h / 4);
        break;
      case 5:
        drawSpot(g, w / 2, h / 2);

      case 4:
        drawSpot(g, w / 4, h / 4);
        drawSpot(g, 3 * w / 4, 3 * h / 4);
        drawSpot(g, 3 * w / 4, h / 4);
        drawSpot(g, w / 4, 3 * h / 4);
        break;
      case 6:
        drawSpot(g, w / 4, h / 4);
        drawSpot(g, 3 * w / 4, 3 * h / 4);
        drawSpot(g, 3 * w / 4, h / 4);
        drawSpot(g, w / 4, 3 * h / 4);
        drawSpot(g, w / 4, h / 2);
        drawSpot(g, 3 * w / 4, h / 2);
        break;
    }
  }

  private void drawSpot(Graphics g, int x, int y) {
    g.setColor(spotColor);
    g.fillOval(x - SPOT_DIAMETER / 2, y - SPOT_DIAMETER / 2,
        SPOT_DIAMETER, SPOT_DIAMETER);
  }

}
