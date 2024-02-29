package Programming.FourthSemLab.ThirdLab;

import java.util.ArrayList;
import java.util.Arrays;


public class Circles {

  static int defineCircles(String args) {
    var arr = new ArrayList<Double>();
    Arrays.stream(args.split(" ")).forEach(x -> arr.add(Double.parseDouble(x)));

    var distance = Math.sqrt(
        Math.pow(arr.get(0) - arr.get(3), 2) + Math.pow(arr.get(1) - arr.get(4), 2));
    System.out.println(distance);
    if (distance == 0 && (arr.get(2).equals(arr.get(5)))) {
      return 1;
    }
    if (distance == arr.get(2) + arr.get(5)) {
      return 2;
    }
    if (distance < arr.get(2) + arr.get(5) && (distance
        > Math.max(arr.get(2), arr.get(5)) - Math.min(arr.get(2), arr.get(5)))) {
      return 3;
    }
    if (distance + arr.get(2) <= arr.get(5)) {
      return 4;
    }
    if (distance + arr.get(5) <= arr.get(2)) {
      return 5;
    }
    return 6;

  }
}
