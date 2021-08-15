package unsw.satellite;

import unsw.connection.ConnectionObject;
import unsw.device.Device;
import unsw.util.CommonUtil;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Blue origin satellite.
 */
public class BlueOriginSatellite extends Satellite {
    /**
     * The constant BlueOriginSatellite's speed
     */
    private static final double LINEAR_VELOCITY = 8500;
    /**
     * The constant 60 seconds.
     */
    private static final double TIME_DURATION = 60;


    /**
     * Instantiates a new Blue origin satellite.
     *
     * @param connections         the connections
     * @param height              the height
     * @param id                  the id
     * @param position            the position
     * @param possibleConnections the possible connections
     * @param type                the type
     */
    public BlueOriginSatellite(List<ConnectionObject> connections, double height, String id, double position, List<Device> possibleConnections, String type) {
        super(connections, height, id, position, possibleConnections, type);
        super.setVelocity(LINEAR_VELOCITY / TIME_DURATION);
        super.setSupportDeviceList(new ArrayList<>(List.of(HANDHELD, LAPTOP, DESKTOP, MOBILE_X, AWS)));
    }

    @Override
    public boolean doConnectionAndUpdateConnection(LocalTime currentTime, LocalTime endTime, Device device) {
        if (this.getLaptopAmount() >= 5 && device.getType().equals(LAPTOP)) {
            return false;
        } else if (this.getDesktopAmount() >= 2 && device.getType().equals(DESKTOP)) {
            return false;
        } else if (this.getHandheldAmount() + this.getLaptopAmount() + this.getDesktopAmount() >= 10) {
            return false;
        }
        List<ConnectionObject> connections = this.getConnections();
        connections.add(new ConnectionObject(device, currentTime, endTime, this, 0, true));
        this.deviceConnectAmountAdd(device);
        device.setConnected(true);
        return true;
    }

    @Override
    public boolean isAwsCanConnected(Device device) {
        if (this.getLaptopAmount() >= 5 && device.getType().equals(LAPTOP)) {
            return false;
        } else if (this.getDesktopAmount() >= 2 && device.getType().equals(DESKTOP)) {
            return false;
        } else return this.getHandheldAmount() + this.getLaptopAmount() + this.getDesktopAmount() < 10;
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
        LocalTime newEndTime = CommonUtil.getNewEndTime(device, currentTime);
        this.deviceConnectAmountAdd(device);
        LocalTime newStart = null;
        if (currentTime.equals(LocalTime.of(0, 0))) {
            newStart = currentTime;
        } else {
            newStart = currentTime.plusMinutes(-1);
        }
        connections.add(new ConnectionObject(device, newStart, newEndTime, this, 0, true));
    }
}
