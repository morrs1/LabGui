package expOperation.fourthLab;

import java.util.Arrays;

public class Dijkstra {

  public static void main(String[] args) {
    final int START = 0;
    final int VERTICES = 5;
    final int[][] ADJACENCY_MATRIX = {
        {0, 2, 7, 4, 6},
        {0, 0, 2, 1, 2},
        {0, 0, 0, 0, 0},
        {0, 2, 0, 0, 1},
        {0, 0, 1, 0, 0}
    };

    int[] distances = new int[VERTICES];
    boolean[] visited = new boolean[VERTICES];
    Arrays.fill(distances, Integer.MAX_VALUE);
    distances[START] = 0;
//Сам алгоритм
    int minimalIndex;
    do {
      minimalIndex = Integer.MAX_VALUE;
      int minimalWeight = Integer.MAX_VALUE;

      for (int i = 0; i < VERTICES; ++i) {
        if (!visited[i] && distances[i] < minimalWeight) {
          minimalIndex = i;
          minimalWeight = distances[i];
        }
      }

      if (minimalIndex != Integer.MAX_VALUE) {
        for (int i = 0; i < VERTICES; ++i) {
          if (ADJACENCY_MATRIX[minimalIndex][i] > 0) {
            int temp = minimalWeight + ADJACENCY_MATRIX[minimalIndex][i];
            if (temp < distances[i]) {
              distances[i] = temp;
              System.out.println(Arrays.toString(distances));
            }
          }
        }
        visited[minimalIndex] = true;
      }
      System.out.println();
    }
    while (minimalIndex < Integer.MAX_VALUE);
//Вывод
    for (int i = 0; i < VERTICES; ++i) {
      if (distances[i] != Integer.MAX_VALUE) {
        System.out.printf("Вес: %d ~> %d = %-6d\t", START + 1, i + 1, distances[i]);

        int end = i;
        int weight = distances[end];
        StringBuilder way = new StringBuilder(end + 1 + " >~ ");

        while (end != START) {
          for (int j = 0; j < VERTICES; ++j) {
            if (ADJACENCY_MATRIX[j][end] > 0) {
              int temp = weight - ADJACENCY_MATRIX[j][end];
              if (temp == distances[j]) {
                end = j;
                weight = temp;
                way.append(j + 1).append(" >~ ");
              }
            }
          }
        }

        System.out.print("Путь: ");
        for (int j = way.length() - 5; j >= 0; --j) {
          System.out.print(way.charAt(j));
        }
        System.out.println();
      } else {
        System.out.println("Вес: " + START + " ~ " + i + 1 + " = маршрут недоступен");
      }
    }
  }
}