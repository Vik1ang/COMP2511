package test.test_helpers;

import java.time.LocalTime;

public class DummyConnection {
    private String deviceId;
    private LocalTime startTime;
    private LocalTime endTime;
    private int minutesActive;

    public DummyConnection(String deviceId, LocalTime startTime, LocalTime endTime, int minutesActive) {
        this.deviceId = deviceId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.minutesActive = minutesActive;
    }

    public DummyConnection(String deviceId, LocalTime startTime, int minutesActive) {
        this.deviceId = deviceId;
        this.startTime = startTime;
        this.endTime = null;
        this.minutesActive = minutesActive;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getMinutesActive() {
        return minutesActive;
    }
}
