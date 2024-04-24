package Formal_languages.seventhLab;

import Programming.FourthSemLab.FifthLab.Pair;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите правила грамматики(Например: A-abA)");
        var str = input.nextLine();
        ArrayList<Pair<String,String>> rules = new ArrayList<>();
        while (!Objects.equals(str, "exit")) {
            Pair<String, String> p = new Pair<>(str.split("-")[0], str.split("-")[1]);
            rules.add(p);
            str = input.nextLine();
        }
        System.out.println(rules);
    }
}
