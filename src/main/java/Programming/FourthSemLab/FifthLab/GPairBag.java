package Programming.FourthSemLab.FifthLab;

public class GPairBag<T> extends Bag{
    public GPairBag(int size) {
        super(size);
    }

    public void putPair(Pair<T, T> pair) {
        super.add(pair);
    }

    public Pair<T, T> getPair() {
        return (Pair<T, T>) super.remove();
    }
}
