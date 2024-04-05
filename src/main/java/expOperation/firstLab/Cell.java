package expOperation.firstLab;

public class Cell {

//  private int i;
//  private int j;
//
//  public Cell(int i, int j) {
//    this.i = i;
//    this.j = j;
//  }
//
//  public int getI() {
//    return i;
//  }
//
//  public int getJ() {
//    return j;
//  }
//
//  public void setI(int i) {
//    this.i = i;
//  }
//
//  public void setJ(int j) {
//    this.j = j;
//  }
//
//  @Override
//  public String toString() {
//    return "Cell{" +
//        "i=" + i +
//        ", j=" + j +
//        '}';
//  }

  private final int cost;
  private final int x;
  private final int y;
  private int traffic;
  private boolean hasTraffic;
  private int delta;
  public Cell(int x, int y, int cost) {
    this.x = x;
    this.y = y;
    this.cost = cost;
  }
  public void setTraffic(int traffic) {
    this.traffic = traffic;
    this.hasTraffic = true;
  }
  public void addLambda(int lambda) {
    this.traffic += lambda;
    this.hasTraffic = true;
  }
  public boolean isHasTraffic() {
    return hasTraffic;
  }
  public int getCost() {
    return cost;
  }
  public int getTraffic() {
    return traffic;
  }
  public void removeTraffic() {
    this.traffic = 0;
    this.hasTraffic = false;
  }
  public int getX() {
    return x;
  }
  public int getY() {
    return y;
  }
  public void setDelta(int delta) {
    this.delta = delta;
  }
  public int getDelta() {
    return delta;
  }

  @Override
  public String toString() {
    return  traffic + " ";// + hasTraffic + " " + cost;

  }
}
