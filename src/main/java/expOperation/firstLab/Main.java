package expOperation.firstLab;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    int[][] costs = {
        {1, 2, 3, 5, 2},
        {4, 6, 7, 3, 1},
        {2, 2, 3, 4, 5}
    };
    int[] supplies = {120, 120, 200, 180, 110};
    int[] demands = {200, 150, 350};

    TableTransportTask mainTable = new TableTransportTask(costs, supplies, demands);
    System.out.println(mainTable);
    mainTable.checkForEquality();
    var ss = mainTable.northWestAngle();
    System.out.println(mainTable);
    System.out.println(ss);

  }


}
