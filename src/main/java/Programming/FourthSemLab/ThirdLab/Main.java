package Programming.FourthSemLab.ThirdLab;

import java.util.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Main {

  private final static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    Object string = "";
    while (string != " ") {
      System.out.println("Введите задание: ");
      var task = scanner.nextInt();
      string = switch (task) {
        case 1 -> firstTask(" ");
        case 2 -> secondTask(" ");
        case 3 -> thirdTask(" ");
        case 4 -> {
          System.out.println(
              "Введите координаты центра первой окружности и ее радиус, и координаты центра и радиус второй окружности (Все через пробел!! x1 y1 r1 x2 y2 r2 )");
          scanner.nextLine();
          var str = scanner.nextLine();
          yield fourthTask(str);
        }
        default -> {
          System.out.println("Вы ввели неправильное задание");
          yield " ";
        }
      };

      System.out.println(string);
    }

  }

  public static String firstTask(String ignoredUnused) {
    var listX = makeListX();
//    System.out.println(makeTable(listX, makeListSin(listX), makeListE(listX)));
    return makeTable(listX, makeListSin(listX), makeListE(listX));
  }

  public static String secondTask(String ignoredUnused) {
    List<ArrayList<Integer>> arr = makeRandomArray();
    var result = new StringBuilder();
    arr.forEach(x -> {
      x.forEach(xx -> result.append(xx.toString()).append(" "));
      result.append("\n");
    });
    var maxNegative = maxNegative(arr);
    return "Массив:\n" + result + "Максимальный отрицательный элемент: " + maxNegative;
  }

  public static String thirdTask(String ignoredUnused) {
    var matrix = makeRandomMatrix();
    var result = new StringBuilder();
    result.append("Изначальный массив:\n");
    matrix.forEach(x -> result.append(x.toString()).append("\n"));
    result.append("Отсортированная матрица:\n");
    matrix.forEach(x -> x.sort(Comparator.naturalOrder()));
    matrix.forEach(x -> result.append(x.toString()).append("\n"));
    return result.toString();
  }

  public static String fourthTask(String args) {
    var res = Circles.defineCircles(args);
    return switch (res){
      case COINCIDENT -> "Совпадают";
      case TOUCHING -> "Касаются";
      case INTERSECTING_AT_TWO_POINTS -> "Пересекаются в двух точках";
      case FIRST_CIRCLE_EMBEDDED -> "Первая окружность вложена во вторую";
      case SECOND_CIRCLE_EMBEDDED -> "Вторая окружность вложена в первую";
      case NON_INTERSECTING -> "Не пересекаются";
    };
  }

  private static String makeTable(ArrayList<String> listX, ArrayList<String> listSin,
      ArrayList<String> listE) {
    List<List<String>> columns = new ArrayList<>();
    listX.add(0, "x");
    listX.add(1, "-".repeat(15));
    listSin.add(0, "sin(x)");
    listSin.add(1, "-".repeat(15));
    listE.add(0, "(e^x)/(x*lg(x))");
    listE.add(1, "-".repeat(15));
    columns.add(listX);
    columns.add(listSin);
    columns.add(listE);

    StringBuilder table = new StringBuilder();
    int row_count = columns.get(0).size();

    for (int i = 0; i < row_count; i++) {
      for (List<String> column : columns) {
        table.append(String.format("| %-" + 15 + "s ", column.get(i)));
      }
      table.append("|\n");
    }

    return table.toString();
  }


  private static ArrayList<String> makeListX() {
    var start = Math.PI / 15;
    var end = 16 * Math.PI / 15;
    var step = Math.PI / 15;
    var listX = new ArrayList<String>();
    DoubleStream.iterate(start, value -> value + step)
        .limit((int) ((end - start) / step)).mapToObj(x -> String.format("%.5f", x))
        .forEach(listX::add);

    return listX;
  }

  private static ArrayList<String> makeListSin(ArrayList<String> listX) {
    var listSin = new ArrayList<String>();
    listX.forEach(x -> listSin.add(String.format("%.7e", Math.sin(Double.parseDouble(x)))));

    return listSin;
  }

  private static ArrayList<String> makeListE(ArrayList<String> listX) {
    var listE = new ArrayList<String>();
    listX.forEach(x -> listE.add(String.format("%.7e",
        Math.pow(Math.E, Double.parseDouble(x)) / (Double.parseDouble(x) * Math.log10(
            Double.parseDouble(x))))));

    return listE;
  }


  private static List<ArrayList<Integer>> makeRandomArray() {
    List<ArrayList<Integer>> arr1 = new ArrayList<>();
    var r = new Random();
    IntStream.range(1, r.nextInt(2, 10)).forEach(x -> {
      var arr2 = new ArrayList<Integer>();
      IntStream.range(0, r.nextInt(2, 10)).forEach(y -> arr2.add(r.nextInt(-100, 100)));
      arr1.add(arr2);
    });

    return arr1;
  }

  private static Integer maxNegative(List<ArrayList<Integer>> arr) {
    return arr.stream().map(x -> x
            .parallelStream().filter(xx -> xx < 0)
            .max(Comparator.naturalOrder())
            .orElse(Integer.MIN_VALUE))
        .max(Comparator.naturalOrder()).orElseThrow();
  }

  private static List<ArrayList<Integer>> makeRandomMatrix() {
    List<ArrayList<Integer>> matrix = new ArrayList<>();
    IntStream.range(0, 3).forEach(x -> {
      var arr = new ArrayList<Integer>();
      IntStream.range(0, 3).forEach(xx -> arr.add(new Random().nextInt(-10, 10)));
      matrix.add(arr);
    });

    return matrix;
  }

}
