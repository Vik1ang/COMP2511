package test.wTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import test.test_helpers.DummyConnection;
import test.test_helpers.ResponseHelper;
import test.test_helpers.TestHelper;

import java.time.LocalTime;

@TestInstance(value = Lifecycle.PER_CLASS)
public class Task3Tests2 {
    @Test
    public void testMobileXADay() {
        // Task 2
        // test MobileXPhone for one day
        // Creates 1 BlueOriginSatellite (smaller angle), 1 SpaceXSatellite (larger angle)
        // 1 MobileXPhone, 1 HandheldDevice, 1 DesktopDevice

        String initialWorldState = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("BlueOriginSatellite", "BlueOrigin", 10000, 0, 141.67,
                        new String[] { "Desktop","Handheld","MobileX"})
                .expectSatellite("SpaceXSatellite", "SpaceX", 10000,100, 55.5,
                        new String[] { "Handheld","MobileX"})
                .expectDevice("DesktopDevice", "Desktop", 60, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld", 40, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("MobileXPhone", "MobileX", 50, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .toString();

        // then simulates for a full day (1440 mins)
        String afterADay = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("BlueOriginSatellite", "BlueOrigin", 10000, 20.4, 141.67,
                        new String[] { "Desktop","Handheld","MobileX"},
                        new DummyConnection[] {
                                new DummyConnection("Desktop", LocalTime.of(0, 0), LocalTime.of(6, 41), 395),
                                new DummyConnection("Handheld", LocalTime.of(0, 0), LocalTime.of(6, 41), 399),
                        })

                .expectSatellite("SpaceXSatellite", "SpaceX", 10000,107.99, 55.5,
                        new String[] { "Handheld","MobileX"},
                        new DummyConnection[] {
                                new DummyConnection("MobileX", LocalTime.of(0, 0), LocalTime.of(6, 41), 400),
                        })
                .expectDevice("DesktopDevice", "Desktop", 60, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld", 40, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("MobileXPhone", "MobileX", 50, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .toString();

        TestHelper plan = new TestHelper()
                .createDevice("HandheldDevice", "Handheld", 40)
                .createDevice("MobileXPhone", "MobileX", 50)
                .createDevice("DesktopDevice", "Desktop", 60)
                .createSatellite("SpaceXSatellite", "SpaceX", 10000, 100)
                .createSatellite("BlueOriginSatellite", "BlueOrigin", 10000, 0)
                .scheduleDeviceActivation("Handheld", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("MobileX", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Desktop", LocalTime.of(0, 0), 400)
                .showWorldState(initialWorldState)
                .simulate(1440)
                .showWorldState(afterADay);
        plan.executeTestPlan();
    }

    @Test
    public void testMobileXNoSpaceXADay() {
        // Task 3
        // test MobileXPhone for one day
        // Creates 1 SovietSatellite(smaller angle), 1 BlueOriginSatellite (larger angle)
        // 1 MobileXPhone, 1 HandheldDevice, 1 DesktopDevice
        // MobileXPhone should act as normal handheld device

        String initialWorldState = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("BlueOriginSatellite", "BlueOrigin", 10000, 190, 141.67,
                        new String[] { "Desktop","Handheld","MobileX"})
                .expectSatellite("SovietSatellite", "Soviet", 8000, 150, 100,
                        new String[] {"Desktop"})
                .expectDevice("DesktopDevice", "Desktop", 160, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld", 140, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("MobileXPhone", "MobileX", 150, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .toString();

        // then simulates for a full day (1440 mins)
        String afterADay = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("BlueOriginSatellite", "BlueOrigin", 10000, 210.40, 141.67,
                        new String[] { "Desktop","Handheld","MobileX"},
                        new DummyConnection[] {
                                new DummyConnection("Handheld", LocalTime.of(0, 0), LocalTime.of(6, 41), 399),
                                new DummyConnection("MobileX", LocalTime.of(0, 0), LocalTime.of(6, 41), 399),
                        })
                .expectSatellite("SovietSatellite", "Soviet", 8000, 168.00, 100,
                        new String[] {"Desktop"},
                        new DummyConnection[] {
                                new DummyConnection("Desktop", LocalTime.of(0, 0), LocalTime.of(6, 41), 390),
                        })
                .expectDevice("DesktopDevice", "Desktop", 160, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld", 140, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("MobileXPhone", "MobileX", 150, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .toString();

        TestHelper plan = new TestHelper()
                .createDevice("HandheldDevice", "Handheld", 140)
                .createDevice("MobileXPhone", "MobileX", 150)
                .createDevice("DesktopDevice", "Desktop", 160)
                .createSatellite("SovietSatellite", "Soviet", 8000, 150)
                .createSatellite("BlueOriginSatellite", "BlueOrigin", 10000, 190)
                .scheduleDeviceActivation("Handheld", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("MobileX", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Desktop", LocalTime.of(0, 0), 400)
                .showWorldState(initialWorldState)
                .simulate(1440)
                .showWorldState(afterADay);
        plan.executeTestPlan();
    }

    @Test
    public void testAWSADay1() {
        // Task 3
        // test AWS for one day
        // same example provided in spec
        // Creates 1 BlueOriginSatellite(A)
        // device will have no connection
        String initialWorldState = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("BlueOriginSatellite", "A", 10000, 110, 141.67,
                        new String[] {"AWS"})
                .expectDevice("AWSCloudServer", "AWS", 40.5, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(23, 20) } })
                .toString();

        // then simulates for a full day (1440 mins)
        String afterADay = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("BlueOriginSatellite", "A", 10000, 130.40, 141.67,
                        new String[] {})
                .expectDevice("AWSCloudServer", "AWS", 40.5, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(23, 20) } })
                .toString();

        TestHelper plan = new TestHelper()
                .createDevice("AWSCloudServer", "AWS", 40.5)
                .createSatellite("BlueOriginSatellite", "A", 10000, 110)
                .scheduleDeviceActivation("AWS", LocalTime.of(0, 0), 1400)
                .showWorldState(initialWorldState)
                .simulate(1440)
                .showWorldState(afterADay);
        plan.executeTestPlan();
    }



    @Test
    public void testAWSADay2() {
        // Task 3
        // test AWS for one day
        // same example provided in spec
        // Creates 3 BlueOriginSatellite(A, B & C)
        // device will connect to A & B first
        // then A will be invisible, and device will connect to B & C
        String initialWorldState = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("BlueOriginSatellite", "A", 10000, 110, 141.67,
                        new String[] {"AWS"})
                .expectSatellite("BlueOriginSatellite", "B", 10000, 20, 141.67,
                        new String[] {"AWS"})
                .expectSatellite("BlueOriginSatellite", "C", 10000, 350, 141.67,
                        new String[] {"AWS"})
                .expectDevice("AWSCloudServer", "AWS", 40.5, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(23, 20) } })
                .toString();

        // then simulates for a full day (1440 mins)
        String afterADay = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("BlueOriginSatellite", "A", 10000, 130.40, 141.67,
                        new String[] {},
                        new DummyConnection[] {
                                new DummyConnection("AWS", LocalTime.of(0, 0), LocalTime.of(13, 58), 832),
                        })
                .expectSatellite("BlueOriginSatellite", "B", 10000, 40.40, 141.67,
                        new String[] {"AWS"},
                        new DummyConnection[] {
                                new DummyConnection("AWS", LocalTime.of(0, 0), LocalTime.of(23, 21), 1395),
                        })
                .expectSatellite("BlueOriginSatellite", "C", 10000, 10.4, 141.67,
                        new String[] {"AWS"},
                        new DummyConnection[] {
                                new DummyConnection("AWS", LocalTime.of(13, 58), LocalTime.of(23, 21), 557),
                        })
                .expectDevice("AWSCloudServer", "AWS", 40.5, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(23, 20) } })
                .toString();

        TestHelper plan = new TestHelper()
                .createDevice("AWSCloudServer", "AWS", 40.5)
                .createSatellite("BlueOriginSatellite", "A", 10000, 110)
                .createSatellite("BlueOriginSatellite", "B", 10000, 20)
                .createSatellite("BlueOriginSatellite", "C", 10000, 350)
                .scheduleDeviceActivation("AWS", LocalTime.of(0, 0), 1400)
                .showWorldState(initialWorldState)
                .simulate(1440)
                .showWorldState(afterADay);
        plan.executeTestPlan();
    }

