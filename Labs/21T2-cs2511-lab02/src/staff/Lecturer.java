package staff;


import java.time.LocalDate;

/**
 * The type Lecturer.
 */
public class Lecturer extends StaffMember {
    private String school;
    private char status;

    /**
     * Instantiates a new Lecturer.
     *
     * @param name   the name
     * @param salary the salary
     * @param school the school
     * @param status the status
     */
    public Lecturer(String name, double salary, String school, char status) {
        super(name, salary);
        this.school = school;
        this.status = status;
    }

    /**
     * Instantiates a new Lecturer.
     *
     * @param name     the name
     * @param salary   the salary
     * @param hireData the hire data
     * @param endDate  the end date
     * @param school   the school
     * @param status   the status
     */
    public Lecturer(String name, double salary, LocalDate hireData, LocalDate endDate, String school, char status) {
        super(name, salary, hireData, endDate);
        this.school = school;
        this.status = status;
    }

    /**
     * Instantiates a new Lecturer.
     *
     * @param school the school
     * @param status the status
     */
    public Lecturer(String school, char status) {
        this.school = school;
        this.status = status;
    }

    /**
     * Gets school.
     *
     * @return the school
     */
    public String getSchool() {
        return school;
    }

    /**
     * Sets school.
     *
     * @param school the school
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public char getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(char status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StaffMember{" +
                "name='" + this.getName() + '\'' +
                ", salary=" + this.getSalary() +
                ", hireData=" + this.getHireData() +
                ", endDate=" + this.getEndDate() +
                ", school=" + school +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Lecturer) {
            Lecturer lecturer = (Lecturer) obj;
            if (!this.getName().equals(lecturer.getName())) {
                return false;
            } else if (this.getSalary() != lecturer.getSalary()) {
                return false;
            } else if (!this.getHireData().equals(lecturer.getHireData())) {
                return false;
            } else if (!this.getSchool().equals(lecturer.getSchool())) {
                return false;
            }
            return this.getStatus() == lecturer.getStatus();
        }
        return true;
    }
}
