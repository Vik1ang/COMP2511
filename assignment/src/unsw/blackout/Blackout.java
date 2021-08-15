package unsw.blackout;

import org.json.JSONArray;
import org.json.JSONObject;
import unsw.connection.ConnectionObject;
import unsw.device.*;
import unsw.satellite.*;

import java.time.LocalTime;
import java.util.*;

public class Blackout {

    List<Device> deviceList = new ArrayList<>();
    List<Satellite> satelliteList = new ArrayList<>();
    LocalTime currentTime = LocalTime.of(0, 0);

    public void createDevice(String id, String type, double position) {
        if (deviceList == null) {
            deviceList = new ArrayList<>();
        }
        Device device = null;
        switch (type) {
            case "HandheldDevice":
                device = new Handheld(id, false, position, type, new ArrayList<>());
                break;
            case "MobileXPhone":
                device = new MobileXPhone(id, false, position, type, new ArrayList<>());
                break;
            case "LaptopDevice":
                device = new Laptop(id, false, position, type, new ArrayList<>());
                break;
            case "DesktopDevice":
                device = new Desktop(id, false, position, type, new ArrayList<>());
                break;
            case "AWSCloudServer":
                device = new AWSCloudServer(id, false, position, type, new ArrayList<>());
                break;
            default:
                break;
        }
        deviceList.add(device);
    }

    public void createSatellite(String id, String type, double height, double position) {
        if (satelliteList == null) {
            satelliteList = new ArrayList<>();
        }
        Satellite satellite = null;
        switch (type) {
            case "SpaceXSatellite":
                satellite = new SpaceXSatellite(new ArrayList<>(), height, id, position, new ArrayList<>(), type);
                break;
            case "BlueOriginSatellite":
                satellite = new BlueOriginSatellite(new ArrayList<>(), height, id, position, new ArrayList<>(), type);
                break;
            case "NasaSatellite":
                satellite = new NasaSatellite(new ArrayList<>(), height, id, position, new ArrayList<>(), type);
                break;
            case "SovietSatellite":
                satellite = new SovietSatellite(new ArrayList<>(), height, id, position, new ArrayList<>(), type);
            default:
                break;
        }
        satelliteList.add(satellite);

    }

    public void scheduleDeviceActivation(String deviceId, LocalTime start, int durationInMinutes) {
        for (Device device : deviceList) {
            if (device.getId().equals(deviceId)) {
                HashMap<String, LocalTime> hashMap = new HashMap<>(2);
                hashMap.put("startTime", start);
                hashMap.put("endTime", start.plusMinutes(durationInMinutes));
                List<HashMap<String, LocalTime>> activationPeriods = device.getActivationPeriods();
                if (activationPeriods == null) {
                    activationPeriods = new ArrayList<>();
                    device.setActivationPeriods(activationPeriods);
                }
                activationPeriods.add(hashMap);
                break;
            }
        }
    }

    public void removeSatellite(String id) {
        for (Satellite satellite : satelliteList) {
            if (satellite.getId().equals(id)) {
                satelliteList.remove(satellite);
                break;
            }
        }
    }

    public void removeDevice(String id) {
        for (Device device : deviceList) {
            if (device.getId().equals(id)) {
                deviceList.remove(device);
                break;
            }
        }
    }

    public void moveDevice(String id, double newPos) {
        for (Device device : deviceList) {
            if (device.getId().equals(id)) {
                device.setPosition(newPos);
                break;
            }
        }
    }

    public JSONObject showWorldState() {
        JSONObject result = new JSONObject();
        JSONArray devices = new JSONArray();
        JSONArray satellites = new JSONArray();

        if (currentTime == null) {
            currentTime = LocalTime.of(0, 0);
        }

        // sort
        if (deviceList != null) {
            deviceList.sort((o1, o2) -> o1.getId().compareTo(o2.getId()));
        }

        if (satelliteList != null) {
            satelliteList.sort((o1, o2) -> o1.getId().compareTo(o2.getId()));
        }

        if (deviceList != null) {
            handleDeviceList(devices);
        }
        if (satelliteList != null) {
            handleSatelliteList(satellites);
        }

        result.put("devices", devices);
        result.put("satellites", satellites);
        result.put("currentTime", currentTime);

        return result;
    }

