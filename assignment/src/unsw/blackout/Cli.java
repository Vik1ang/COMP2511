package unsw.blackout;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.json.JSONObject;

/**
 * A command line wrapper around Blackout
 * 
 * @author Braedon Wooding & Nick Patrikeos
 */
public class Cli {
    private Blackout blackout;

    // recommendation: Don't add any extra state here, it's not good design to
    // expose your inner workings to any Cli frontends.

    public Cli() {
        // feel free to change this to point to whatever class you want to point it to
        // or to pass in arguments or however else this feeds into your design.
        blackout = new Blackout();
    }

    /// There is no need to change this function, please don't change this
    /// If you change this there is a good chance you'll break something...
    public static void main(String[] args) {
        Cli system = new Cli();
        system.run(args);
    }

    /// Processes a given command.
    public void processCommand(JSONObject json) {
        switch (json.getString("command")) {
        case "createDevice": {
            String id = json.getString("id");
            String deviceType = json.getString("type");
            double position = json.getDouble("position");
            blackout.createDevice(id, deviceType, position);
            break;
        }
        case "createSatellite": {
            String id = json.getString("id");
            String satelliteType = json.getString("type");
            double height = json.getDouble("height");
            double position = json.getDouble("position");

            blackout.createSatellite(id, satelliteType, height, position);
            break;
        }
        case "scheduleDeviceActivation": {
            LocalTime start = LocalTime.parse(json.getString("startTime"), DateTimeFormatter.ofPattern("HH:mm"));
            int duration = json.getInt("durationInMinutes");
            String deviceId = json.getString("deviceId");

            blackout.scheduleDeviceActivation(deviceId, start, duration);
            break;
        }
        case "removeDevice": {
            String id = json.getString("id");

            blackout.removeDevice(id);
            break;
        }
        case "moveDevice": {
            String id = json.getString("id");
            double newPos = json.getDouble("newPosition");

            blackout.moveDevice(id, newPos);
            break;
        }
        case "removeSatellite":
            String id = json.getString("id");

            blackout.removeSatellite(id);
            break;
        case "showWorldState":
            System.out.println(blackout.showWorldState().toString(0));
            break;
        case "simulate": {
            int minutes = json.getInt("durationInMinutes");
            blackout.simulate(minutes);
            break;
        }
        default:
            throw new UnsupportedOperationException(
                    String.format("%s is not currently implemented.", json.getString("command")));
        }
    }

    /// DON'T TOUCH ANY CODE BELOW THIS POINT
    ///
    /// this code is so you get a nice command line interface to make it easier
    /// for you to test / develop, it supports both a human readable command line
    /// i.e. createDevice DesktopDevice MyDevice 40.5 as well as the JSON equivalent
    /// { "command": "createDevice", "type": "DesktopDevice", "id": "MyDevice",
    /// "position": 40.5 }
    ///
    /// Code has been documented well for your curiosity and intrigue and while it
    /// would be good for you
    /// to read through this all; you aren't required to understand / know this for
    /// this assignment. If you do not understand this don't fret.
    ///
    /// The documentation provided here is excessively verbose as to help those
    /// who are newer to the language and are struggling to understand some of the
    /// more difficult concepts.

    /// Below are a series of exceptions for each of the possible cases of invalid
    /// errors
    /// - Too many or too few args
    /// - An arg was expected to be in a certain number format but wasn't
    /// - An arg was expected to be in a certain time format but wasn't
    /// - Unknown command
    private static class CliCommandException extends Exception {
        /**
         * Subclassing is useful for exceptions since we can provide many possible
         * 'variants' of the same exception while still containing them to a single
         * section of the code (i.e. we don't need 100 files)
         * 
         * 'static' classes can be created without needing an instance of the outer
         * class i.e. I don't need to write `new Cli().new CliCommandException()` I can
         * just write new Cli.CliCommandException()
         * 
         * Making them private since they only matter to this Cli class.
         * 
         * You SHOULDN'T be testing these exceptions in your tests.
         */

