package test.test_helpers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.skyscreamer.jsonassert.comparator.DefaultComparator;

import unsw.blackout.Cli;

/**
 * Allows you to specify a series of tests to execute, we will then compare the JSON and assert it's the same.
 */
public class TestHelper {
    /**
     * This compares JSON using a specificed precision for any doubles.
     */
    public static class JSONComparerDoublePrecision extends DefaultComparator {
        private Double precision;

        public JSONComparerDoublePrecision(JSONCompareMode mode, Double precision) {
            super(mode);
            this.precision = precision;
        }

        public static void assertEquals(String expected, String actual, double precision) throws JSONException {
            JSONCompareResult result = JSONCompare.compareJSON(expected, actual,
                    new JSONComparerDoublePrecision(JSONCompareMode.STRICT_ORDER, precision));
            if (result.failed()) {
                throw new AssertionError(result.getMessage());
            }
        }

        @Override
        public void compareValues(String prefix, Object expectedValue, Object actualValue, JSONCompareResult result)
                throws JSONException {
            if (expectedValue instanceof Number && actualValue instanceof Number) {
                if (Math.abs(
                        ((Number) expectedValue).doubleValue() - ((Number) actualValue).doubleValue()) > precision) {
                    result.fail(prefix, expectedValue, actualValue);
                }
            } else if (expectedValue.getClass().isAssignableFrom(actualValue.getClass())) {
                if (expectedValue instanceof JSONArray) {
                    compareJSONArray(prefix, (JSONArray) expectedValue, (JSONArray) actualValue, result);
                } else if (expectedValue instanceof JSONObject) {
                    compareJSON(prefix, (JSONObject) expectedValue, (JSONObject) actualValue, result);
                } else if (!expectedValue.equals(actualValue)) {
                    result.fail(prefix, expectedValue, actualValue);
                }
            } else {
                result.fail(prefix, expectedValue, actualValue);
            }
        }
    }

    private List<String> commands = new ArrayList<String>();
    private List<String> expectedOutput = new ArrayList<String>();
    private final static Pattern validRegexPattern = Pattern.compile("[a-zA-Z_0-9]+");

    private void validateId(String id) {
        Assert.assertTrue(String.format("id (%s) needs to match pattern a-z, A-Z, 0-9, or _", id),
                validRegexPattern.matcher(id).matches());
    }

    /**
     * <p> {@code > createDevice type id angle} </p>
     * 
     * <p> Creates a new device </p>
     * 
     * <pre>{@code { "command": "createDevice", "id": <String>, "type": <String>, "angle": <double> }</pre>
     * 
     * @param type the type of the device i.e. Handheld, MobileX, ...
     * @param id the alphanumeric name of the device
     * @param angle the angle of the device with respect to the x-axis (anti-clockwise), measured in degrees.
     */
    public TestHelper createDevice(String type, String id, double angle) {
        validateId(id);
        commands.add(
                String.format("{ \"command\": \"createDevice\", \"id\": \"%s\", \"type\": \"%s\", \"position\": %f }",
                        id, type, angle));
        return this;
    }

    /**
     * <p> {@code > removeDevice id} </p>
     * 
     * <p> Removes a given device </p>
     * 
     * <pre>{@code { "command": "removeDevice", "id": <String> }</pre>
     * 
     * @param id the alphanumeric name of the device
     */
    public TestHelper removeDevice(String id) {
        validateId(id);
        commands.add(String.format("{ \"command\": \"removeDevice\", \"id\": \"%s\" }", id));
        return this;
    }

    /**
     * <p> {@code > moveDevice id} </p>
     * 
     * <p> Moves a given device </p>
     * 
     * <pre>{@code { "command": "moveDevice", "id": <String> }</pre>
     * 
     * @param id the alphanumeric name of the device
     * @param newPosition the new position measured in degrees
     */
    public TestHelper moveDevice(String id, double newPosition) {
        validateId(id);
        commands.add(
                String.format("{ \"command\": \"moveDevice\", \"id\": \"%s\", \"newPosition\": %f }", id, newPosition));
        return this;
    }

    /**
     * <p> {@code > createSatellite type id angle} </p>
     * 
     * <p> Creates a new device </p>
     * 
     * <pre>{@code { "command": "createSatellite", "id": <String>, "type": <String>, "height": <double>, "angle": <double> }</pre>
     * 
     * @param type the type of the satellite i.e. SpaceX, Soviet, ...
     * @param id the alphanumeric name of the satellite
     * @param angle the angle of the satellite with respect to the x-axis (anti-clockwise), measured in degrees.
     */
    public TestHelper createSatellite(String type, String id, double height, double position) {
        validateId(id);
        commands.add(String.format(
                "{ \"command\": \"createSatellite\", \"id\": \"%s\", \"type\": \"%s\", \"height\": %f, \"position\": %f }",
                id, type, height, position));
        return this;
    }

    /**
     * <p> {@code > removeSatellite id} </p>
     * 
     * <p> Removes a given device </p>
     * 
     * <pre>{@code { "command": "removeSatellite", "id": <String> }</pre>
     * 
     * @param id the alphanumeric name of the device
     */
    public TestHelper removeSatellite(String id) {
        validateId(id);
        commands.add(String.format("{ \"command\": \"removeSatellite\", \"id\": \"%s\" }", id));
        return this;
    }

