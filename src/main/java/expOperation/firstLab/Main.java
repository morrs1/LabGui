package expOperation.firstLab;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
//    int[][] costs = {
//        {1, 2, 3, 5, 2},
//        {4, 6, 7, 3, 1},
//        {2, 2, 3, 4, 5}
//    };
//    int[] supplies = {120, 120, 200, 180, 110};
//    int[] demands = {200, 150, 350};
    int[][] costs = {
        {1, 4, 5, 3, 1},
        {2, 1, 2, 1, 2},
        {3, 1, 4, 2, 1}
    };
    int[] supplies = {100, 120, 130, 100, 90};
    int[] demands = {300, 120, 300};
    TableTransportTask mainTable = new TableTransportTask(costs, supplies, demands);
    System.out.println("Изначальная таблица:");
    mainTable.printTable(true);
    mainTable.checkForEquality();
    mainTable.northWestAngle();
    System.out.println("Таблица после проверки на закрытую/незакрытую:");
    mainTable.printTable(true);
    System.out.println("Таблица после метода северо-западного угла:");
    mainTable.printTable(false);


  }


}
