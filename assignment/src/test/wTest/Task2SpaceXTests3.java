package test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import test.test_helpers.DummyConnection;
import test.test_helpers.ResponseHelper;
import test.test_helpers.TestHelper;

import java.time.LocalTime;

@TestInstance(value = Lifecycle.PER_CLASS)
public class Task2SpaceXTests3 {
    @Test
    public void testSpaceXADay() {
        // Task 2
        // test spaceXSatellite for one day
        // Creates 1 satellite and 3 devices
        // Activates 3 devices (HandheldDevice has 2 periods) and then schedules connections

        String initialWorldState = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("SpaceXSatellite", "Satellite1", 10000, 340, 55.50, new String[] { "DeviceA"})
                .expectDevice("HandheldDevice", "DeviceA", 30, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) },
                                            { LocalTime.of(8, 0), LocalTime.of(16, 20) }})
                .expectDevice("LaptopDevice", "DeviceB", 40, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("DesktopDevice", "DeviceC", 50, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .toString();

        // then simulates for a full day (1440 mins)
        String afterADay = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("SpaceXSatellite", "Satellite1", 10000, 347.99, 55.50,
                        new String[] { "DeviceA"},
                        new DummyConnection[] {
                                new DummyConnection("DeviceA", LocalTime.of(0, 0), LocalTime.of(6, 41), 400),
                                new DummyConnection("DeviceA", LocalTime.of(8, 0), LocalTime.of(16, 21), 500), //
                        })
                .expectDevice("HandheldDevice", "DeviceA", 30, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) },
                                            { LocalTime.of(8, 0), LocalTime.of(16, 20) } })
                .expectDevice("LaptopDevice", "DeviceB", 40, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("DesktopDevice", "DeviceC", 50, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .toString();

        TestHelper plan = new TestHelper().createDevice("HandheldDevice", "DeviceA", 30)
                .createDevice("DesktopDevice", "DeviceC", 50)
                .createDevice("LaptopDevice", "DeviceB", 40)
                .createSatellite("SpaceXSatellite", "Satellite1", 10000, 340)
                .scheduleDeviceActivation("DeviceA", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("DeviceA", LocalTime.of(8, 0), 500)
                .scheduleDeviceActivation("DeviceB", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("DeviceC", LocalTime.of(0, 0), 400)
                .showWorldState(initialWorldState)
                .simulate(1440)
                .showWorldState(afterADay);
        plan.executeTestPlan();
    }

    @Test
    public void testSingleSpaceX3Days() {
        // Task 2
        // test spaceXSatellite for 3 days
        // Creates 1 satellite and 3 devices
        // Activates 3 devices (HandheldDevice has 2 periods) and then schedules connections

        String initialWorldState = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("SpaceXSatellite", "Satellite1", 10000, 340, 55.50, new String[] { "DeviceA"})
                .expectDevice("HandheldDevice", "DeviceA", 30, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) },
                                { LocalTime.of(8, 0), LocalTime.of(16, 20) }})
                .expectDevice("LaptopDevice", "DeviceB", 40, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("DesktopDevice", "DeviceC", 50, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .toString();

        // then simulates for 3 days (4320 mins)
        String afterADay = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("SpaceXSatellite", "Satellite1", 10000, 3.98, 55.50,
                        new String[] { "DeviceA"},
                        new DummyConnection[] {
                                new DummyConnection("DeviceA", LocalTime.of(0, 0), LocalTime.of(6, 41), 400),
                                new DummyConnection("DeviceA", LocalTime.of(0, 0), LocalTime.of(6, 41), 400),
                                new DummyConnection("DeviceA", LocalTime.of(0, 0), LocalTime.of(6, 41), 400),
                                new DummyConnection("DeviceA", LocalTime.of(8, 0), LocalTime.of(16, 21), 500),
                                new DummyConnection("DeviceA", LocalTime.of(8, 0), LocalTime.of(16, 21), 500),
                                new DummyConnection("DeviceA", LocalTime.of(8, 0), LocalTime.of(16, 21), 500),
                        })
                .expectDevice("HandheldDevice", "DeviceA", 30, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) },
                                { LocalTime.of(8, 0), LocalTime.of(16, 20) } })
                .expectDevice("LaptopDevice", "DeviceB", 40, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("DesktopDevice", "DeviceC", 50, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .toString();

        TestHelper plan = new TestHelper().createDevice("HandheldDevice", "DeviceA", 30)
                .createDevice("LaptopDevice", "DeviceB", 40)
                .createDevice("DesktopDevice", "DeviceC", 50)
                .createSatellite("SpaceXSatellite", "Satellite1", 10000, 340)
                .scheduleDeviceActivation("DeviceA", LocalTime.of(8, 0), 500)
                .scheduleDeviceActivation("DeviceA", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("DeviceB", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("DeviceC", LocalTime.of(0, 0), 400)
                .showWorldState(initialWorldState)
                .simulate(4320)
                .showWorldState(afterADay);
        plan.executeTestPlan();
    }

    @Test
    public void test2SpaceX2Days() {
        // Task 2
        // test 2 similar position spaceXSatellite for 2 days
        // Creates 1 satellite and 3 devices
        // Activates 3 devices (HandheldDevice has 2 periods) and then schedules connections

        String initialWorldState = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("SpaceXSatellite", "Satellite1", 10000, 359, 55.50,
                        new String[] { "DeviceA", "DeviceB", "DeviceC"})
                .expectSatellite("SpaceXSatellite", "Satellite2", 8000, 10, 55.50,
                        new String[] { "DeviceA", "DeviceB", "DeviceC"})
                .expectDevice("HandheldDevice", "DeviceA", 30, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "DeviceB", 40, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "DeviceC", 50, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .toString();

        // then simulates for 2 days (2880 mins)
        String afterADay = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("SpaceXSatellite", "Satellite1", 10000, 14.98, 55.50,
                        new String[] { "DeviceA", "DeviceB", "DeviceC"},
                        new DummyConnection[] {
                                new DummyConnection("DeviceA", LocalTime.of(0, 0), LocalTime.of(6, 41), 400),
                                new DummyConnection("DeviceB", LocalTime.of(0, 0), LocalTime.of(6, 41), 400),
                                new DummyConnection("DeviceC", LocalTime.of(0, 0), LocalTime.of(6, 41), 400)
                        })
                .expectSatellite("SpaceXSatellite", "Satellite2", 8000, 29.98, 55.50,
                        new String[] { "DeviceA", "DeviceB", "DeviceC"},
                        new DummyConnection[] {
                                new DummyConnection("DeviceA", LocalTime.of(0, 0), LocalTime.of(6, 41), 400),
                                new DummyConnection("DeviceB", LocalTime.of(0, 0), LocalTime.of(6, 41), 400),
                                new DummyConnection("DeviceC", LocalTime.of(0, 0), LocalTime.of(6, 41), 400)
                        })
                .expectDevice("HandheldDevice", "DeviceA", 30, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "DeviceB", 40, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "DeviceC", 50, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .toString();

        TestHelper plan = new TestHelper().createDevice("HandheldDevice", "DeviceA", 30)
                .createDevice("HandheldDevice", "DeviceB", 40)
                .createDevice("HandheldDevice", "DeviceC", 50)
                .createSatellite("SpaceXSatellite", "Satellite1", 10000, 359)
                .createSatellite("SpaceXSatellite", "Satellite2", 8000, 10)
                .scheduleDeviceActivation("DeviceA", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("DeviceB", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("DeviceC", LocalTime.of(0, 0), 400)
                .showWorldState(initialWorldState)
                .simulate(2880)
                .showWorldState(afterADay);
        plan.executeTestPlan();
    }

}
