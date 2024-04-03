package expOperation.firstLab;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    /////1 вариант/////
    var mainTable = getTableTransportTask(9);
    System.out.println("Изначальная таблица:");
    mainTable.printTable(true);
    mainTable.checkForEquality();
    mainTable.northWestAngle();
    System.out.println("Таблица после проверки на закрытую/незакрытую:");
    mainTable.printTable(true);
    System.out.println("Таблица после метода северо-западного угла:");
    mainTable.printTable(false);


  }




  private static TableTransportTask getTableTransportTask(int Task) {
    int[][] costs = new int[0][0];
    int[] supplies = new int[0];
    int[] demands = new int[0];

    switch (Task){
      case 1->{
        costs = new int[][]{
            {1, 2, 3, 5, 2},
            {4, 6, 7, 3, 1},
            {2, 2, 3, 4, 5}
        };
        supplies = new int[]{120, 120, 200, 180, 110};
        demands = new int[]{200, 150, 350};
      }
      case 9->{
        costs = new int[][]{
            {1, 4, 5, 3, 1},
            {2, 1, 2, 1, 2},
            {3, 1, 4, 2, 1}
        };
    supplies = new int[]{100, 120, 130, 100, 90};
    demands = new int[]{300, 120, 300};
      }
    }

    return new TableTransportTask(costs, supplies, demands);
  }


}
