package unsw.satellite;


import unsw.blackout.MathsHelper;
import unsw.connection.ConnectionObject;
import unsw.device.Desktop;
import unsw.device.Device;
import unsw.device.Handheld;
import unsw.device.Laptop;
import unsw.util.CommonUtil;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Parent class for Satellite
 */
public abstract class Satellite {
    /**
     * The constant HANDHELD.
     */
    public static final String HANDHELD = "HandheldDevice";
    /**
     * The constant LAPTOP.
     */
    public static final String LAPTOP = "LaptopDevice";
    /**
     * The constant DESKTOP.
     */
    public static final String DESKTOP = "DesktopDevice";
    /**
     * The constant MOBILE_X.
     */
    public static final String MOBILE_X = "MobileXPhone";
    /**
     * The constant AWS.
     */
    public static final String AWS = "AWSCloudServer";

    /**
     * The variable which store this satellite connection object.
     */
    private List<ConnectionObject> connections;
    /**
     * The satellite's height
     */
    private double height;
    /**
     * The satellite's name
     */
    private String id;
    /**
     * The satellite's angle
     */
    private double position;
    /**
     * The list of all device that satellite could connect
     */
    private List<Device> possibleConnections;
    /**
     * The satellite's type
     */
    private String type;
    /**
     * The satellite's velocity
     */
    private double velocity;
    /**
     * The satellite connected time
     */
    private int connectionTime;
    /**
     * The type list for satellite could connect
     */
    private List<String> supportDeviceList;
    /**
     * The number of handheld
     */
    private int handheldAmount;
    /**
     * The number of laptop
     */
    private int laptopAmount;
    /**
     * The number of desktop
     */
    private int desktopAmount;


    /**
     * Instantiates a new Satellite.
     *
     * @param connections         the connections
     * @param height              the height
     * @param id                  the id
     * @param position            the position
     * @param possibleConnections the possible connections
     * @param type                the type
     */
    public Satellite(List<ConnectionObject> connections, double height, String id, double position, List<Device> possibleConnections, String type) {
        this.connections = connections;
        this.height = height;
        this.id = id;
        this.position = position;
        this.possibleConnections = possibleConnections;
        this.type = type;
    }

    /**
     * Gets connections.
     *
     * @return the connections
     */
    public List<ConnectionObject> getConnections() {
        return connections;
    }

