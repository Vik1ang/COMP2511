package unsw.device;

import unsw.connection.ConnectionObject;
import unsw.satellite.Satellite;
import unsw.util.CommonUtil;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * The type Aws cloud server.
 */
public class AWSCloudServer extends Desktop {
    /**
     * Instantiates a new Aws cloud server.
     *
     * @param id                the id
     * @param isConnected       the is connected
     * @param position          the position
     * @param type              the type
     * @param activationPeriods the activation periods
     */
    public AWSCloudServer(String id, boolean isConnected, double position, String type, List<HashMap<String, LocalTime>> activationPeriods) {
        super(id, isConnected, position, type, activationPeriods);
    }

    @Override
    public void updateConnectionByDevice(List<Satellite> satelliteList, LocalTime currentTime) {
        satelliteList.sort(new Comparator<Satellite>() {
            @Override
            public int compare(Satellite o1, Satellite o2) {
                return Double.compare(o1.getPosition(), o2.getPosition());
            }
        });
        if (this.getConnectedList().size() >= 2) {
            return;
        }
        for (Satellite satellite : satelliteList) {
            if (this.getConnectedList().size() >= 2) {
                break;
            }
            List<Device> possibleConnections = CommonUtil.getPossibleConnectionsAndSort(satellite);
            if (possibleConnections.contains(this)) {
                List<HashMap<String, LocalTime>> activationPeriods = this.getActivationPeriods();
                for (HashMap<String, LocalTime> activationPeriod : activationPeriods) {
                    LocalTime startTime = activationPeriod.get("startTime");
                    LocalTime endTime = activationPeriod.get("endTime");
                    if (startTime.compareTo(currentTime) <= 0 && endTime.compareTo(currentTime) >= 0) {
                        if (satellite.isAwsCanConnected(this)) {
                            this.getConnectedList().add(satellite);
                            break;
                        }
                    }
                }
            }
        }

        if (this.getConnectedList().size() < 2) {
            for (Satellite satellite : this.getConnectedList()) {
                List<ConnectionObject> connections = satellite.getConnections();
                for (ConnectionObject connection : connections) {
                    if (connection.getDevice() == this && connection.isConnect()) {
                        CommonUtil.endConnectionByTime(connection, satellite, currentTime);
                    }
                }
            }
            this.getConnectedList().clear();
        } else {
            for (Satellite satellite : this.getConnectedList()) {
                satellite.doAwsAddNewConnection(this, currentTime);
            }
        }
    }

    @Override
    public void updateAwsSecond(LocalTime currentTime) {
        if (this.getConnectedList().size() < 2) {
            for (Satellite satellite : this.getConnectedList()) {
                List<ConnectionObject> connections = satellite.getConnections();
                for (ConnectionObject connection : connections) {
                    if (connection.getDevice() == this && connection.isConnect()) {
                        CommonUtil.endConnectionByTime(connection, satellite, currentTime);
                        int connectionTime = connection.getSatellite().getSatelliteDevicePreConnectionTime(this);
                        int activeMinutes = (currentTime.toSecondOfDay() - connection.getStartTime().toSecondOfDay()) / 60 - connectionTime;
                        if (activeMinutes < 0) {
                            activeMinutes = 0;
                        }
                        connection.setMinutesActive(activeMinutes);
                    }
                }
            }
            this.getConnectedList().clear();
        }
    }
}
