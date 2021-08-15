package test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import test.test_helpers.DummyConnection;
import test.test_helpers.ResponseHelper;
import test.test_helpers.TestHelper;

import java.time.LocalTime;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class MyOwnTest {
    @Test
    public void testSpaceX() {
        String initialWorldState = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("SpaceXSatellite", "Satellite1", 5838, 50, 55.5,
                        new String[]{"Device1", "Device10", "Device2", "Device3", "Device4", "Device5", "Device6", "Device7", "Device8", "Device9"})
                .expectDevice("HandheldDevice", "Device1", 50, false,
                        new LocalTime[][]{{LocalTime.of(0, 0), LocalTime.of(1, 0)}})
                .expectDevice("HandheldDevice", "Device10", 59, false,
                        new LocalTime[][]{{LocalTime.of(9, 0), LocalTime.of(10, 0)}})
                .expectDevice("HandheldDevice", "Device2", 51, false,
                        new LocalTime[][]{{LocalTime.of(1, 0), LocalTime.of(2, 0)}})
                .expectDevice("HandheldDevice", "Device3", 52, false,
                        new LocalTime[][]{{LocalTime.of(2, 0), LocalTime.of(3, 0)}})
                .expectDevice("HandheldDevice", "Device4", 53, false,
                        new LocalTime[][]{{LocalTime.of(3, 0), LocalTime.of(4, 0)}})
                .expectDevice("HandheldDevice", "Device5", 54, false,
                        new LocalTime[][]{{LocalTime.of(4, 0), LocalTime.of(5, 0)}})
                .expectDevice("HandheldDevice", "Device6", 55, false,
                        new LocalTime[][]{{LocalTime.of(5, 0), LocalTime.of(6, 0)}})
                .expectDevice("HandheldDevice", "Device7", 56, false,
                        new LocalTime[][]{{LocalTime.of(6, 0), LocalTime.of(7, 0)}})
                .expectDevice("HandheldDevice", "Device8", 57, false,
                        new LocalTime[][]{{LocalTime.of(7, 0), LocalTime.of(8, 0)}})
                .expectDevice("HandheldDevice", "Device9", 58, false,
                        new LocalTime[][]{{LocalTime.of(8, 0), LocalTime.of(9, 0)}})
                .toString();


        String afterWorldState = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("SpaceXSatellite", "Satellite1", 5838, 63.69, 55.5,
                        new String[]{"Device1", "Device10", "Device2", "Device3", "Device4", "Device5", "Device6", "Device7", "Device8", "Device9"},
                        new DummyConnection[]{
                                new DummyConnection("Device1", LocalTime.of(0, 0), LocalTime.of(1, 1), 60),
                                new DummyConnection("Device2", LocalTime.of(1, 0), LocalTime.of(2, 1), 60),
                                new DummyConnection("Device3", LocalTime.of(2, 0), LocalTime.of(3, 1), 60),
                                new DummyConnection("Device4", LocalTime.of(3, 0), LocalTime.of(4, 1), 60),
                                new DummyConnection("Device5", LocalTime.of(4, 0), LocalTime.of(5, 1), 60),
                                new DummyConnection("Device6", LocalTime.of(5, 0), LocalTime.of(6, 1), 60),
                                new DummyConnection("Device7", LocalTime.of(6, 0), LocalTime.of(7, 1), 60),
                                new DummyConnection("Device8", LocalTime.of(7, 0), LocalTime.of(8, 1), 60),
                                new DummyConnection("Device9", LocalTime.of(8, 0), LocalTime.of(9, 1), 60),
                                new DummyConnection("Device10", LocalTime.of(9, 0), LocalTime.of(10, 1), 60)
                        })
                .expectDevice("HandheldDevice", "Device1", 50, false,
                        new LocalTime[][]{{LocalTime.of(0, 0), LocalTime.of(1, 0)}})
                .expectDevice("HandheldDevice", "Device10", 59, false,
                        new LocalTime[][]{{LocalTime.of(9, 0), LocalTime.of(10, 0)}})
                .expectDevice("HandheldDevice", "Device2", 51, false,
                        new LocalTime[][]{{LocalTime.of(1, 0), LocalTime.of(2, 0)}})
                .expectDevice("HandheldDevice", "Device3", 52, false,
                        new LocalTime[][]{{LocalTime.of(2, 0), LocalTime.of(3, 0)}})
                .expectDevice("HandheldDevice", "Device4", 53, false,
                        new LocalTime[][]{{LocalTime.of(3, 0), LocalTime.of(4, 0)}})
                .expectDevice("HandheldDevice", "Device5", 54, false,
                        new LocalTime[][]{{LocalTime.of(4, 0), LocalTime.of(5, 0)}})
                .expectDevice("HandheldDevice", "Device6", 55, false,
                        new LocalTime[][]{{LocalTime.of(5, 0), LocalTime.of(6, 0)}})
                .expectDevice("HandheldDevice", "Device7", 56, false,
                        new LocalTime[][]{{LocalTime.of(6, 0), LocalTime.of(7, 0)}})
                .expectDevice("HandheldDevice", "Device8", 57, false,
                        new LocalTime[][]{{LocalTime.of(7, 0), LocalTime.of(8, 0)}})
                .expectDevice("HandheldDevice", "Device9", 58, false,
                        new LocalTime[][]{{LocalTime.of(8, 0), LocalTime.of(9, 0)}})
                .toString();

        TestHelper plan = new TestHelper()
                .createSatellite("SpaceXSatellite", "Satellite1", 5838, 50)
                .createDevice("HandheldDevice", "Device1", 50)
                .createDevice("HandheldDevice", "Device2", 51)
                .createDevice("HandheldDevice", "Device3", 52)
                .createDevice("HandheldDevice", "Device4", 53)
                .createDevice("HandheldDevice", "Device5", 54)
                .createDevice("HandheldDevice", "Device6", 55)
                .createDevice("HandheldDevice", "Device7", 56)
                .createDevice("HandheldDevice", "Device8", 57)
                .createDevice("HandheldDevice", "Device9", 58)
                .createDevice("HandheldDevice", "Device10", 59)
                .scheduleDeviceActivation("Device1", LocalTime.of(0, 0), 60)
                .scheduleDeviceActivation("Device2", LocalTime.of(1, 0), 60)
                .scheduleDeviceActivation("Device3", LocalTime.of(2, 0), 60)
                .scheduleDeviceActivation("Device4", LocalTime.of(3, 0), 60)
                .scheduleDeviceActivation("Device5", LocalTime.of(4, 0), 60)
                .scheduleDeviceActivation("Device6", LocalTime.of(5, 0), 60)
                .scheduleDeviceActivation("Device7", LocalTime.of(6, 0), 60)
                .scheduleDeviceActivation("Device8", LocalTime.of(7, 0), 60)
                .scheduleDeviceActivation("Device9", LocalTime.of(8, 0), 60)
                .scheduleDeviceActivation("Device10", LocalTime.of(9, 0), 60)
                .showWorldState(initialWorldState)
                .simulate(1440)
                .showWorldState(afterWorldState);

        plan.executeTestPlan();
    }

    @Test
    public void testBlueOriginTest() {
        String initialWorldState = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("BlueOriginSatellite", "Satellite1", 5838, 50, 141.67,
                        new String[]{"Device1", "Device10", "Device2", "Device3", "Device4", "Device5", "Device6", "Device7", "Device8", "Device9"})
                .expectDevice("LaptopDevice", "Device1", 50, false,
                        new LocalTime[][]{{LocalTime.of(0, 0), LocalTime.of(1, 0)}})
                .expectDevice("LaptopDevice", "Device10", 59, false,
                        new LocalTime[][]{{LocalTime.of(9, 0), LocalTime.of(10, 0)}})
                .expectDevice("LaptopDevice", "Device2", 51, false,
                        new LocalTime[][]{{LocalTime.of(1, 0), LocalTime.of(2, 0)}})
                .expectDevice("HandheldDevice", "Device3", 52, false,
                        new LocalTime[][]{{LocalTime.of(2, 0), LocalTime.of(3, 0)}})
                .expectDevice("HandheldDevice", "Device4", 53, false,
                        new LocalTime[][]{{LocalTime.of(3, 0), LocalTime.of(4, 0)}})
                .expectDevice("DesktopDevice", "Device5", 54, false,
                        new LocalTime[][]{{LocalTime.of(4, 0), LocalTime.of(5, 0)}})
                .expectDevice("DesktopDevice", "Device6", 55, false,
                        new LocalTime[][]{{LocalTime.of(5, 0), LocalTime.of(6, 0)}})
                .expectDevice("DesktopDevice", "Device7", 56, false,
                        new LocalTime[][]{{LocalTime.of(6, 0), LocalTime.of(7, 0)}})
                .expectDevice("DesktopDevice", "Device8", 57, false,
                        new LocalTime[][]{{LocalTime.of(7, 0), LocalTime.of(8, 0)}})
                .expectDevice("DesktopDevice", "Device9", 58, false,
                        new LocalTime[][]{{LocalTime.of(8, 0), LocalTime.of(9, 0)}})
                .toString();

        String afterWorldState = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("BlueOriginSatellite", "Satellite1", 5838, 84.94, 141.67,
                        new String[]{"Device1", "Device10", "Device2", "Device3", "Device4", "Device5", "Device6", "Device7", "Device8", "Device9"},
                        new DummyConnection[]{
                                new DummyConnection("Device1", LocalTime.of(0, 0), LocalTime.of(1, 1), 58),
                                new DummyConnection("Device2", LocalTime.of(1, 0), LocalTime.of(2, 1), 58),
                                new DummyConnection("Device3", LocalTime.of(2, 0), LocalTime.of(3, 1), 59),
                                new DummyConnection("Device4", LocalTime.of(3, 0), LocalTime.of(4, 1), 59),
                                new DummyConnection("Device5", LocalTime.of(4, 0), LocalTime.of(5, 1), 55),
                                new DummyConnection("Device6", LocalTime.of(5, 0), LocalTime.of(6, 1), 55),
                                new DummyConnection("Device7", LocalTime.of(6, 0), LocalTime.of(7, 1), 55),
                                new DummyConnection("Device8", LocalTime.of(7, 0), LocalTime.of(8, 1), 55),
                                new DummyConnection("Device9", LocalTime.of(8, 0), LocalTime.of(9, 1), 55),
                                new DummyConnection("Device10", LocalTime.of(9, 0), LocalTime.of(10, 1), 58)
                        })
                .expectDevice("LaptopDevice", "Device1", 50, false,
                        new LocalTime[][]{{LocalTime.of(0, 0), LocalTime.of(1, 0)}})
                .expectDevice("LaptopDevice", "Device10", 59, false,
                        new LocalTime[][]{{LocalTime.of(9, 0), LocalTime.of(10, 0)}})
                .expectDevice("LaptopDevice", "Device2", 51, false,
                        new LocalTime[][]{{LocalTime.of(1, 0), LocalTime.of(2, 0)}})
                .expectDevice("HandheldDevice", "Device3", 52, false,
                        new LocalTime[][]{{LocalTime.of(2, 0), LocalTime.of(3, 0)}})
                .expectDevice("HandheldDevice", "Device4", 53, false,
                        new LocalTime[][]{{LocalTime.of(3, 0), LocalTime.of(4, 0)}})
                .expectDevice("DesktopDevice", "Device5", 54, false,
                        new LocalTime[][]{{LocalTime.of(4, 0), LocalTime.of(5, 0)}})
                .expectDevice("DesktopDevice", "Device6", 55, false,
                        new LocalTime[][]{{LocalTime.of(5, 0), LocalTime.of(6, 0)}})
                .expectDevice("DesktopDevice", "Device7", 56, false,
                        new LocalTime[][]{{LocalTime.of(6, 0), LocalTime.of(7, 0)}})
                .expectDevice("DesktopDevice", "Device8", 57, false,
                        new LocalTime[][]{{LocalTime.of(7, 0), LocalTime.of(8, 0)}})
                .expectDevice("DesktopDevice", "Device9", 58, false,
                        new LocalTime[][]{{LocalTime.of(8, 0), LocalTime.of(9, 0)}})
                .toString();

        TestHelper plan = new TestHelper()
                .createSatellite("BlueOriginSatellite", "Satellite1", 5838, 50)
                .createDevice("LaptopDevice", "Device1", 50)
                .createDevice("LaptopDevice", "Device2", 51)
                .createDevice("HandheldDevice", "Device3", 52)
                .createDevice("HandheldDevice", "Device4", 53)
                .createDevice("DesktopDevice", "Device5", 54)
                .createDevice("DesktopDevice", "Device6", 55)
                .createDevice("DesktopDevice", "Device7", 56)
                .createDevice("DesktopDevice", "Device8", 57)
                .createDevice("DesktopDevice", "Device9", 58)
                .createDevice("LaptopDevice", "Device10", 59)
                .scheduleDeviceActivation("Device1", LocalTime.of(0, 0), 60)
                .scheduleDeviceActivation("Device2", LocalTime.of(1, 0), 60)
                .scheduleDeviceActivation("Device3", LocalTime.of(2, 0), 60)
                .scheduleDeviceActivation("Device4", LocalTime.of(3, 0), 60)
                .scheduleDeviceActivation("Device5", LocalTime.of(4, 0), 60)
                .scheduleDeviceActivation("Device6", LocalTime.of(5, 0), 60)
                .scheduleDeviceActivation("Device7", LocalTime.of(6, 0), 60)
                .scheduleDeviceActivation("Device8", LocalTime.of(7, 0), 60)
                .scheduleDeviceActivation("Device9", LocalTime.of(8, 0), 60)
                .scheduleDeviceActivation("Device10", LocalTime.of(9, 0), 60)
                .showWorldState(initialWorldState)
                .simulate(1440)
                .showWorldState(afterWorldState);

        plan.executeTestPlan();
    }

    @Test
    public void testNasa() {
        String initialWorldState = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("NasaSatellite", "Satellite1", 5838, 50, 85,
                        new String[]{"Device1", "Device10", "Device2", "Device3", "Device4", "Device5", "Device6", "Device7", "Device8", "Device9"})
                .expectDevice("LaptopDevice", "Device1", 50, false,
                        new LocalTime[][]{{LocalTime.of(0, 0), LocalTime.of(1, 0)}})
                .expectDevice("LaptopDevice", "Device10", 59, false,
                        new LocalTime[][]{{LocalTime.of(9, 0), LocalTime.of(10, 0)}})
                .expectDevice("LaptopDevice", "Device2", 51, false,
                        new LocalTime[][]{{LocalTime.of(1, 0), LocalTime.of(2, 0)}})
                .expectDevice("HandheldDevice", "Device3", 52, false,
                        new LocalTime[][]{{LocalTime.of(2, 0), LocalTime.of(3, 0)}})
                .expectDevice("HandheldDevice", "Device4", 53, false,
                        new LocalTime[][]{{LocalTime.of(3, 0), LocalTime.of(4, 0)}})
                .expectDevice("DesktopDevice", "Device5", 54, false,
                        new LocalTime[][]{{LocalTime.of(4, 0), LocalTime.of(5, 0)}})
                .expectDevice("DesktopDevice", "Device6", 55, false,
                        new LocalTime[][]{{LocalTime.of(5, 0), LocalTime.of(6, 0)}})
                .expectDevice("DesktopDevice", "Device7", 56, false,
                        new LocalTime[][]{{LocalTime.of(6, 0), LocalTime.of(7, 0)}})
                .expectDevice("DesktopDevice", "Device8", 57, false,
                        new LocalTime[][]{{LocalTime.of(7, 0), LocalTime.of(8, 0)}})
                .expectDevice("DesktopDevice", "Device9", 58, false,
                        new LocalTime[][]{{LocalTime.of(8, 0), LocalTime.of(9, 0)}})
                .toString();

        String afterWorldState = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("NasaSatellite", "Satellite1", 5838, 70.97, 85,
                        new String[]{"Device1", "Device10", "Device2", "Device3", "Device4", "Device5", "Device6", "Device7", "Device8", "Device9"},
                        new DummyConnection[]{
                                new DummyConnection("Device1", LocalTime.of(0, 0), LocalTime.of(1, 1), 50),
                                new DummyConnection("Device2", LocalTime.of(1, 0), LocalTime.of(2, 1), 50),
                                new DummyConnection("Device3", LocalTime.of(2, 0), LocalTime.of(3, 1), 50),
                                new DummyConnection("Device4", LocalTime.of(3, 0), LocalTime.of(4, 1), 50),
                                new DummyConnection("Device5", LocalTime.of(4, 0), LocalTime.of(5, 1), 50),
                                new DummyConnection("Device6", LocalTime.of(5, 0), LocalTime.of(6, 1), 50),
                                new DummyConnection("Device7", LocalTime.of(6, 0), LocalTime.of(7, 1), 50),
                                new DummyConnection("Device8", LocalTime.of(7, 0), LocalTime.of(8, 1), 50),
                                new DummyConnection("Device9", LocalTime.of(8, 0), LocalTime.of(9, 1), 50),
                                new DummyConnection("Device10", LocalTime.of(9, 0), LocalTime.of(10, 1), 50)
                        })
                .expectDevice("LaptopDevice", "Device1", 50, false,
                        new LocalTime[][]{{LocalTime.of(0, 0), LocalTime.of(1, 0)}})
                .expectDevice("LaptopDevice", "Device10", 59, false,
                        new LocalTime[][]{{LocalTime.of(9, 0), LocalTime.of(10, 0)}})
                .expectDevice("LaptopDevice", "Device2", 51, false,
                        new LocalTime[][]{{LocalTime.of(1, 0), LocalTime.of(2, 0)}})
                .expectDevice("HandheldDevice", "Device3", 52, false,
                        new LocalTime[][]{{LocalTime.of(2, 0), LocalTime.of(3, 0)}})
                .expectDevice("HandheldDevice", "Device4", 53, false,
                        new LocalTime[][]{{LocalTime.of(3, 0), LocalTime.of(4, 0)}})
                .expectDevice("DesktopDevice", "Device5", 54, false,
                        new LocalTime[][]{{LocalTime.of(4, 0), LocalTime.of(5, 0)}})
                .expectDevice("DesktopDevice", "Device6", 55, false,
                        new LocalTime[][]{{LocalTime.of(5, 0), LocalTime.of(6, 0)}})
                .expectDevice("DesktopDevice", "Device7", 56, false,
                        new LocalTime[][]{{LocalTime.of(6, 0), LocalTime.of(7, 0)}})
                .expectDevice("DesktopDevice", "Device8", 57, false,
                        new LocalTime[][]{{LocalTime.of(7, 0), LocalTime.of(8, 0)}})
                .expectDevice("DesktopDevice", "Device9", 58, false,
                        new LocalTime[][]{{LocalTime.of(8, 0), LocalTime.of(9, 0)}})
                .toString();

        TestHelper plan = new TestHelper()
                .createSatellite("NasaSatellite", "Satellite1", 5838, 50)
                .createDevice("LaptopDevice", "Device1", 50)
                .createDevice("LaptopDevice", "Device2", 51)
                .createDevice("HandheldDevice", "Device3", 52)
                .createDevice("HandheldDevice", "Device4", 53)
                .createDevice("DesktopDevice", "Device5", 54)
                .createDevice("DesktopDevice", "Device6", 55)
                .createDevice("DesktopDevice", "Device7", 56)
                .createDevice("DesktopDevice", "Device8", 57)
                .createDevice("DesktopDevice", "Device9", 58)
                .createDevice("LaptopDevice", "Device10", 59)
                .scheduleDeviceActivation("Device1", LocalTime.of(0, 0), 60)
                .scheduleDeviceActivation("Device2", LocalTime.of(1, 0), 60)
                .scheduleDeviceActivation("Device3", LocalTime.of(2, 0), 60)
                .scheduleDeviceActivation("Device4", LocalTime.of(3, 0), 60)
                .scheduleDeviceActivation("Device5", LocalTime.of(4, 0), 60)
                .scheduleDeviceActivation("Device6", LocalTime.of(5, 0), 60)
                .scheduleDeviceActivation("Device7", LocalTime.of(6, 0), 60)
                .scheduleDeviceActivation("Device8", LocalTime.of(7, 0), 60)
                .scheduleDeviceActivation("Device9", LocalTime.of(8, 0), 60)
                .scheduleDeviceActivation("Device10", LocalTime.of(9, 0), 60)
                .showWorldState(initialWorldState)
                .simulate(1440)
                .showWorldState(afterWorldState);

        plan.executeTestPlan();
    }

    @Test
    public void testSoviet() {
        String initialWorldState = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("SovietSatellite", "Satellite1", 5838, 50, 100,
                        new String[]{"Device1", "Device10", "Device2", "Device5", "Device6", "Device7", "Device8", "Device9"})
                .expectDevice("LaptopDevice", "Device1", 50, false,
                        new LocalTime[][]{{LocalTime.of(0, 0), LocalTime.of(1, 0)}})
                .expectDevice("LaptopDevice", "Device10", 59, false,
                        new LocalTime[][]{{LocalTime.of(9, 0), LocalTime.of(10, 0)}})
                .expectDevice("LaptopDevice", "Device2", 51, false,
                        new LocalTime[][]{{LocalTime.of(1, 0), LocalTime.of(2, 0)}})
                .expectDevice("HandheldDevice", "Device3", 52, false,
                        new LocalTime[][]{{LocalTime.of(2, 0), LocalTime.of(3, 0)}})
                .expectDevice("HandheldDevice", "Device4", 53, false,
                        new LocalTime[][]{{LocalTime.of(3, 0), LocalTime.of(4, 0)}})
                .expectDevice("DesktopDevice", "Device5", 54, false,
                        new LocalTime[][]{{LocalTime.of(4, 0), LocalTime.of(5, 0)}})
                .expectDevice("DesktopDevice", "Device6", 55, false,
                        new LocalTime[][]{{LocalTime.of(5, 0), LocalTime.of(6, 0)}})
                .expectDevice("DesktopDevice", "Device7", 56, false,
                        new LocalTime[][]{{LocalTime.of(6, 0), LocalTime.of(7, 0)}})
                .expectDevice("DesktopDevice", "Device8", 57, false,
                        new LocalTime[][]{{LocalTime.of(7, 0), LocalTime.of(8, 0)}})
                .expectDevice("DesktopDevice", "Device9", 58, false,
                        new LocalTime[][]{{LocalTime.of(8, 0), LocalTime.of(9, 0)}})
                .toString();

        String afterWorldState = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("SovietSatellite", "Satellite1", 5838, 74.67, 100,
                        new String[]{"Device1", "Device10", "Device2", "Device5", "Device6", "Device7", "Device8", "Device9"},
                        new DummyConnection[]{
                                new DummyConnection("Device1", LocalTime.of(0, 0), LocalTime.of(1, 1), 56),
                                new DummyConnection("Device2", LocalTime.of(1, 0), LocalTime.of(2, 1), 56),
                                new DummyConnection("Device5", LocalTime.of(4, 0), LocalTime.of(5, 1), 50),
                                new DummyConnection("Device6", LocalTime.of(5, 0), LocalTime.of(6, 1), 50),
                                new DummyConnection("Device7", LocalTime.of(6, 0), LocalTime.of(7, 1), 50),
                                new DummyConnection("Device8", LocalTime.of(7, 0), LocalTime.of(8, 1), 50),
                                new DummyConnection("Device9", LocalTime.of(8, 0), LocalTime.of(9, 1), 50),
                                new DummyConnection("Device10", LocalTime.of(9, 0), LocalTime.of(10, 1), 56)
                        })
                .expectDevice("LaptopDevice", "Device1", 50, false,
                        new LocalTime[][]{{LocalTime.of(0, 0), LocalTime.of(1, 0)}})
                .expectDevice("LaptopDevice", "Device10", 59, false,
                        new LocalTime[][]{{LocalTime.of(9, 0), LocalTime.of(10, 0)}})
                .expectDevice("LaptopDevice", "Device2", 51, false,
                        new LocalTime[][]{{LocalTime.of(1, 0), LocalTime.of(2, 0)}})
                .expectDevice("HandheldDevice", "Device3", 52, false,
                        new LocalTime[][]{{LocalTime.of(2, 0), LocalTime.of(3, 0)}})
                .expectDevice("HandheldDevice", "Device4", 53, false,
                        new LocalTime[][]{{LocalTime.of(3, 0), LocalTime.of(4, 0)}})
                .expectDevice("DesktopDevice", "Device5", 54, false,
                        new LocalTime[][]{{LocalTime.of(4, 0), LocalTime.of(5, 0)}})
                .expectDevice("DesktopDevice", "Device6", 55, false,
                        new LocalTime[][]{{LocalTime.of(5, 0), LocalTime.of(6, 0)}})
                .expectDevice("DesktopDevice", "Device7", 56, false,
                        new LocalTime[][]{{LocalTime.of(6, 0), LocalTime.of(7, 0)}})
                .expectDevice("DesktopDevice", "Device8", 57, false,
                        new LocalTime[][]{{LocalTime.of(7, 0), LocalTime.of(8, 0)}})
                .expectDevice("DesktopDevice", "Device9", 58, false,
                        new LocalTime[][]{{LocalTime.of(8, 0), LocalTime.of(9, 0)}})
                .toString();

        TestHelper plan = new TestHelper()
                .createSatellite("SovietSatellite", "Satellite1", 5838, 50)
                .createDevice("LaptopDevice", "Device1", 50)
                .createDevice("LaptopDevice", "Device2", 51)
                .createDevice("HandheldDevice", "Device3", 52)
                .createDevice("HandheldDevice", "Device4", 53)
                .createDevice("DesktopDevice", "Device5", 54)
                .createDevice("DesktopDevice", "Device6", 55)
                .createDevice("DesktopDevice", "Device7", 56)
                .createDevice("DesktopDevice", "Device8", 57)
                .createDevice("DesktopDevice", "Device9", 58)
                .createDevice("LaptopDevice", "Device10", 59)
                .scheduleDeviceActivation("Device1", LocalTime.of(0, 0), 60)
                .scheduleDeviceActivation("Device2", LocalTime.of(1, 0), 60)
                .scheduleDeviceActivation("Device3", LocalTime.of(2, 0), 60)
                .scheduleDeviceActivation("Device4", LocalTime.of(3, 0), 60)
                .scheduleDeviceActivation("Device5", LocalTime.of(4, 0), 60)
                .scheduleDeviceActivation("Device6", LocalTime.of(5, 0), 60)
                .scheduleDeviceActivation("Device7", LocalTime.of(6, 0), 60)
                .scheduleDeviceActivation("Device8", LocalTime.of(7, 0), 60)
                .scheduleDeviceActivation("Device9", LocalTime.of(8, 0), 60)
                .scheduleDeviceActivation("Device10", LocalTime.of(9, 0), 60)
                .showWorldState(initialWorldState)
                .simulate(1440)
                .showWorldState(afterWorldState);

        plan.executeTestPlan();
    }
}
