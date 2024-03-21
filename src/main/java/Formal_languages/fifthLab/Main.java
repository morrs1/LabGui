package Formal_languages.fifthLab;

import org.jgrapht.Graph;

import org.jgrapht.graph.SimpleDirectedGraph;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main extends Formal_languages.firstLab.Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        var alphabet = setupAlphabet();
        System.out.println("Введите кол-во вершин: ");
        var arrConditions = setupConditions(scanner.nextInt());
        var arrStart = addVertex(arrConditions, "начальные");
        var arrEnd = addVertex(arrConditions, "конечные");

        // Создание ориентированного графа с именованными ребрами
        Graph<String, NamedEdge> graph = new SimpleDirectedGraph<>(NamedEdge.class);

        // Добавление вершин
        addVertexToGraph(arrConditions, graph);

        // Добавление ориентированного ребра с именем "а"
        graph.addEdge(arrConditions.get(1), arrConditions.get(2), new NamedEdge(String.format("(%s, a) -> %s ", arrConditions.get(1), arrConditions.get(2))));

        // Вывод информации о графе
        System.out.println("Вершины графа: " + graph.vertexSet());
        System.out.println("Ребра графа: " + graph.edgeSet());
        System.out.println(alphabet + "\n" + arrConditions + "\n Начальные:" + arrStart + "\n Конечные:" + arrEnd);


    }

    private static ArrayList<String> setupConditions(Integer amount) {
        var arr = new ArrayList<String>();
        IntStream.range(0, amount).forEach(x -> arr.add(String.format("v%d", x)));
        return arr;
    }

    private static void addVertexToGraph(ArrayList<String> arrV, Graph<String, NamedEdge> graph) {
        arrV.forEach(graph::addVertex);
    }

    private static ArrayList<String> addVertex(ArrayList<String> arrV, String buf) {
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
}