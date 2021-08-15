package staff;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A staff member
 *
 * @author Robert Clifton-Everest
 */
public class StaffMember {
    private String name;
    private double salary;
    private LocalDate hireData;
    private LocalDate endDate;

    /**
     * Instantiates a new Staff member.
     *
     * @param name   staff name
     * @param salary staff salary
     */
    public StaffMember(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    /**
     * Instantiates a new Staff member.
     *
     * @param name     staff name
     * @param salary   staff salary
     * @param hireData the hire data
     * @param endDate  the end date
     */
    public StaffMember(String name, double salary, LocalDate hireData, LocalDate endDate) {
        this.name = name;
        this.salary = salary;
        this.hireData = hireData;
        this.endDate = endDate;
    }

    /**
     * Instantiates a new Staff member.
     */
    public StaffMember() {
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets salary.
     *
     * @return the salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Sets salary.
     *
     * @param salary the salary
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Gets hire data.
     *
     * @return the hire data
     */
    public LocalDate getHireData() {
        return hireData;
    }

    /**
     * Sets hire data.
     *
     * @param hireData the hire data
     */
    public void setHireData(LocalDate hireData) {
        this.hireData = hireData;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "StaffMember{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireData=" + hireData +
                ", endDate=" + endDate +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StaffMember) {
            StaffMember staffMember = (StaffMember) obj;
            if (!this.getName().equals(staffMember.getName())) {
                return false;
            } else if (this.getSalary() != staffMember.getSalary()) {
                return false;
            } else if (!this.getHireData().equals(staffMember.getHireData())) {
                return false;
            }
            return this.getEndDate().equals(staffMember.getEndDate());
        }
        return false;
    }


}
