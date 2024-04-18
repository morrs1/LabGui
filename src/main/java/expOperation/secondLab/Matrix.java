package expOperation.secondLab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Matrix {

  static StringBuilder res = new StringBuilder();
  static int countOperations = 0;

  public static void printMatrix(int[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        System.out.printf("%10d ", matrix[i][j]);
      }
      System.out.println("\n\n");
    }
  }

  public static int[][] matrixMultiply(int[][] A, int[][] B) {
    int rowsA = A.length;
    int columnsA = A[0].length;
    int rowsB = B.length;
    int columnsB = B[0].length;

    if (columnsA != rowsB) {
      throw new IllegalArgumentException("Mismatch in matrix dimensions");
    }

    int[][] C = new int[rowsA][columnsB];

    for (int i = 0; i < rowsA; i++) {
      for (int j = 0; j < columnsB; j++) {
        for (int k = 0; k < columnsA; k++) {
          C[i][j] += A[i][k] * B[k][j];
          countOperations += 1;
        }
      }
    }

    return C;
  }

  public static void matrixChainOrder(int[] p, int[][] m, int[][] s) {
    int n = p.length - 1;

    // Initialize matrix m
    for (int i = 1; i <= n; i++) {
      m[i][i] = 0;
    }

    // Compute m[i, j] for all i, j
    for (int l = 2; l <= n; l++) {
      for (int i = 1; i <= n - l + 1; i++) {
        int j = i + l - 1;
        m[i][j] = Integer.MAX_VALUE;
        for (int k = i; k <= j - 1; k++) {
          int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
          if (q < m[i][j]) {
            m[i][j] = q;
            s[i][j] = k;
          }
        }
      }
    }
  }

  public static void printOptimalParens(int[][] s, int i, int j) {
    if (i == j) {
      res.append("A").append(i);
    } else {
      res.append("(");
      printOptimalParens(s, i, s[i][j]);
      printOptimalParens(s, s[i][j] + 1, j);
      res.append(")");
    }
  }

  public static ArrayList<String> getOrderMultiply(int[][][] arrA) {
    String inputString = res.toString();

    // Регулярное выражение для поиска матриц
    String patternString = "\\b([A-Za-z0-9]+)\\b";

    // Создание объекта Pattern
    Pattern pattern = Pattern.compile(patternString);

    // Создание объекта Matcher для исходной строки
    Matcher matcher = pattern.matcher(inputString);

    // Список для хранения найденных матриц
    ArrayList<String> foundMatrices = new ArrayList<>();

    var c = arrA.length + 1;
    while (res.length() > 3) {
      matcher = pattern.matcher(res);
      while (matcher.find()) {
        // Извлечение текущего совпадения и добавление его в список
        if (matcher.group().length() > 3) {
          foundMatrices.add(matcher.group());
        }
      }
      for (var l : foundMatrices) {
        if (res.indexOf("(" + l + ")") != -1) {
          res = new StringBuilder(res.toString().replace("(" + l + ")", "A" + c));
          c += 1;
        }

      }

//      System.out.println(foundMatrices);
//      System.out.println(res.toString());
    }
//        // Цикл по всем совпадениям
//        while (matcher.find()) {
//            // Извлечение текущего совпадения и добавление его в список
//            if (matcher.group().length() > 3) {
//                foundMatrices.add(matcher.group());
//            }
//        }
//        var c = 7;
//        for (var l : foundMatrices) {
//            if(res.indexOf("(" + l + ")") != -1){
//                res = new StringBuilder(res.toString().replace("(" + l + ")", "A"+c));
//                c+=1;
//            }
//
//        }
//
//        System.out.println(foundMatrices);
//        System.out.println(res.toString());
//
//
//        matcher = pattern.matcher(res);
//        while (matcher.find()) {
//            // Извлечение текущего совпадения и добавление его в список
//            if (matcher.group().length() > 3) {
//                foundMatrices.add(matcher.group());
//            }
//        }
//        for (var l : foundMatrices) {
//            if(res.indexOf("(" + l + ")") != -1){
//                res = new StringBuilder(res.toString().replace("(" + l + ")", "A"+c));
//                c+=1;
//            }
//
//        }
//
//        System.out.println(foundMatrices);
//        System.out.println(res.toString());
//
//////////////////
//        matcher = pattern.matcher(res);
//        while (matcher.find()) {
//            // Извлечение текущего совпадения и добавление его в список
//            if (matcher.group().length() > 3) {
//                foundMatrices.add(matcher.group());
//            }
//        }
//        for (var l : foundMatrices) {
//            if(res.indexOf("(" + l + ")") != -1){
//                res = new StringBuilder(res.toString().replace("(" + l + ")", "A"+c));
//                c+=1;
//            }
//
//        }
//
//        System.out.println(foundMatrices);
//        System.out.println(res.toString());
    return foundMatrices;
  }

  public static void multiplyOrderedMatrix(int[][][] arrA, ArrayList<String> order) {
    var arrAA = new ArrayList<>(Arrays.asList(arrA));
    for (var o : order) {
      if(o.length() == 5){
//        System.out.println(o);
//        System.out.println(
//            Arrays.deepToString(arrAA.get(Integer.parseInt(String.valueOf(o.charAt(1))))));
//        System.out.println(Arrays.deepToString(arrAA.get(Integer.parseInt(o.substring(3, 5)))));
        arrAA.add(matrixMultiply(arrAA.get(Integer.parseInt(String.valueOf(o.charAt(1))) - 1), arrAA.get(Integer.parseInt(o.substring(3,5)) - 1)));  //+ o.substring(3,5));
      }
      if(o.length() == 6){
//        System.out.println(o);
//        System.out.println(Arrays.deepToString(arrAA.get(Integer.parseInt(o.substring(1, 3)))));
//        System.out.println(Arrays.deepToString(arrAA.get(Integer.parseInt(o.substring(4, 6)))));
        arrAA.add(matrixMultiply(arrAA.get(Integer.parseInt(o.substring(1,3)) -1 ), arrAA.get(Integer.parseInt(o.substring(4, 6)) - 1)));
        //System.out.println(o.substring(1,3) + " " + o.substring(4, 6));
      }
      if(o.length() == 4){
//        System.out.println(o);
//        System.out.println(
//            Arrays.deepToString(arrAA.get(Integer.parseInt(String.valueOf(o.charAt(1))))));
//        System.out.println(
//            Arrays.deepToString(arrAA.get(Integer.parseInt(String.valueOf(o.charAt(3))))));
        arrAA.add(matrixMultiply(arrAA.get(Integer.parseInt(String.valueOf(o.charAt(1))) - 1), arrAA.get(Integer.parseInt(String.valueOf(o.charAt(3))) - 1)));
        //System.out.println(o.charAt(1) + " " + o.charAt(3));
      }

    }
    System.out.println("Матрица после умножения: ");
    for(var a: arrAA.get(arrAA.size() - 1)){
      System.out.println(Arrays.toString(a));
    };

    System.out.println("Количество арифметических действий: " + countOperations);
  }
  /**
   * Создает и заполняет матрицу случайными числами.
   *
   * @param rows количество строк
   * @param cols количество столбцов
   * @return заполненная матрица
   */
  public static int[][] createAndFillMatrix(int rows, int cols) {
    Random random = new Random();
    int[][] matrix = new int[rows][cols];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        matrix[i][j] = random.nextInt(101); // Генерация случайного числа от 0 до 100
      }
    }
    return matrix;
  }

  public static void main(String[] args) {
//    int[][] A1 =
//        {
//            {1, 2, 3},
//            {4, 5, 6},
//            {7, 8, 9}
//        };
//    int[][] A2 =
//        {
//            {1},
//            {4},
//            {7}
//        };
//    int[][] A3 =
//        {
//            {1, 2, 3, 5, 6}
//        };
//    int[][] A4 =
//        {
//            {1},
//            {4},
//            {7},
//            {8},
//            {9}
//        };
//    int[][] A5 =
//        {
//            {1, 2}
//        };
//
//    int[][] A6 =
//        {
//            {1, 2},
//            {4, 5}
//        };

    int[][] A1 = createAndFillMatrix(30, 30);
    int[][] A2 = createAndFillMatrix(30, 10);
    int[][] A3 = createAndFillMatrix(10, 50);
    int[][] A4 = createAndFillMatrix(50, 10);
    int[][] A5 = createAndFillMatrix(10, 20);
    int[][] A6 = createAndFillMatrix(20, 20);
    int[][][] arrA = {A1, A2, A3, A4, A5, A6};
    int[] p = {30, 30, 10, 50, 10, 20, 20};
//    int[] p = {3, 3, 1, 5, 1, 2, 2};
    int n = p.length;

    int[][] m = new int[n][n];
    int[][] s = new int[n][n];

    matrixChainOrder(p, m, s);

    System.out.println("Оптимальное количество операций: " + m[1][n - 1]);
    System.out.print("Оптимальный порядок: ");

    printOptimalParens(s, 1, n - 1);
    System.out.println(res.toString());
//    for(var ss: m){
//      System.out.println(Arrays.toString(ss));
//    }
//    System.out.println(Arrays.deepToString(s));
    System.out.println();
    multiplyOrderedMatrix(arrA, getOrderMultiply(arrA));
  }
}