        /** Conversion of what was meant to be a number failed */
        private static class CliArgNumberFormatException extends CliCommandException {
            public CliArgNumberFormatException(CliCommand.Arg arg, NumberFormatException inner) {
                super(String.format("Error %s, expected arg %s", inner.getMessage(), arg.toString()));
            }
        }

        /** Conversion of what was meant to be a date time failed */
        private static class CliArgDateTimeParseException extends CliCommandException {
            public CliArgDateTimeParseException(CliCommand.Arg arg, DateTimeParseException inner) {
                super(String.format("Error %s, expected arg %s", inner.getMessage(), arg.toString()));
            }
        }

        /** Missing a required argument */
        private static class CliArgsMissingException extends CliCommandException {
            public CliArgsMissingException(List<CliCommand.Arg> args, int argsProcessed) {
                super("Missing " + Integer.toString(args.size() - argsProcessed) + " args: " + args.stream()
                        .skip(argsProcessed).map(CliCommand.Arg::toString).collect(Collectors.joining(", ")));
            }
        }

        /** Extra arguments that aren't required/optional */
        private static class CliTooManyArgsException extends CliCommandException {
            public CliTooManyArgsException(int extraArgs, int expectedArgs) {
                super(String.format("Too many args, only expected %d but got %d", expectedArgs, extraArgs));
            }
        }

        /** Unknown command specified */
        private static class CliUnknownCommandException extends CliCommandException {
            public CliUnknownCommandException(String cmd) {
                super(String.format("Unknown Command %s", cmd));
            }
        }

        public CliCommandException(String err) {
            super(err);
        }
    }

    /**
     * This represents a command like 'createDevice' which is composed of multiple
     * arguments like <id: String> and so on, by abstracting it like this we make it
     * much more convenient to parse and manipulate it.
     **/
    private static class CliCommand {
        /// Having a 'private' class like Arg here is useful since it means we can
        /// encapsulate the behaviour of arguments within a CliCommand without having
        /// to expose them to outer classes since they don't need to be exposed.
        private static abstract class Arg {
            private String name;
            private String typeName;

            public Arg(String name, String typeName) {
                this.name = name;
                this.typeName = typeName;
            }

            public abstract Object convert(String arg) throws CliCommandException;

            /**
             * Converts 'arg' to the correct type for this argument and attemps to write it
             * to the given JSON object if any formatting fails will throw command exception
             */
            public void writeToJSON(String arg, JSONObject obj) throws CliCommandException {
                obj.put(name, convert(arg));
            }

            @Override
            public String toString() {
                return String.format("<%s: %s>", name, typeName);
            }
        }

        private String cmd;
        private String description;
        private List<Arg> args = new ArrayList<>();

        public CliCommand(String cmd, String description) {
            this.cmd = cmd;
            this.description = description;
        }

        // a lot of modern languages allow us to do what is called 'generic
        // specialisation'
        // i.e. we could say <String>addArg(String name) here, the benefit of doing this
        // is
        // that we have a single function to call (addArg) and we specialise the type
        // rather than having to ad-hoc attach a type suffix here...
        // This is fine though, since Java doesn't support that stuff.
        public CliCommand addStringArg(String name) {
            // a useful way is to use classes like this is to create 'anonymous' classes
            // this will create a hidden 'special' class that is a subclass of Arg with the
            // function overload.
            // quite nice!
            this.args.add(new Arg(name, "String") {
                @Override
                public Object convert(String arg) throws CliCommandException {
                    return arg;
                }
            });
            return this;
        }

        public CliCommand addDoubleArg(String name) {
            this.args.add(new Arg(name, "Double") {
                @Override
                public Object convert(String arg) throws CliCommandException {
                    try {
                        return Double.parseDouble(arg);
                    } catch (NumberFormatException e) {
                        throw new CliCommandException.CliArgNumberFormatException(this, e);
                    }
                }
            });
            return this;
        }

