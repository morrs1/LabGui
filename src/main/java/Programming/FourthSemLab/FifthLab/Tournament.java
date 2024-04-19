package Programming.FourthSemLab.FifthLab;

import java.util.Scanner;

public class Tournament {
    private final Bag bag;
    private final GenericPairBag<String> gBag;
    public Tournament(int amountTeams) {
        if (amountTeams <= 1 || amountTeams % 2 != 0) {
            amountTeams = 8;
        }

        // Создаем мешок команд
        bag = new Bag(amountTeams);
        for (int i = 1; i <= amountTeams; i++) {
            bag.add("Команда " + i);
        }
        gBag = new GenericPairBag<>();
    }

    public String playTournament() {
        // Создаем мешок пар команд
        while (bag.getCurrentSize() > 1) {
            String team1 = (String) bag.remove();
            String team2 = (String) bag.remove();
            gBag.putPair(new Pair<>(team1, team2));
        }

        while (gBag.getCurrentSize() > 0) {
            Pair<String, String> pair = gBag.getPair();
            String winner = playMatch(pair.getFirst(), pair.getSecond());
            bag.add(winner);
        }

        if (bag.getCurrentSize() != 1) {
            return playTournament();
        } else
            return (String) bag.remove();
    }

    private String playMatch(String team1, String team2) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n\n >> " + team1 + " против " + team2);
            System.out.print("\nКакая команда выиграла? \n\n\t1 - " + team1 + "\n\t2 - " + team2 + "\n\nВвод: ");
            int winner = scanner.nextInt();
            if (winner == 1) {
                return team1;
            } else if (winner == 2) {
                return team2;
            } else {
                System.out.println("Некорректный выбор, попробуйте еще раз.");
            }
        }
    }

}