package expOperation.firstLab;

public record HeadNode(String name, Integer amount) {

  @Override
  public String toString() {
    return
        name+
           " " + amount;
  }
}
