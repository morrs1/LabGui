package expOperation.firstLab;

public class Node {
    private Integer cost;
    private Integer amount;

    public Node(Integer cost) {
        this.cost = cost;
    }

    public Integer getCost() {
        return cost;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
