package expOperation.firstLab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class TableTransportTask {

  private int[][] costs; // Затраты на перевоз товаров
  private int[] demands; // Заголовки слева и их объем
  private int[] supplies; // Заголовки сверху и их объем
  private Integer[][] amount;//Объем товаров после разгрузки

  private Integer[] demandsPotentials; // Заголовки слева и их потенциалы
  private Integer[] suppliesPotentials; // Заголовки сверху и их потенциалы

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
      supplies = Arrays.copyOf(supplies, supplies.length + 1);
      supplies[supplies.length - 1] = sumDem.get() - sumSup.get();
      for (var i = 0; i < demands.length; i++) {
        costs[i] = Arrays.copyOf(costs[i], supplies.length);
      }
    }
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


  public void printTable(boolean printT) {
    StringBuilder table = new StringBuilder();

    // Вывод заголовка
    table.append(String.format("%-" + WIDTH + "s", ""));
    for (int i = 0; i < (printT ? costs[0].length : amount[0].length); i++) {
      table.append(String.format("%-" + WIDTH + "d", supplies[i]));
    }
    table.append("\n");

    // Вывод основного содержимого
    for (int i = 0; i < (printT ? costs.length : amount.length); i++) {
      table.append(String.format("%-" + WIDTH + "d", demands[i]));
      for (int j = 0; j < (printT ? costs[0].length : amount[0].length); j++) {
        if (printT) {
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

  public void calculatePotentials() {
    demandsPotentials = new Integer[demands.length];
    suppliesPotentials = new Integer[supplies.length];
    demandsPotentials[0] = 0;
    calculatePotentialsHelper();
    if (Arrays.asList(demandsPotentials).contains(null) || Arrays.asList(
            suppliesPotentials)//это проверка на вырожденность(n+m-1 == кол-во занятых ячеек)
        .contains(null)) {
      var i = Arrays.asList(demandsPotentials).indexOf(null);
      var j = Arrays.asList(suppliesPotentials).indexOf(null);
      //проверка на случай, когда получается пустой столбец(тогда i становится -1 из-за того, что нет null)
      if(i == -1){
        i = 1;
      }
      if(j == -1){
        j = 1;
        i+=1;
      }
      amount[i - 1][j] = 0;
      calculatePotentialsHelper();
    }

    System.out.println(Arrays.toString(demandsPotentials));
    System.out.println(Arrays.toString(suppliesPotentials));

  }

  /**
   * Метод в котором происходит обход массива amount и подсчет потенциалов верхний цикл сделан для
   * того, чтобы исключить ситуацию, когда непустые ячейки расположены непоследовательно, а
   * хаотично. Выделил в отдельный метод для того, чтобы при появлении вырожденной таблицы повторить
   * проход по массиву и дозаполнить потенциалы
   */

  private void calculatePotentialsHelper() {
    for (var g = 0; g < amount.length + amount[0].length; g++) {
      for (var i = 0; i < amount.length; i++) {
        for (var j = 0; j < amount[0].length; j++) {
          if (amount[i][j] != null) {
            if (demandsPotentials[i] != null && suppliesPotentials[j] == null) {
              suppliesPotentials[j] = costs[i][j] - demandsPotentials[i];
            }
            if (suppliesPotentials[j] != null && demandsPotentials[i] == null) {
              demandsPotentials[i] = costs[i][j] - suppliesPotentials[j];
            }
          }
        }
      }
    }
  }
}
