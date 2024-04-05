package expOperation.firstLab;

import expOperation.firstLab.TableTransportTask.Direction;

public class Main {

  private static final int task = 17;

  public static void main(String[] args) {
    var mainTable = getTableTransportTask();
    System.out.println("Изначальная таблица:");
    mainTable.printTable(true);
    mainTable.checkForEquality();
    mainTable.northWestAngle();
    System.out.println("Таблица после проверки на закрытую/незакрытую:");
    mainTable.printTable(true);
    System.out.println("Таблица после метода северо-западного угла:");
    mainTable.printTable(false);
    mainTable.calculatePotentials();
    System.out.println("Таблица потенциалов:");
    mainTable.printPotentialTable(1);
    mainTable.calculateIndirectCosts();
//    System.out.println("Таблица косвенных стоимостей:");
//    mainTable.printPotentialTable(2);


  mainTable.potentialMethod();
  }

  private static TableTransportTask getTableTransportTask() {
    int[][] costs = new int[0][0];
    Integer[] supplies = new Integer[0];
    Integer[] demands = new Integer[0];

    switch (task) {
      case 1 -> {
        costs = new int[][]{
            {1, 2, 3, 5, 2},
            {4, 6, 7, 3, 1},
            {2, 2, 3, 4, 5}
        };
        supplies = new Integer[]{120, 120, 200, 180, 110};
        demands = new Integer[]{200, 150, 350};
      }
      case 7 -> {
        costs = new int[][]{
            {1, 4, 5, 3, 1},
            {2, 3, 1, 4, 2},
            {2, 1, 3, 1, 1}
        };
        supplies = new Integer[]{100, 100, 80, 90, 70};
        demands = new Integer[]{200, 350, 150};
      }
      case 9 -> {
        costs = new int[][]{
            {1, 4, 5, 3, 1},
            {2, 1, 2, 1, 2},
            {3, 1, 4, 2, 1}
        };
        supplies = new Integer[]{100, 120, 130, 100, 90};
        demands = new Integer[]{300, 120, 300};
      }
      case 17 -> {
        costs = new int[][]{
            {1, 4, 7, 2, 1},
            {2, 5, 1, 4, 3},
            {2, 3, 1, 2, 1}
        };
        supplies = new Integer[]{100, 100, 80, 90, 70};
        demands = new Integer[]{200, 350, 150};
      }
    }

    return new TableTransportTask(costs, supplies, demands);
  }


}
