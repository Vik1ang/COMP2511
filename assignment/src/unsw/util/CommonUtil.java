package unsw.util;

import unsw.connection.ConnectionObject;
import unsw.device.Device;
import unsw.satellite.Satellite;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * helper functions
 */
public class CommonUtil {
    /**
     * Gets sorted possible connections.
     *
     * @param satellite the satellite
     * @return the possible connections and sort
     */
    public static List<Device> getPossibleConnectionsAndSort(Satellite satellite) {
        List<Device> possibleConnections = satellite.getPossibleConnections();
        possibleConnections.sort(new Comparator<Device>() {
            @Override
            public int compare(Device o1, Device o2) {
                if (o1.getId().compareTo(o2.getId()) != 0) {
                    return o1.getId().compareTo(o2.getId());
                }
                return Double.compare(o1.getPosition(), o2.getPosition());
            }
        });
        return possibleConnections;
    }

    /**
     * End connection by time.
     *
     * @param connection  the connection
     * @param satellite   the satellite
     * @param currentTime the current time
     */
    public static void endConnectionByTime(ConnectionObject connection, Satellite satellite, LocalTime currentTime) {
        connection.setConnect(false);
        connection.getDevice().setConnected(false);
        satellite.deviceConnectAmountMinus(connection.getDevice());
        connection.setEndTime(currentTime);
        int connectionTime = satellite.getSatelliteDevicePreConnectionTime(connection.getDevice());
        int activeMinutes = (currentTime.toSecondOfDay() - connection.getStartTime().toSecondOfDay()) / 60 - connectionTime - 1;
        if (activeMinutes < 0) {
            activeMinutes = 0;
        }
        connection.setMinutesActive(activeMinutes);
    }

    /**
     * End connection by visible.
     *
     * @param connection  the connection
     * @param satellite   the satellite
     * @param currentTime the current time
     */
    public static void endConnectionByVisible(ConnectionObject connection, Satellite satellite, LocalTime currentTime) {
        connection.setConnect(false);
        connection.getDevice().setConnected(false);
        satellite.deviceConnectAmountMinus(connection.getDevice());
        connection.setEndTime(currentTime.plusMinutes(-1));
        int connectionTime = satellite.getSatelliteDevicePreConnectionTime(connection.getDevice());
        int activeMinutes = (currentTime.plusMinutes(-1).toSecondOfDay() - connection.getStartTime().toSecondOfDay()) / 60 - connectionTime - 1;
        if (activeMinutes < 0) {
            activeMinutes = 0;
        }
        connection.setMinutesActive(activeMinutes);
    }

    /**
     * Gets new end time.
     *
     * @param device      the device
     * @param currentTime the current time
     * @return the new end time
     */
    public static LocalTime getNewEndTime(Device device, LocalTime currentTime) {
        List<HashMap<String, LocalTime>> activationPeriods = device.getActivationPeriods();
        for (HashMap<String, LocalTime> activationPeriod : activationPeriods) {
            LocalTime startTime = activationPeriod.get("startTime");
            LocalTime endTime = activationPeriod.get("endTime");
            if (startTime.compareTo(currentTime) <= 0 && endTime.compareTo(currentTime) >= 0) {
                return endTime;
            }
        }
        return null;
    }
}
