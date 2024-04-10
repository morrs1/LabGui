package Formal_languages.sixthLab;

import Formal_languages.fifthLab.NamedEdge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

import lombok.Getter;
import lombok.Setter;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;

@Getter
public class Automate {

    static Scanner scanner = new Scanner(System.in);
    private ArrayList<Character> alphabet = new ArrayList<>();
    private ArrayList<String> arrayConditions;
    private ArrayList<String> startV;
    private ArrayList<String> endV;
    private static final Graph<String, NamedEdge> graph = new DefaultDirectedGraph<>(NamedEdge.class);
    private Map<String, Map<String, Set<String>>> delta;
    private ArrayList<Set<String>> previousPartition;
    private ArrayList<Set<String>> currentPartition;

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

    public boolean isDeterministic() {
        for (var k : delta.keySet()) {
            for (var a : alphabet) {
                if (delta.get(k).get(a.toString()).size() > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public void checkForReachable(Set<String> reachable) {
        arrayConditions = new ArrayList<>(reachable);
        for (var k : delta.keySet()) {
            if (!reachable.contains(k)) {
                delta.remove(k);
            }
        }
        System.out.println(
                "Новое множество состояний:" + arrayConditions + "\n" + "Новая дельта:" + delta + "\n");
    }

    public ArrayList<Set<String>> partition() {
        previousPartition = new ArrayList<>();
        previousPartition.add(new LinkedHashSet<>(getNotEndSet()));
        previousPartition.add(new LinkedHashSet<>(endV));

        while (true) {
            System.out.println(deepCopy(previousPartition));
            currentPartition = newClassEquality(deepCopy(previousPartition));
            if (currentPartition.containsAll(previousPartition)) {
                break;
            }
            previousPartition = currentPartition;
        }
        return null;
    }

    public ArrayList<Set<String>> newClassEquality(ArrayList<Set<String>> previousPartition) {
        currentPartition = new ArrayList<>();
        for (var classEq : previousPartition) {

            ArrayList<Set<String>> arrayNewClassEq = new ArrayList<>();
            for (var symbol : alphabet) {
                var transitionsOfSymbol = new LinkedHashMap<String, Set<String>>();

                for (var vertex : classEq) {
                    var reachableVertex = String.join("", delta.get(vertex).get(symbol.toString()));
                    transitionsOfSymbol.put(vertex,
                            checkForBelongingToClassEq(reachableVertex, previousPartition));
                }
                System.out.printf("По символу %s " + transitionsOfSymbol + "\n", symbol.toString());
                var transitionsOfSymbol2 = new LinkedHashMap<Set<String>, Set<String>>();
                for (var vertex : classEq) {
                    if (!transitionsOfSymbol2.containsKey(transitionsOfSymbol.get(vertex))) {
                        var hs = new HashSet<String>();
                        hs.add(vertex);
                        transitionsOfSymbol2.put(transitionsOfSymbol.get(vertex), hs);
                    } else {
                        transitionsOfSymbol2.get(transitionsOfSymbol.get(vertex)).add(vertex);
                    }
                }

                var newClassEq = new HashSet<String>();
                if (!(transitionsOfSymbol2.keySet().size() == 1)) {
                    for (var key : transitionsOfSymbol2.keySet()) {
                        if (key != classEq) {
                            newClassEq = (HashSet<String>) transitionsOfSymbol2.get(key);

                        }
                    }
                }
                arrayNewClassEq.add(newClassEq);
            }
            HashSet<String> hs = new HashSet<>();
            if (arrayNewClassEq.stream().anyMatch(x -> !x.isEmpty())) {
                for (var c : arrayNewClassEq) {
                    if (!c.isEmpty()) {
                        for (var vertex : classEq) {
                            if (!c.contains(vertex)) {
                                hs.add(vertex);
                            }
                        }
                        if (!hs.isEmpty()) {
                            System.out.println(hs + " " + c);
                            currentPartition.add(hs);
                            currentPartition.add(c);
                        }
                    }
                }
            } else {
                System.out.println(classEq);
                currentPartition.add(classEq);
            }

        }
        System.out.println("\nНовое разбиение: " + currentPartition + "\n\n");
        return currentPartition;
    }

    private Set<String> checkForBelongingToClassEq(String vertex, ArrayList<Set<String>> partition) {
        for (var classEq : partition) {
            if (classEq.contains(vertex)) {
                return classEq;
            }
        }
        return null;
    }

    private ArrayList<Set<String>> deepCopy(ArrayList<Set<String>> previousPartition) {
        var currPar = new ArrayList<Set<String>>();
        for (var el : previousPartition) {
            currPar.add(new LinkedHashSet<>(el));
        }
        return currPar;
    }

    public Set<String> getNotEndSet() {
        var notEndSet = new LinkedHashSet<String>();
        for (var vertex : arrayConditions) {
            if (!endV.contains(vertex)) {
                notEndSet.add(vertex);
            }
        }
        return notEndSet;
    }

    public void calculateNewDelta() {
        var newDelta = new LinkedHashMap<String, Map<String, Set<String>>>();
        for (var classEq : currentPartition) {
            var arrayClassEq = new ArrayList<>(classEq);
            var nameNewClassEq = arrayClassEq.get(0);

            var map = new LinkedHashMap<String, Set<String>>();
            for (var a : alphabet) {
                var set = new LinkedHashSet<>(delta.get(nameNewClassEq).get(a.toString()));
                var newSet = set;
                for(var s : set){
                    for(var classEq2 : currentPartition){
                        if (classEq2.contains(s)){
                            newSet = new LinkedHashSet<>();
                            newSet.add(new ArrayList<>(classEq2).get(0));
                        }
                    }
                }
                map.put(a.toString(), newSet);
            }
            newDelta.put(nameNewClassEq, map);

        }
        System.out.println("Новая функция дельта(новый граф):" + newDelta);
    }

    @Override
    public String toString() {
        return "Automate{" +
                "alphabet=" + alphabet +
                ", arrayConditions=" + arrayConditions +
                ", startV=" + startV +
                ", endV=" + endV +
                ", delta=" + delta +
                '}';
    }


}
