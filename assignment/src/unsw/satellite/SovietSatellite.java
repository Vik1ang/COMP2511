package unsw.satellite;

import unsw.connection.ConnectionObject;
import unsw.device.Device;
import unsw.util.CommonUtil;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Soviet satellite.
 */
public class SovietSatellite extends Satellite {
    /**
     * The constant SovietSatellite's speed
     */
    private static final double LINEAR_VELOCITY = 6000;
    /**
     * The constant 60 seconds.
     */
    private static final double TIME_DURATION = 60;
    /**
     * The constant Soviet upper position
     */
    private static final double UPPER = 190;
    /**
     * The constant Soviet lower position
     */
    private static final double LOWER = 140;
    /**
     * The constant threshold angle for Soviet
     */
    private static final double THRESHOLD_ANGLE = 345;

    /**
     * Instantiates a new Soviet satellite.
     *
     * @param connections         the connections
     * @param height              the height
     * @param id                  the id
     * @param position            the position
     * @param possibleConnections the possible connections
     * @param type                the type
     */
    public SovietSatellite(List<ConnectionObject> connections, double height, String id, double position, List<Device> possibleConnections, String type) {
        super(connections, height, id, position, possibleConnections, type);
        super.setVelocity(LINEAR_VELOCITY / TIME_DURATION);
        super.setSupportDeviceList(new ArrayList<>(List.of(LAPTOP, DESKTOP, AWS)));
    }

    @Override
    public void checkSatelliteSpeed() {
        if (super.getPosition() > THRESHOLD_ANGLE || super.getPosition() < LOWER) {
            if (super.getVelocity() < 0) {
                super.setVelocity(-super.getVelocity());
            }
        } else if (super.getPosition() > UPPER && super.getPosition() < THRESHOLD_ANGLE) {
            if (super.getVelocity() > 0) {
                super.setVelocity(-super.getVelocity());
            }
        }
    }

    @Override
    public int getSatelliteDevicePreConnectionTime(Device device) {
        return device.getConnectionTime() * 2;
    }

    @Override
    public boolean doConnectionAndUpdateConnection(LocalTime currentTime, LocalTime endTime, Device device) {
        List<ConnectionObject> connections = this.getConnections();
        if (this.getLaptopAmount() + this.getDesktopAmount() >= 9) {
            for (ConnectionObject connection : connections) {
                if (connection.isConnect()) {
                    CommonUtil.endConnectionByTime(connection, this, currentTime);
                    int connectionTime = this.getSatelliteDevicePreConnectionTime(connection.getDevice());
                    int activeMinutes = (currentTime.toSecondOfDay() - connection.getStartTime().toSecondOfDay()) / 60 - connectionTime;
                    if (activeMinutes < 0) {
                        activeMinutes = 0;
                    }
                    connection.setMinutesActive(activeMinutes);
                    break;
                }
            }
        }
        connections.add(new ConnectionObject(device, currentTime, endTime, this, 0, true));
        this.deviceConnectAmountAdd(device);
        device.setConnected(true);
        return true;
    }

    @Override
    public boolean isAwsCanConnected(Device device) {
        return true;
    }

    @Override
    public void doAwsAddNewConnection(Device device, LocalTime currentTime) {
        List<ConnectionObject> connections = this.getConnections();
        for (ConnectionObject connection : connections) {
            if (connection.isConnect()) {
                if (connection.getDevice() == device) {
                    return;
                }
            }
        }
        if (this.getDesktopAmount() + this.getLaptopAmount() >= 9) {
            for (ConnectionObject connection : connections) {
                if (connection.isConnect()) {
                    connection.getDevice().getConnectedList().remove(this);
                    CommonUtil.endConnectionByTime(connection, this, currentTime);
                    break;
                }
            }
        }
        LocalTime newEndTime = CommonUtil.getNewEndTime(device, currentTime);
        connections.add(new ConnectionObject(device, currentTime, newEndTime, this, 0, true));
    }
}
