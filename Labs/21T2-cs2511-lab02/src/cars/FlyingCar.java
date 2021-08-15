package cars;

import java.time.LocalDateTime;
import java.util.List;

public class FlyingCar extends Car{
    public FlyingCar(List<Engine> engineList, LocalDateTime localDateTime, boolean canFly) {
        super(engineList, localDateTime, canFly);
    }
}
