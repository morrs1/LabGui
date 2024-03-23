package Formal_languages.fifthLab;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import org.checkerframework.checker.units.qual.A;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main extends Formal_languages.firstLab.Main {

  static Scanner scanner = new Scanner(System.in);
  private static final Graph<String, NamedEdge> graph = new DefaultDirectedGraph<>(NamedEdge.class);
  private static ArrayList<Character> alphabet;
  private static ArrayList<String> arrConditions;

  public static void main(String[] args) {
    alphabet = setupAlphabet();
    System.out.println("Введите кол-во вершин: ");
    arrConditions = setupConditions(scanner.nextInt());
    var arrStart = setupVertex(arrConditions, "начальные");
    var arrEnd = setupVertex(arrConditions, "конечные");

    // Добавление вершин
    addVertexToGraph(arrConditions);
    var rr = addEdgesToGraph();
    System.out.println(rr);

    // Вывод информации о графе
    System.out.println("Вершины графа:\n" + graph.vertexSet());
    System.out.println("Граф:\n" + String.join("\n",
        graph.edgeSet().toString().replace("]", "").replace("[", "").split(" , ")));
    System.out.println(
        "Начальные:\n" + arrStart + "\nКонечные:\n" + arrEnd + "\nАлфавит: " + alphabet);
    System.out.println("Эпсилон-замыкания: " + calculateEpsilonClosure(rr));

  }

  private static ArrayList<String> setupConditions(Integer amount) {
    var arr = new ArrayList<String>();
    IntStream.range(0, amount).forEach(x -> arr.add(String.format("v%d", x)));
    return arr;
  }

  private static void addVertexToGraph(ArrayList<String> arrV) {
    arrV.forEach(graph::addVertex);
  }

  private static ArrayList<String> setupVertex(ArrayList<String> arrV, String buf) {
    var arrayV = new ArrayList<String>();
    System.out.printf("Введите %s вершины: %n", buf);

    while (true) {
      String str = scanner.nextLine();
      if (str.equals("exit")) {
        break;
      }

      if(str.isEmpty()) continue;
      if (!arrV.contains(str)) {
        System.out.println("Такой вершины нет во множестве вершин, введите заново: ");
      } else if (!arrayV.contains(str)) {
        arrayV.add(str);
      }


    }

    return arrayV;
  }

  private static Map<String, Map<String, Set<String>>> addEdgesToGraph() {
    System.out.println("Введите дуги графа(ИСХ вершина ВХД вершина символ алфавита)");
    var input = scanner.nextLine();
    var inf = new ArrayList<String[]>();
    while (!input.equals("exit")) {
      var info = input.split(" ");
      inf.add(info);
      graph.addEdge(info[0], info[1],
          new NamedEdge(String.format("(%s, %s) -> %s ", info[0], info[2], info[1])));
      input = scanner.nextLine();
    }
    return setupTable(inf);
  }

  private static Map<String, Map<String, Set<String>>> setupTable(ArrayList<String[]> inf) {
    var table = new LinkedHashMap<String, Map<String, Set<String>>>();

    inf.forEach(x -> {
      Map<String, Set<String>> innerMap = table.computeIfAbsent(x[0],
          k -> new LinkedHashMap<>());
      Set<String> states = innerMap.computeIfAbsent(x[2], k -> new HashSet<>());
      states.add(x[1]);
    });

    return table;
  }


  private static Map<String, Set<String>> calculateEpsilonClosure(
      Map<String, Map<String, Set<String>>> graph) {

    // Создаем пустую карту для хранения эпсилон-замыканий
    Map<String, Set<String>> closures = new HashMap<>();

    // Проходим по всем узлам графа
    for (String vertex : graph.keySet()) {

      // Создаем пустое множество для эпсилон-замыкания текущего узла
      Set<String> closure = new HashSet<>();

      // Добавляем сам узел в его эпсилон-замыкание
      closure.add(vertex);

      // Рекурсивно строим эпсилон-замыкание
      calculateEpsilonClosureHelper(graph, vertex, closure);

      // Сохраняем эпсилон-замыкание для текущего узла
      closures.put(vertex, closure);
    }

    // Возвращаем карту с эпсилон-замыканиями
    return closures;
  }

  // Вспомогательная функция для рекурсивного построения эпсилон-замыкания
  private static void calculateEpsilonClosureHelper(Map<String, Map<String, Set<String>>> graph,
      String currentVertex, Set<String> closure) {

    // Проверяем, есть ли у текущего узла исходящие эпсилон-переходы
    if (graph.get(currentVertex).containsKey("ε")) {

      // Проходим по всем узлам, достижимым по эпсилон-переходам
      for (String epsilonTarget : graph.get(currentVertex).get("ε")) {

        // Если узел еще не в эпсилон-замыкании, добавляем его
        if (!closure.contains(epsilonTarget)) {
          closure.add(epsilonTarget);

          // Рекурсивно строим эпсилон-замыкание для найденного узла
          calculateEpsilonClosureHelper(graph, epsilonTarget, closure);
        }
      }
    }
  }


}