    public void simulate(int tickDurationInMinutes) {
        if (currentTime == null) {
            currentTime = LocalTime.of(0, 0);
        }
        if (satelliteList == null) {
            currentTime = currentTime.plusMinutes(tickDurationInMinutes);
            return;
        }

        for (int i = 0; i < tickDurationInMinutes; i++) {
            // currentTime = currentTime.plusMinutes(1);

            // update connection by Activation
            for (Satellite satellite : satelliteList) {
                satellite.updateConnectionByActivationPeriod(currentTime);
            }

            // update connection by MathHelper
            for (Satellite satellite : satelliteList) {
                satellite.updateConnectionByMathHelper(currentTime);
            }

            // update possibleConnection
            for (Satellite satellite : satelliteList) {
                satellite.updatePossibleConnection(currentTime, deviceList);
            }

            // update aws device
            for (Satellite satellite : satelliteList) {
                satellite.updateAwsFirst(satellite, currentTime);
            }


            // satelliteList.sort((o1, o2) -> Double.compare(o1.getPosition(), o2.getPosition()));
            deviceList.sort(new Comparator<Device>() {
                @Override
                public int compare(Device o1, Device o2) {
                    return o1.getId().compareTo(o2.getId());
                }
            });
            // device make connection
            for (Device device : deviceList) {
                device.updateConnectionByDevice(satelliteList, currentTime);
            }

            // check aws again
            for (Device device : deviceList) {
                device.updateAwsSecond(currentTime);
            }

            // update position
            for (Satellite satellite : satelliteList) {
                satellite.checkSatelliteSpeed();
                satellite.updatePosition();
            }

            // update word time
            currentTime = currentTime.plusMinutes(1);
        }
    }

    private void handleSatelliteList(JSONArray satellites) {

        for (Satellite satellite : satelliteList) {
            List<Device> possibleConnections = satellite.getPossibleConnections();
            possibleConnections.clear();
            for (Device device : deviceList) {
                if (MathsHelper.satelliteIsVisibleFromDevice(satellite.getPosition(), satellite.getHeight(), device.getPosition())
                        && satellite.getSupportDeviceList().contains(device.getType())) {
                    possibleConnections.add(device);
                }
            }
            ArrayList<String> possibleReturnList = new ArrayList<>();
            for (Device possibleConnection : possibleConnections) {
                possibleReturnList.add(possibleConnection.getId());
            }

            JSONArray connectionList = new JSONArray();
            List<ConnectionObject> connections = satellite.getConnections();
            connections.sort(new Comparator<ConnectionObject>() {
                @Override
                public int compare(ConnectionObject o1, ConnectionObject o2) {
                    if (o1.getStartTime() != o2.getStartTime()) {
                        return o1.getStartTime().compareTo(o2.getStartTime());
                    }
                    return o1.getDevice().getId().compareTo(o2.getDevice().getId());
                }
            });
            for (ConnectionObject connection : satellite.getConnections()) {
                JSONObject deviceTemp = new JSONObject();
                deviceTemp.put("deviceId", connection.getDevice().getId());
                deviceTemp.put("minutesActive", connection.getMinutesActive());
                deviceTemp.put("satelliteId", connection.getSatellite().getId());
                deviceTemp.put("startTime", connection.getStartTime());
                deviceTemp.put("endTime", connection.getEndTime());
                connectionList.put(deviceTemp);
            }

            JSONObject satelliteObject = new JSONObject();
            satelliteObject.put("id", satellite.getId());
            satelliteObject.put("position", satellite.getPosition());
            satelliteObject.put("velocity", satellite.getVelocity());
            satelliteObject.put("possibleConnections", possibleReturnList);
            satelliteObject.put("type", satellite.getType());
            satelliteObject.put("connections", connectionList);
            satelliteObject.put("height", satellite.getHeight());
            satellites.put(satelliteObject);

        }
    }

    private void handleDeviceList(JSONArray devices) {
        for (Device device : deviceList) {
            JSONObject deviceObject = new JSONObject();
            deviceObject.put("id", device.getId());
            deviceObject.put("isConnected", device.isConnected());
            deviceObject.put("position", device.getPosition());
            deviceObject.put("type", device.getType());
            if (device.getActivationPeriods() == null) {
                deviceObject.put("activationPeriods", new String[0]);
            } else {
                List<HashMap<String, LocalTime>> activationPeriods = device.getActivationPeriods();
                activationPeriods.sort((o1, o2) -> o1.get("startTime").compareTo(o2.get("startTime")));
                deviceObject.put("activationPeriods", device.getActivationPeriods());
            }
            devices.put(deviceObject);
        }
    }
}