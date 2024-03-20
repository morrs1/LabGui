package Formal_languages.fifthLab;

import org.jgrapht.Graph;

import org.jgrapht.graph.SimpleDirectedGraph;

public class Main {
  public static void main(String[] args) {
    // Создание ориентированного графа с именованными ребрами
    Graph<String, NamedEdge> graph = new SimpleDirectedGraph<>(NamedEdge.class);

    // Добавление вершин
    String v1 = "v1";
    String v2 = "v2";
    graph.addVertex(v1);
    graph.addVertex(v2);

    // Добавление ориентированного ребра с именем "а"
    graph.addEdge(v1, v2, new NamedEdge(String.format("%s -> %s (a)", v1, v2)));

    // Вывод информации о графе
    System.out.println("Вершины графа: " + graph.vertexSet());
    System.out.println("Ребра графа: " + graph.edgeSet());

//// Создание алфавита
//    Alphabet<Character> alphabet = Alphabets.characters('a', 'b');
//
//    // Создание автомата
//    DFA<?, Character> dfa = Automaton.newDFA(alphabet)
//        .withInitial("q0")
//        .from("q0")
//        .on('a').to("q1")
//        .from("q1")
//        .on('a').to("q1")
//        .on('b').to("q2")
//        .from("q2")
//        .on('b').to("q2")
//        .withAccepting("q2")
//        .create();
//
//    // Вывод автомата
//    System.out.println("Конечный автомат: " + dfa);

  }
}