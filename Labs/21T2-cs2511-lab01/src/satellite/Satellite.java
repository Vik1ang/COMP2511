package satellite;


public class Satellite {

    private String name;
    private int height;
    private double position;
    private double velocity;


    /**
     * Constructor for Satellite
     *
     * @param name
     * @param height
     * @param velocity
     */
    public Satellite(String name, int height, double position, double velocity) {
        this.name = name;
        this.height = height;
        this.position = position;
        this.velocity = velocity;
    }

    /**
     * Getter for name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Getter for position (degrees)
     */
    public double getPositionDegrees() {
        return this.position;
    }

    /**
     * Getter for position (radians)
     */
    public double getPositionRadians() {
        return Math.toRadians(this.position);
    }

    /**
     * Returns the linear velocity (metres per second) of the satellite
     */
    public double getLinearVelocity() {
        return this.velocity;
    }

    /**
     * Returns the angular velocity (degrees per second) of the satellite
     */
    public double getAngularVelocity() {
        return this.velocity / this.getHeight();
        // return this.velocity / this.getPositionRadians();
    }

    /**
     * Setter for name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for height
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Setter for velocity
     *
     * @param velocity
     */
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    /**
     * Setter for position
     *
     * @param position
     */
    public void setPosition(double position) {
        this.position = position;
    }

    /**
     * Calculates the distance travelled by the satellite in the given time
     *
     * @param time (seconds)
     * @return distance in metres
     */
    public double distance(double time) {
        return this.velocity * time;
    }

    public static void main(String[] args) {
        // Creates Satellite A, which is 10000 km above the centre of the earth, with a velocity of 55 mps and at θ = 122.
        Satellite satelliteA = new Satellite("Satellite A", 10000, 122, 55);
        // Creates Satellite B, which is 5438 km above the centre of the earth, with a velocity of 234 mps and at θ = 0.
        Satellite satelliteB = new Satellite("Satellite B", 5438, 0, 234);
        // Creates Satellite C, which is 9029 km above the centre of the earth, with a velocity of 0 mps and at θ = 284.
        Satellite satelliteC = new Satellite("Satellite C", 9029, 284, 0);
        // For Satellite A, print out I am {name} at position {theta} degrees, {height} km above the centre of the earth and moving at a velocity of {velocity} metres per second
        System.out.printf("I am %s at position %.1f degrees, %d km above the centre of the earth and moving at a velocity of %.1f metres per second\n", satelliteA.getName(), satelliteA.getPositionDegrees(), satelliteA.getHeight(), satelliteA.getLinearVelocity());
        // Change Satellite A's height to 9999
        satelliteA.setHeight(9999);
        // Change Satellite B's angle to 45
        satelliteB.setPosition(45);
        // Change Satellite C's velocity to 36.5 mps
        satelliteC.setVelocity(36.5);
        // Print out Satellite A's position in radians
        System.out.println(satelliteA.getPositionRadians());
        // Print out Satellite B's angular velocity
        System.out.println(satelliteB.getAngularVelocity());
        // Print out the distance Satellite C travels after 2 minutes.
        System.out.println(satelliteC.distance(120));
    }

}