    /**
     * Sets connections.
     *
     * @param connections the connections
     */
    public void setConnections(List<ConnectionObject> connections) {
        this.connections = connections;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets height.
     *
     * @param height the height
     */
    public void setHeight(double height) {
        this.height = height;
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
     * Gets possible connections.
     *
     * @return the possible connections
     */
    public List<Device> getPossibleConnections() {
        return possibleConnections;
    }

    /**
     * Sets possible connections.
     *
     * @param possibleConnections the possible connections
     */
    public void setPossibleConnections(List<Device> possibleConnections) {
        this.possibleConnections = possibleConnections;
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
     * Gets velocity.
     *
     * @return the velocity
     */
    public double getVelocity() {
        return velocity;
    }

    /**
     * Sets velocity.
     *
     * @param velocity the velocity
     */
    public void setVelocity(double velocity) {
        this.velocity = velocity;
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
     * Gets support device list.
     *
     * @return the support device list
     */
    public List<String> getSupportDeviceList() {
        return supportDeviceList;
    }

    /**
     * Sets support device list.
     *
     * @param supportDeviceList the support device list
     */
    public void setSupportDeviceList(List<String> supportDeviceList) {
        this.supportDeviceList = supportDeviceList;
    }

    /**
     * Gets handheld amount.
     *
     * @return the handheld amount
     */
    public int getHandheldAmount() {
        return handheldAmount;
    }


    /**
     * Gets laptop amount.
     *
     * @return the laptop amount
     */
    public int getLaptopAmount() {
        return laptopAmount;
    }


    /**
     * Gets desktop amount.
     *
     * @return the desktop amount
     */
    public int getDesktopAmount() {
        return desktopAmount;
    }


    /**
     * Check satellite speed.
     */
    public void checkSatelliteSpeed() {

    }

    /**
     * Gets satellite device pre connection time.
     *
     * @param device the device
     * @return the satellite device pre connection time
     */
    public int getSatelliteDevicePreConnectionTime(Device device) {
        return device.getConnectionTime();
    }


    /**
     * Device connect amount add.
     *
     * @param device the device
     */
    public void deviceConnectAmountAdd(Device device) {
        if (device instanceof Handheld) {
            this.handheldAmount++;
        } else if (device instanceof Laptop) {
            this.laptopAmount++;
        } else if (device instanceof Desktop) {
            this.desktopAmount++;
        }
    }

    /**
     * Device connect amount minus.
     *
     * @param device the device
     */
    public void deviceConnectAmountMinus(Device device) {
        if (device instanceof Handheld) {
            this.handheldAmount--;
        } else if (device instanceof Laptop) {
            this.laptopAmount--;
        } else if (device instanceof Desktop) {
            this.desktopAmount--;
        }

    }

    /**
     * Update position.
     */
    public void updatePosition() {
        double angularVelocity = this.getVelocity() / this.getHeight();
        this.setPosition((angularVelocity + this.getPosition()) % 360);
    }

    /**
     * Update connection by activation period.
     *
     * @param currentTime the current time
     */
    public void updateConnectionByActivationPeriod(LocalTime currentTime) {
        // check connections
        List<ConnectionObject> connections = this.getConnections();
        for (ConnectionObject connection : connections) {
            if (connection.getDevice().getType().equals(AWS)) {
                continue;
            }
            if (connection.isConnect() && connection.getDevice().isConnected() && connection.getEndTime().compareTo(currentTime) < 0) {
                CommonUtil.endConnectionByTime(connection, this, currentTime);
            }
        }
    }

    /**
     * Update connection by math helper.
     *
     * @param currentTime the current time
     */
    public void updateConnectionByMathHelper(LocalTime currentTime) {
        List<ConnectionObject> connections = this.getConnections();
        for (ConnectionObject connection : connections) {
            if (connection.getDevice().getType().equals(AWS)) {
                continue;
            }
            if (!MathsHelper.satelliteIsVisibleFromDevice(this.getPosition(), this.getHeight(), connection.getDevice().getPosition()) && connection.isConnect()) {
                CommonUtil.endConnectionByVisible(connection, this, currentTime);
            }
        }
    }

    /**
     * Update possible connection.
     *
     * @param currentTime the current time
     * @param deviceList  the device list
     */
    public void updatePossibleConnection(LocalTime currentTime, List<Device> deviceList) {
        // update possibleConnections
        List<Device> possibleConnections = this.getPossibleConnections();
        possibleConnections.clear();

        for (Device device : deviceList) {
            // check visible
            if (MathsHelper.satelliteIsVisibleFromDevice(this.getPosition(), this.getHeight(), device.getPosition())
                    && supportDeviceList.contains(device.getType())) {
                // get activation time period and sort by startTime
                List<HashMap<String, LocalTime>> activationPeriods = device.getActivationPeriods();
                activationPeriods.sort(new Comparator<HashMap<String, LocalTime>>() {
                    @Override
                    public int compare(HashMap<String, LocalTime> o1, HashMap<String, LocalTime> o2) {
                        return o1.get("startTime").compareTo(o2.get("startTime"));
                    }
                });

                for (HashMap<String, LocalTime> activationPeriod : activationPeriods) {
                    LocalTime startTime = activationPeriod.get("startTime");
                    LocalTime endTime = activationPeriod.get("endTime");
                    if (startTime.compareTo(currentTime) <= 0 && endTime.compareTo(currentTime) >= 0) {
                        possibleConnections.add(device);
                        break;
                    }
                }
            }
        }

    }

    /**
     * Do connection and update connection.
     *
     * @param currentTime the current time
     * @param endTime     the end time
     * @param device      the device
     * @return the boolean
     */
    public boolean doConnectionAndUpdateConnection(LocalTime currentTime, LocalTime endTime, Device device) {
        List<ConnectionObject> connections = this.getConnections();
        connections.add(new ConnectionObject(device, currentTime, endTime, this, 0, true));
        this.deviceConnectAmountAdd(device);
        device.setConnected(true);
        return true;
    }

    /**
     * Is aws can connected.
     *
     * @param device the device
     * @return the boolean
     */
    public boolean isAwsCanConnected(Device device) {
        return false;
    }

    /**
     * Aws add new connection.
     *
     * @param device      the device
     * @param currentTime the current time
     */
    public void doAwsAddNewConnection(Device device, LocalTime currentTime) {
    }

    /**
     * Update aws first.
     *
     * @param satellite   the satellite
     * @param currentTime the current time
     */
    public void updateAwsFirst(Satellite satellite, LocalTime currentTime) {
        List<ConnectionObject> connections = satellite.getConnections();
        for (ConnectionObject connection : connections) {
            if (!connection.isConnect()) {
                continue;
            }
            if (!"AWSCloudServer".equals(connection.getDevice().getType())) {
                continue;
            }
            if (connection.getEndTime().compareTo(currentTime) < 0) {
                connection.getDevice().getConnectedList().remove(connection.getSatellite());
                CommonUtil.endConnectionByTime(connection, this, currentTime);
            } else if (!MathsHelper.satelliteIsVisibleFromDevice(satellite.getPosition(), satellite.getHeight(), connection.getDevice().getPosition())) {
                CommonUtil.endConnectionByVisible(connection, this, currentTime);
                connection.getDevice().getConnectedList().remove(connection.getSatellite());
            }
        }
    }

}
