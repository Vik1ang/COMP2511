package test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import java.time.LocalTime;

import test.test_helpers.DummyConnection;
import test.test_helpers.ResponseHelper;
import test.test_helpers.TestHelper;

@TestInstance(value = Lifecycle.PER_CLASS)
public class Task2ExampleTests {
    @Test
    public void testExample() {
        // Task 2
        // Example from the specification
        // Creates 1 satellite and 3 devices
        // Activates 2 of the devices and then schedules connections

        String initialWorldState = new ResponseHelper(LocalTime.of(0, 0))
            .expectSatellite("NasaSatellite", "Satellite1", 10000, 340, 85, new String[] { "DeviceA", "DeviceC" })
            .expectDevice("HandheldDevice", "DeviceA", 30, false,
                new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
            .expectDevice("LaptopDevice", "DeviceB", 180)
            .expectDevice("DesktopDevice", "DeviceC", 330, false,
                new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(5, 0) } })
            .toString();

        // then simulates for a full day (1440 mins)
        String afterADay = new ResponseHelper(LocalTime.of(0, 0))
            .expectSatellite("NasaSatellite", "Satellite1", 10000, 352.24, 85,
                new String[] { "DeviceA", "DeviceC" },
                new DummyConnection[] {
                    new DummyConnection("DeviceA", LocalTime.of(0, 0), LocalTime.of(6, 41), 390),
                    new DummyConnection("DeviceC", LocalTime.of(0, 0), LocalTime.of(5, 1), 290), //
                })
            .expectDevice("HandheldDevice", "DeviceA", 30, false,
                new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
            .expectDevice("LaptopDevice", "DeviceB", 180)
            .expectDevice("DesktopDevice", "DeviceC", 330, false,
                new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(5, 0) } })
            .toString();

        TestHelper plan = new TestHelper().createDevice("HandheldDevice", "DeviceA", 30)
            .createDevice("LaptopDevice", "DeviceB", 180)
            .createDevice("DesktopDevice", "DeviceC", 330)
            .createSatellite("NasaSatellite", "Satellite1", 10000, 340)
            .scheduleDeviceActivation("DeviceA", LocalTime.of(0, 0), 400)
            .scheduleDeviceActivation("DeviceC", LocalTime.of(0, 0), 300)
            .showWorldState(initialWorldState)
            .simulate(1440)
            .showWorldState(afterADay);
        plan.executeTestPlan();
    }

    @Test
    public void testHalfADay() {
        // Task 2
        // Tests half a day simulation to ensure you are treating current time correctly

        String initialWorldState = new ResponseHelper(LocalTime.of(0, 0))
            .toString();

        // then simulates for a full day (720 mins)
        String afterADay = new ResponseHelper(LocalTime.of(12, 0))
            .toString();

        TestHelper plan = new TestHelper().showWorldState(initialWorldState)
            .simulate(720)
            .showWorldState(afterADay);
        plan.executeTestPlan();
    }

    @Test
    public void testMovementOfSatellites() {
        // Task 2
        // This test is here to help you make sure your movement of satellite code is
        // correct
        String finalWorldState = new ResponseHelper(LocalTime.of(0, 0))
            .expectSatellite("NasaSatellite", "BadBatch", 6000, 290.4, 85, new String[] {})
            .expectSatellite("SovietSatellite", "FalconAndTheWinterSoldier", 6000, 164, 100, new String[] {})
            .expectSatellite("SpaceXSatellite", "Loki", 6000, 193.32, 55.5, new String[] {})
            .expectSatellite("BlueOriginSatellite", "Wandavision", 6000, 34, 141.66, new String[] {}).toString();

        TestHelper plan = new TestHelper()
            .createSatellite("BlueOriginSatellite", "Wandavision", 6000, 0)
            .createSatellite("SovietSatellite", "FalconAndTheWinterSoldier", 6000, 140)
            .createSatellite("SpaceXSatellite", "Loki", 6000, 180)
            .createSatellite("NasaSatellite", "BadBatch", 6000, 270) //
            .simulate(1440).showWorldState(finalWorldState);
        plan.executeTestPlan();
    }

    @Test
    public void testSovietSatelliteMovement() {
        // Task 2
        // This test shows how we can easily check edge cases using this method
        // You NEED to test more edge cases for Soviet, this only covers a basic example
        String finalWorldState = new ResponseHelper(LocalTime.of(0, 0))
            // the result shows that now it's going backwards! which is what we want
            .expectSatellite("SovietSatellite", "FalconAndTheWinterSoldier", 6000, 176, -100, new String[] {})
            .toString();

        TestHelper plan = new TestHelper() //
            // this satellite is going to go past the 190 boundary, and is going to reverse
            // to fix it's orbit.
            .createSatellite("SovietSatellite", "FalconAndTheWinterSoldier", 6000, 180) //
            .simulate(1440)
            .showWorldState(finalWorldState);
        plan.executeTestPlan();
    }
}