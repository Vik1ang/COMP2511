package unsw.device;

import unsw.blackout.MathsHelper;
import unsw.satellite.Satellite;
import unsw.util.CommonUtil;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * The type Device.
 */
public abstract class Device {
    /**
     * Device name
     */
    private String id;
    /**
     * Device if connected
     */
    private boolean isConnected;
    /**
     * Device angle
     */
    private double position;
    /**
     * Device type
     */
    private String type;
    /**
     * Device activationPeriods
     */
    private List<HashMap<String, LocalTime>> activationPeriods;
    /**
     * Device connection time
     */
    private int connectionTime;
    /**
     * Device connected list which store satellite
     * Only use for Aws
     */
    private List<Satellite> connectedList;

    /**
     * Instantiates a new Device.
     *
     * @param id                the id
     * @param isConnected       the is connected
     * @param position          the position
     * @param type              the type
     * @param activationPeriods the activation periods
     */
    public Device(String id, boolean isConnected, double position, String type, List<HashMap<String, LocalTime>> activationPeriods) {
        this.id = id;
        this.isConnected = isConnected;
        this.position = position;
        this.type = type;
        this.activationPeriods = activationPeriods;
        this.connectedList = new ArrayList<>();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Is connected boolean.
     *
     * @return the boolean
     */
    public boolean isConnected() {
        return isConnected;
    }

    /**
     * Sets connected.
     *
     * @param connected the connected
     */
    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public double getPosition() {
        return position;
    }

    /**
     * Sets position.
     *
     * @param position the position
     */
    public void setPosition(double position) {
        this.position = position;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets activation periods.
     *
     * @return the activation periods
     */
    public List<HashMap<String, LocalTime>> getActivationPeriods() {
        return activationPeriods;
    }

    /**
     * Sets activation periods.
     *
     * @param activationPeriods the activation periods
     */
    public void setActivationPeriods(List<HashMap<String, LocalTime>> activationPeriods) {
        this.activationPeriods = activationPeriods;
    }

    /**
     * Gets connection time.
     *
     * @return the connection time
     */
    public int getConnectionTime() {
        return connectionTime;
    }

    /**
     * Sets connection time.
     *
     * @param connectionTime the connection time
     */
    public void setConnectionTime(int connectionTime) {
        this.connectionTime = connectionTime;
    }

    /**
     * Gets connected list.
     *
     * @return the connected list
     */
    public List<Satellite> getConnectedList() {
        return connectedList;
    }


    /**
     * Update connection by device.
     *
     * @param satelliteList the satellite list
     * @param currentTime   the current time
     */
    public void updateConnectionByDevice(List<Satellite> satelliteList, LocalTime currentTime) {
        satelliteList.sort(new Comparator<Satellite>() {
            @Override
            public int compare(Satellite o1, Satellite o2) {
                return Double.compare(o1.getPosition(), o2.getPosition());
            }
        });
        for (Satellite satellite : satelliteList) {
            // get possibleConnection
            List<Device> possibleConnections = CommonUtil.getPossibleConnectionsAndSort(satellite);
            if (possibleConnections.contains(this) && !this.isConnected()) {
                if (MathsHelper.satelliteIsVisibleFromDevice(satellite.getPosition(), satellite.getHeight(), this.getPosition())) {
                    List<HashMap<String, LocalTime>> activationPeriods = this.getActivationPeriods();
                    boolean find = false;
                    for (HashMap<String, LocalTime> activationPeriod : activationPeriods) {
                        LocalTime startTime = activationPeriod.get("startTime");
                        LocalTime endTime = activationPeriod.get("endTime");
                        if (startTime.compareTo(currentTime) <= 0 && endTime.compareTo(currentTime) >= 0) {
                            if (satellite.doConnectionAndUpdateConnection(currentTime, endTime, this)) {
                                find = true;
                                break;
                            }
                        }
                    }
                    if (find) {
                        break;
                    }
                }
            }
        }
    }

    /**
     * Update aws second.
     *
     * @param currentTime the current time
     */
    public void updateAwsSecond(LocalTime currentTime) {

    }
}
