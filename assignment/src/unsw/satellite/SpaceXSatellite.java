package unsw.satellite;

import unsw.connection.ConnectionObject;
import unsw.device.Device;

import java.util.List;

/**
 * The type Space x satellite.
 */
public class SpaceXSatellite extends Satellite {
    /**
     * The constant SpaceXSatellite's speed
     */
    private static final double LINEAR_VELOCITY = 3330;
    /**
     * The constant 60 seconds.
     */
    private static final double TIME_DURATION = 60;

    /**
     * Instantiates a new Space x satellite.
     *
     * @param connections         the connections
     * @param height              the height
     * @param id                  the id
     * @param position            the position
     * @param possibleConnections the possible connections
     * @param type                the type
     */
    public SpaceXSatellite(List<ConnectionObject> connections, double height, String id, double position, List<Device> possibleConnections, String type) {
        super(connections, height, id, position, possibleConnections, type);
        super.setVelocity(LINEAR_VELOCITY / TIME_DURATION);
        super.setSupportDeviceList(List.of(HANDHELD, MOBILE_X));
    }

    @Override
    public int getSatelliteDevicePreConnectionTime(Device device) {
        return 0;
    }


}
