package programmingexample7;

public class Engineer extends Employee {

    private double bonus;

    public Engineer(String title, String firstName, String lastName, double bonus) {
        super(title, firstName, lastName);
        this.bonus = bonus;
    }

    public double calculateSalary() {
        double totalSal;
        totalSal = super.getBaseSalary() + bonus
                 + super.calculateParkingFringeBenefits() - super.calculateTax();
        return totalSal;
    }
}
