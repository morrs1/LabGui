package Programming.FourthSemLab.SecondLab;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Main {

  public static void main(String[] args) {
    var scanner = new Scanner(System.in);
    Object string = "";
    while (string != " ") {
      System.out.println("Введите номер задания, которое хотите выполнить: ");
      var task = scanner.nextInt();
      string = switch (task) {
        case 1 -> firstTask(" ");
        case 2 -> secondTask(" ");
        case 3 -> thirdTask(" ");
        case 4 -> {
          System.out.println("Введите R, r, x, y: (Через пробел!!!)");
          scanner.nextLine();
          var str = scanner.nextLine();
          yield fourthTask(str);
        }
        case 5 -> {
          System.out.println("Введите число: ");
          var number = scanner.nextInt();
          yield fifthTask(Integer.toString(number));
        }
        case 6 -> sixthTask(" ");

        case 7 -> seventhTask(" ");
        case 9 -> {
          System.out.println("Введите строку, подстроку: (Через пробел!!!)");
          scanner.nextLine();
          var str = scanner.nextLine();
          yield ninth(str);
        }
        case 10 -> {
          System.out.println("Введите строку: ");
          scanner.nextLine();
          var str = scanner.nextLine();
          yield tenthTask(str);
        }
        default -> {
          System.out.println("Неправильно выбрано задание");
          System.exit(0);
          yield " ";
        }

      };

      System.out.println(string);
    }
    scanner.close();
  }

  public static String firstTask(String ignoredUnused) {
    return "Type\t   Size\tMin Value\tMax Value" + "\n"
        + "Byte\t\t" + "  " + Byte.SIZE + "\t\t" + Byte.MIN_VALUE + "\t\t" + "     "
        + Byte.MAX_VALUE
        + "\n"
        + "Short\t\t" + "  " + Short.SIZE + "\t\t" + Short.MIN_VALUE + "\t\t" + Short.MAX_VALUE
        + "\n"
        + "Int\t\t" + "    " + Integer.SIZE + "\t\t" + Integer.MIN_VALUE + "\t\t"
        + Integer.MAX_VALUE
        + "\n"
        + "Long\t\t" + "  " + Long.SIZE + "\t\t" + Long.MIN_VALUE + "\t\t" + Long.MAX_VALUE
        + "\n"
        + "Float\t\t" + "  " + Float.SIZE + "\t\t" + Float.MIN_VALUE + "\t\t" + Float.MAX_VALUE
        + "\n"
        + "Double\t\t" + Double.SIZE + "\t\t" + Double.MIN_VALUE + "\t\t" + Double.MAX_VALUE
        + "\n";
  }

  public static String secondTask(String ignoredUnused) {
    var res = new StringBuilder();
    int[] arr = new int[]{-3, -3, 3, 10};
    double product = 1.0;
    int count = 0;
    for (double number : arr) {
      if (number < 0) {
        product *= Math.abs(number);
        count += 1;
      }
    }
    res.append("Массив: ").append(Arrays.toString(arr)).append("\n");
    res.append("Среднее геометрическое отрицательных элементов:")
        .append(Math.pow(product, 1.0 / count));
    return res.toString();
  }

  public static String thirdTask(String ignoredUnused) {
    var result = new StringBuilder();
    double R = 200.0, r = 20.0;
    int x = 150, y = 150;
    var res = Math.pow((Math.pow(x, 2) + Math.pow(y, 2)), 1.0 / 2.0);
    if (res < r) {
      result.append("Тревога");
    } else if (res > r && res < R) {
      result.append("Обнаружен");
    } else {
      result.append("Не обнаружен");
    }
    return result.toString();
  }

  public static String fourthTask(String args) {
    var result = new StringBuilder();
    var arrNumbers = args.split(" ");
    int R = Integer.parseInt(arrNumbers[0]), r = Integer.parseInt(
        arrNumbers[1]), x = Integer.parseInt(arrNumbers[2]), y = Integer.parseInt(arrNumbers[3]);

    var res = Math.pow((Math.pow(x, 2) + Math.pow(y, 2)), 1.0 / 2.0);
    if (res < r) {
      result.append("Тревога");
    } else if (res > r && res < R) {
      result.append("Обнаружен");
    } else {
      result.append("Не обнаружен");
    }
    return result.toString();
  }

  public static String fifthTask(String args) {
    int number = Integer.parseInt(args);

    return "Число: " + number + " Двоичная: " + Integer.toString(number, 2)
        + " Восьмеричная: " + Integer.toString(number, 8)
        + " Шестнадцатеричная: " + Integer.toString(number, 16);
  }

  public static String sixthTask(String ignoresUnused) {
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
//    var str1 = "0x040" + "16" + "16";
    var res = matrix(0x0400, 16, 16).append("\n").append(matrix(0x20a0, 2, 16));
    return res.toString();
  }


  private static StringBuilder matrix(int start, int rows, int cols) {
    var res = new StringBuilder();
    System.out.println("  " + IntStream.range(0, cols)
        .mapToObj(x -> Integer.toString(x, 16))
        .collect(Collectors.joining("  "))
    );
    for (int i = 0; i < rows; i++) {
      var row = new StringBuilder(String.format("%4X ", start + i * cols));
      for (int j = 0; j < cols; j++) {
        row.append((char) (start + i * cols + j)).append("  ");
      }
      res.append(row).append("\n");

    }
    return res;
  }

  public static String seventhTask(String ignoredUnused) {
    String str = "Hello W3or56ld";

    List<Character> arrChars = new ArrayList<>();
    IntStream.range(0, str.length()).forEach(x -> arrChars.add(str.charAt(x)));

    var streamLetter = arrChars.parallelStream().filter(Character::isLetter).count();
    var streamUpperCase = arrChars.parallelStream().filter(Character::isUpperCase).count();
    var streamLowerCase = arrChars.parallelStream().filter(Character::isLowerCase).count();

    var streamDigit = arrChars.parallelStream().filter(Character::isDigit).count();
    var streamArabicDigit = arrChars.parallelStream().filter(x -> x >= '0' && x <= '9').count();

    return String.format(
        """
            Общее кол-во символов: %d\s
            Кол-во букв: %d\s
            Кол-во прописных букв: %d\s
            Кол-во заглавных букв: %d\s
            Кол-во цифр: %d\s
            Кол-во арабских цифр: %d\s
            Кол-во не арабских цифр: %d\s
            Кол-во других символов: %d\s
            """
        , arrChars.size(), streamLetter, streamLowerCase, streamUpperCase,
        streamDigit, streamArabicDigit,
        streamDigit - streamArabicDigit,
        arrChars.size() - streamLetter - streamDigit
    );
  }

  public static String ninth(String args) {
    var res = args.split(" ");
    var str = new StringBuilder(res[0]);
    var subStr = res[1];

    var c = 0;
    while (str.indexOf(subStr) >= 0) {
      str.delete(str.indexOf(subStr), str.indexOf(subStr) + subStr.length());
      c += 1;
    }
    return String.format("Данная подстрока встречается %d раз \n", c);

  }

  public static String tenthTask(String args) {
    var res = new StringBuilder();
    Queue<String> queue = new LinkedList<>();
    args.chars().forEach(x -> queue.add(String.valueOf((char) x)));

    for (var i = 0; i < args.length(); i++) {
      var buf = queue.remove();
      queue.add(buf);
      res.append(String.join("", queue)).append("\n");
    }
    return res.toString();

  }
}