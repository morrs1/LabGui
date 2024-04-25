package Formal_languages.seventhLab;

import Programming.FourthSemLab.FifthLab.Pair;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Grammar grammar = setupGrammar();
        System.out.println(grammar);
        if (grammar.isRLG()){
            System.out.println("Тип 3: Грамматика праволинейная!");
        } else if (grammar.isLLG()) {

            System.out.println("Тип 3: Грамматика леволинейная!");
        } else if (grammar.isCS()) {

            System.out.println("Тип 2: Грамматика контекстно-свободная!");
        } else if (grammar.isCZ()) {

            System.out.println("Тип 1: Грамматика контекстно-зависимая!");
        } else if (grammar.isNU()) {

            System.out.println("Тип 1: Грамматика неукорачивающая!");
        }else {
            System.out.println("Грамматика типа 0!");
        }
    }

    private static Grammar setupGrammar() {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите правила грамматики(Например: A-abA)");
        var str = input.nextLine();
        ArrayList<Pair<String, String>> rules = new ArrayList<>();
        while (!Objects.equals(str, "exit")) {
            Pair<String, String> p = new Pair<>(str.split("-")[0], str.split("-")[1]);
            rules.add(p);
            str = input.nextLine();
        }


        System.out.println("Введите терминальные символы грамматики");
        var str1 = input.nextLine();
        ArrayList<String> terms = new ArrayList<>();
        while (!Objects.equals(str1, "exit")) {
            terms.add(str1);
            str1 = input.nextLine();
        }


        System.out.println("Введите нетерминальные символы грамматики");
        var str2 = input.nextLine();
        ArrayList<String> nonTerms = new ArrayList<>();
        while (!Objects.equals(str2, "exit")) {
            nonTerms.add(str2);
            str2 = input.nextLine();
        }

        return new Grammar(rules, terms, nonTerms);
    }
}
