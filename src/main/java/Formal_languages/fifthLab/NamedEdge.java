package Formal_languages.fifthLab;

public record NamedEdge(String name) {

  @Override
  public String toString() {
    return name;
  }
}
