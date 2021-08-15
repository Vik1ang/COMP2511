package staff;


import java.time.LocalDate;
import java.time.LocalTime;

public class StaffTest {

    public static void main(String[] args) {
        StaffMember staffMember = new StaffMember("Test1", 12333, LocalDate.of(2017, 10, 8), LocalDate.of(2018, 1, 1));
        Lecturer lecturer = new Lecturer("Test1", 12333, LocalDate.of(2017, 10, 8), LocalDate.of(2018, 1, 1), "School1", 'A');
        printStaffDetails(staffMember);
        printStaffDetails(lecturer);
        System.out.println(staffMember.equals(lecturer));

    }

    public static void printStaffDetails(StaffMember staffMember) {
        System.out.println(staffMember);
    }

}
