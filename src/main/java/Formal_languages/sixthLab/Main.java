package Formal_languages.sixthLab;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        var automate = new Automate();
        automate.setupAlphabet();
        System.out.println("Введите кол-во вершин: ");
        automate.setupConditions(scanner.nextInt());
        automate.setupVertex("начальные");
        automate.setupVertex("конечные");
        automate.addVertexToGraph();
        automate.addEdgesToGraph();
        System.out.println(automate);
        System.out.println(automate.isDeterministic());
        GraphA graphA = new GraphA(automate.getDelta());
        automate.checkForReachable(graphA.bfs());
        automate.partition();
        automate.calculateNewDelta();
    }
}
