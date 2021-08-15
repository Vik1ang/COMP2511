package unsw.satellite;

import unsw.connection.ConnectionObject;
import unsw.device.Device;
import unsw.util.CommonUtil;

import java.time.LocalTime;
import java.util.List;


/**
 * The type Nasa satellite.
 */
public class NasaSatellite extends Satellite {
    /**
     * The constant NasaSatellite's speed
     */
    private static final double LINEAR_VELOCITY = 5100;
    /**
     * The constant 60 seconds.
     */
    private static final double TIME_DURATION = 60;

    /**
     * Instantiates a new Nasa satellite.
     *
     * @param connections         the connections
     * @param height              the height
     * @param id                  the id
     * @param position            the position
     * @param possibleConnections the possible connections
     * @param type                the type
     */
    public NasaSatellite(List<ConnectionObject> connections, double height, String id, double position, List<Device> possibleConnections, String type) {
        super(connections, height, id, position, possibleConnections, type);
        super.setVelocity(LINEAR_VELOCITY / TIME_DURATION);
        super.setConnectionTime(10);
        super.setSupportDeviceList(List.of(HANDHELD, LAPTOP, DESKTOP, MOBILE_X, AWS));
        super.setConnectionTime(10);
    }

    @Override
    public int getSatelliteDevicePreConnectionTime(Device device) {
        return 10;
    }

    @Override
    public boolean doConnectionAndUpdateConnection(LocalTime currentTime, LocalTime endTime, Device device) {
        List<ConnectionObject> connections = this.getConnections();
        if (this.getHandheldAmount() + this.getLaptopAmount() + this.getDesktopAmount() >= 6) {
            if (device.getPosition() < 30 || device.getPosition() > 40) {
                return false;
            }
            boolean find = false;
            for (ConnectionObject connection : connections) {
                if (!connection.isConnect()) {
                    continue;
                }
                if (connection.getDevice().getPosition() < 30 || connection.getDevice().getPosition() > 40) {
                    find = true;
                    connection.getDevice().getConnectedList().remove(this);
                    CommonUtil.endConnectionByTime(connection, this, currentTime);
                    int connectionTime = connection.getSatellite().getSatelliteDevicePreConnectionTime(device);
                    int activeMinutes = (currentTime.toSecondOfDay() - connection.getStartTime().toSecondOfDay()) / 60 - connectionTime;
                    if (activeMinutes < 0) {
                        activeMinutes = 0;
                    }
                    connection.setMinutesActive(activeMinutes);
                    break;
                }
            }
            if (!find) {
                return false;
            }
        }
        connections.add(new ConnectionObject(device, currentTime, endTime, this, 0, true));
        this.deviceConnectAmountAdd(device);
        device.setConnected(true);
        return true;
    }

    @Override
    public boolean isAwsCanConnected(Device device) {
        List<ConnectionObject> connections = this.getConnections();
        if (this.getHandheldAmount() + this.getLaptopAmount() + this.getDesktopAmount() >= 6) {
            if (device.getPosition() < 30 || device.getPosition() > 40) {
                return false;
            }
            boolean find = false;
            for (ConnectionObject connection : connections) {
                if (connection.isConnect()) {
                    if (connection.getDevice().getPosition() < 30 || connection.getDevice().getPosition() > 40) {
                        find = true;
                        break;
                    }
                }
            }
            if (!find) {
                return false;
            }
        }
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
        if (this.getHandheldAmount() + this.getLaptopAmount() + this.getDesktopAmount() >= 6) {
            for (ConnectionObject connection : connections) {
                if (connection.isConnect()) {
                    if (connection.getDevice().getPosition() < 30 || connection.getDevice().getPosition() > 40) {
                        connection.getDevice().getConnectedList().remove(this);
                        CommonUtil.endConnectionByTime(connection, this, currentTime);
                        break;
                    }
                }
            }
        }
        LocalTime newEndTime = CommonUtil.getNewEndTime(device, currentTime);
        connections.add(new ConnectionObject(device, currentTime, newEndTime, this, 0, true));
        this.deviceConnectAmountAdd(device);
    }
}
