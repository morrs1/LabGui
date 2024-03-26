package Formal_languages.fifthLab;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
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
    alphabet = setupAlphabet();
    System.out.println("Введите кол-во вершин: ");
    arrConditions = setupConditions(scanner.nextInt());
    var arrS = setupVertex(arrConditions, "начальные");

    var arrEnd = setupVertex(arrConditions, "конечные");

    // Добавление вершин
    addVertexToGraph(arrConditions);
    var mainGraph = addEdgesToGraph();
    System.out.println(mainGraph);
    var arrStart = checkStart(arrS, mainGraph);
    // Вывод информации о графе
    System.out.println("Вершины графа:\n" + graph.vertexSet());
    System.out.println("Граф:\n" + String.join("\n",
        graph.edgeSet().toString().replace("]", "").replace("[", "").split(" , ")));
    System.out.println(
        "Начальные:\n" + arrStart + "\nКонечные:\n" + arrEnd + "\nАлфавит: " + alphabet);
    var epsilonClosure = calculateEpsilonClosure(mainGraph);
    System.out.println("Эпсилон-замыкания: " + epsilonClosure);

//    Map<String, Set<String>> epsilonClosure = new HashMap<>();
//    Set<String> v0Set = new HashSet<>();
//    v0Set.add("v0");
//    v0Set.add("v3");
//    epsilonClosure.put("v0", v0Set);
//    Set<String> v1Set = new HashSet<>();
//    v1Set.add("v1");
//    v1Set.add("v2");
//    epsilonClosure.put("v1", v1Set);
//    Set<String> v2Set = new HashSet<>();
//    v2Set.add("v2");
//    epsilonClosure.put("v2", v2Set);
//    Set<String> v3Set = new HashSet<>();
//    v3Set.add("v3");
//    epsilonClosure.put("v3", v3Set);
//
//    Map<String, Map<String, Set<String>>> mainGraph = new HashMap<>();
//    Map<String, Set<String>> v0Map = new HashMap<>();
//    v0Map.put("a", new HashSet<>(Set.of("v1")));
//    v0Map.put("b", new HashSet<>(Set.of("v0")));
//    v0Map.put("ε", new HashSet<>(Set.of("v3")));
//    mainGraph.put("v0", v0Map);
//    Map<String, Set<String>> v1Map = new HashMap<>();
//    v1Map.put("c", new HashSet<>(Set.of("v3")));
//    v1Map.put("ε", new HashSet<>(Set.of("v2")));
//    mainGraph.put("v1", v1Map);
//    Map<String, Set<String>> v2Map = new HashMap<>();
//    v2Map.put("a", new HashSet<>(Set.of("v3")));
//    v2Map.put("b", new HashSet<>(Set.of("v1")));
//    mainGraph.put("v2", v2Map);
//    Map<String, Set<String>> v3Map = new HashMap<>();
//    v3Map.put("c", new HashSet<>(Set.of("v3")));
//    mainGraph.put("v3", v3Map);
//    var arrStart = new ArrayList<String>();
//    arrStart.add("v0");
//    arrStart.add("v3");
//    var arrEnd = new ArrayList<String>();
//    arrEnd.add("v3");