    @Test
    public void testAWSADay3() {
        // Task 3
        // test AWS for one day
        // Creates 1 NasaSatellite(A)(smaller angle) and 2 SovietSatellite(B & C)(larger angle)
        // AWS will connect to A & B first
        // with other devices added with angle between[30,40], AWS will lose connection with nasa
        // then connect to 2 soviets
        String initialWorldState = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("NasaSatellite", "A", 10000, 40, 85,
                        new String[] {"AWS","Desktop1","Desktop2","Desktop3","Desktop4",
                                "Desktop5", "Desktop6"})
                .expectSatellite("SovietSatellite", "B", 12000, 50, 100,
                        new String[] {"AWS","Desktop1","Desktop2","Desktop3","Desktop4",
                                "Desktop5", "Desktop6"})
                .expectSatellite("SovietSatellite", "C", 12000, 60, 100,
                        new String[] {"AWS","Desktop1","Desktop2","Desktop3","Desktop4",
                                "Desktop5", "Desktop6"})
                .expectDevice("AWSCloudServer", "AWS", 51, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("DesktopDevice", "Desktop1", 52, false,
                        new LocalTime[][] { { LocalTime.of(1, 0), LocalTime.of(7, 40) } })
                .expectDevice("DesktopDevice", "Desktop2", 53, false,
                        new LocalTime[][] { { LocalTime.of(2, 0), LocalTime.of(8, 40) } })
                .expectDevice("DesktopDevice", "Desktop3", 54, false,
                        new LocalTime[][] { { LocalTime.of(3, 0), LocalTime.of(9, 40) } })
                .expectDevice("DesktopDevice", "Desktop4", 55, false,
                        new LocalTime[][] { { LocalTime.of(4, 0), LocalTime.of(10, 40) } })
                .expectDevice("DesktopDevice", "Desktop5", 56, false,
                        new LocalTime[][] { { LocalTime.of(5, 0), LocalTime.of(11, 40) } })
                .expectDevice("DesktopDevice", "Desktop6", 35, false,
                        new LocalTime[][] { { LocalTime.of(6, 0), LocalTime.of(12, 40) } })
                .toString();

        // then simulates for a full day (1440 mins)
        String afterADay = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("NasaSatellite", "A", 10000, 52.24, 85,
                        new String[] {"AWS","Desktop1","Desktop2","Desktop3","Desktop4",
                                "Desktop5", "Desktop6"},
                        new DummyConnection[] {
                                new DummyConnection("AWS", LocalTime.of(0, 0), LocalTime.of(6, 00), 350),
                                new DummyConnection("Desktop1", LocalTime.of(1, 0), LocalTime.of(7, 41), 390),
                                new DummyConnection("Desktop2", LocalTime.of(2, 0), LocalTime.of(8, 41), 390),
                                new DummyConnection("Desktop3", LocalTime.of(3, 0), LocalTime.of(9, 41), 390),
                                new DummyConnection("Desktop4", LocalTime.of(4, 0), LocalTime.of(10, 41), 390),
                                new DummyConnection("Desktop5", LocalTime.of(5, 0), LocalTime.of(11, 41), 390),
                                new DummyConnection("Desktop6", LocalTime.of(6, 0), LocalTime.of(12, 41), 390),
                        })
                .expectSatellite("SovietSatellite", "B", 12000, 62, 100,
                        new String[] {"AWS","Desktop1","Desktop2","Desktop3","Desktop4",
                                "Desktop5", "Desktop6"},
                        new DummyConnection[] {
                                new DummyConnection("AWS", LocalTime.of(0, 0), LocalTime.of(6, 00), 350),
                                new DummyConnection("AWS", LocalTime.of(6, 1), LocalTime.of(6, 41), 29),
                        })
                .expectSatellite("SovietSatellite", "C", 12000, 72, 100,
                        new String[] {"AWS","Desktop1","Desktop2","Desktop3","Desktop4",
                                "Desktop5", "Desktop6"},
                        new DummyConnection[] {
                                new DummyConnection("AWS", LocalTime.of(6, 1), LocalTime.of(6, 41), 29),
                        })
                .expectDevice("AWSCloudServer", "AWS", 51, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("DesktopDevice", "Desktop1", 52, false,
                        new LocalTime[][] { { LocalTime.of(1, 0), LocalTime.of(7, 40) } })
                .expectDevice("DesktopDevice", "Desktop2", 53, false,
                        new LocalTime[][] { { LocalTime.of(2, 0), LocalTime.of(8, 40) } })
                .expectDevice("DesktopDevice", "Desktop3", 54, false,
                        new LocalTime[][] { { LocalTime.of(3, 0), LocalTime.of(9, 40) } })
                .expectDevice("DesktopDevice", "Desktop4", 55, false,
                        new LocalTime[][] { { LocalTime.of(4, 0), LocalTime.of(10, 40) } })
                .expectDevice("DesktopDevice", "Desktop5", 56, false,
                        new LocalTime[][] { { LocalTime.of(5, 0), LocalTime.of(11, 40) } })
                .expectDevice("DesktopDevice", "Desktop6", 35, false,
                        new LocalTime[][] { { LocalTime.of(6, 0), LocalTime.of(12, 40) } })
                .toString();

        TestHelper plan = new TestHelper()
                .createDevice("AWSCloudServer", "AWS", 51)
                .createDevice("DesktopDevice", "Desktop1", 52)
                .createDevice("DesktopDevice", "Desktop2", 53)
                .createDevice("DesktopDevice", "Desktop3", 54)
                .createDevice("DesktopDevice", "Desktop4", 55)
                .createDevice("DesktopDevice", "Desktop5", 56)
                .createDevice("DesktopDevice", "Desktop6", 35)
                .createSatellite("NasaSatellite", "A", 10000, 40)
                .createSatellite("SovietSatellite", "B", 12000, 50)
                .createSatellite("SovietSatellite", "C", 12000, 60)
                .scheduleDeviceActivation("AWS", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Desktop1", LocalTime.of(1, 0), 400)
                .scheduleDeviceActivation("Desktop2", LocalTime.of(2, 0), 400)
                .scheduleDeviceActivation("Desktop3", LocalTime.of(3, 0), 400)
                .scheduleDeviceActivation("Desktop4", LocalTime.of(4, 0), 400)
                .scheduleDeviceActivation("Desktop5", LocalTime.of(5, 0), 400)
                .scheduleDeviceActivation("Desktop6", LocalTime.of(6, 0), 400)
                .showWorldState(initialWorldState)
                .simulate(1440)
                .showWorldState(afterADay);
        plan.executeTestPlan();
    }
}
