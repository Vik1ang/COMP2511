package test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import java.time.LocalTime;

import test.test_helpers.DummyConnection;
import test.test_helpers.ResponseHelper;
import test.test_helpers.TestHelper;

@TestInstance(value = Lifecycle.PER_CLASS)
public class Task2ExtraTests {
    @Test
    public void testSmallId() {
        // Task 2
        // Smallest device ID takes priority over larger ones.

        String initialWorldState = new ResponseHelper(LocalTime.of(0, 0))
            .expectSatellite("NasaSatellite", "Satellite1", 10000, 30, 85, new String[] { "DeviceA", "DeviceB", "DeviceC", "DeviceD", "DeviceE", "DeviceF", "DeviceG" })
            .expectDevice("HandheldDevice", "DeviceA", 30, false, new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
            .expectDevice("LaptopDevice", "DeviceB", 31, false, new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
            .expectDevice("DesktopDevice", "DeviceC", 32, false, new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
            .expectDevice("DesktopDevice", "DeviceD", 33, false, new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
            .expectDevice("HandheldDevice", "DeviceE", 34, false, new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
            .expectDevice("LaptopDevice", "DeviceF", 35, false, new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
            .expectDevice("DesktopDevice", "DeviceG", 36, false, new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
            .toString();

        // then simulates for a full day (1440 mins)
        String afterADay = new ResponseHelper(LocalTime.of(0, 0))
            .expectSatellite("NasaSatellite", "Satellite1", 10000, 42.24, 85,
                new String[] { "DeviceA", "DeviceB", "DeviceC", "DeviceD", "DeviceE", "DeviceF", "DeviceG" },
                new DummyConnection[] {
                    new DummyConnection("DeviceA", LocalTime.of(0, 0), LocalTime.of(6, 41), 390),
                    new DummyConnection("DeviceB", LocalTime.of(0, 0), LocalTime.of(6, 41), 390),
                    new DummyConnection("DeviceC", LocalTime.of(0, 0), LocalTime.of(6, 41), 390),
                    new DummyConnection("DeviceD", LocalTime.of(0, 0), LocalTime.of(6, 41), 390),
                    new DummyConnection("DeviceE", LocalTime.of(0, 0), LocalTime.of(6, 41), 390),
                    new DummyConnection("DeviceF", LocalTime.of(0, 0), LocalTime.of(6, 41), 390), //
                })
            .expectDevice("HandheldDevice", "DeviceA", 30, false, new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
            .expectDevice("LaptopDevice", "DeviceB", 31, false, new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
            .expectDevice("DesktopDevice", "DeviceC", 32, false, new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
            .expectDevice("DesktopDevice", "DeviceD", 33, false, new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
            .expectDevice("HandheldDevice", "DeviceE", 34, false, new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
            .expectDevice("LaptopDevice", "DeviceF", 35, false, new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
            .expectDevice("DesktopDevice", "DeviceG", 36, false, new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
            .toString();

        TestHelper plan = new TestHelper()
            .createDevice("HandheldDevice", "DeviceA", 30)
            .createDevice("LaptopDevice", "DeviceB", 31)
            .createDevice("DesktopDevice", "DeviceC", 32)
            .createDevice("DesktopDevice", "DeviceD", 33)
            .createDevice("HandheldDevice", "DeviceE", 34)
            .createDevice("LaptopDevice", "DeviceF", 35)
            .createDevice("DesktopDevice", "DeviceG", 36)
            .createSatellite("NasaSatellite", "Satellite1", 10000, 30)
            .scheduleDeviceActivation("DeviceA", LocalTime.of(0, 0), 400)
            .scheduleDeviceActivation("DeviceB", LocalTime.of(0, 0), 400)
            .scheduleDeviceActivation("DeviceC", LocalTime.of(0, 0), 400)
            .scheduleDeviceActivation("DeviceD", LocalTime.of(0, 0), 400)
            .scheduleDeviceActivation("DeviceE", LocalTime.of(0, 0), 400)
            .scheduleDeviceActivation("DeviceF", LocalTime.of(0, 0), 400)
            .scheduleDeviceActivation("DeviceG", LocalTime.of(0, 0), 400)
            .showWorldState(initialWorldState)
            .simulate(1440)
            .showWorldState(afterADay);
        plan.executeTestPlan();
    }

    @Test
    public void testSmallAngle() {
        // Task 2
        // Smallest satellite angle takes priority

        String initialWorldState = new ResponseHelper(LocalTime.of(0, 0))
            .expectSatellite("NasaSatellite", "Satellite1", 10000, 30, 85, new String[] { "DeviceA" })
            .expectSatellite("NasaSatellite", "Satellite2", 10000, 50, 85, new String[] { "DeviceA" })
            .expectSatellite("NasaSatellite", "Satellite3", 10000, 0, 85, new String[] { "DeviceA" })
            .expectSatellite("NasaSatellite", "Satellite4", 10000, 359, 85, new String[] { "DeviceA" })
            .expectDevice("HandheldDevice", "DeviceA", 30, false, new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) }, { LocalTime.of(12, 30), LocalTime.of(19, 10) } })
            .toString();

        // then simulates for half a day
        String afterHalfADay = new ResponseHelper(LocalTime.of(12, 0))
            .expectSatellite("NasaSatellite", "Satellite1", 10000, 36.12, 85,
                new String[] { "DeviceA" })
            .expectSatellite("NasaSatellite", "Satellite2", 10000, 56.12, 85,
                new String[] { "DeviceA" })
            .expectSatellite("NasaSatellite", "Satellite3", 10000, 6.12, 85,
                new String[] { "DeviceA" },
                new DummyConnection[] {
                    new DummyConnection("DeviceA", LocalTime.of(0, 0), LocalTime.of(6, 41), 390), //
                })
            .expectSatellite("NasaSatellite", "Satellite4", 10000, 5.12, 85,
                new String[] { "DeviceA" })
            .expectDevice("HandheldDevice", "DeviceA", 30, false, new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) }, { LocalTime.of(12, 30), LocalTime.of(19, 10) } })
            .toString();

        String afterADay = new ResponseHelper(LocalTime.of(0, 0))
            .expectSatellite("NasaSatellite", "Satellite1", 10000, 42.24, 85,
                new String[] { "DeviceA" })
            .expectSatellite("NasaSatellite", "Satellite2", 10000, 62.24, 85,
                new String[] { "DeviceA" })
            .expectSatellite("NasaSatellite", "Satellite3", 10000, 12.24, 85,
                new String[] { "DeviceA" },
                new DummyConnection[] {
                    new DummyConnection("DeviceA", LocalTime.of(0, 0), LocalTime.of(6, 41), 390), //
                })
            .expectSatellite("NasaSatellite", "Satellite4", 10000, 11.24, 85,
                new String[] { "DeviceA" },
                new DummyConnection[] {
                    new DummyConnection("DeviceA", LocalTime.of(12, 30), LocalTime.of(19, 11), 390), //
                })
            .expectDevice("HandheldDevice", "DeviceA", 30, false, new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) }, { LocalTime.of(12, 30), LocalTime.of(19, 10) } })
            .toString();

        TestHelper plan = new TestHelper()
            .createDevice("HandheldDevice", "DeviceA", 30)
            .createSatellite("NasaSatellite", "Satellite1", 10000, 30)
            .createSatellite("NasaSatellite", "Satellite2", 10000, 50)
            .createSatellite("NasaSatellite", "Satellite3", 10000, 0)
            .createSatellite("NasaSatellite", "Satellite4", 10000, 359)
            .scheduleDeviceActivation("DeviceA", LocalTime.of(0, 0), 400)
            .scheduleDeviceActivation("DeviceA", LocalTime.of(12, 30), 400)
            .showWorldState(initialWorldState)
            .simulate(720)
            .showWorldState(afterHalfADay)
            .simulate(720)
            .showWorldState(afterADay);
        plan.executeTestPlan();
    }
}
