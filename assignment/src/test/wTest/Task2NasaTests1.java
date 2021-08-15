package test.wTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import test.test_helpers.DummyConnection;
import test.test_helpers.ResponseHelper;
import test.test_helpers.TestHelper;

import java.time.LocalTime;

@TestInstance(value = Lifecycle.PER_CLASS)
public class Task2NasaTests1 {
    @Test
    public void testNasaADay1() {
        // Task 2
        // test nasaSatellite for one day
        // Creates 1 satellite and 7 devices
        // Activates 7 devices
        String initialWorldState = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("NasaSatellite", "Satellite1", 10000, 70, 85,
                        new String[] {"Handheld1","Handheld2","Handheld3","Handheld4",
                                "Handheld5", "Handheld6","Handheld7"})
                .expectDevice("HandheldDevice", "Handheld1", 70, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld2", 71, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld3", 72, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld4", 73, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld5", 74, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld6", 75, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld7", 76, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .toString();

        // then simulates for a full day (1440 mins)
        String afterADay = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("NasaSatellite", "Satellite1", 10000, 82.24, 85,
                        new String[] {"Handheld1","Handheld2","Handheld3","Handheld4",
                                "Handheld5", "Handheld6","Handheld7"},
                        new DummyConnection[] {
                                new DummyConnection("Handheld1", LocalTime.of(0, 0), LocalTime.of(6, 41), 390),
                                new DummyConnection("Handheld2", LocalTime.of(0, 0), LocalTime.of(6, 41), 390),
                                new DummyConnection("Handheld3", LocalTime.of(0, 0), LocalTime.of(6, 41), 390),
                                new DummyConnection("Handheld4", LocalTime.of(0, 0), LocalTime.of(6, 41), 390),
                                new DummyConnection("Handheld5", LocalTime.of(0, 0), LocalTime.of(6, 41), 390),
                                new DummyConnection("Handheld6", LocalTime.of(0, 0), LocalTime.of(6, 41), 390),
                        })

                .expectDevice("HandheldDevice", "Handheld1", 70, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld2", 71, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld3", 72, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld4", 73, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld5", 74, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld6", 75, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld7", 76, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .toString();

        TestHelper plan = new TestHelper().createDevice("HandheldDevice", "Handheld1", 70)
                .createDevice("HandheldDevice", "Handheld2", 71)
                .createDevice("HandheldDevice", "Handheld3", 72)
                .createDevice("HandheldDevice", "Handheld4", 73)
                .createDevice("HandheldDevice", "Handheld5", 74)
                .createDevice("HandheldDevice", "Handheld6", 75)
                .createDevice("HandheldDevice", "Handheld7", 76)
                .createSatellite("NasaSatellite", "Satellite1", 10000, 70)
                .scheduleDeviceActivation("Handheld1", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Handheld2", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Handheld3", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Handheld4", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Handheld5", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Handheld6", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Handheld7", LocalTime.of(0, 0), 400)
                .showWorldState(initialWorldState)
                .simulate(1440)
                .showWorldState(afterADay);
        plan.executeTestPlan();
    }


    @Test
    public void testNasaADay2() {
        // Task 2
        // test nasaSatellite for one day
        // Creates 1 satellite and 7 devices (device 7 and 8 are inside [30, 40])
        // Activates 8 devices
        String initialWorldState = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("NasaSatellite", "Satellite1", 10000, 70, 85,
                        new String[] {"Handheld1","Handheld2","Handheld3","Handheld4",
                                "Handheld5", "Handheld6","Handheld7","Handheld8"})
                .expectDevice("HandheldDevice", "Handheld1", 70, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld2", 71, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld3", 72, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld4", 73, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld5", 74, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld6", 75, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld7", 35, false,
                new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld8", 36, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .toString();

        // then simulates for a full day (1440 mins)
        String afterADay = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("NasaSatellite", "Satellite1", 10000, 82.24, 85,
                        new String[] {"Handheld1","Handheld2","Handheld3","Handheld4",
                                "Handheld5", "Handheld6","Handheld7","Handheld8"},
                        new DummyConnection[] {
                                new DummyConnection("Handheld1", LocalTime.of(0, 0), LocalTime.of(0, 0), 0),
                                new DummyConnection("Handheld2", LocalTime.of(0, 0), LocalTime.of(0, 0), 0),
                                new DummyConnection("Handheld3", LocalTime.of(0, 0), LocalTime.of(6, 41), 390),
                                new DummyConnection("Handheld4", LocalTime.of(0, 0), LocalTime.of(6, 41), 390),
                                new DummyConnection("Handheld5", LocalTime.of(0, 0), LocalTime.of(6, 41), 390),
                                new DummyConnection("Handheld6", LocalTime.of(0, 0), LocalTime.of(6, 41), 390),
                                new DummyConnection("Handheld7", LocalTime.of(0, 0), LocalTime.of(6, 41), 390),
                                new DummyConnection("Handheld8", LocalTime.of(0, 0), LocalTime.of(6, 41), 390),
                        })

                .expectDevice("HandheldDevice", "Handheld1", 70, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld2", 71, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld3", 72, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld4", 73, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld5", 74, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld6", 75, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld7", 35, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld8", 36, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .toString();

        TestHelper plan = new TestHelper().createDevice("HandheldDevice", "Handheld1", 70)
                .createDevice("HandheldDevice", "Handheld2", 71)
                .createDevice("HandheldDevice", "Handheld3", 72)
                .createDevice("HandheldDevice", "Handheld4", 73)
                .createDevice("HandheldDevice", "Handheld5", 74)
                .createDevice("HandheldDevice", "Handheld6", 75)
                .createDevice("HandheldDevice", "Handheld7", 35)
                .createDevice("HandheldDevice", "Handheld8", 36)
                .createSatellite("NasaSatellite", "Satellite1", 10000, 70)
                .scheduleDeviceActivation("Handheld1", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Handheld2", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Handheld3", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Handheld4", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Handheld5", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Handheld6", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Handheld7", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Handheld8", LocalTime.of(0, 0), 400)
                .showWorldState(initialWorldState)
                .simulate(1440)
                .showWorldState(afterADay);
        plan.executeTestPlan();
    }

    @Test
    public void testNasaADay3() {
        // Task 2
        // test nasaSatellite for one day
        // Creates 1 satellite and 7 devices (all inside [30,40])
        // Activates 7 devices
        String initialWorldState = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("NasaSatellite", "Satellite1", 10000, 70, 85,
                        new String[] {"Handheld1","Handheld2","Handheld3","Handheld4",
                                "Handheld5", "Handheld6","Handheld7"})
                .expectDevice("HandheldDevice", "Handheld1", 30, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld2", 31, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld3", 32, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld4", 33, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld5", 34, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld6", 35, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld7", 36, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .toString();

        // then simulates for a full day (1440 mins)
        String afterADay = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("NasaSatellite", "Satellite1", 10000, 82.24, 85,
                        new String[] {"Handheld1","Handheld2","Handheld3","Handheld4",
                                "Handheld5", "Handheld6","Handheld7"},
                        new DummyConnection[] {
                                new DummyConnection("Handheld1", LocalTime.of(0, 0), LocalTime.of(6, 41), 390),
                                new DummyConnection("Handheld2", LocalTime.of(0, 0), LocalTime.of(6, 41), 390),
                                new DummyConnection("Handheld3", LocalTime.of(0, 0), LocalTime.of(6, 41), 390),
                                new DummyConnection("Handheld4", LocalTime.of(0, 0), LocalTime.of(6, 41), 390),
                                new DummyConnection("Handheld5", LocalTime.of(0, 0), LocalTime.of(6, 41), 390),
                                new DummyConnection("Handheld6", LocalTime.of(0, 0), LocalTime.of(6, 41), 390),
                        })

                .expectDevice("HandheldDevice", "Handheld1", 30, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld2", 31, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld3", 32, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld4", 33, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld5", 34, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld6", 35, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("HandheldDevice", "Handheld7", 36, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .toString();

        TestHelper plan = new TestHelper().createDevice("HandheldDevice", "Handheld1", 30)
                .createDevice("HandheldDevice", "Handheld2", 31)
                .createDevice("HandheldDevice", "Handheld3", 32)
                .createDevice("HandheldDevice", "Handheld4", 33)
                .createDevice("HandheldDevice", "Handheld5", 34)
                .createDevice("HandheldDevice", "Handheld6", 35)
                .createDevice("HandheldDevice", "Handheld7", 36)
                .createSatellite("NasaSatellite", "Satellite1", 10000, 70)
                .scheduleDeviceActivation("Handheld1", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Handheld2", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Handheld3", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Handheld4", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Handheld5", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Handheld6", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("Handheld7", LocalTime.of(0, 0), 400)
                .showWorldState(initialWorldState)
                .simulate(1440)
                .showWorldState(afterADay);
        plan.executeTestPlan();
    }
}
