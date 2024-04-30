package Programming.FourthSemLab.SixthLab.secondTask;

public class Curve {
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
}
