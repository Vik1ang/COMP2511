package programmingexample6;

public class FreeShippingDecorator extends Decorator {

    // TODO Complete this class

    private int cost;
    private int weigh;

    public FreeShippingDecorator(Product product, int cost, int weigh) {
        super(product);
        this.cost = cost;
        this.weigh = weigh;
    }

    @Override
    public double getShippingCost() {
        if (getWeight() <= weigh && getPrice() >= cost) {
            return 0;
        }
        return super.getShippingCost();
    }
}
