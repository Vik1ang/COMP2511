package cars;

import java.time.LocalDateTime;
import java.util.List;

public class Planes extends Car {
    private List<String> passengersList;

    public Planes(List<Engine> engineList, LocalDateTime localDateTime, boolean canFly, List<String> passengersList) {
        super(engineList, localDateTime, canFly);
        this.passengersList = passengersList;
    }
}
