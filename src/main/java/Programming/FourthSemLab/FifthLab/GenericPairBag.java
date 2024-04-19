package Programming.FourthSemLab.FifthLab;

import java.util.ArrayList;

public class GenericPairBag<T> {

    private final ArrayList<Pair<T, T>> pairs;

    public GenericPairBag() {
        pairs = new ArrayList<>();
    }

    public void putPair(Pair<T, T> pair) {
        pairs.add(pair);
    }

    public Pair<T, T> getPair() {
        if (pairs.isEmpty()) {
            throw new IllegalStateException("Pair bag is empty");
        }

        int randomIndex = (int)Math.round(Math.random()*(pairs.size() - 1));
        Pair<T, T> pair = pairs.get(randomIndex);
        pairs.remove(randomIndex);
        return pair;
    }

    public int getCurrentSize() {
        return pairs.size();
    }
}
