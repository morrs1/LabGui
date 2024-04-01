package expOperation.firstLab;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.units.qual.A;

public class TableTransportTask {

  private int[][] costs; // Затраты на перевоз товаров
  private int[] demands; // Объемы товаров, которые нужно перевезти из каждого пункта отправления
  private int[] supplies; // Объемы товаров, которые нужно принять в каждом пункте назначения
  private int[][] amount;//Объем товаров после разгрузки

  private static final int WIDTH = 10;

  public TableTransportTask(int[][] costs, int[] supplies, int[] demands) {
    this.costs = costs;
    this.supplies = supplies;
    this.demands = demands;
  }

  public void checkForEquality(){
    AtomicInteger sumSup = new AtomicInteger();
    Arrays.stream(supplies).forEach(sumSup::addAndGet);

    AtomicInteger sumDem = new AtomicInteger();
    Arrays.stream(demands).forEach(sumDem::addAndGet);

    if (sumSup.get() > sumDem.get()){
      var newCosts = Arrays.copyOf(costs, costs.length+1);
      newCosts[costs.length] = new int[5];
      costs = newCosts;
      demands = Arrays.copyOf(demands, demands.length + 1);
      demands[demands.length-1] = sumSup.get() - sumDem.get();
    }

  }

  public TableTransportTask northWestAngle() {
    int[][] amount = new int[demands.length][supplies.length];
    var dem = Arrays.copyOf(demands, demands.length);
    var sup = Arrays.copyOf(supplies, supplies.length);
    var i = 0;
    var j = 0;
    while (i < costs.length && j < costs[0].length) {
      if (dem[i] > sup[j]) {
        amount[i][j] = sup[j];
        dem[i] -= sup[j];
        j++;
      } else if (dem[i] < sup[j]) {
        amount[i][j] = dem[i];
        sup[j] -= dem[i];
        i++;
      } else {
        amount[i][j] = dem[i];
        i++;
        j++;
      }
    }
    this.amount = amount;
    return new TableTransportTask(amount, this.supplies, this.demands);
  }



  @Override
  public String toString() {
    StringBuilder table = new StringBuilder();

    // Header row
    table.append(String.format("%-" + WIDTH + "s", ""));
    for (int i = 0; i < costs[0].length; i++) {
      table.append(String.format("%-" + WIDTH + "d", supplies[i]));
    }
    table.append("\n");

    // First table
    for (int i = 0; i < costs.length; i++) {
      table.append(String.format("%-" + WIDTH + "d", demands[i]));
      for (int j = 0; j < costs[0].length; j++) {
        table.append(String.format("%-" + WIDTH + "d", costs[i][j]));
      }
      table.append("\n");
    }

    // Empty line
    table.append("\n");


    return table.toString();
  }

}
