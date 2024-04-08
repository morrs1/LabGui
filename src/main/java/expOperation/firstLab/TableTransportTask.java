package expOperation.firstLab;

import java.util.*;


public class TableTransportTask {

  private int[][] costs; // Затраты на перевоз товаров
  private Integer[] demands; // Заголовки слева и их объем
  private Integer[] supplies; // Заголовки сверху и их объем
  private Integer[][] amount;//Объем товаров после разгрузки

  private Integer[] demandsPotentials; // Заголовки слева и их потенциалы
  private Integer[] suppliesPotentials; // Заголовки сверху и их потенциалы
  private Integer[][] tableIndirectCosts;//Косвенные стоимости

  private Cell[][] cells; //переработанные массивы для метода потенциалов


  private static final int WIDTH = 10;

  public TableTransportTask(int[][] costs, Integer[] supplies, Integer[] demands) {
    this.costs = costs;
    this.supplies = supplies;
    this.demands = demands;
  }

  public void checkForEquality() {
    int sumSup = Arrays.stream(supplies).mapToInt(Integer::intValue).sum();
    int sumDem = Arrays.stream(demands).mapToInt(Integer::intValue).sum();

    if (sumSup > sumDem) {
      var newCosts = Arrays.copyOf(costs, costs.length + 1);
      newCosts[costs.length] = new int[supplies.length];
      costs = newCosts;
      demands = Arrays.copyOf(demands, demands.length + 1);
      demands[demands.length - 1] = sumSup - sumDem;
    }
    if (sumSup < sumDem) {
      supplies = Arrays.copyOf(supplies, supplies.length + 1);
      supplies[supplies.length - 1] = sumDem - sumSup;
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

  public void printPotentialTable(Integer b) {

    var amount = new Integer[this.amount.length][this.amount[0].length];
    if (b == 1) {
      amount = this.amount;
    } else {
      amount = this.tableIndirectCosts;
    }
    StringBuilder table = new StringBuilder();

    table.append(String.format("%-" + WIDTH + "s", ""));
    for (int i = 0; i < amount[0].length; i++) {
      table.append(String.format("%-" + WIDTH + "d", suppliesPotentials[i]));
    }
    table.append("\n");

    for (int i = 0; i < amount.length; i++) {
      table.append(String.format("%-" + WIDTH + "d", demandsPotentials[i]));
      for (int j = 0; j < amount[0].length; j++) {
        if (amount[i][j] != null) {
          table.append(String.format("%-" + WIDTH + "d", amount[i][j]));
        } else {
          table.append(String.format("%-" + WIDTH + "s", "---"));
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
      if (i == -1) {
        i = 1;
      }
      if (j == -1) {
        j = 1;
        i += 1;
      }
      amount[i - 1][j] = 0;
      calculatePotentialsHelper();
    }
  }

  public void calculateIndirectCosts() {
    tableIndirectCosts = new Integer[amount.length][amount[0].length];
    for (var i = 0; i < amount.length; i++) {
      for (var j = 0; j < amount[0].length; j++) {
        if (amount[i][j] == null) {
          tableIndirectCosts[i][j] = costs[i][j] - demandsPotentials[i] - suppliesPotentials[j];
        }
      }
    }

  }

  public boolean pM(int ccc) {
    if(ccc>0){
      calculatePotentials2();
    }
    if (calculateIndirectCosts2()){
      return true;
    };
    System.out.println("Таблица потенциалов и дельт: ");
    PrintTable(cells, 0);

    var min = new Cell(0, 0, 0);
    min.setTraffic(1000);
    min.setDelta(1000);
    for (var c : cells) {
      for (var cc : c) {
        if (cc.getDelta() < min.getDelta()) {
          min = cc;
        }

      }
    }
    min.setTraffic(0);
   var  cycle = new ArrayList<>(Collections.singleton(min));
    CycleFinder.FindCycle(cycle, cells);
    System.out.println("Цикл: " + cycle + "\n");
    System.out.println("-------------------------------------------------");
   var lambda = 1000;
    //нахождение лямбды
    for (var i = 0; i < cycle.size() - 1; i++) {
      if (i % 2 != 0) {
        if (cycle.get(i).getTraffic() < lambda) {
          lambda = cycle.get(i).getTraffic();
        }
      }
    }
    //убираем те ситуации, когда в цикле на "-" стоит 0

    //перестановка значений в цикле
    for (var i = 0; i < cycle.size() - 1; i++) {
      if (i % 2 != 0) {
        if (cycle.get(i).getTraffic() == lambda) {
          cycle.get(i).removeTraffic();
        } else {
          cycle.get(i).setTraffic(cycle.get(i).getTraffic() - lambda);
        }
      } else {
        cycle.get(i).setTraffic(cycle.get(i).getTraffic() + lambda);
      }
    }
    System.out.println("Таблица трафика: ");
    PrintTable(cells, 1);
    //проверка на то, чтобы не образовывалась вырожденная таблица
   var count = 0;
    for (var c : cycle) {
      if (c.getTraffic() == 0 && c != cycle.get(0)) {
        count += 1;
        if (count == 2 && c != cycle.get(0)) {
          c.setTraffic(0);
        }
      }
    }

    return  false;
  }

  public void potentialMethod() {
    prepareToPotentialMethod();
    var ccc=0;
    while(true){
      if (pM(ccc)){
        return;
      }
      ccc+=1;
    }
  }

  public void calculatePotentials2() {
    demandsPotentials = new Integer[cells.length];
    suppliesPotentials = new Integer[cells[0].length];
    demandsPotentials[0] = 0;
    for (var g = 0; g < cells.length + cells[0].length; g++) {
      for (var i = 0; i < cells.length; i++) {
        for (var j = 0; j < cells[0].length; j++) {
          if (cells[i][j].isHasTraffic()) {
            if (demandsPotentials[i] != null && suppliesPotentials[j] == null) {
              suppliesPotentials[j] = cells[i][j].getCost() - demandsPotentials[i];
            }
            if (suppliesPotentials[j] != null && demandsPotentials[i] == null) {
              demandsPotentials[i] = cells[i][j].getCost() - suppliesPotentials[j];
            }
          }
        }
      }
    }
  }

  public boolean calculateIndirectCosts2() {
    var flag = true;
    for (var i = 0; i < cells.length; i++) {
      for (var j = 0; j < cells[0].length; j++) {
        if (!cells[i][j].isHasTraffic()) {
          cells[i][j].setDelta(
              cells[i][j].getCost() - demandsPotentials[i] - suppliesPotentials[j]);
          if(cells[i][j].getDelta() <0){
            flag = false;
          }
        } else {
          cells[i][j].setDelta(0);
        }
      }
    }
return flag;
  }

  public void prepareToPotentialMethod() {

    var cells = new Cell[amount.length][amount[0].length];
    for (var i = 0; i < amount.length; i++) {
      for (var j = 0; j < amount[0].length; j++) {
        var c = new Cell(i, j, costs[i][j]);
        if (amount[i][j] == null) {
//                    c.setTraffic(0);
        } else {
          c.setTraffic(amount[i][j]);
        }
        if (tableIndirectCosts[i][j] == null) {
//                    c.setDelta(0);
        } else {
          c.setDelta(tableIndirectCosts[i][j]);
        }

        cells[i][j] = c;
      }
    }

    this.cells = cells;
  }


  public void PrintTable(Cell[][] cells, int flag) {
    if (flag == 0) {
      StringBuilder table = new StringBuilder();

      table.append(String.format("%-" + WIDTH + "s", ""));
      for (int i = 0; i < cells[0].length; i++) {
        table.append(String.format("%-" + WIDTH + "d", suppliesPotentials[i]));
      }
      table.append("\n");

      for (int i = 0; i < cells.length; i++) {
        table.append(String.format("%-" + WIDTH + "d", demandsPotentials[i]));
        for (int j = 0; j < cells[0].length; j++) {
          if (!cells[i][j].isHasTraffic()) {
            table.append(String.format("%-" + WIDTH + "d", cells[i][j].getDelta()));
          } else {
            table.append(String.format("%-" + WIDTH + "s", "---"));
          }
        }
        table.append("\n");
      }

      table.append("\n");
      System.out.println(table);
    }else{
      StringBuilder table = new StringBuilder();

      table.append(String.format("%-" + WIDTH + "s", ""));
      for (int i = 0; i < cells[0].length; i++) {
        table.append(String.format("%-" + WIDTH + "d", supplies[i]));
      }
      table.append("\n");

      for (int i = 0; i < cells.length; i++) {
        table.append(String.format("%-" + WIDTH + "d", demands[i]));
        for (int j = 0; j < cells[0].length; j++) {
          if (cells[i][j].isHasTraffic()) {
            table.append(String.format("%-" + WIDTH + "d", cells[i][j].getTraffic()));
          } else {
            table.append(String.format("%-" + WIDTH + "s", "---"));
          }
        }
        table.append("\n");
      }

      table.append("\n");
      System.out.println(table);
    }
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
