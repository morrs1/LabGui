package Programming.FourthSemLab.FifthLab;

public class PairBag extends Bag{
    public PairBag(int size) {
        super(size);
    }

    public Pair<Object, Object> getPair() {
        return (Pair<Object, Object>) super.remove();
    }

    public void putPair(Pair<Object, Object> pair) {
        super.add(pair);
    }
}
