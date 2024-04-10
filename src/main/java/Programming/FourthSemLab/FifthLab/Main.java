package Programming.FourthSemLab.FifthLab;

import java.util.Arrays;

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

  public void secondTask(String args){

  }
}
