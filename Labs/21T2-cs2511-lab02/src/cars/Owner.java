package cars;

import java.util.List;

public class Owner {
    private String name;
    private String address;
    private List<Car> carList;

    public Owner(String name, String address, List<Car> carList) {
        this.name = name;
        this.address = address;
        this.carList = carList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }
}