//
//    System.out.println("Граф:\n" + mainTable);
//    System.out.println("\nЭпсилон-замыкания:\n" + epsilonClosure);
//
//    Map<String, Map<String, Set<String>>> bagaTransitions = new HashMap<>();
//
//// Transitions for v0
//    Map<String, Set<String>> v0Transitions = new HashMap<>();
//    v0Transitions.put("a", Set.of("v1"));
//    v0Transitions.put("b", Set.of("v0"));
//    v0Transitions.put("ε", Set.of("v3"));
//    bagaTransitions.put("v0", v0Transitions);
//
//// Transitions for v1
//    Map<String, Set<String>> v1Transitions = new HashMap<>();
//    v1Transitions.put("c", Set.of("v3"));
//    v1Transitions.put("ε", Set.of("v2"));
//    bagaTransitions.put("v1", v1Transitions);
//
//// Transitions for v2
//    Map<String, Set<String>> v2Transitions = new HashMap<>();
//    v2Transitions.put("a", Set.of("v3"));
//    v2Transitions.put("b", Set.of("v1"));
//    bagaTransitions.put("v2", v2Transitions);
//
//// Transitions for v3
//    Map<String, Set<String>> v3Transitions = new HashMap<>();
//    v3Transitions.put("c", Set.of("v3"));
//    bagaTransitions.put("v3", v3Transitions);

    var calcSTable = calculateSTable(mainGraph, epsilonClosure);

    var calcSTable1 = new LinkedHashMap<>(calcSTable);
    var bufMap = new LinkedHashMap<Set<String>, String>();
    var cc = 0;
    for (var cs : calcSTable1.keySet()) {
      bufMap.put(cs, String.format("S%d", cc));
      cc += 1;
    }

    var bufMap1 = new LinkedHashMap<String, Set<String>>();
    var cc1 = 0;
    for (var cs1 : calcSTable1.keySet()) {
      bufMap1.put(String.format("S%d", cc1), cs1);
      cc1 += 1;
    }

    transformDictionary(calcSTable, bufMap);
    System.out.println("\nS-таблица:\n" + calcSTable);

    System.out.println(bufMap1);
//    System.out.println(bufMap);
    var startVertexS = castStartEnd(bufMap1, arrStart);
    var endVertexS = castStartEnd(bufMap1, arrEnd);
    System.out.println("Начальные S вершины" + startVertexS);
    System.out.println("Конечные S вершины" + endVertexS);

    var firstPTable = calcP(calcSTable, startVertexS);

//    var firstPTable = calculatePTable(calcSTable, startVertexS);
//
    var bufMap3 = new LinkedHashMap<Set<String>, String>();
    var cc2 = 0;
    for (var pt : firstPTable.keySet()) {
      bufMap3.put(pt, String.format("P%d", cc2));
      cc2 += 1;
    }
    var bufMap4 = new LinkedHashMap<String, Set<String>>();
    var cc3 = 0;
    for (var pt : firstPTable.keySet()) {
      bufMap4.put(String.format("P%d", cc3), pt);
      cc3 += 1;
    }
