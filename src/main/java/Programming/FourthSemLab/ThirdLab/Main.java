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
                case 4, 5 -> fourthAndFive();
                case 6 -> {
                    System.out.println("Введите отрезок: ");
                    scanner.nextLine();
                    var str = scanner.nextLine();
                    yield sixthTask(str);
                }
                case 7 -> {
                    System.out.println("Введите число и основание системы счисления (через пробел, основание должно быть [2,8]!!!");
                    scanner.nextLine();
                    var str = scanner.nextLine();
                    while (Integer.parseInt(str.split(" ")[1]) < 2 || Integer.parseInt(str.split(" ")[1]) > 8) {
                        System.out.println("Введите число и основание системы счисления (через пробел, основание должно быть [2,8]!!!");
                        str = scanner.nextLine();
                    }
                    yield seventhTask(str);
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
        return switch (res) {
            case COINCIDENT -> "Совпадают";
            case TOUCHING -> "Касаются";
            case INTERSECTING_AT_TWO_POINTS -> "Пересекаются в двух точках";
            case FIRST_CIRCLE_EMBEDDED -> "Первая окружность вложена во вторую";
            case SECOND_CIRCLE_EMBEDDED -> "Вторая окружность вложена в первую";
            case NON_INTERSECTING -> "Не пересекаются";
        };
    }

    @SuppressWarnings("unused")
    public static String fifthTask(String args) {
        return fourthTask(args);
    }

    public static String sixthTask(String args) {
        var str = new StringBuilder();
        var arg = Arrays.stream(args.split(" ")).map(Double::parseDouble).toList();
        var b = arg.get(1);
        var a = arg.get(0);
        var tabArr = tabulateFunc(a, b);

        var exactIntegral = Math.exp(b) - b * b * b * b / 4 - Math.exp(a) + a * a * a * a / 4;
        return str.append("Значение интеграла: ")
                .append(String.format("%.4f", integrateLeftRectangles(tabArr.get(0), tabArr.get(1)))).append("\n")
                .append("Точное значение интеграла: ").append(String.format("%.4f", exactIntegral)).toString();

    }

    public static String seventhTask(String args) {
        var res = new StringBuilder();
        res.append("Число ручным переводом: ").append(convertNum(args))
                .append("\n").append("Число переводом toString: ")
                .append(Integer.toString(Integer.parseInt(args.split(" ")[0]), Integer.parseInt(args.split(" ")[1])));
        return res.toString();
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

    private static String fourthAndFive() {
        System.out.println(
                "Введите координаты центра первой окружности и ее радиус, и координаты центра и радиус второй окружности (Все через пробел!! x1 y1 r1 x2 y2 r2 )");
        scanner.nextLine();
        var str = scanner.nextLine();
        return fourthTask(str);
    }

    private static ArrayList<ArrayList<Double>> tabulateFunc(Double a, Double b) {
        var arr = new ArrayList<ArrayList<Double>>();
        var arrX = new ArrayList<Double>();
        var arrY = new ArrayList<Double>();
        var step = (b - a) / 100;

        IntStream.range(0, 101).forEach(x -> {
            arrX.add(a + x * step);
            arrY.add(Math.exp(arrX.get(x)) - Math.pow(arrX.get(x), 3));
        });
        arr.add(arrX);
        arr.add(arrY);
        return arr;
    }

    private static Double integrateLeftRectangles(ArrayList<Double> arrX, ArrayList<Double> arrY) {
        double integral = 0;

        for (var i = 0; i < arrX.size() - 1; i++) {
            double width = arrX.get(i + 1) - arrX.get(i);
            double height = arrY.get(i);
            integral += width * height;
        }

        return integral;

    }

    private static String convertNum(String args) {
        var num = Integer.parseInt(args.split(" ")[0]);
        var base = Integer.parseInt(args.split(" ")[1]);

        StringBuilder result = new StringBuilder();

        while (num > 0) {
            result.insert(0, num % base);
            num /= base;
        }

        return result.toString();
    }
}
