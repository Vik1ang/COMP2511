package cars;

public abstract class Engine {
    private int speed;
    private Manufacturer manufacturer;

    public Engine(int speed, Manufacturer manufacturer) {
        this.speed = speed;
        this.manufacturer = manufacturer;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
