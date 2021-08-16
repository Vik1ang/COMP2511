package programmingexample6;

public class DiscountDecorator extends Decorator {

    private double discount;

    // TODO: Complete this class
    public DiscountDecorator(Product product, double discount) {
        super(product);
        this.discount = discount;
    }

    @Override
    public double getPrice() {
        return super.getPrice() * (1 - (discount) / 100);
    }
}
