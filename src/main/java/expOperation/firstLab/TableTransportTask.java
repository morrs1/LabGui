package expOperation.firstLab;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class TableTransportTask {

    private int[][] costs; // Затраты на перевоз товаров
    private int[] demands; // Заголовки слева и их объем
    private int[] supplies; // Заголовки сверху и их объем
    private Integer[][] amount;//Объем товаров после разгрузки

    private static final int WIDTH = 10;

    public TableTransportTask(int[][] costs, int[] supplies, int[] demands) {
        this.costs = costs;
        this.supplies = supplies;
        this.demands = demands;
    }

    public void checkForEquality() {
        AtomicInteger sumSup = new AtomicInteger();
        Arrays.stream(supplies).forEach(sumSup::addAndGet);
        AtomicInteger sumDem = new AtomicInteger();
        Arrays.stream(demands).forEach(sumDem::addAndGet);

        if (sumSup.get() > sumDem.get()) {
            var newCosts = Arrays.copyOf(costs, costs.length + 1);
            newCosts[costs.length] = new int[supplies.length];
            costs = newCosts;
            demands = Arrays.copyOf(demands, demands.length + 1);
            demands[demands.length - 1] = sumSup.get() - sumDem.get();
        }
      if (sumSup.get() < sumDem.get()) {
        supplies= Arrays.copyOf(supplies, supplies.length+1);
        supplies[supplies.length - 1] = sumDem.get() - sumSup.get();
        for(var i= 0; i < demands.length; i++){
          costs[i] = Arrays.copyOf(costs[i], supplies.length);
        }
        }
//      }

    }

    public void northWestAngle() {
        Integer[][] amount = new Integer[demands.length][supplies.length];
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
    }


  public void printTable(boolean printCosts) {
    StringBuilder table = new StringBuilder();

    // Вывод заголовка
    table.append(String.format("%-" + WIDTH + "s", ""));
    for (int i = 0; i < (printCosts ? costs[0].length : amount[0].length); i++) {
      table.append(String.format("%-" + WIDTH + "d", supplies[i]));
    }
    table.append("\n");

    // Вывод основного содержимого
    for (int i = 0; i < (printCosts ? costs.length : amount.length); i++) {
      table.append(String.format("%-" + WIDTH + "d", demands[i]));
      for (int j = 0; j < (printCosts ? costs[0].length : amount[0].length); j++) {
        if (printCosts) {
          table.append(String.format("%-" + WIDTH + "d", costs[i][j]));
        } else {
          if (amount[i][j] != null) {
            table.append(String.format("%-" + WIDTH + "d", amount[i][j]));
          } else {
            table.append(String.format("%-" + WIDTH + "s", "---"));
          }
        }
      }
      table.append("\n");
    }

    table.append("\n");
    System.out.println(table);
  }

}
