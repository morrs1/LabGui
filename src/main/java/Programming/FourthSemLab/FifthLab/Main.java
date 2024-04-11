package Programming.FourthSemLab.FifthLab;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {

  public static void main(String[] args) {

  }

  public String firstTask(String ignoredUnused){
    var res = new StringBuilder();
    Pair<String, Integer> pair = Pair.makePair("foo", 42);
    res.append(pair.getFirst()).append(" ");
    res.append(pair.getSecond()).append("\n");

    String[] strings = {"hello", "world"};
    Pair<String, String[]> pair2 = Pair.makePair("foo", strings);
    res.append(pair2.getFirst()).append(" ");
    res.append(Arrays.toString(pair2.getSecond()));
    return res.toString();

  }

  public String secondTask(String args){
    var res = new StringBuilder();
    Bag bag = new Bag(10);
    IntStream.range(0,11).forEach(x-> bag.add(String.format("item%d", x)));
    res.append(bag.getCurrentSize()).append("\n");
    res.append(bag.remove()).append("\n");
    res.append(bag.remove()).append("\n");
    res.append(bag.getCurrentSize()).append("\n");
    bag.add("item11");
    res.append(bag.getCurrentSize()).append("\n");
    res.append(bag.remove()).append("\n");
    return res.toString();
  }
}
