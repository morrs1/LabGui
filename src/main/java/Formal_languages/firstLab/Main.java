package Formal_languages.firstLab;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Введите алфавит: ");
    Set<Character> alphabet = new HashSet<>();

    String str = scanner.nextLine();
    while (!str.equals("exit")) {
      for (char c : str.toCharArray()) {
        alphabet.add(c);
      }
      str = scanner.nextLine();
    }

    System.out.println("Введите слово: ");
    var word = scanner.nextLine();
    var charArr = word.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

    boolean allCharsValid = alphabet.containsAll(charArr);
    if (allCharsValid) {
      System.out.println(charArr);
    } else {
      System.out.println(" ");
    }



  }
}
