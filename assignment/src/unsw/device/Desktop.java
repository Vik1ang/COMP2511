package unsw.device;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

/**
 * The type Desktop.
 */
public class Desktop extends Device {
    /**
     * Instantiates a new Desktop.
     *
     * @param id                the id
     * @param isConnected       the is connected
     * @param position          the position
     * @param type              the type
     * @param activationPeriods the activation periods
     */
    public Desktop(String id, boolean isConnected, double position, String type, List<HashMap<String, LocalTime>> activationPeriods) {
        super(id, isConnected, position, type, activationPeriods);
        this.setConnectionTime(5);
    }

}
