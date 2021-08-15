package cars;

import java.time.LocalDateTime;
import java.util.List;

public class TimeTravellingCar  extends FlyingCar{

    public TimeTravellingCar(List<Engine> engineList, LocalDateTime localDateTime, boolean canFly) {
        super(engineList, localDateTime, canFly);
    }
}
