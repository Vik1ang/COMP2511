package employee;

/**
 * A named salaried employee
 *
 * @author Robert Clifton-Everest
 *
 */
public class Employee {
    private String name;
    private int salary;

    /**
     * Creates an Employee with the given name and salary.
     * @param name The full name of the employee.
     * @param salary The employee's yearly salary in dollars.
     */
    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    /**
     * Returns the employee's name
     * @return The full name of the employee.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the employee's name
     * @param name The employee's new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the employee's salary.
     * @return The employee's yearly salary in dollars.
     */
    public int getSalary() {
        return salary;
    }

    /**
     * Set the employee's salary.
     * @param salary The employee's yearly salary in dollars.
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[name=" + name + ", salary=" + salary + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Employee other = (Employee) obj;
        if (name.equals(other.name) && salary == other.salary)
            return true;
        return false;
    }
}