        public CliCommand addIntegerArg(String name) {
            this.args.add(new Arg(name, "Integer") {
                @Override
                public Object convert(String arg) throws CliCommandException {
                    try {
                        return Integer.parseInt(arg);
                    } catch (NumberFormatException e) {
                        throw new CliCommandException.CliArgNumberFormatException(this, e);
                    }
                }
            });
            return this;
        }

        public CliCommand addTimeArg(String name) {
            this.args.add(new Arg(name, "Time") {
                @Override
                public Object convert(String arg) throws CliCommandException {
                    try {
                        LocalTime.parse(arg, DateTimeFormatter.ofPattern("HH:mm"));
                    } catch (DateTimeParseException e) {
                        throw new CliCommandException.CliArgDateTimeParseException(this, e);
                    }
                    return arg;
                }
            });
            return this;
        }

        public JSONObject toJSON(String[] cmdArgs) throws CliCommandException {
            JSONObject obj = new JSONObject();
            obj.put("command", cmd);
            if (cmdArgs.length > args.size()) {
                throw new CliCommandException.CliTooManyArgsException(cmdArgs.length - args.size(), args.size());
            } else if (cmdArgs.length < args.size()) {
                throw new CliCommandException.CliArgsMissingException(args, cmdArgs.length);
            }

            for (int i = 0; i < args.size(); i++) {
                args.get(i).writeToJSON(cmdArgs[i], obj);
            }
            return obj;
        }

        @Override
        public String toString() {
            return cmd + " " + args.stream().map(Arg::toString).collect(Collectors.joining(" "))
                    + (args.size() > 0 ? " " : "") + description;
        }
    }

    private static final Map<String, CliCommand> commands = new HashMap<String, CliCommand>();

    static {
        commands.put("createDevice",
                new CliCommand("createDevice", "creates a device at the given position measured in degrees")
                        .addStringArg("type").addStringArg("id").addDoubleArg("position"));
        commands.put("removeDevice",
                new CliCommand("removeDevice", "removes the given device from the world").addStringArg("id"));
        commands.put("moveDevice",
                new CliCommand("moveDevice", "moves the device to the newly specified position measured in degrees")
                        .addStringArg("id").addDoubleArg("newPosition"));
        commands.put("createSatellite",
                new CliCommand("createSatellite", "creates a satellite at the given position measured in degrees")
                        .addStringArg("type").addStringArg("id").addDoubleArg("height").addDoubleArg("position"));
        commands.put("removeSatellite",
                new CliCommand("removeSatellite", "removes the satellite with the specified id from the world")
                        .addStringArg("id"));
        commands.put("scheduleDeviceActivation", new CliCommand("scheduleDeviceActivation",
                "schedules a device to activate from the given start time for the duration specified (in minutes)")
                        .addStringArg("deviceId").addTimeArg("startTime").addIntegerArg("durationInMinutes"));
        commands.put("showWorldState", new CliCommand("showWorldState", "shows all devices and satellites"));
        commands.put("simulate", new CliCommand("simulate", "runs the simulation and schedules device activations")
                .addIntegerArg("durationInMinutes"));
    }

    public static void printHelp() {
        System.out.println("Help");
        System.out.println("\tq - exits this tool (Ctrl+C & Ctrl+D will also work)");
        System.out.println("\th - this message");
        for (CliCommand cliCommand : commands.values()) {
            System.out.println("\t" + cliCommand.toString());
        }
        System.out.println("\n");

        System.out.println("Examples");
        System.out.println("\tcreateDevice DesktopDevice MyDevice 40.5");
        System.out.println("\tcreateSatellite NasaSatellite MySatellite 10000 40.5");
        System.out.println("\tmoveDevice MyDevice");
        System.out.println("\tremoveDevice MyDevice");
        System.out.println("\tremoveSatellite MySatellite");
        System.out.println("\tscheduleDeviceActivation MyDevice 00:00 100");
        System.out.println("\tshowWorldState");
        System.out.println("\tsimulate 1440");

        System.out.println("\n");
        System.out.println("NOTE: <id> is purely alphanumerical i.e. letter or number");
    }

