package programmingexample7;

public class Employee {
	private String title;
	private String firstName;
	private String lastName;
	// private double salesTarget;
	// private double salesAchieved;
	private double baseSalary;

	// Code Smell:  RefusedBequest - salesTarget and salesAchieved were relevant only to sub-class SalesPerson
	// Used Refactoring technique Push Down Field and Push Down Method to move field and behaviour to SalesPerson




	public Employee (String title, String firstName, String lastName) {
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	// public double getSalesTarget() {
	// 	return salesTarget;
	// }

	public String getTitle() {
		return title;
	}

	// public double getSalesAchieved() {
	// 	return salesAchieved;
	// }
	//
	public double getBaseSalary() {
		return baseSalary;
	}

	public double calculateTax() {
		double tax = 0;
		double salary = baseSalary;
		if (salary > 50000) {
		    tax += 0.3*(salary - 50000);
		}
		if (salary > 30000) {
		    tax += 0.2*(salary - 30000);
		}
		return tax;
	}

	public double calculateParkingFringeBenefits() {
		return (baseSalary - 50000) / 10000;
	}


}
