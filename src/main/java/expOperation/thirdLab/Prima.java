package expOperation.thirdLab;

import java.util.ArrayList;
import java.util.Arrays;


public class Prima {


  static int minKey(int[] key, boolean[] inMST, int V) {
    int min = Integer.MAX_VALUE;
    int min_index = -1;

    for (int v = 0; v < V; v++) {
      if (!inMST[v] && key[v] < min) {
        min = key[v];
        min_index = v;
      }
    }

    return min_index;
  }

  // Метод для построения минимального остовного дерева
  static ArrayList<Integer> primMST(int[][] graph, int V) {
    System.out.println("Промежуточные этапы:\n");
    var sum = 0;
    ArrayList<Integer> parent = new ArrayList<>(Arrays.asList(new Integer[V]));
    int[] key = new int[V];
    boolean[] inMST = new boolean[V];

    Arrays.fill(key, Integer.MAX_VALUE);
    Arrays.fill(inMST, false);

    key[0] = 0; // Устанавливаем ключ для вершины 0 как 0
    parent.set(0, -1); // Устанавливаем родителя для вершины 0 как -1

    for (int count = 0; count < V - 1; count++) {
      int u = minKey(key, inMST, V);
      inMST[u] = true;

      for (int v = 0; v < V; v++) {
        if (graph[u][v] != 0 && !inMST[v] && graph[u][v] < key[v]) {
          parent.set(v, u);
          key[v] = graph[u][v];
          sum+=key[v];
          System.out.println(key[v]);

          for(var el: parent){
            if(el!= null) {
              System.out.print(el+1 + " ");
            }
            else System.out.print(el + " ");
          }
          System.out.println("\n");
        }
      }
    }

    System.out.println("Сумма: " + sum);
    return parent;
  }

  public static void main(String[] args) {
    int V = 7; // Количество вершин
    int[][] graph = {
        {0, 2, 0, 2, 0, 0, 0},
        {2, 0, 5, 3, 5, 0, 0},
        {0, 5, 0, 0, 3, 4, 0},
        {2, 3, 0, 0, 6, 0, 7},
        {0, 5, 3, 6, 0, 4, 0},
        {0, 0, 4, 0, 4, 0, 8},
        {0, 0, 0, 7, 0, 8, 0}
    };

    ArrayList<Integer> mst = primMST(graph, V);

    System.out.println("Минимальное остовное дерево: ");
    for (int i = 1; i < V; i++) {
      System.out.println((mst.get(i) + 1) + " - " + (i + 1));
    }
  }
}
