package test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import test.test_helpers.DummyConnection;
import test.test_helpers.ResponseHelper;
import test.test_helpers.TestHelper;

import java.time.LocalTime;

@TestInstance(value = Lifecycle.PER_CLASS)
public class Task2BlueOriginTests1 {
    @Test
    public void testBlueOriginMoreDevicesADay1() {
        // Task 2
        // test BlueOriginSatellite for one day with more Desktops and Laptops
        // Creates 1 satellite, 3 LaptopDevices (2 activation periods each), 3 DesktopDevice

        String initialWorldState = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("BlueOriginSatellite", "Satellite1", 6000, 75, 141.67,
                        new String[] { "Desktop1","Desktop2","Desktop3","Laptop1","Laptop2","Laptop3"})
                .expectSatellite("BlueOriginSatellite", "Satellite2", 10000, 65, 141.67,
                        new String[] { "Desktop1","Desktop2","Desktop3","Laptop1","Laptop2","Laptop3"})
                .expectDevice("DesktopDevice", "Desktop1", 75, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("DesktopDevice", "Desktop2", 80, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("DesktopDevice", "Desktop3", 85, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })

                .expectDevice("LaptopDevice", "Laptop1", 90, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) },
                                            { LocalTime.of(8, 0), LocalTime.of(16, 20) }})
                .expectDevice("LaptopDevice", "Laptop2", 95, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) },
                                            { LocalTime.of(8, 0), LocalTime.of(16, 20) } })
                .expectDevice("LaptopDevice", "Laptop3", 100, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) },
                                            { LocalTime.of(8, 0), LocalTime.of(16, 20) } })
                .toString();

        // then simulates for a full day (1440 mins)
        String afterADay = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("BlueOriginSatellite", "Satellite1", 6000, 109, 141.67,
                        new String[] { "Desktop1","Desktop2","Desktop3","Laptop1","Laptop2","Laptop3"},
                        new DummyConnection[] {
                                new DummyConnection("Desktop3", LocalTime.of(0, 0), LocalTime.of(6, 41), 395),
                        })
                .expectSatellite("BlueOriginSatellite", "Satellite2", 10000, 85.4, 141.67,
                        new String[] { "Desktop1","Desktop2","Desktop3","Laptop1","Laptop2","Laptop3"},
                        new DummyConnection[] {
                                new DummyConnection("Desktop1", LocalTime.of(0, 0), LocalTime.of(6, 41), 395),
                                new DummyConnection("Desktop2", LocalTime.of(0, 0), LocalTime.of(6, 41), 395),
                                new DummyConnection("Laptop1", LocalTime.of(0, 0), LocalTime.of(6, 41), 398),
                                new DummyConnection("Laptop2", LocalTime.of(0, 0), LocalTime.of(6, 41), 398),
                                new DummyConnection("Laptop3", LocalTime.of(0, 0), LocalTime.of(6, 41), 398),
                                new DummyConnection("Laptop1", LocalTime.of(8, 0), LocalTime.of(16, 21), 498),
                                new DummyConnection("Laptop2", LocalTime.of(8, 0), LocalTime.of(16, 21), 498),
                                new DummyConnection("Laptop3", LocalTime.of(8, 0), LocalTime.of(16, 21), 498)
                        })
                .expectDevice("DesktopDevice", "Desktop1", 75, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("DesktopDevice", "Desktop2", 80, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("DesktopDevice", "Desktop3", 85, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })

                .expectDevice("LaptopDevice", "Laptop1", 90, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) },
                                { LocalTime.of(8, 0), LocalTime.of(16, 20) }})
                .expectDevice("LaptopDevice", "Laptop2", 95, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) },
                                { LocalTime.of(8, 0), LocalTime.of(16, 20) } })
                .expectDevice("LaptopDevice", "Laptop3", 100, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) },
                                { LocalTime.of(8, 0), LocalTime.of(16, 20) } })
                .toString();

        TestHelper plan = new TestHelper()
                .createDevice("LaptopDevice", "Laptop1", 90)
                .createDevice("LaptopDevice", "Laptop2", 95)
                .createDevice("LaptopDevice", "Laptop3", 100)
                .createDevice("DesktopDevice", "Desktop1", 75)
                .createDevice("DesktopDevice", "Desktop2", 80)
                .createDevice("DesktopDevice", "Desktop3", 85)
                .createSatellite("BlueOriginSatellite", "Satellite1", 6000, 75)
                .createSatellite("BlueOriginSatellite", "Satellite2", 10000, 65)
                .scheduleDeviceActivation("Laptop1", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Laptop2", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Laptop3", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Laptop1", LocalTime.of(8, 0), 500)
                .scheduleDeviceActivation("Laptop2", LocalTime.of(8, 0), 500)
                .scheduleDeviceActivation("Laptop3", LocalTime.of(8, 0), 500)
                .scheduleDeviceActivation("Desktop1", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Desktop2", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Desktop3", LocalTime.of(0, 0), 400)
                .showWorldState(initialWorldState)
                .simulate(1440)
                .showWorldState(afterADay);
        plan.executeTestPlan();
    }

    @Test
    public void testBlueOriginMoreDevicesADay2() {
        // Task 2
        // test BlueOriginSatellite for one day with more Desktops and Laptops
        // Creates 1 satellite, 6 LaptopDevices (3 has 2 activation periods each)

        String initialWorldState = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("BlueOriginSatellite", "Satellite1", 6000, 75, 141.67,
                        new String[] { "Laptop1","Laptop2","Laptop3", "Laptop4","Laptop5","Laptop6"})

                .expectDevice("LaptopDevice", "Laptop1", 90, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) },
                                { LocalTime.of(8, 0), LocalTime.of(16, 20) }})
                .expectDevice("LaptopDevice", "Laptop2", 95, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) },
                                { LocalTime.of(8, 0), LocalTime.of(16, 20) } })
                .expectDevice("LaptopDevice", "Laptop3", 100, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) },
                                { LocalTime.of(8, 0), LocalTime.of(16, 20) } })
                .expectDevice("LaptopDevice", "Laptop4", 105, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) }})
                .expectDevice("LaptopDevice", "Laptop5", 110, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) }})
                .expectDevice("LaptopDevice", "Laptop6", 115, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) }})
                .toString();

        // then simulates for a full day (1440 mins)
        String afterADay = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("BlueOriginSatellite", "Satellite1", 6000, 109, 141.67,
                        new String[] { "Laptop1","Laptop2","Laptop3", "Laptop4","Laptop5","Laptop6"},
                        new DummyConnection[] {
                                new DummyConnection("Laptop1", LocalTime.of(0, 0), LocalTime.of(6, 41), 398),
                                new DummyConnection("Laptop2", LocalTime.of(0, 0), LocalTime.of(6, 41), 398),
                                new DummyConnection("Laptop3", LocalTime.of(0, 0), LocalTime.of(6, 41), 398),
                                new DummyConnection("Laptop4", LocalTime.of(0, 0), LocalTime.of(6, 41), 398),
                                new DummyConnection("Laptop5", LocalTime.of(0, 0), LocalTime.of(6, 41), 398),
                                new DummyConnection("Laptop1", LocalTime.of(8, 0), LocalTime.of(16, 21), 498),
                                new DummyConnection("Laptop2", LocalTime.of(8, 0), LocalTime.of(16, 21), 498),
                                new DummyConnection("Laptop3", LocalTime.of(8, 0), LocalTime.of(16, 21), 498)
                        })

                .expectDevice("LaptopDevice", "Laptop1", 90, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) },
                                { LocalTime.of(8, 0), LocalTime.of(16, 20) }})
                .expectDevice("LaptopDevice", "Laptop2", 95, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) },
                                { LocalTime.of(8, 0), LocalTime.of(16, 20) } })
                .expectDevice("LaptopDevice", "Laptop3", 100, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) },
                                { LocalTime.of(8, 0), LocalTime.of(16, 20) } })
                .expectDevice("LaptopDevice", "Laptop4", 105, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) }})
                .expectDevice("LaptopDevice", "Laptop5", 110, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) }})
                .expectDevice("LaptopDevice", "Laptop6", 115, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) }})
                .toString();

        TestHelper plan = new TestHelper()
                .createDevice("LaptopDevice", "Laptop1", 90)
                .createDevice("LaptopDevice", "Laptop2", 95)
                .createDevice("LaptopDevice", "Laptop3", 100)
                .createDevice("LaptopDevice", "Laptop4", 105)
                .createDevice("LaptopDevice", "Laptop5", 110)
                .createDevice("LaptopDevice", "Laptop6", 115)
                .createSatellite("BlueOriginSatellite", "Satellite1", 6000, 75)
                .scheduleDeviceActivation("Laptop1", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Laptop2", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Laptop3", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Laptop4", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Laptop5", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Laptop6", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Laptop1", LocalTime.of(8, 0), 500)
                .scheduleDeviceActivation("Laptop2", LocalTime.of(8, 0), 500)
                .scheduleDeviceActivation("Laptop3", LocalTime.of(8, 0), 500)
                .showWorldState(initialWorldState)
                .simulate(1440)
                .showWorldState(afterADay);
        plan.executeTestPlan();
    }

    @Test
    public void testBlueOriginMoreDevicesADay3() {
        // Task 2
        // test BlueOriginSatellite for one day with more than 10 activations
        // Creates 1 satellite, 2 LaptopDevices 4 DesktopDevice and 7 HandheldsDevice
        // activate  1 LaptopDevice, 3 DesktopDevice and 6 HandheldsDevice
        // activate the other 1 LaptopDevice, 1 DesktopDevice and 1 HandheldsDevice

        String initialWorldState = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("BlueOriginSatellite", "SatelliteA", 10000, 90, 141.67,
                        new String[] { "Desktop1","Desktop2","Handheld1","Handheld2","Handheld3","Handheld4",
                                "Handheld5", "Handheld6","Handheld7","Laptop1","Laptop2","Laptop3","Laptop4"})
                .expectSatellite("BlueOriginSatellite", "SatelliteB", 10000, 70, 141.67,
                        new String[] { "Desktop1","Desktop2","Handheld1","Handheld2","Handheld3","Handheld4",
                                "Handheld5", "Handheld6","Handheld7","Laptop1","Laptop2","Laptop3","Laptop4"} )

                .expectDevice("DesktopDevice", "Desktop1", 82, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("DesktopDevice", "Desktop2", 83, false,
                        new LocalTime[][] { { LocalTime.of(0, 11), LocalTime.of(6, 51) } })

                .expectDevice("HandheldDevice", "Handheld1", 70, false,
                        new LocalTime[][] { { LocalTime.of(0, 1), LocalTime.of(6, 41) } })
                .expectDevice("HandheldDevice", "Handheld2", 71, false,
                        new LocalTime[][] { { LocalTime.of(0, 2), LocalTime.of(6, 42) } })
                .expectDevice("HandheldDevice", "Handheld3", 72, false,
                        new LocalTime[][] { { LocalTime.of(0, 3), LocalTime.of(6, 43) } })
                .expectDevice("HandheldDevice", "Handheld4", 73, false,
                        new LocalTime[][] { { LocalTime.of(0, 4), LocalTime.of(6, 44) } })
                .expectDevice("HandheldDevice", "Handheld5", 74, false,
                        new LocalTime[][] { { LocalTime.of(0, 5), LocalTime.of(6, 45) } })
                .expectDevice("HandheldDevice", "Handheld6", 75, false,
                        new LocalTime[][] { { LocalTime.of(0, 6), LocalTime.of(6, 46) } })
                .expectDevice("HandheldDevice", "Handheld7", 76, false,
                        new LocalTime[][] { { LocalTime.of(0, 12), LocalTime.of(6, 52) } })

                .expectDevice("LaptopDevice", "Laptop1", 90, false,
                        new LocalTime[][] { { LocalTime.of(0, 7), LocalTime.of(6, 47) },})
                .expectDevice("LaptopDevice", "Laptop2", 91, false,
                        new LocalTime[][] { { LocalTime.of(0, 8), LocalTime.of(6, 48) },})
                .expectDevice("LaptopDevice", "Laptop3", 92, false,
                        new LocalTime[][] { { LocalTime.of(0, 9), LocalTime.of(6, 49) },})
                .expectDevice("LaptopDevice", "Laptop4", 93, false,
                        new LocalTime[][] { { LocalTime.of(0, 10), LocalTime.of(6, 50) },})

                .toString();

        // then simulates for a full day (1440 mins)
        String afterADay = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("BlueOriginSatellite", "SatelliteA", 10000, 110.40, 141.67,
                        new String[] { "Desktop1","Desktop2","Handheld1","Handheld2","Handheld3","Handheld4",
                                "Handheld5", "Handheld6","Handheld7","Laptop1","Laptop2","Laptop3","Laptop4"} ,
                        new DummyConnection[] {

                                new DummyConnection("Laptop4", LocalTime.of(0, 10), LocalTime.of(6, 51), 398),
                                new DummyConnection("Desktop2", LocalTime.of(0, 11), LocalTime.of(6, 52), 395),
                                new DummyConnection("Handheld7", LocalTime.of(0, 12), LocalTime.of(6, 53), 399),
                        })
                .expectSatellite("BlueOriginSatellite", "SatelliteB", 10000, 90.40, 141.67,
                        new String[] { "Desktop1","Desktop2","Handheld1","Handheld2","Handheld3","Handheld4",
                                "Handheld5", "Handheld6","Handheld7","Laptop1","Laptop2","Laptop3","Laptop4"} ,
                        new DummyConnection[] {
                                new DummyConnection("Desktop1", LocalTime.of(0, 0), LocalTime.of(6, 41), 395),
                                new DummyConnection("Handheld1", LocalTime.of(0, 1), LocalTime.of(6, 42), 399),
                                new DummyConnection("Handheld2", LocalTime.of(0, 2), LocalTime.of(6, 43), 399),
                                new DummyConnection("Handheld3", LocalTime.of(0, 3), LocalTime.of(6, 44), 399),
                                new DummyConnection("Handheld4", LocalTime.of(0, 4), LocalTime.of(6, 45), 399),
                                new DummyConnection("Handheld5", LocalTime.of(0, 5), LocalTime.of(6, 46), 399),
                                new DummyConnection("Handheld6", LocalTime.of(0, 6), LocalTime.of(6, 47), 399),
                                new DummyConnection("Laptop1", LocalTime.of(0, 7), LocalTime.of(6, 48), 398),
                                new DummyConnection("Laptop2", LocalTime.of(0, 8), LocalTime.of(6, 49), 398),
                                new DummyConnection("Laptop3", LocalTime.of(0, 9), LocalTime.of(6, 50), 398)})

                .expectDevice("DesktopDevice", "Desktop1", 82, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("DesktopDevice", "Desktop2", 83, false,
                        new LocalTime[][] { { LocalTime.of(0, 11), LocalTime.of(6, 51) } })

                .expectDevice("HandheldDevice", "Handheld1", 70, false,
                        new LocalTime[][] { { LocalTime.of(0, 1), LocalTime.of(6, 41) } })
                .expectDevice("HandheldDevice", "Handheld2", 71, false,
                        new LocalTime[][] { { LocalTime.of(0, 2), LocalTime.of(6, 42) } })
                .expectDevice("HandheldDevice", "Handheld3", 72, false,
                        new LocalTime[][] { { LocalTime.of(0, 3), LocalTime.of(6, 43) } })
                .expectDevice("HandheldDevice", "Handheld4", 73, false,
                        new LocalTime[][] { { LocalTime.of(0, 4), LocalTime.of(6, 44) } })
                .expectDevice("HandheldDevice", "Handheld5", 74, false,
                        new LocalTime[][] { { LocalTime.of(0, 5), LocalTime.of(6, 45) } })
                .expectDevice("HandheldDevice", "Handheld6", 75, false,
                        new LocalTime[][] { { LocalTime.of(0, 6), LocalTime.of(6, 46) } })
                .expectDevice("HandheldDevice", "Handheld7", 76, false,
                        new LocalTime[][] { { LocalTime.of(0, 12), LocalTime.of(6, 52) } })

                .expectDevice("LaptopDevice", "Laptop1", 90, false,
                        new LocalTime[][] { { LocalTime.of(0, 7), LocalTime.of(6, 47) }})
                .expectDevice("LaptopDevice", "Laptop2", 91, false,
                        new LocalTime[][] { { LocalTime.of(0, 8), LocalTime.of(6, 48) }})
                .expectDevice("LaptopDevice", "Laptop3", 92, false,
                        new LocalTime[][] { { LocalTime.of(0, 9), LocalTime.of(6, 49) }})
                .expectDevice("LaptopDevice", "Laptop4", 93, false,
                        new LocalTime[][] { { LocalTime.of(0, 10), LocalTime.of(6, 50) }})
                .toString();

        TestHelper plan = new TestHelper()
                .createDevice("LaptopDevice", "Laptop1", 90)
                .createDevice("LaptopDevice", "Laptop2", 91)
                .createDevice("LaptopDevice", "Laptop3", 92)
                .createDevice("LaptopDevice", "Laptop4", 93)
                .createDevice("DesktopDevice", "Desktop1", 82)
                .createDevice("DesktopDevice", "Desktop2", 83)
                .createDevice("HandheldDevice", "Handheld1", 70)
                .createDevice("HandheldDevice", "Handheld2", 71)
                .createDevice("HandheldDevice", "Handheld3", 72)
                .createDevice("HandheldDevice", "Handheld4", 73)
                .createDevice("HandheldDevice", "Handheld5", 74)
                .createDevice("HandheldDevice", "Handheld6", 75)
                .createDevice("HandheldDevice", "Handheld7", 76)
                .createSatellite("BlueOriginSatellite", "SatelliteA", 10000, 90)
                .createSatellite("BlueOriginSatellite", "SatelliteB", 10000, 70)
                .scheduleDeviceActivation("Laptop1", LocalTime.of(0, 7), 400)
                .scheduleDeviceActivation("Laptop2", LocalTime.of(0, 8), 400)
                .scheduleDeviceActivation("Laptop3", LocalTime.of(0, 9), 400)
                .scheduleDeviceActivation("Laptop4", LocalTime.of(0, 10), 400)
                .scheduleDeviceActivation("Desktop1", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Desktop2", LocalTime.of(0, 11), 400)
                .scheduleDeviceActivation("Handheld1", LocalTime.of(0, 1), 400)
                .scheduleDeviceActivation("Handheld2", LocalTime.of(0, 2), 400)
                .scheduleDeviceActivation("Handheld3", LocalTime.of(0, 3), 400)
                .scheduleDeviceActivation("Handheld4", LocalTime.of(0, 4), 400)
                .scheduleDeviceActivation("Handheld5", LocalTime.of(0, 5), 400)
                .scheduleDeviceActivation("Handheld6", LocalTime.of(0, 6), 400)
                .scheduleDeviceActivation("Handheld7", LocalTime.of(0, 12), 400)
                .showWorldState(initialWorldState)
                .simulate(1440)
                .showWorldState(afterADay);
        plan.executeTestPlan();
    }
}
