package programmingexample3;

public abstract class Item {
    public abstract int getPrice();

    @Override
    public String toString(){
        return getClass().getName();
    }
}
