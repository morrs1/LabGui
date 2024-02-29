package Programming.FourthSemLab.ThirdLab;

import java.util.ArrayList;
import java.util.Arrays;


public class Circles {

  enum ResultType {
    TOUCHING,  // касаются
    INTERSECTING_AT_TWO_POINTS,  // пересекаются в двух точках
    COINCIDENT,  // совпадают
    NON_INTERSECTING,  // не пересекаются
    FIRST_CIRCLE_EMBEDDED,  // первая окружность вложена во вторую
    SECOND_CIRCLE_EMBEDDED  // вторая окружность вложена в первую
  }

  static ResultType defineCircles(String args) {
    var arr = new ArrayList<Double>();
    Arrays.stream(args.split(" ")).forEach(x -> arr.add(Double.parseDouble(x)));

    var distance = Math.sqrt(
        Math.pow(arr.get(0) - arr.get(3), 2) + Math.pow(arr.get(1) - arr.get(4), 2));
    if (distance == 0 && (arr.get(2).equals(arr.get(5)))) {
      return ResultType.COINCIDENT;
    }
    if (distance == arr.get(2) + arr.get(5)) {
      return ResultType.TOUCHING;
    }
    if (distance < arr.get(2) + arr.get(5) && (distance
        > Math.max(arr.get(2), arr.get(5)) - Math.min(arr.get(2), arr.get(5)))) {
      return ResultType.INTERSECTING_AT_TWO_POINTS;
    }
    if (distance + arr.get(2) <= arr.get(5)) {
      return ResultType.FIRST_CIRCLE_EMBEDDED;
    }
    if (distance + arr.get(5) <= arr.get(2)) {
      return ResultType.SECOND_CIRCLE_EMBEDDED;
    }
    return ResultType.NON_INTERSECTING;

  }
}
