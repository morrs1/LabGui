package Formal_languages.sixthLab;

import java.util.Scanner;

public class Main {
  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    var automat = new Automat();
    automat.setupAlphabet();
    System.out.println("Введите кол-во вершин: ");
    automat.setupConditions(scanner.nextInt());
    automat.setupVertex("начальные");
    automat.setupVertex("конечные");
    automat.addVertexToGraph();
    automat.addEdgesToGraph();
    System.out.println(automat);
    System.out.println(automat.getDelta());
    System.out.println(automat.isDeterministic());
    GraphA graphA = new GraphA(automat.getDelta());
    System.out.println(graphA.bfs());

  }
}
