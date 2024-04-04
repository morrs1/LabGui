package expOperation.firstLab;

public class Cell {

  private int i;
  private int j;

  public Cell(int i, int j) {
    this.i = i;
    this.j = j;
  }

  public int getI() {
    return i;
  }

  public int getJ() {
    return j;
  }

  public void setI(int i) {
    this.i = i;
  }

  public void setJ(int j) {
    this.j = j;
  }

  @Override
  public String toString() {
    return "Cell{" +
        "i=" + i +
        ", j=" + j +
        '}';
  }
}
