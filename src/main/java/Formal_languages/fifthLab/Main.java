package Formal_languages.fifthLab;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
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
  private static ArrayList<Character> alphabet = new ArrayList<>(Arrays.asList('a', 'b', 'c'));
  private static ArrayList<String> arrConditions;

  public static void main(String[] args) {
//    alphabet = setupAlphabet();
//    System.out.println("Введите кол-во вершин: ");
//    arrConditions = setupConditions(scanner.nextInt());
//    var arrStart = setupVertex(arrConditions, "начальные");
//    var arrEnd = setupVertex(arrConditions, "конечные");
//
//    // Добавление вершин
//    addVertexToGraph(arrConditions);
//    var mainGraph = addEdgesToGraph();
//    System.out.println(mainGraph);
//
//    // Вывод информации о графе
//    System.out.println("Вершины графа:\n" + graph.vertexSet());
//    System.out.println("Граф:\n" + String.join("\n",
//        graph.edgeSet().toString().replace("]", "").replace("[", "").split(" , ")));
//    System.out.println(
//        "Начальные:\n" + arrStart + "\nКонечные:\n" + arrEnd + "\nАлфавит: " + alphabet);
//    var epsilonClosure = calculateEpsilonClosure(mainGraph);
//    System.out.println("Эпсилон-замыкания: " + epsilonClosure);
//    System.out.println(calculateSTable(mainGraph, epsilonClosure));

    Map<String, Set<String>> epsilonClosure = new HashMap<>();
    Set<String> v0Set = new HashSet<>();
    v0Set.add("v0");
    v0Set.add("v3");
    epsilonClosure.put("v0", v0Set);
    Set<String> v1Set = new HashSet<>();
    v1Set.add("v1");
    v1Set.add("v2");
    epsilonClosure.put("v1", v1Set);
    Set<String> v2Set = new HashSet<>();
    v2Set.add("v2");
    epsilonClosure.put("v2", v2Set);
    Set<String> v3Set = new HashSet<>();
    v3Set.add("v3");
    epsilonClosure.put("v3", v3Set);

    Map<String, Map<String, Set<String>>> mainTable = new HashMap<>();
    Map<String, Set<String>> v0Map = new HashMap<>();
    v0Map.put("a", new HashSet<>(Set.of("v1")));
    v0Map.put("b", new HashSet<>(Set.of("v0")));
    v0Map.put("ε", new HashSet<>(Set.of("v3")));
    mainTable.put("v0", v0Map);
    Map<String, Set<String>> v1Map = new HashMap<>();
    v1Map.put("c", new HashSet<>(Set.of("v3")));
    v1Map.put("ε", new HashSet<>(Set.of("v2")));
    mainTable.put("v1", v1Map);
    Map<String, Set<String>> v2Map = new HashMap<>();
    v2Map.put("a", new HashSet<>(Set.of("v3")));
    v2Map.put("b", new HashSet<>(Set.of("v1")));
    mainTable.put("v2", v2Map);
    Map<String, Set<String>> v3Map = new HashMap<>();
    v3Map.put("c", new HashSet<>(Set.of("v3")));
    mainTable.put("v3", v3Map);

    System.out.println(mainTable);
    System.out.println(epsilonClosure);

    Map<String, Map<String, Set<String>>> bagaTransitions = new HashMap<>();

// Transitions for v0
    Map<String, Set<String>> v0Transitions = new HashMap<>();
    v0Transitions.put("a", Set.of("v1"));
    v0Transitions.put("b", Set.of("v0"));
    v0Transitions.put("ε", Set.of("v3"));
    bagaTransitions.put("v0", v0Transitions);

// Transitions for v1
    Map<String, Set<String>> v1Transitions = new HashMap<>();
    v1Transitions.put("c", Set.of("v3"));
    v1Transitions.put("ε", Set.of("v2"));
    bagaTransitions.put("v1", v1Transitions);

// Transitions for v2
    Map<String, Set<String>> v2Transitions = new HashMap<>();
    v2Transitions.put("a", Set.of("v3"));
    v2Transitions.put("b", Set.of("v1"));
    bagaTransitions.put("v2", v2Transitions);

// Transitions for v3
    Map<String, Set<String>> v3Transitions = new HashMap<>();
    v3Transitions.put("c", Set.of("v3"));
    bagaTransitions.put("v3", v3Transitions);

    var calcSTable = calculateSTable(mainTable, epsilonClosure);
    System.out.println(calcSTable);
    var bufMap = new HashMap<Set<String>, String>();
    var cc = 0;
    for (var cs : calcSTable.keySet()) {
      bufMap.put(cs, String.format("S%d", cc));
      cc += 1;
    }

    System.out.println("\n" + bufMap);
    transformDictionary(calcSTable, bufMap);
    System.out.println(calcSTable);
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

      if (str.isEmpty()) {
        continue;
      }
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
    Map<String, Set<String>> closures = new HashMap<>();

    // Iterate over all vertices in the graph
    for (String vertex : graph.keySet()) {
      Set<String> closure = new HashSet<>();
      closure.add(vertex);
      calculateEpsilonClosureHelper(graph, vertex, closure);
      closures.put(vertex, closure); // Store closure for each vertex
    }

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


  private static Map<Set<String>, Map<String, Set<String>>> calculateSTable(
      Map<String, Map<String, Set<String>>> mainTable, Map<String, Set<String>> epsilonClosure) {

    Map<Set<String>, Map<String, Set<String>>> sTable = new HashMap<>();

    // Итерируем по всем эпсилон-замыканиям
    for (Set<String> closure : epsilonClosure.values()) {
      Map<String, Set<String>> transitions = new HashMap<>();

      // Итерируем по всем символам алфавита
      for (Character character : alphabet) {
        Set<String> reachableStates = new HashSet<>();

        // Для каждой вершины в замыкании
        for (String vertex : closure) {
          // Проверяем, есть ли переход по символу из этой вершины
          if (mainTable.get(vertex).containsKey(character.toString())) {
            // Добавляем достижимые вершины в множество
            reachableStates.addAll(mainTable.get(vertex).get(character.toString()));
          }
        }

        // Добавляем эпсилон-замыкания для всех достижимых вершин
        for (String state : reachableStates) {
          reachableStates.addAll(epsilonClosure.get(state));
        }

        // Добавляем переход по символу в таблицу S
        transitions.put(character.toString(), reachableStates);
      }

      // Добавляем строку в таблицу S для текущего эпсилон-замыкания
      sTable.put(closure, transitions);
    }

    return sTable;
  }


  public static void transformDictionary(
      Map<Set<String>, Map<String, Set<String>>> dict1,
      Map<Set<String>, String> dict2) {

    var hs = new HashSet<String>();
    for (var fL : dict1.keySet()) {
      for (var sL : dict1.get(fL).keySet()) {
        for (var tL : dict2.keySet()) {
          if (dict1.get(fL).get(sL).containsAll(tL)) {
            hs.add(dict2.get(tL));
          }
        }
        dict1.get(fL).get(sL).clear();
        dict1.get(fL).get(sL).addAll(hs);
        hs.clear();
      }
    }

  }


}