    private JSONObject parseCommand(String line) {
        // split commands on spaces i.e. "a b c" => a, b, c
        // \s includes any space (' ', '\t', '\r', '\n' and so on)
        String[] cmdArgs = line.split("\\s+");
        if (cmdArgs.length == 0)
            return null;

        String cmd = cmdArgs[0];
        // pop off the first argument since that's just the command
        cmdArgs = Arrays.copyOfRange(cmdArgs, 1, cmdArgs.length);

        if (cmd.equals("q")) {
            System.out.println("Exiting...");
            System.exit(0);
            /* unreachable */ return null;
        } else if (cmd.equals("h")) {
            printHelp();
            return null;
        } else {
            CliCommand cmdToExecute = null;
            try {
                cmdToExecute = commands.get(cmd);
                if (cmdToExecute == null) {
                    throw new CliCommandException.CliUnknownCommandException(cmd);
                }

                return cmdToExecute.toJSON(cmdArgs);
            } catch (CliCommandException e) {
                System.out.println(String.format("Error: %s", e.getMessage()));
                if (cmdToExecute != null)
                    System.out.println(cmdToExecute.toString());
                System.out.println("Type h for help / commands");
                return null;
            }
        }
    }

    public void run(String[] args) {
        boolean jsonOnlyEnabled = false;

        // if they supply '--json' at any point we'll switch to JSON only mode
        jsonOnlyEnabled = Arrays.stream(args).anyMatch("--json"::equals);

        if (!jsonOnlyEnabled) {
            System.out.println("Welcome to Jovian CLI Version 1.0 ...");
            System.out.println("\n");
            printHelp();
        }

        try (Scanner in = new Scanner(System.in)) {
            while (true) {
                if (!jsonOnlyEnabled)
                    System.out.print("> ");
                if (!in.hasNextLine())
                    break;

                String line = in.nextLine().trim();

                if (line == null) // no more input
                    break;
                if (line.isBlank()) // empty line
                    continue;

                JSONObject command;

                if (jsonOnlyEnabled) {
                    command = new JSONObject(line);
                } else {
                    command = parseCommand(line);
                }

                if (command == null)
                    continue;

                // just read each non empty line and process the JSON command
                if (!jsonOnlyEnabled)
                    System.out.print("< ");

                try {
                    processCommand(command);
                } catch (Exception e) {
                    if (jsonOnlyEnabled) {
                        StringWriter out = new StringWriter();
                        e.printStackTrace(new PrintWriter(out));
                        JSONObject jo = new JSONObject();
                        jo.put("type", "error");
                        jo.put("error", e.getMessage() == null ? e.getClass().getSimpleName() : e.getMessage());
                        jo.put("stack_trace", out.toString());
                        System.out.println(jo.toString(0));

                        // try to dump out world state to help :D.
                        try {
                            processCommand(parseCommand("showWorldState"));
                        } catch (Exception newE) {
                            // no world state... so we just give you another error object
                            out = new StringWriter();
                            newE.printStackTrace(new PrintWriter(out));
                            jo = new JSONObject();
                            jo.put("type", "error");
                            jo.put("error", newE.getMessage() == null ? newE.getClass().getSimpleName() : newE.getMessage());
                            jo.put("stack_trace", out.toString());
                            System.out.println(jo.toString(0));
                        }
                    } else {
                        System.out.println(String.format("Error %s", e.getMessage()));
                        e.printStackTrace();

                        System.out.println("Dumping world state... (to help debugging)");
                        try {
                            processCommand(parseCommand("showWorldState"));
                        } catch (Exception newE) {
                            System.out.println(
                                    String.format("Failed to dump world state :(... Error %s", newE.getMessage()));
                            newE.printStackTrace();
                        }
                    }
                    continue;
                }
                System.out.println("");
            }
        }
    }
}
