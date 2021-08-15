package employee;

import java.time.LocalDate;

/**
 * An employee that is also a manager.
 * @author Robert Clifton-Everest
 *
 */
public class Manager extends Employee {

    private LocalDate hireDate;

    /**
     * Create a manager with the given name, salary and hiring date.
     * @param name The full name of the manager.
     * @param salary The manager's salary in dollars.
     * @param hireDate The date the manager was hired.
     */
    public Manager(String name, int salary, LocalDate hireDate) {
        super(name, salary);
        this.hireDate = hireDate;
    }

    /**
     * Create a manager with the given name and salary.
     *
     * The manager is recorded as having been hired today.
     *
     * @param name The full name of the manager.
     * @param salary The manager's salary in dollars.
     * @param hireDate The date the manager was hired.
     */
    public Manager(String name, int salary) {
        this(name, salary, LocalDate.now());
    }

    /**
     * Get the manager's hire date.
     * @return The date the manager was hired.
     */
    public LocalDate getHireDate() {
        return hireDate;
    }

    @Override
    public String toString() {
        return super.toString() + "[hireDate=" + hireDate + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        Manager manager = (Manager) obj;
        if (hireDate.equals(manager.hireDate))
            return true;
        return false;
    }

}
