package unsw.connection;

import unsw.device.Device;
import unsw.satellite.Satellite;

import java.time.LocalTime;

/**
 * The type Connection object.
 */
public class ConnectionObject {
    /**
     * connection's device
     */
    private Device device;
    /**
     * connection's start time
     */
    private LocalTime startTime;
    /**
     * connection's end time
     */
    private LocalTime endTime;
    /**
     * connection's satellite
     */
    private Satellite satellite;
    /**
     * connection's active time
     */
    private int minutesActive;
    /**
     * connection's status
     */
    private boolean isConnect;

    /**
     * Instantiates a new Connection object.
     *
     * @param device        the device
     * @param startTime     the start time
     * @param endTime       the end time
     * @param satellite     the satellite
     * @param minutesActive the minutes active
     * @param isConnect     the is connect
     */
    public ConnectionObject(Device device, LocalTime startTime, LocalTime endTime, Satellite satellite, int minutesActive, boolean isConnect) {
        this.device = device;
        this.startTime = startTime;
        this.endTime = endTime;
        this.satellite = satellite;
        this.minutesActive = minutesActive;
        this.isConnect = isConnect;
    }

    /**
     * Gets device.
     *
     * @return the device
     */
    public Device getDevice() {
        return device;
    }

    /**
     * Sets device.
     *
     * @param device the device
     */
    public void setDevice(Device device) {
        this.device = device;
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Sets start time.
     *
     * @param startTime the start time
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets end time.
     *
     * @return the end time
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Sets end time.
     *
     * @param endTime the end time
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Gets satellite.
     *
     * @return the satellite
     */
    public Satellite getSatellite() {
        return satellite;
    }

    /**
     * Sets satellite.
     *
     * @param satellite the satellite
     */
    public void setSatellite(Satellite satellite) {
        this.satellite = satellite;
    }

    /**
     * Gets minutes active.
     *
     * @return the minutes active
     */
    public int getMinutesActive() {
        return minutesActive;
    }

    /**
     * Sets minutes active.
     *
     * @param minutesActive the minutes active
     */
    public void setMinutesActive(int minutesActive) {
        this.minutesActive = minutesActive;
    }

    /**
     * Is connect boolean.
     *
     * @return the boolean
     */
    public boolean isConnect() {
        return isConnect;
    }

    /**
     * Sets connect.
     *
     * @param connect the connect
     */
    public void setConnect(boolean connect) {
        isConnect = connect;
    }
}
