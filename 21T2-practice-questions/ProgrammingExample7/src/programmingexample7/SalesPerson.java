package programmingexample7;

public class SalesPerson extends Employee {

    private float commission;
    private double salesTarget;
    private double salesAchieved;


    public SalesPerson(String title, String firstName, String lastName, int quota) {
        super(title, firstName, lastName);
        this.salesTarget = quota;
    }


    public double calculateSalary() {
        double totalSal;
        totalSal = super.getBaseSalary() + commission * getSalesAchieved()
                + super.calculateParkingFringeBenefits()
                - super.calculateTax();
        return totalSal;
    }

    public double getSalesAchieved() {
        return salesAchieved;
    }

    public double getSalesTarget() {
    	return salesTarget;
    }


    // Code Smells:  Inappropriate Intimacy and Lazy class
    public String getSalesSummary() {
        return getFirstName() + getLastName() + "Sales Target: " + getSalesTarget() + "$\n" +
                "Sales to date: " + getSalesAchieved() + "$";
    }

}
