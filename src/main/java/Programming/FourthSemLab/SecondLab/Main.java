package Programming.FourthSemLab.SecondLab;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Main {

  public static void main(String[] args) {
    var scanner = new Scanner(System.in);

    while (true) {
      System.out.println("Введите номер задания, которое хотите выполнить: ");
      var task = scanner.nextInt();
      switch (task) {
        case 1 -> firstTask();
        case 2 -> secondTask();
        case 3 -> thirdTask();
        case 4 -> fourthTask();
        case 5 -> fifthTask(args);
        case 6 -> {
          sixthTask(0x0400, 16, 16);
          sixthTask(0x20a0, 2, 16);
        }
        case 7 -> seventhTask();
        case 8 -> {
        }
        case 9 -> {
          ninth();
        }
        case 10 -> {
          tenthTask();
        }
        default -> {
          return;
        }
      }
    }
  }

  public static void firstTask() {
    System.out.println("Type\t   Size\tMin Value\tMax Value");
    System.out.println(
        "Byte\t\t" + "  " + Byte.SIZE + "\t\t" + Byte.MIN_VALUE + "\t\t" + "     "
            + Byte.MAX_VALUE);
    System.out.println(
        "Short\t\t" + "  " + Short.SIZE + "\t\t" + Short.MIN_VALUE + "\t\t" + Short.MAX_VALUE);
    System.out.println(
        "Int\t\t" + "    " + Integer.SIZE + "\t\t" + Integer.MIN_VALUE + "\t\t"
            + Integer.MAX_VALUE);
    System.out.println(
        "Long\t\t" + "  " + Long.SIZE + "\t\t" + Long.MIN_VALUE + "\t\t" + Long.MAX_VALUE);
    System.out.println(
        "Float\t\t" + "  " + Float.SIZE + "\t\t" + Float.MIN_VALUE + "\t\t" + Float.MAX_VALUE);
    System.out.println(
        "Double\t\t" + Double.SIZE + "\t\t" + Double.MIN_VALUE + "\t\t" + Double.MAX_VALUE);

  }

  public static void secondTask() {
    int[] arr = new int[]{-3, -3, 3, 10};
    double product = 1.0;
    int count = 0;
    for (double number : arr) {
      if (number < 0) {
        product *= Math.abs(number);
        count += 1;
      }
    }
    System.out.println("Массив: " + Arrays.toString(arr));
    System.out.println(
        "Среднее геометрическое отрицательных элементов:" + Math.pow(product, 1.0 / count));
  }

  public static void thirdTask() {

    double R = 200.0, r = 20.0;
    int x = 150, y = 150;
    var res = Math.pow((Math.pow(x, 2) + Math.pow(y, 2)), 1.0 / 2.0);
    if (res < r) {
      System.out.println("Тревога");
    } else if (res > r && res < R) {
      System.out.println("Обнаружен");
    } else {
      System.out.println("Не обнаружен");
    }


  }

  public static void fourthTask() {
    var scanner = new Scanner(System.in);
    double R, r;
    int x, y;
    System.out.println("Введите R: ");
    R = scanner.nextDouble();
    System.out.println("Введите r: ");
    r = scanner.nextDouble();
    System.out.println("Введите x: ");
    x = scanner.nextInt();
    System.out.println("Введите y: ");
    y = scanner.nextInt();
    var res = Math.pow((Math.pow(x, 2) + Math.pow(y, 2)), 1.0 / 2.0);
    if (res <= r) {
      System.out.println("Тревога");
    } else if (res > r && res <= R) {
      System.out.println("Обнаружен");
    } else {
      System.out.println("Не обнаружен");
    }
  }

  public static void fifthTask(String[] args) {
//    System.out.println(args[0]);
//    var scanner = new Scanner(System.in);
//    System.out.println("Введите число: ");
//    var number = scanner.nextInt();
    System.out.println(args[0]);
    var number = Integer.parseInt(args[0]);
    System.out.println(
        "Число: " + number +
            " Двоичная: " + Integer.toString(number, 2) +
            " Восьмеричная: " + Integer.toString(number, 8) +
            " Шестнадцатеричная: " + Integer.toString(number, 16));
  }

  public static void sixthTask(int start, int rows, int cols) {
//    var rowKeys = IntStream.range(0, rows).mapToObj(x -> Integer.toString(x, 16)).toList();
//    var colKeys = IntStream.range(0, cols).mapToObj(x -> String.format("%4X ", start + x * cols)).toList();
//    Table<String, String, Object> at = ArrayTable.create(rowKeys, colKeys);
//
//    for (int i = 0; i < rows; i++) {
//      for (int j = 0; j < cols; j++) {
//        at.put(rowKeys.get(i), colKeys.get(j), (char) (start + i * cols + j));
//      }
//    }
//
//    for (Cell<String, String, Object> cell : at.cellSet()) {
//      System.out.printf("(%s, %s) => %s%n", cell.getRowKey(), cell.getColumnKey(), cell.getValue());
//    }
    System.out.println(
        "     " + IntStream.range(0, cols)
            .mapToObj(x -> Integer.toString(x, 16))
            .collect(Collectors.joining("  "))
    );
    for (int i = 0; i < rows; i++) {
      var row = new StringBuilder(String.format("%4X ", start + i * cols));
      for (int j = 0; j < cols; j++) {
        row.append((char) (start + i * cols + j)).append("  ");
      }
      System.out.println(row);

    }
  }

  public static void seventhTask() {
    String str = "Hello W3or56ld";

    List<Character> arrChars = new ArrayList<>();
    IntStream.range(0, str.length()).forEach(x -> arrChars.add(str.charAt(x)));

    var streamLetter = arrChars.parallelStream().filter(Character::isLetter).count();
    var streamUpperCase = arrChars.parallelStream().filter(Character::isUpperCase).count();
    var streamLowerCase = arrChars.parallelStream().filter(Character::isLowerCase).count();

    var streamDigit = arrChars.parallelStream().filter(Character::isDigit).count();
    var streamArabicDigit = arrChars.parallelStream().filter(x -> x >= '0' && x <= '9').count();

    System.out.printf(
        "Общее кол-во символов: %d \n"
            + "Кол-во букв: %d \n"
            + "Кол-во прописных букв: %d \n"
            + "Кол-во заглавных букв: %d \n"
            + "Кол-во цифр: %d \n"
            + "Кол-во арабских цифр: %d \n"
            + "Кол-во не арабских цифр: %d \n"
            + "Кол-во других символов: %d \n"
        , arrChars.size(), streamLetter, streamLowerCase, streamUpperCase,
        streamDigit, streamArabicDigit,
        streamDigit - streamArabicDigit,
        arrChars.size() - streamLetter - streamDigit
    );

  }

  public static void ninth() {

    var scanner = new Scanner(System.in);
    System.out.println("Введите строку: ");
    var str = new StringBuilder(scanner.nextLine());
    System.out.println("Введите подстроку: ");
    var subStr = scanner.nextLine();

    var c = 0;
    while (str.indexOf(subStr) >= 0) {
      str.delete(str.indexOf(subStr), str.indexOf(subStr) + subStr.length());
      c += 1;
    }
    System.out.printf("Данная подстрока встречается %d раз \n", c);

  }

  public static void tenthTask() {
    var scanner = new Scanner(System.in);
    System.out.println("Введите строку: ");
    var str = scanner.nextLine();

    Queue<String> queue = new LinkedList<>();
    str.chars().forEach(x -> queue.add(String.valueOf((char) x)));

    for (var i = 0; i < str.length(); i++) {
      var buf = queue.remove();
      queue.add(buf);
      System.out.println(String.join("", queue));
    }

  }
}