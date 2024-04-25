package Programming.FourthSemLab.FifthLab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

  public static void main(String[] args) {
    seventhTask("");
  }

  public String firstTask(String ignoredUnused) {
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

  public String secondTask(String args) {
    var res = new StringBuilder();
    Bag bag = new Bag(10);
    IntStream.range(0, 11).forEach(x -> bag.add(String.format("item%d", x)));
    res.append(bag.getCurrentSize()).append("\n");
    res.append(bag.remove()).append("\n");
    res.append(bag.remove()).append("\n");
    res.append(bag.getCurrentSize()).append("\n");
    bag.add("item11");
    res.append(bag.getCurrentSize()).append("\n");
    res.append(bag.remove()).append("\n");
    return res.toString();
  }

  public String thirdTask(String args) {
    StringBuilder res = new StringBuilder();
    PairBag pairBag = new PairBag(10);
    pairBag.putPair(new Pair<>(10, "sdsdsdsa"));
    Pair<Object, Object> some = pairBag.getPair();

    res.append(some.getSecond()).append("\n");
    res.append(some.getFirst());
    return res.toString();
  }

  public String fourthTask(String args) {
    StringBuilder res = new StringBuilder();
    GPairBag<String> bag = new GPairBag<>(10);

    bag.putPair(new Pair<>("s1", "s2"));

    Pair<String, String> some = bag.getPair();

    res.append(some.getFirst()).append("\n");
    res.append(some.getSecond());
    return res.toString();
  }

  public String fifthTask(String args) {
    StringBuilder res = new StringBuilder();
    GenericPairBag<String> bag = new GenericPairBag<>();

    Pair<String, String> pair1 = new Pair<>("1", "2");
    Pair<String, String> pair2 = new Pair<>("3", "4");

    bag.putPair(pair1);
    bag.putPair(pair2);

    Pair<String, String> pair = bag.getPair();
    res.append(pair.getFirst()).append(" ").append(pair.getSecond());
    return res.toString();
  }

  public static String sixthTask(String args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Введите количество команд: ");
    int numTeams = scanner.nextInt();

    if (numTeams <= 1 || numTeams % 2 != 0) {
      System.out.println("Некорректное количество команд. Используется значение по умолчанию (8).");
      numTeams = 8;
    }

    Tournament tournament = new Tournament(numTeams);
    System.out.println("Победитель турнира: " + tournament.playTournament());
    return null;
  }

  public static String seventhTask(String ignoredUnused) {
    StringBuilder res = new StringBuilder();
    DList<Integer, String> dList = new DList<>();
    ArrayList<String> arr = new ArrayList<>();
    ArrayList<String> arr1 = new ArrayList<>();
    ArrayList<String> arr2 = new ArrayList<>();
    IntStream.range(0, 10).forEach(x -> arr.add(String.valueOf(x)));
    dList.add(10, arr);
    IntStream.range(10, 21).forEach(x -> arr1.add(String.valueOf(x)));
    dList.add(6, arr1);
    IntStream.range(5, 11).forEach(x -> arr2.add(String.valueOf(x)));
    res.append(dList.get(0)).append("\n");
    res.append(dList.getByElement(6)).append("\n");
    dList.remove(1);
    dList.add(11, arr2);
    res.append(dList.get(1)).append("\n");
    return res.toString();
  }


}
