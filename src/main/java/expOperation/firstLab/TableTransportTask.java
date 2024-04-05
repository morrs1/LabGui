package expOperation.firstLab;

import java.util.*;
import java.util.stream.IntStream;

import org.checkerframework.checker.units.qual.A;

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
//    public Cell getNearestCell(Cell cell, Direction dir) {
//        var I = cell.getI();
//        var J = cell.getJ();
//
//        switch (dir) {
//            case RIGHT -> {
//                for (var j = J + 1; j < amount[0].length; j++) {
//                    if (amount[I][j] != null) {
////            System.out.println(amount[I][j] + " "+  I + " "+ j);
//                        return new Cell(I, j);
//                    }
//                }
//                return null;
//            }
//            case DOWN -> {
//                for (var i = I + 1; i < amount.length; i++) {
//                    if (amount[i][J] != null) {
////            System.out.println(amount[i][J]+ " " + i + " "+ J);
//                        return new Cell(i, J);
//                    }
//                }
//                return null;
//            }
//            case LEFT -> {
//                for (var j = J - 1; j >= 0; j--) {
//                    if (amount[I][j] != null) {
////            System.out.println(amount[I][j]+ " " + I + " "+ j);
//                        return new Cell(I, j);
//                    }
//                }
//                return null;
//            }
//            case UP -> {
//                for (var i = I - 1; i >= 0; i--) {
//                    if (amount[i][J] != null) {
////            System.out.println(amount[i][J]+ " " + i + " "+ J);
//                        return new Cell(i, J);
//                    }
//                }
//                return null;
//            }
//        }
//        return null;
//    }

//    public Cell getFirstCellOfCycle() {
//        var min = 1000;
//        var I = 0;
//        var J = 0;
//        for (var i = 0; i < amount.length; i++) {
//            for (var j = 0; j < amount[0].length; j++) {
//                if (tableIndirectCosts[i][j] != null && tableIndirectCosts[i][j] < min) {
//                    min = tableIndirectCosts[i][j];
//                    I = i;
//                    J = j;
//                }
//            }
//        }
//        amount[I][J] = 0;
//
//        return new Cell(I, J);
//    }
//
//    public void potentialMethod() {
//        var cycle = new ArrayList<Cell>(Collections.singleton(getFirstCellOfCycle()));
//        System.out.println(cycle);
//        findCycle(cycle, null);
//
//        System.out.println(getNearestCell(new Cell(1,2), Direction.RIGHT));
//        System.out.println(cycle);
//    }
//
//    private boolean findCycle(List<Cell> cycle, Direction prevDir) {
//        var cell = cycle.get(cycle.size() - 1);
//        for (var dir : Direction.values()) {
//            var nearestCell = getNearestCell(cell, dir);
//            if (nearestCell != null) {
//                if (cycle.contains(nearestCell)) {
//                    // Если пришли в начальную ячейку
//                    if (nearestCell.getI() == cycle.get(0).getI() && nearestCell.getJ() == cycle.get(0).getJ() ) {
//                        // Убираем из цикла ячейку-посредника на линии
//                        if (dir == prevDir) {
//                            cycle.remove(cell);
//                        }
//                        // Цикл существует
//                        if (cycle.size() % 2 == 0 && cycle.size() >= 4) {
//                            cycle.add(nearestCell);
//                            return true;
//                        }
//                        // Цикл невозможен
//                        continue;
//                    }
//                    continue;
//                }
//                // Если есть прошлое направление
//                if (prevDir != null) {
//                    // Если направление совпадает с прошлым направлением (не ломанная)
//                    if (dir == prevDir) {
//                        cycle.remove(cell);
//                        cycle.add(nearestCell);
//                        if (findCycle(cycle, dir)) return true;
//                        continue;
//                    }
//                    // Если направление противоположно прошлому направлению (не ломанная)
//                    if (dir.getCode() == prevDir.getCode()) continue;
//                }
//                cycle.add(nearestCell);
//                if (findCycle(cycle, dir)) return true;
//            }
//        }
//        cycle.remove(cell);
//        return false;
//    }

//  public boolean FindNextStep(List<Cell> cycle, Direction prevDir) {
//    Cell cell = cycle.get(cycle.size() - 1);
//    // Ищем соседей по всем направлениям
//    for (Direction dir : Direction.values()) {
//      Cell nearestCell = getNearestCell(cell, dir);
//      if (nearestCell != null) {
//        // Если в цикле уже есть ячейка
//        if (cycle.contains(nearestCell)) {
//          // Если пришли в начальную ячейку
//          if (nearestCell == cycle.get(0)) {
//            // Убираем из цикла ячейку-посредника на линии
//            if (dir == prevDir) {
//              cycle.remove(cell);
//            }
//            // Цикл существует
//            if (cycle.size() % 2 == 0 && cycle.size() >= 4) {
//              cycle.add(nearestCell);
//              return true;
//            }
//            // Цикл невозможен
//            continue;
//          }
//          continue;
//        }
//        // Если есть прошлое направление
//        if (prevDir != null) {
//          // Если направление совпадает с прошлым направлением (не ломанная)
//          if (dir == prevDir) {
//            cycle.remove(cell);
//            cycle.add(nearestCell);
//            if (FindNextStep(cycle, dir)) {
//              return true;
//            }
//            continue;
//          }
//          // Если направление противоположно прошлому направлению (не ломанная)
//          if (dir.getCode() == prevDir.getCode()) {
//            continue;
//          }
//        }
//        cycle.add(nearestCell);
//        if (FindNextStep(cycle, dir)) {
//          return true;
//        }
//      }
//      cycle.forEach(x -> {
//        System.out.print(amount[x.getI()][x.getJ()] + " ");
//      });
//      System.out.println("\n");
//    }
//    cycle.remove(cell);
//    return false;
//  }

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


  enum Direction {
    UP(0), RIGHT(1), DOWN(0), LEFT(1);
    private final int code;

    Direction(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }
  }
}
