package Programming.FourthSemLab.FifthLab;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Pair<T extends Object, K extends Object> {

  private T first;
  private K second;

  static <T, K> Pair<T, K> makePair(T firstElem, K secondElem) {
    return new Pair<>(firstElem, secondElem);
  }

  @Override
  public String toString() {
    return
             first + "->" + second;
  }
}
