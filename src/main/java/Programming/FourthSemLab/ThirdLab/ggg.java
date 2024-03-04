package Programming.FourthSemLab.ThirdLab;

import java.util.Arrays;
import java.util.Scanner;

public class ggg {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Введите начальное значение интервала: ");
    double a = scanner.nextDouble();

    System.out.print("Введите конечное значение интервала: ");
    double b = scanner.nextDouble();

    // постоянный шаг
    double h = (b - a) / 100;

    double[] x = new double[101];
    double[] y = new double[101];

    // вычисление значений аргумента и функции на интервале
    for (int i = 0; i <= 100; i++) {
      x[i] = a + i * h;
      y[i] = Math.exp(x[i]) - Math.pow(x[i], 3);
    }
    System.out.println(Arrays.toString(x));
    System.out.println(Arrays.toString(y));

    double integral = integrateLeftRectangles(x, y);
    System.out.printf("\nЗначение интеграла: %.4f%n", integral);

    double exactIntegral = Math.exp(b) - b * b * b * b / 4 - Math.exp(a) + a * a * a * a / 4;
    System.out.printf("Точное значение интеграла: %.4f%n", exactIntegral);
  }

  public static double integrateLeftRectangles(double[] x, double[] y) {
    double integral = 0;

    for (int i = 0; i < x.length - 1; i++) {
      double width = x[i + 1] - x[i];
      double height = y[i];
      integral += width * height;
    }

    return integral;
  }
}
