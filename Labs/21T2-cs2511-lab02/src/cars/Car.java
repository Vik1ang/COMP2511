package cars;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Car {
    private List<Engine> engineList;
    private LocalDateTime localDateTime;
    private boolean canFly;
    private Producer producer;

    public void drive(int x, int y) {

    }

    public Car(List<Engine> engineList, LocalDateTime localDateTime, boolean canFly) {
        this.engineList = engineList;
        this.localDateTime = localDateTime;
        this.canFly = canFly;
    }

    public List<Engine> getEngineList() {
        return engineList;
    }

    public void setEngineList(List<Engine> engineList) {
        this.engineList = engineList;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public boolean isCanFly() {
        return canFly;
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }
}
