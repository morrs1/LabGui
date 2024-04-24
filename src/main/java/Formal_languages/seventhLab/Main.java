package Formal_languages.seventhLab;

import Programming.FourthSemLab.FifthLab.Pair;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Grammar grammar = setupGrammar();
        System.out.println(grammar);
//        System.out.println(grammar.isRLG());
//        System.out.println(grammar.isLLG());
//        System.out.println(grammar.isCS());
        System.out.println(grammar.isCZ());
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
