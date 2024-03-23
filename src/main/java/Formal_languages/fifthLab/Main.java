package Formal_languages.fifthLab;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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
    private static  ArrayList<Character> alphabet;
    private static ArrayList<String> arrConditions;
    public static void main(String[] args) {
        alphabet = setupAlphabet();
        System.out.println("Введите кол-во вершин: ");
         arrConditions = setupConditions(scanner.nextInt());
        var arrStart = setupVertex(arrConditions, "начальные");
        var arrEnd = setupVertex(arrConditions, "конечные");

        // Добавление вершин
        addVertexToGraph(arrConditions);

        System.out.println(addEdgesToGraph());

        // Вывод информации о графе
        System.out.println("Вершины графа:\n" + graph.vertexSet());
        System.out.println("Граф:\n" + String.join("\n", graph.edgeSet().toString().replace("]","").replace("[", "").split(" , ")));
        System.out.println("Начальные:\n" + arrStart + "\nКонечные:\n" + arrEnd + "\nАлфавит: " + alphabet);


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
        String str = scanner.nextLine();
        while (!str.equals("exit")) {
            while (!arrV.contains(str)) {
                System.out.println("Такой вершины нет во множестве вершин, введите заново: ");
                str = scanner.nextLine();
                if(str.equals("exit")) return arrayV;
            }
            if(!arrayV.contains(str)) arrayV.add(str);
            str = scanner.nextLine();
        }
        return arrayV;
    }

    private static Map<String,Map<String, Set<String>>> addEdgesToGraph(){
        System.out.println("Введите дуги графа(ИСХ вершина ВХД вершина символ алфавита)");
        var input = scanner.nextLine();
        var inf = new ArrayList<String[]>();
        while (!input.equals("exit")){
            var info = input.split(" ");
            inf.add(info);
            graph.addEdge(info[0], info[1], new NamedEdge(String.format("(%s, %s) -> %s ", info[0],info[2] ,info[1])));
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
    public static Set<String> findEpsilonClosure(Map<String, Map<String, Set<String>>> graph, String startVertex) {
        Set<String> closure = new HashSet<>();
        Stack<String> stack = new Stack<>();

        closure.add(startVertex);
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            String currentVertex = stack.pop();

            if (graph.get(currentVertex).containsKey("")) {
                for (String nextVertex : graph.get(currentVertex).get("")) {
                    if (!closure.contains(nextVertex)) {
                        closure.add(nextVertex);
                        stack.push(nextVertex);
                    }
                }
            }
        }

        return closure;
    }
}

