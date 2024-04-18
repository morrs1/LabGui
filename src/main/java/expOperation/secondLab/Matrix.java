package expOperation.secondLab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Matrix {
    static StringBuilder res = new StringBuilder();

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

    public static LinkedHashMap<String, String> getOrderMultiply() {
        String inputString = res.toString();

        // Регулярное выражение для поиска матриц
        String patternString = "\\b([A-Za-z0-9]+)\\b";

        // Создание объекта Pattern
        Pattern pattern = Pattern.compile(patternString);

        // Создание объекта Matcher для исходной строки
        Matcher matcher = pattern.matcher(inputString);

        // Список для хранения найденных матриц
        List<String> foundMatrices = new ArrayList<>();

        // Цикл по всем совпадениям
        while (matcher.find()) {
            // Извлечение текущего совпадения и добавление его в список
            if (matcher.group().length() > 3) {
                foundMatrices.add(matcher.group());
            }
        }
        var c = 7;
        for (var l : foundMatrices) {
            if(res.indexOf("(" + l + ")") != -1){
                res = new StringBuilder(res.toString().replace("(" + l + ")", "A"+c));
                c+=1;
            }

        }

        System.out.println(foundMatrices);
        System.out.println(res.toString());


        matcher = pattern.matcher(res);
        while (matcher.find()) {
            // Извлечение текущего совпадения и добавление его в список
            if (matcher.group().length() > 3) {
                foundMatrices.add(matcher.group());
            }
        }
        for (var l : foundMatrices) {
            if(res.indexOf("(" + l + ")") != -1){
                res = new StringBuilder(res.toString().replace("(" + l + ")", "A"+c));
                c+=1;
            }

        }

        System.out.println(foundMatrices);
        System.out.println(res.toString());

////////////////
        matcher = pattern.matcher(res);
        while (matcher.find()) {
            // Извлечение текущего совпадения и добавление его в список
            if (matcher.group().length() > 3) {
                foundMatrices.add(matcher.group());
            }
        }
        for (var l : foundMatrices) {
            if(res.indexOf("(" + l + ")") != -1){
                res = new StringBuilder(res.toString().replace("(" + l + ")", "A"+c));
                c+=1;
            }

        }

        System.out.println(foundMatrices);
        System.out.println(res.toString());
        return null;
    }

    public static void main(String[] args) {
        int[] p = {30, 35, 15, 5, 10, 20, 25};
        int n = p.length;

        int[][] m = new int[n][n];
        int[][] s = new int[n][n];

        matrixChainOrder(p, m, s);

        System.out.println("Minimum number of multiplications: " + m[1][n - 1]);
        System.out.print("Optimal parenthesization: ");

        printOptimalParens(s, 1, n - 1);
        System.out.println(res.toString());
        System.out.println();
        System.out.println(Arrays.deepToString(s));
        System.out.println(Arrays.deepToString(m));
        getOrderMultiply();
    }
}

