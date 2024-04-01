package expOperation.firstLab;

import java.util.Arrays;

public class TableTransportTask {

  private int[][] costs; // Затраты на перевоз товаров
  private int[] supplies; // Объемы товаров, которые нужно перевезти из каждого пункта отправления
  private int[] demands; // Объемы товаров, которые нужно принять в каждом пункте назначения


  public TableTransportTask(int[][] costs, int[] supplies, int[] demands) {
    this.costs = costs;
    this.supplies = supplies;
    this.demands = demands;
  }

  public TableTransportTask northWestAngle() {
    int[][] amount = new int[3][5];
    var i = 0;
    var j = 0;
    while (i < costs.length && j < costs[0].length) {
      if (demands[i] > supplies[j]) {
        amount[i][j] = supplies[j];
        demands[i] -= supplies[j];
        j++;
      } else if (demands[i] < supplies[j]) {
        amount[i][j] = demands[i];
        supplies[j] -= demands[i];
        i++;
      } else {
        amount[i][j] = demands[i];
        i++;
        j++;
      }
    }

    return new TableTransportTask(amount, this.supplies, this.demands);
  }



  @Override
  public String toString() {
    var res = new StringBuilder();
    var str = new StringBuilder().append("      ");
    for (var s : supplies) {
      str.append(s).append("      ");
    }
    res.append(str).append("\n");

    for (var i = 0; i < costs.length; i++) {
      str = new StringBuilder();
      str.append(demands[i]).append("    ");
      for (var j = 0; j < costs[0].length; j++) {
        str.append(costs[i][j]).append("        ");
      }
      str.append("\n");
      res.append(str);
    }

    return res.toString();
  }

}
