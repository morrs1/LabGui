package Formal_languages.firstLab;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
//        wordToNumber();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите алфавит: ");
        Set<Character> alphabet = new LinkedHashSet<>();

        String str = scanner.nextLine();
        while (!str.equals("exit")) {
            for (char c : str.toCharArray()) {
                alphabet.add(c);
            }
            str = scanner.nextLine();
        }
        var alphabet1 = new ArrayList<>(alphabet);
        System.out.println("Введите лексико-графический номер слова: ");
        var num = scanner.nextInt();
        int numOfChar = 0;
        int charSize = alphabet1.size();
        int c = num;
        StringBuilder finalStr = new StringBuilder();

        while (c / charSize >= charSize) {

            int p = c;
            if (p % charSize == 0) {
                numOfChar = charSize;
                c = c / charSize - 1;
            } else {
                numOfChar = c % charSize;
                c = c / charSize;
            }
            finalStr.append(alphabet1.get(numOfChar - 1));
            System.out.printf("%d * %d + %d %n", c, charSize, numOfChar);
        }
        finalStr.append(alphabet1.get(c - 1));
        System.out.println(finalStr.reverse());
    }

    private static void wordToNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите алфавит: ");
        Set<Character> alphabet = new LinkedHashSet<>();

        String str = scanner.nextLine();
        while (!str.equals("exit")) {
            for (char c : str.toCharArray()) {
                alphabet.add(c);
            }
            str = scanner.nextLine();
        }
        var alphabet1 = new ArrayList<>(alphabet);
        System.out.println("Введите слово: ");
        var word = scanner.nextLine();
        var charArr = word.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        boolean allCharsValid = alphabet.containsAll(charArr);
        double sum = 0;
        var index = 0;
        if (allCharsValid) {
            System.out.println("Слово соответствует алфавиту");
            for (var i = charArr.size() - 1; i >= 0; i--) {
                sum += Math.pow(alphabet1.size(), i) * (alphabet1.indexOf(charArr.get(index)) + 1);
                index++;
            }

        } else {
            System.out.println("Слово не соответствует алфавиту, введите заново");
        }

        System.out.println("Лексико-графический номер: " + sum);
    }
}