//    System.out.println(firstPTable);
//    System.out.println( firstPTable + "\n\n");\
    var pTable = transformPString(firstPTable, bufMap3);
    System.out.println("\nP-таблица:\n" + pTable);
    System.out.println(bufMap3);
    var startVertexP = castStartEnd(bufMap4, new ArrayList<>(startVertexS));
    var endVertexP = castStartEnd(bufMap4, new ArrayList<>(endVertexS));
    System.out.println("Начальные P вершины" + startVertexP);
    System.out.println("Конечные P вершины" + endVertexP);

    checkWord(pTable, startVertexP, endVertexP);

  }

  private static ArrayList<String> checkStart(ArrayList<String> arrS,
      Map<String, Map<String, Set<String>>> mainT) {
    var arrStart = new ArrayList<>(arrS);
    var curr = arrS.get(0);
    while (true) {
      if (mainT.get(curr).containsKey("ε")) {
        if(!arrStart.contains(mainT.get(curr).get("ε").toString().replace("[", "").replace("]",""))){
          arrStart.add(mainT.get(curr).get("ε").toString().replace("[", "").replace("]",""));
        }
        curr = mainT.get(curr).get("ε").toString().replace("[", "").replace("]","");
      } else {
        break;
      }

    }
return arrStart;

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
      Set<String> states = innerMap.computeIfAbsent(x[2], k -> new LinkedHashSet<>());
      states.add(x[1]);
    });

    return table;
  }


  private static Map<String, Set<String>> calculateEpsilonClosure(
      Map<String, Map<String, Set<String>>> graph) {
    Map<String, Set<String>> closures = new LinkedHashMap<>();

    // Iterate over all vertices in the graph
    for (String vertex : graph.keySet()) {
      Set<String> closure = new LinkedHashSet<>();
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

    Map<Set<String>, Map<String, Set<String>>> sTable = new LinkedHashMap<>();

    // Итерируем по всем эпсилон-замыканиям
    for (Set<String> closure : epsilonClosure.values()) {
      Map<String, Set<String>> transitions = new LinkedHashMap<>();

      // Итерируем по всем символам алфавита
      for (Character character : alphabet) {
        Set<String> reachableStates = new LinkedHashSet<>();

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

    var hs = new LinkedHashSet<String>();
    Map<Set<String>, Map<String, Set<String>>> newDict1 = new LinkedHashMap<>();

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

    // Создание нового словаря с измененными ключами
    for (var fL : dict1.keySet()) {
      var bb = dict2.get(fL);
      // Создание нового LinkedHashSet с bb в качестве единственного элемента
      Set<String> newKey = new LinkedHashSet<>();
      newKey.add(bb);
      // Добавление нового ключа и соответствующего значения в новый словарь
      newDict1.put(newKey, dict1.get(fL));
    }

    // Замена старого словаря новым
    dict1.clear();
    dict1.putAll(newDict1);
  }

  private static LinkedHashSet<String> castStartEnd(LinkedHashMap<String, Set<String>> bufMap1,
      ArrayList<String> arr) {
    var startVertexS = new LinkedHashSet<String>();
    for (var bm : bufMap1.keySet()) {
      for (var vv : bufMap1.get(bm)) {
        if (arr.contains(vv)) {
          startVertexS.add(bm);
        }
      }
    }
    return startVertexS;
  }

  private static Map<Set<String>, Map<String, Set<String>>> calculatePTable(
      Map<Set<String>, Map<String, Set<String>>> sTable, Set<String> startSet) {

    var mainMap = new LinkedHashMap<Set<String>, Map<String, Set<String>>>();
    Map<String, Map<String, Set<String>>> Stable = transformKeysToString(sTable);
//    System.out.println(Stable + "\n");

    for (var a : alphabet) {
      var hs = new LinkedHashSet<String>();
      for (var fV : startSet) {
        hs.addAll(Stable.get(fV).get(a.toString()));
      }
      var notMainMap = new LinkedHashMap<String, Set<String>>();
      notMainMap.put(a.toString(), hs);

      if (mainMap.containsKey(startSet)) {
        mainMap.get(startSet).putAll(notMainMap);
      } else {
        mainMap.put(startSet, notMainMap);
      }

    }
//    System.out.println("mm" + mainMap);

    var mainMap2 = new LinkedHashMap<Set<String>, Map<String, Set<String>>>(mainMap);
    int countOfP;
    do {
      countOfP = mainMap.keySet().size();
      for (var v : mainMap.keySet()) {
        for (var a : alphabet) {
          if (!mainMap.containsKey(mainMap.get(v).get(a.toString()))) {
            mainMap2.put(mainMap.get(v).get(a.toString()), new LinkedHashMap<>());
          }
        }
      }

    }
    while (countOfP != mainMap.keySet().size());

//    System.out.println("mm2" + mainMap2);

    for (var k : mainMap2.keySet()) {
      if (mainMap2.get(k).isEmpty()) {

        for (var a : alphabet) {
          var hs = new LinkedHashSet<String>();
          for (var v : k) {
            hs.addAll(Stable.get(v).get(a.toString()));
          }
          var notMainMap = new LinkedHashMap<String, Set<String>>();
          notMainMap.put(a.toString(), hs);

          mainMap2.get(k).putAll(notMainMap);

        }
      }
    }

//    System.out.println("mm2.2" + mainMap2);
    var mainMap3 = new LinkedHashMap<>(mainMap2);
    for (var b : mainMap2.keySet()) {
      for (var k : mainMap2.get(b).values()) {
        if (!mainMap2.containsKey(k)) {
          if (!k.isEmpty()) {
            mainMap3.put(k, new LinkedHashMap<>());
          }
        }
      }
    }
    //System.out.println("mm3" + mainMap3);

    for (var m : mainMap3.keySet()) {
      if (mainMap3.get(m).isEmpty()) {
        for (var a : alphabet) {
          mainMap3.get(m).put(a.toString(), sTable.get(m).get(a.toString()));
        }
      }
    }

    return mainMap3;
  }


  private static Map<String, Map<String, Set<String>>> transformKeysToString(
      Map<Set<String>, Map<String, Set<String>>> originalSet) {
    Map<String, Map<String, Set<String>>> transformedSet = new LinkedHashMap<>();

    originalSet.forEach((keySet, valueMap) -> {
      // Преобразование множества ключей в строку
      String keyString = String.join(", ", keySet);
      transformedSet.put(keyString, valueMap);
    });

    return transformedSet;
  }


  private static Map<String, Map<String, String>> transformPString(
      Map<Set<String>, Map<String, Set<String>>> pTable,
      LinkedHashMap<Set<String>, String> bufMap) {
    Map<String, Map<String, String>> resultMap = new LinkedHashMap<>();
    for (Map.Entry<Set<String>, Map<String, Set<String>>> entry : pTable.entrySet()) {
      String key = bufMap.get(entry.getKey()); // Преобразование ключа в строку
      Map<String, String> innerMap = new LinkedHashMap<>();
      for (Map.Entry<String, Set<String>> innerEntry : entry.getValue().entrySet()) {
        String innerKey = innerEntry.getKey();
        String innerValue = bufMap.get(innerEntry.getValue()); // Преобразование значения в строку
        innerMap.put(innerKey, innerValue);
      }
      resultMap.put(key, innerMap);
    }
    return resultMap;
  }

  private static Map<Set<String>, Map<String, Set<String>>> calcP(
      Map<Set<String>, Map<String, Set<String>>> sTable, Set<String> startSet) {

    var firstPTable = new LinkedHashMap<Set<String>, Map<String, Set<String>>>();
    var sTable1 = transformKeysToString(sTable);

    Map<String, Set<String>> mm = calculateNodeOfPTable(startSet, sTable1);

    var queue = new LinkedList<Set<String>>();
    queue.add(startSet);
    queue.addAll(mm.values());
//    System.out.println(mm + " f");
//    System.out.println(queue);

    while (!queue.isEmpty()) {
      var q = queue.poll();
      mm = calculateNodeOfPTable(q, sTable1);
      if (!firstPTable.containsKey(q)) {
        firstPTable.put(q, mm);
      }
      for (var m : mm.values()) {
        if (!firstPTable.containsKey(m) && !m.isEmpty()) {
          queue.add(m);
        }
      }

//      System.out.println(mm);

    }
    return firstPTable;
  }

  private static Map<String, Set<String>> calculateNodeOfPTable(Set<String> set,
      Map<String, Map<String, Set<String>>> sTable) {

    var map = new LinkedHashMap<String, Set<String>>();

    for (var a : alphabet) {
      for (var s : set) {
        var curentSet = sTable.get(s).get(a.toString());
        if (map.containsKey(a.toString()) && !curentSet.isEmpty()) {
          map.get(a.toString()).addAll(curentSet);
        } else if (!map.containsKey(a.toString())) {
          map.put(a.toString(), curentSet);
        }
//        System.out.println(a + " " +sTable.get(s).get(a.toString()));
      }
    }
    return map;
  }

  private static void checkWord(Map<String, Map<String, String>> pTable,
      LinkedHashSet<String> startV, LinkedHashSet<String> endV) {

    while (true) {
      System.out.println("Введите строку, которую хотите проверить: ");
      var str = scanner.nextLine();
      if (str.equals("exit")) {
        break;
      }
      var queue = new LinkedList<>(Arrays.asList(str.split("")));
      var flag = false;

      for (var s : startV) {
        var curr = s;
        while (!queue.isEmpty()) {
          var q = queue.poll();
          var nextV = pTable.get(curr).get(q);
          if (endV.contains(nextV) && queue.isEmpty()) {
            flag = true;
          }
          curr = nextV;
        }
      }
      if (flag) {
        System.out.println("Строка подходит");
      } else {
        System.out.println("Строка не подходит");
      }

    }

  }
}