    /**
     * <p> {@code > scheduleDeviceActivation id start durationInMinutes} </p>
     * 
     * <p> Schedules a device for activation from start to end (where end = start + durationInMinutes), during this time the device can connect to satellites </p>
     * 
     * <pre>{@code { "command": "scheduleDeviceActivation", "id": <String>, "startTime": <LocalTime>, "durationInMinutes": <int> }</pre>
     * 
     * @param id the alphanumeric name of the device
     * @param start the start (local time, hour + min) of the device activation i.e. LocalTime.of(10, 2) being 10:02 (24 hr time)
     * @param durationInMinutes the duration in minutes of the activation
     */
    public TestHelper scheduleDeviceActivation(String id, LocalTime start, int durationInMinutes) {
        validateId(id);
        commands.add(String.format(
                "{ \"command\": \"scheduleDeviceActivation\", \"deviceId\": \"%s\", \"startTime\": \"%s\", \"durationInMinutes\": %d }",
                id, start.toString(), durationInMinutes));
        return this;
    }

    /**
     * <p> {@code > showWorldState} </p>
     * 
     * <p> Shows the world state (as JSON) </p>
     * 
     * <pre>{@code { "command": "showWorldState" }</pre>
     * 
     * @param expected a string holding the expect json output, recommended to use {@link ResponseHelper}.toString() to generate it
     */
    public TestHelper showWorldState(String expected) {
        commands.add(String.format("{ \"command\": \"showWorldState\" }"));
        expectedOutput.add(expected);
        return this;
    }

    /**
     * <p> {@code > simulate durationInMinutes} </p>
     * 
     * <p> Runs the world simulation moving satellites and connecting/disconnecting them to devices, runs for the supplied period of time (specified in minutes) </p>
     * 
     * <pre>{@code { "command": "simulate", "durationInMinutes": <int> }</pre>
     * 
     * @param durationInMinutes the duration of the simulation bounded from (0, 60 * 24]
     */
    public TestHelper simulate(int durationInMinutes) {
        commands.add(String.format("{ \"command\": \"simulate\", \"durationInMinutes\": %d }", durationInMinutes));
        return this;
    }

    public void executeTestPlan() {
        PrintStream oldOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        InputStream oldIn = System.in;
        String input = String.join("\n", commands);

        System.setOut(new PrintStream(out));
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        try {
            Cli cli = new Cli();
            cli.run(new String[] { "--json" });

            String rawOutput = out.toString(StandardCharsets.UTF_8).strip();

            // because we want to be nice to students we are letting them output the objects
            // in 'any' format for JSON
            // however we can just convert this stream into a series of JSON objects with
            // some light processing
            // since we know that '{ and }' can't exist in identifiers we can use them as
            // separated chars
            int currentDepth = 0;
            int lastIndex = 0;
            int objectIndex = 0;

            // count the braces and form objects from them
            for (int i = 0; i < rawOutput.length(); i++) {
                if (rawOutput.charAt(i) == '{') {
                    currentDepth++;
                } else if (rawOutput.charAt(i) == '}') {
                    if (currentDepth == 0) {
                        // to prevent students forming ill-formed JSON.
                        throw new RuntimeException("The JSON outputted is invalid and is ill-formed");
                    }

                    currentDepth--;
                    if (currentDepth == 0) {
                        // from [lastIndex, i + 1)
                        String slice = rawOutput.substring(lastIndex, i + 1).strip();
                        lastIndex = i + 1;
                        if (objectIndex >= expectedOutput.size()) {
                            if (!slice.strip().isBlank())
                                Assert.fail("Too many JSON Objects detected, found extra: " + slice);
                        } else if (!slice.equals(expectedOutput.get(objectIndex).strip())) {
                            // we only need to run the JSON assert in the case the strings don't match
                            // the strings will match for things like empty strings / no outputs

                            // check if slice contains an exception if so then we should log that to be
                            // nicer!
                            JSONObject jo = new JSONObject(slice);
                            if (jo.has("type") && jo.getString("type").equals("error")) {
                                // the rest of the output will contain world dumps and so on
                                Assert.fail(String.format(
                                        "Error: %s\n%s\nRest of output (will contain world dump if it exists)%s",
                                        jo.optString("error", ""), jo.getString("stack_trace"),
                                        rawOutput.substring(lastIndex)));
                            } else {
                                JSONComparerDoublePrecision.assertEquals(expectedOutput.get(objectIndex), slice, 0.01);
                            }
                        }

                        objectIndex++;
                    }
                }
            }

            if (objectIndex < expectedOutput.size()) {
                Assert.fail("Too few outputs was expected the following JSON Objects:\n"
                        + String.join("\n", expectedOutput.stream().skip(objectIndex).collect(Collectors.toList())));
            }
        } finally {
            // if the asserts trigger tests will continue to run and we should restore the
            // outs for that
            System.setOut(oldOut);
            System.setIn(oldIn);
        }
    }
}
