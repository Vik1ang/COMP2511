package test.wTest;

import org.junit.Test;
import test.test_helpers.DummyConnection;
import test.test_helpers.ResponseHelper;
import test.test_helpers.TestHelper;

import java.time.LocalTime;

public class TestSpecial {
    @Test
    public void testNasaDeviceAlwaysActivate() {
        // Task 3
        // test AWS for one day
        // Creates 1 NasaSatellite(A) and DesktopDevice device1(always activate)
        // simulate for one day
        String initialWorldState = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("NasaSatellite", "A", 10000, 40, 85,
                        new String[] {"Desktop1"})
                .expectDevice("DesktopDevice", "Desktop1", 52, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(23, 59) } })
                .toString();

        // then simulates for a full day (1440 mins)
        String afterADay = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("NasaSatellite", "A", 10000, 52.24, 85,
                        new String[] {"Desktop1"},
                        new DummyConnection[] {
                                new DummyConnection("Desktop1", LocalTime.of(0, 0), 1429),
                        })
                .expectDevice("DesktopDevice", "Desktop1", 52, true,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(23, 59) } })
                .toString();

        TestHelper plan = new TestHelper()
                .createDevice("DesktopDevice", "Desktop1", 52)
                .createSatellite("NasaSatellite", "A", 10000, 40)
                .scheduleDeviceActivation("Desktop1", LocalTime.of(0, 0), 1439)
                .showWorldState(initialWorldState)
                .simulate(1440)
                .showWorldState(afterADay);
        plan.executeTestPlan();
    }
}
