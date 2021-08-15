package test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import java.time.LocalTime;

import test.test_helpers.ResponseHelper;
import test.test_helpers.TestHelper;

@TestInstance(value = Lifecycle.PER_CLASS)
public class Task1ExampleTests {
    @Test
    public void testExample() {
        // Task 1
        // Example from the specification

        // Creates 1 satellite and 3 devices
        // 2 devices are in view of the satellite
        // 1 device is out of view of the satellite
        String initialWorldState = new ResponseHelper(LocalTime.of(0, 0))
            // note: all doubles are to 0.01 precision
            // so 141.66 == 141.67.
            .expectSatellite("BlueOriginSatellite", "Satellite1", 10000, 340, 141.66,
                /* Possible Connections */ new String[] { "DeviceA", "DeviceC" })
            .expectDevice("HandheldDevice", "DeviceA", 30)
            .expectDevice("LaptopDevice", "DeviceB", 180)
            .expectDevice("DesktopDevice", "DeviceC", 330)
            .toString();

        // this is what we call the 'builder' pattern, effectively we scope out a test
        // plan in this case
        // by asking the TestHelper to create a series of devices/satellites, then we
        // ask it to show the world state
        // and we use a *different* builder pattern to state how we want the response to
        // look (i.e. expect all the devices/satellites we created before).

        // Since we want you to model your own device/satellite code this is a bit
        // overly 'generic', normally you would probably concrete this a bit
        // i.e. createDevice(new Device(...)) but in this case this is still fine /
        // readable.
        // Unsure what a function does? Just mouse over it, and you'll get a nice
        // description including sample json output.
        TestHelper plan = new TestHelper().createDevice("HandheldDevice", "DeviceA", 30)
            .createDevice("LaptopDevice", "DeviceB", 180)
            .createDevice("DesktopDevice", "DeviceC", 330)
            .createSatellite("BlueOriginSatellite", "Satellite1", 10000, 340)
            .showWorldState(initialWorldState);

        // Then after moving DeviceA to theta = 211 the world state should be
        String afterMoveWorldState = new ResponseHelper(LocalTime.of(0, 0))
            .expectSatellite("BlueOriginSatellite", "Satellite1", 10000, 340, 141.66, new String[] { "DeviceC" })
            .expectDevice("HandheldDevice", "DeviceA", 211) // position has changed
            .expectDevice("LaptopDevice", "DeviceB", 180)
            .expectDevice("DesktopDevice", "DeviceC", 330)
            .toString();

        plan = plan.moveDevice("DeviceA", 211).showWorldState(afterMoveWorldState);

        String afterRemovingWorldState = new ResponseHelper(LocalTime.of(0, 0))
            .expectSatellite("BlueOriginSatellite", "Satellite1", 10000, 340, 141.66, new String[] { "DeviceC" })
            .expectDevice("HandheldDevice", "DeviceA", 211)
            .expectDevice("DesktopDevice", "DeviceC", 330)
            // notice no B
            .toString();

        plan = plan.removeDevice("DeviceB").showWorldState(afterRemovingWorldState);

        // this is the magic! DON'T forget this, you need the execute else it won't
        // actually run any tests!
        plan.executeTestPlan();
    }
}
