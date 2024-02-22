package Formal_languages.secondLab;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main extends Formal_languages.firstLab.Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static final Pattern pattern = Pattern.compile("^a(?!.*cb).*c$");

    public static void main(String[] args) {
        var alphabet = setupAlphabet();
        var flag = false;
        while (!flag) {
            if (alphabet.size() != 3 || !alphabet.containsAll(Arrays.asList('a', 'b', 'c'))){
                alphabet = setupAlphabet();
            }
            else {
                flag = true;
            }
        }
        System.out.println("Алфавит: " + alphabet);
        System.out.println("Введите кол-во цепочек: ");
        var amountOfWords = scanner.nextInt();
        System.out.println(regularToWord(amountOfWords, alphabet));
    }

    protected static LinkedHashMap<String, Integer> regularToWord(int amountOfWords, ArrayList<Character> alphabet) {
        var map = new LinkedHashMap<String, Integer>();
        var word = "";
        int amountWords = 0;
        int c = 1;
        while (amountWords != amountOfWords){
            word = numberToWord(c, alphabet);
            System.out.println(word);
            Matcher matcher = pattern.matcher(word);
            if (matcher.matches()) {
                amountWords+=1;
                map.put(word, c);
            }
            c+=1;
        }

        return map;
    }




    protected static String numberToWord(int num, ArrayList<Character> alphabet1) {
        int numOfChar;
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
        }
        finalStr.append(alphabet1.get(c - 1));
        return finalStr.reverse().toString();

    }
}
