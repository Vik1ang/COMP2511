package programmingexample3;

public class FruitHamper extends ArrayListItemHamper<Fruit> {
    /**
     * invariant: FruitHamper must have at least 2 apples and 2 avocados when have >= 6 fruits. Otherwise, adding an item should do nothing
     * fruit hamper has price surcharge of 25%, rounded up to nearest integer
     */

    @Override
    public int getPrice() {
        // TODO implement this
        int price = super.getPrice();
        return (int) Math.ceil(price * 1.25);
    }

    @Override
    public void add(Fruit e, int n) {
        // TODO implement this
        super.add(e, n);
        if (this.size() >= 6) {
            int countApple = getAppleCount();
            int countAvocados = getAvocadoCount();
            if (countApple < 2 || countAvocados < 2) {
                super.remove(e, n);
            }
        }
    }
}
