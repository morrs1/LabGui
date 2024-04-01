package expOperation.firstLab;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class TableTransportTask {

  private LinkedHashMap<HeadNode, LinkedHashMap<HeadNode, Node>> table;
  private static final Scanner scanner = new Scanner(System.in);

  public void setTable() {
    var mainTable = new LinkedHashMap<HeadNode, LinkedHashMap<HeadNode, Node>>();
    // Создаем внешний LinkedHashMap для первого внешнего ключа
    LinkedHashMap<HeadNode, Node> innerMap1 = new LinkedHashMap<>();
    innerMap1.put(new HeadNode("b1", 120), new Node(1));
    innerMap1.put(new HeadNode("b2",120), new Node(2));
    innerMap1.put(new HeadNode("b3", 200), new Node(3));
    innerMap1.put(new HeadNode("b4",180), new Node(5));
    innerMap1.put(new HeadNode("b5", 110), new Node(2));

    // Создаем внешний LinkedHashMap для второго внешнего ключа
    LinkedHashMap<HeadNode, Node> innerMap2 = new LinkedHashMap<>();
    innerMap2.put(new HeadNode("b1", 120), new Node(4));
    innerMap2.put(new HeadNode("b2",120), new Node(6));
    innerMap2.put(new HeadNode("b3", 200), new Node(7));
    innerMap2.put(new HeadNode("b4",180), new Node(3));
    innerMap2.put(new HeadNode("b5", 110), new Node(1));

    // Создаем внешний LinkedHashMap для третьего внешнего ключа
    LinkedHashMap<HeadNode, Node> innerMap3 = new LinkedHashMap<>();
    innerMap3.put(new HeadNode("b1", 120), new Node(2));
    innerMap3.put(new HeadNode("b2",120), new Node(2));
    innerMap3.put(new HeadNode("b3", 200), new Node(3));
    innerMap3.put(new HeadNode("b4",180), new Node(4));
    innerMap3.put(new HeadNode("b5", 110), new Node(5));


    // Добавляем внутренние LinkedHashMap в основную таблицу
    mainTable.put(new HeadNode("a1", 200), innerMap1);
    mainTable.put(new HeadNode("a2", 150), innerMap2);
    mainTable.put(new HeadNode("a3", 350), innerMap3);

    this.table = mainTable;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    // Предполагаем, что максимальная длина строки для каждого столбца равна 10 символам
    int columnWidth = 10;

    // Выводим заголовки столбцов
    sb.append(String.format("%-" + columnWidth + "s", "")); // Пустой столбец для заголовка столбцов
    for (HeadNode innerKey : table.values().iterator().next().keySet()) {
      sb.append(String.format("%-" + columnWidth + "s", innerKey.toString()));
    }
    sb.append("\n");

    // Выводим строки таблицы
    for (HeadNode outerKey : table.keySet()) {
      sb.append(String.format("%-" + columnWidth + "s", outerKey.toString()));
      for (Node node : table.get(outerKey).values()) {
        sb.append(String.format("%-" + columnWidth + "s", node.toString()));
      }
      sb.append("\n");
    }

    return sb.toString();
  }
}
