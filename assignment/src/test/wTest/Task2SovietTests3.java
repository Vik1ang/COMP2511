package test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import test.test_helpers.DummyConnection;
import test.test_helpers.ResponseHelper;
import test.test_helpers.TestHelper;

import java.time.LocalTime;

@TestInstance(value = Lifecycle.PER_CLASS)
public class Task2SovietTests3 {
    @Test
    public void testSovietADay() {
        // Task 2
        // test SovietSatellite for one day
        // Creates 1 satellite and 3 devices
        // Activates 3 devices and then schedules connections

        String initialWorldState = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("SovietSatellite", "Satellite1", 5000, 340, 100, new String[] {"DeviceB", "DeviceC"})
                .expectDevice("HandheldDevice", "DeviceA", 30, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(13, 20) } })
                .expectDevice("LaptopDevice", "DeviceB", 40, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(13, 20) } })
                .expectDevice("DesktopDevice", "DeviceC", 50, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(13, 20) } })
                .toString();

        // then simulates for a full day (1440 mins)
        String afterADay = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("SovietSatellite", "Satellite1", 5000, 311.20, -100,
                        new String[] { },
                        new DummyConnection[] {
                                new DummyConnection("DeviceB", LocalTime.of(0, 0), LocalTime.of(10, 27), 622),
                                new DummyConnection("DeviceC", LocalTime.of(0, 0), LocalTime.of(2, 07), 116),
                        })
                .expectDevice("HandheldDevice", "DeviceA", 30, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(13, 20) } })
                .expectDevice("LaptopDevice", "DeviceB", 40, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(13, 20) } })
                .expectDevice("DesktopDevice", "DeviceC", 50, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(13, 20) } })
                .toString();

        TestHelper plan = new TestHelper().createDevice("HandheldDevice", "DeviceA", 30)
                .createDevice("LaptopDevice", "DeviceB", 40)
                .createDevice("DesktopDevice", "DeviceC", 50)
                .createSatellite("SovietSatellite", "Satellite1", 5000, 340)
                .scheduleDeviceActivation("DeviceA", LocalTime.of(0, 0), 800)
                .scheduleDeviceActivation("DeviceB", LocalTime.of(0, 0), 800)
                .scheduleDeviceActivation("DeviceC", LocalTime.of(0, 0), 800)
                .showWorldState(initialWorldState)
                .simulate(1440)
                .showWorldState(afterADay);
        plan.executeTestPlan();
    }

    @Test
    public void testSovietWith10DeviceADay() {
        // Task 2
        // test SovietSatellite for one day
        // Creates 1 satellite and 10 devices
        // Activates 10 Laptop devices and then schedules connections

        String initialWorldState = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("SovietSatellite", "Satellite1", 10000, 191, 100,
                        new String[] {"Device0", "Device1","Device2", "Device3","Device4", "Device5","Device6", "Device7",
                                        "Device8", "Device9"})

                .expectDevice("LaptopDevice", "Device0", 140, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(00, 10) } })
                .expectDevice("LaptopDevice", "Device1", 141, false,
                        new LocalTime[][] { { LocalTime.of(0, 1), LocalTime.of(00, 11) } })
                .expectDevice("LaptopDevice", "Device2", 142, false,
                        new LocalTime[][] { { LocalTime.of(0, 2), LocalTime.of(00, 12) } })
                .expectDevice("LaptopDevice", "Device3", 143, false,
                        new LocalTime[][] { { LocalTime.of(0, 3), LocalTime.of(00, 13) } })
                .expectDevice("LaptopDevice", "Device4", 144, false,
                        new LocalTime[][] { { LocalTime.of(0, 4), LocalTime.of(00, 14) } })
                .expectDevice("LaptopDevice", "Device5", 145, false,
                        new LocalTime[][] { { LocalTime.of(0, 5), LocalTime.of(00, 15) } })
                .expectDevice("LaptopDevice", "Device6", 146, false,
                        new LocalTime[][] { { LocalTime.of(0, 6), LocalTime.of(00, 16) } })
                .expectDevice("LaptopDevice", "Device7", 147, false,
                        new LocalTime[][] { { LocalTime.of(0, 7), LocalTime.of(00, 17) } })
                .expectDevice("LaptopDevice", "Device8", 148, false,
                        new LocalTime[][] { { LocalTime.of(0, 8), LocalTime.of(00, 18) } })
                .expectDevice("LaptopDevice", "Device9", 149, false,
                        new LocalTime[][] { { LocalTime.of(0, 9), LocalTime.of(00, 19) } })
                .toString();

        // then simulates for a full day (1440 mins)
        String afterADay = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("SovietSatellite", "Satellite1", 10000, 176.60, -100,
                        new String[] {"Device0", "Device1","Device2", "Device3","Device4", "Device5","Device6", "Device7",
                                "Device8", "Device9"},
                        new DummyConnection[] {
                                new DummyConnection("Device0", LocalTime.of(0, 0), LocalTime.of(0, 9), 5),
                                new DummyConnection("Device1", LocalTime.of(0, 1), LocalTime.of(0, 10), 5),
                                new DummyConnection("Device2", LocalTime.of(0, 2), LocalTime.of(0, 10), 4),
                                new DummyConnection("Device3", LocalTime.of(0, 3), LocalTime.of(0, 10), 3),
                                new DummyConnection("Device4", LocalTime.of(0, 4), LocalTime.of(0, 10), 2),
                                new DummyConnection("Device5", LocalTime.of(0, 5), LocalTime.of(0, 10), 1),
                                new DummyConnection("Device6", LocalTime.of(0, 6), LocalTime.of(0, 10), 0),
                                new DummyConnection("Device7", LocalTime.of(0, 7), LocalTime.of(0, 10), 0),
                                new DummyConnection("Device8", LocalTime.of(0, 8), LocalTime.of(0, 10), 0),
                                new DummyConnection("Device9", LocalTime.of(0, 9), LocalTime.of(0, 10), 0),
                                new DummyConnection("Device0", LocalTime.of(0, 10), LocalTime.of(0, 10), 0),
                                new DummyConnection("Device1", LocalTime.of(0, 10), LocalTime.of(0, 12), 0),
                                new DummyConnection("Device2", LocalTime.of(0, 10), LocalTime.of(0, 13), 0),
                                new DummyConnection("Device3", LocalTime.of(0, 10), LocalTime.of(0, 14), 0),
                                new DummyConnection("Device4", LocalTime.of(0, 10), LocalTime.of(0, 15), 0),
                                new DummyConnection("Device5", LocalTime.of(0, 10), LocalTime.of(0, 16), 1),
                                new DummyConnection("Device6", LocalTime.of(0, 10), LocalTime.of(0, 17), 2),
                                new DummyConnection("Device7", LocalTime.of(0, 10), LocalTime.of(0, 18), 3),
                                new DummyConnection("Device8", LocalTime.of(0, 10), LocalTime.of(0, 19), 4),
                                new DummyConnection("Device9", LocalTime.of(0, 10), LocalTime.of(0, 20), 5)

                        })
                .expectDevice("LaptopDevice", "Device0", 140, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(00, 10) } })
                .expectDevice("LaptopDevice", "Device1", 141, false,
                        new LocalTime[][] { { LocalTime.of(0, 1), LocalTime.of(00, 11) } })
                .expectDevice("LaptopDevice", "Device2", 142, false,
                        new LocalTime[][] { { LocalTime.of(0, 2), LocalTime.of(00, 12) } })
                .expectDevice("LaptopDevice", "Device3", 143, false,
                        new LocalTime[][] { { LocalTime.of(0, 3), LocalTime.of(00, 13) } })
                .expectDevice("LaptopDevice", "Device4", 144, false,
                        new LocalTime[][] { { LocalTime.of(0, 4), LocalTime.of(00, 14) } })
                .expectDevice("LaptopDevice", "Device5", 145, false,
                        new LocalTime[][] { { LocalTime.of(0, 5), LocalTime.of(00, 15) } })
                .expectDevice("LaptopDevice", "Device6", 146, false,
                        new LocalTime[][] { { LocalTime.of(0, 6), LocalTime.of(00, 16) } })
                .expectDevice("LaptopDevice", "Device7", 147, false,
                        new LocalTime[][] { { LocalTime.of(0, 7), LocalTime.of(00, 17) } })
                .expectDevice("LaptopDevice", "Device8", 148, false,
                        new LocalTime[][] { { LocalTime.of(0, 8), LocalTime.of(00, 18) } })
                .expectDevice("LaptopDevice", "Device9", 149, false,
                        new LocalTime[][] { { LocalTime.of(0, 9), LocalTime.of(00, 19) } })
                .toString();

        TestHelper plan = new TestHelper().createDevice("LaptopDevice", "Device1", 141)
                .createDevice("LaptopDevice", "Device0", 140)
                .createDevice("LaptopDevice", "Device2", 142)
                .createDevice("LaptopDevice", "Device3", 143)
                .createDevice("LaptopDevice", "Device4", 144)
                .createDevice("LaptopDevice", "Device5", 145)
                .createDevice("LaptopDevice", "Device6", 146)
                .createDevice("LaptopDevice", "Device7", 147)
                .createDevice("LaptopDevice", "Device8", 148)
                .createDevice("LaptopDevice", "Device9", 149)
                .createSatellite("SovietSatellite", "Satellite1", 10000, 191)
                .scheduleDeviceActivation("Device0", LocalTime.of(0, 0), 10)
                .scheduleDeviceActivation("Device1", LocalTime.of(0, 1), 10)
                .scheduleDeviceActivation("Device2", LocalTime.of(0, 2), 10)
                .scheduleDeviceActivation("Device3", LocalTime.of(0, 3), 10)
                .scheduleDeviceActivation("Device4", LocalTime.of(0, 4), 10)
                .scheduleDeviceActivation("Device5", LocalTime.of(0, 5), 10)
                .scheduleDeviceActivation("Device6", LocalTime.of(0, 6), 10)
                .scheduleDeviceActivation("Device7", LocalTime.of(0, 7), 10)
                .scheduleDeviceActivation("Device8", LocalTime.of(0, 8), 10)
                .scheduleDeviceActivation("Device9", LocalTime.of(0, 9), 10)
                .showWorldState(initialWorldState)
                .simulate(1440)
                .showWorldState(afterADay);
        plan.executeTestPlan();
    }
}
