package test.test_helpers;

import java.time.LocalTime;

import org.json.JSONArray;
import org.json.JSONObject;

public class ResponseHelper {
    private JSONArray devices = new JSONArray();
    private JSONArray satellites = new JSONArray();
    private LocalTime currentTime;

    public ResponseHelper(LocalTime currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public String toString() {
        JSONObject result = new JSONObject();
        result.put("currentTime", currentTime);
        result.put("devices", devices);
        result.put("satellites", satellites);

        return result.toString();
    }

    public ResponseHelper expectDevice(String type, String id, double position) {
        return expectDevice(type, id, position, false, new LocalTime[][] {});
    }

    public ResponseHelper expectDevice(String type, String id, double position, boolean isConnected,
            LocalTime[][] times) {
        JSONArray periods = new JSONArray();

        for (int i = 0; i < times.length; i++) {
            JSONObject time = new JSONObject();
            time.put("startTime", times[i][0].toString());
            time.put("endTime", times[i][1].toString());
            periods.put(time);
        }

        JSONObject device = new JSONObject();
        device.put("id", id);
        device.put("type", type);
        device.put("position", position);
        device.put("isConnected", isConnected);
        device.put("activationPeriods", periods);

        devices.put(device);
        return this;
    }

    public ResponseHelper expectSatellite(String type, String id, double height, double position, double velocity,
            String[] possibleConnections, DummyConnection[] connections) {
        JSONObject satellite = new JSONObject();
        satellite.put("id", id);
        satellite.put("type", type);
        satellite.put("height", height);
        satellite.put("velocity", velocity);
        satellite.put("position", position);
        satellite.put("possibleConnections", possibleConnections);

        JSONArray conns = new JSONArray();
        for (DummyConnection connection : connections) {
            JSONObject item = new JSONObject();
            item.put("satelliteId", id);
            item.put("startTime", connection.getStartTime());
            item.put("endTime", connection.getEndTime() == null ? JSONObject.NULL : connection.getEndTime());
            item.put("deviceId", connection.getDeviceId());
            item.put("minutesActive", connection.getMinutesActive());
            conns.put(item);
        }

        satellite.put("connections", conns);
        satellites.put(satellite);
        return this;
    }

    public ResponseHelper expectSatellite(String type, String id, double height, double position, double velocity,
            String[] possibleConnections) {
        return this.expectSatellite(type, id, height, position, velocity, possibleConnections,
                new DummyConnection[] {});
    }
}
