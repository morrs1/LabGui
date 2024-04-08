package Formal_languages.sixthLab;

import Formal_languages.fifthLab.NamedEdge;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;

public class Automat {
  static Scanner scanner = new Scanner(System.in);
  private ArrayList<Character> alphabet = new ArrayList<>();
  private ArrayList<String> arrayConditions;
  private ArrayList<String> startV;
  private ArrayList<String> endV;
  private static final Graph<String, NamedEdge> graph = new DefaultDirectedGraph<>(NamedEdge.class);
  private Map<String, Map<String, Set<String>>> delta;

  public ArrayList<Character> getAlphabet() {
    return alphabet;
  }

  public ArrayList<String> getStartV() {
    return startV;
  }

  public ArrayList<String> getEndV() {
    return endV;
  }

  public ArrayList<String> getArrayConditions() {
    return arrayConditions;
  }

  public Map<String, Map<String, Set<String>>> getDelta() {
    return delta;
  }

  public void setupAlphabet() {
    System.out.println("Введите алфавит: ");
    Set<Character> alphabet = new LinkedHashSet<>();
    String str = scanner.nextLine();
    while (!str.equals("exit")) {
      for (char c : str.toCharArray()) {
        if (c != ' ') {
          alphabet.add(c);
        }
      }
      str = scanner.nextLine();
    }
    this.alphabet = new ArrayList<>(alphabet);
  }

  public void setupConditions(Integer amount) {
    var arr = new ArrayList<String>();
    IntStream.range(0, amount).forEach(x -> arr.add(String.format("v%d", x)));
    this.arrayConditions = arr;
  }

  public void setupVertex(String buf) {
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
      if (!arrayConditions.contains(str)) {
        System.out.println("Такой вершины нет во множестве вершин, введите заново: ");
      } else if (!arrayV.contains(str)) {
        arrayV.add(str);
      }
    }
    if (Objects.equals(buf, "начальные")) {
      this.startV = arrayV;
    } else {
      this.endV = arrayV;
    }
  }

  public void addVertexToGraph() {
    arrayConditions.forEach(graph::addVertex);
  }

  public void addEdgesToGraph() {
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
    this.delta = setupTable(inf);
  }

  public Map<String, Map<String, Set<String>>> setupTable(ArrayList<String[]> inf) {
    var table = new LinkedHashMap<String, Map<String, Set<String>>>();
    inf.forEach(x -> {
      Map<String, Set<String>> innerMap = table.computeIfAbsent(x[0],
          k -> new LinkedHashMap<>());
      Set<String> states = innerMap.computeIfAbsent(x[2], k -> new LinkedHashSet<>());
      states.add(x[1]);
    });

    return table;
  }

  @Override
  public String toString() {
    return "Automat{" +
        "alphabet=" + alphabet +
        ", arrayConditions=" + arrayConditions +
        ", startV=" + startV +
        ", endV=" + endV +
        ", delta=" + delta +
        '}';
  }

  public boolean isDeterministic(){
    for(var k: delta.keySet()){
      for(var a: alphabet){
        if (delta.get(k).get(a.toString()).size() > 1){
          return false;
        }
      }
    }
    return true;
  }

}
