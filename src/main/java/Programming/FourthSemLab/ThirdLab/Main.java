package Programming.FourthSemLab.ThirdLab;

import java.util.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Main {

  public static void main(String[] args) {
    firstTask("");
  }

  public static String firstTask(String ignoredUnused) {
    var listX = makeListX();
//    System.out.println(makeTable(listX, makeListSin(listX), makeListE(listX)));
    return makeTable(listX, makeListSin(listX), makeListE(listX));
  }

  public static String secondTask(String ignoredUnused){
     List <ArrayList<Double>> arr1 = new ArrayList<>();
     var r = (int) (Math.random() * 10);
    IntStream.range(0,r).forEach(x-> arr1.add(new ArrayList<>()));
    System.out.println(arr1);
    return "";
  }

  private static String makeTable(ArrayList<String> listX, ArrayList<String> listSin, ArrayList<String> listE) {
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

}
