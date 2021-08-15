package unsw.device;

import unsw.satellite.Satellite;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


/**
 * The type Mobile x phone.
 */
public class MobileXPhone extends Handheld {

    /**
     * Instantiates a new Mobile x phone.
     *
     * @param id                the id
     * @param isConnected       the is connected
     * @param position          the position
     * @param type              the type
     * @param activationPeriods the activation periods
     */
    public MobileXPhone(String id, boolean isConnected, double position, String type, List<HashMap<String, LocalTime>> activationPeriods) {
        super(id, isConnected, position, type, activationPeriods);
    }

    @Override
    public void updateConnectionByDevice(List<Satellite> satelliteList, LocalTime currentTime) {
        if (this.isConnected()) {
            return;
        }
        satelliteList.sort(new Comparator<Satellite>() {
            @Override
            public int compare(Satellite o1, Satellite o2) {
                return Double.compare(o1.getPosition(), o2.getPosition());
            }
        });
        boolean hasSpaceX = false;
        Satellite findSpaceX = null;
        for (Satellite satellite : satelliteList) {
            if ("SpaceXSatellite".equals(satellite.getType()) && satellite.getPossibleConnections().contains(this)) {
                List<HashMap<String, LocalTime>> activationPeriods = this.getActivationPeriods();
                for (HashMap<String, LocalTime> activationPeriod : activationPeriods) {
                    LocalTime startTime = activationPeriod.get("startTime");
                    LocalTime endTime = activationPeriod.get("endTime");
                    if (startTime.compareTo(currentTime) <= 0 && endTime.compareTo(currentTime) >= 0) {
                        findSpaceX = satellite;
                        hasSpaceX = true;
                        break;
                    }
                }
            }
        }
        if (!hasSpaceX) {
            super.updateConnectionByDevice(satelliteList, currentTime);
        } else {
            List<HashMap<String, LocalTime>> activationPeriods = this.getActivationPeriods();
            for (HashMap<String, LocalTime> activationPeriod : activationPeriods) {
                LocalTime startTime = activationPeriod.get("startTime");
                LocalTime endTime = activationPeriod.get("endTime");
                if (startTime.compareTo(currentTime) <= 0 && endTime.compareTo(currentTime) >= 0) {
                    if (findSpaceX.doConnectionAndUpdateConnection(currentTime, endTime, this)) {
                        break;
                    }
                }
            }
        }

    }
}
