package Formal_languages.secondLab;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main extends Formal_languages.firstLab.Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static final Pattern pattern = Pattern.compile("^a(?!.*cb).*c$");

    public static void main(String[] args) {
        var alphabet = setupAlphabet();
        System.out.println("Алфавит: " + alphabet);
        System.out.println("Введите кол-во цепочек: ");
        var amountOfWords = scanner.nextInt();
        System.out.println(regularToWord(amountOfWords, alphabet));
    }

    protected static ArrayList<String> regularToWord(int amountOfWords, ArrayList<Character> alphabet) {
        var arr = new ArrayList<String>();
        var word = "";
        for (var i = 1; i <= amountOfWords; i++) {
            word =  numberToWord(i, alphabet);
            Matcher matcher = pattern.matcher(word);
            if (matcher.matches()) {
                System.out.println(word);
                arr.add(word);
            }
        }
        return arr;
    }




    protected static String numberToWord(int num, ArrayList<Character> alphabet1) {



//        System.out.println("Введите лексико-графический номер слова: ");

        int numOfChar = 0;
        int charSize = alphabet1.size();
        int c = num;
        StringBuilder finalStr = new StringBuilder();

        while (c / charSize >= charSize || c > charSize) {

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
        return finalStr.reverse().toString();
//        System.out.println(finalStr.reverse());

    }
}
