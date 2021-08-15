package enrolment.test;

import enrolment.*;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnrolmentTest {

    @Test
    public void testIntegration() throws Exception {

        // Create courses
        Course comp1511 = new Course("COMP1511", "Programming Fundamentals");
        Course comp1531 = new Course("COMP1531", "Software Engineering Fundamentals");
        comp1531.addPrereq(comp1511);
        Course comp2521 = new Course("COMP2521", "Data Structures and Algorithms");
        comp2521.addPrereq(comp1511);

        CourseOffering comp1511Offering = new CourseOffering(comp1511, "19T1");
        CourseOffering comp1531Offering = new CourseOffering(comp1531, "19T1");
        CourseOffering comp2521Offering = new CourseOffering(comp2521, "19T2");

        // TODO Create some sessions for the courses
        Lecture comp1531Lecture = new Lecture("Edon", DayOfWeek.MONDAY, LocalTime.of(7, 0), LocalTime.of(9, 0), "Chancellor");
        Lab comp1531Lab = new Lab("Mc Connellsburg", DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0), "Ulices", "Candace");
        Tutorial comp1531Tutorial = new Tutorial("Mc Connellsburg", DayOfWeek.MONDAY, LocalTime.of(13, 0), LocalTime.of(15, 0), "Ulices");
        comp1511Offering.addSession(comp1531Lecture);
        comp1511Offering.addSession(comp1531Lab);
        comp1511Offering.addSession(comp1531Tutorial);


        // TODO Create a student
        Student student1 = new Student("1411181443");
        Student student2 = new Student("609888680");
        Student student3 = new Student("1496646503");

        // TODO Enrol the student in COMP1511 for T1 (this should succeed)
        // Enrolment enrolment1 = new Enrolment(comp1511Offering, student1);
        Enrolment enrolment1 = assertDoesNotThrow(() -> new Enrolment(comp1511Offering, student1));

        // TODO Enrol the student in COMP1531 for T1 (this should fail as they
        // have not met the prereq)
        assertThrows(Exception.class, () -> new Enrolment(comp1531Offering, student2));

        // TODO Give the student a passing grade for COMP1511
        enrolment1.setGrade(new Grade(75, "D"));

        // TODO Enrol the student in 2521 (this should succeed as they have met
        // the prereqs)
        Enrolment enrolment3 = assertDoesNotThrow(() -> new Enrolment(comp2521Offering, student1));

    }

    @Test
    public void testCOMP2511() {
        Course comp1511 = new Course("COMP1511", "Programming Fundamentals");
        Course comp1531 = new Course("COMP1531", "Software Engineering Fundamentals");
        comp1531.addPrereq(comp1511);
        Course comp2521 = new Course("COMP2521", "Data Structures and Algorithms");
        comp2521.addPrereq(comp1511);
        Course comp2511 = new Course("COMP2511", "OOP");
        comp2511.addPrereq(comp1531);
        comp2511.addPrereq(comp2521);

        CourseOffering comp1511Offering = new CourseOffering(comp1511, "19T1");
        CourseOffering comp1531Offering = new CourseOffering(comp1531, "19T1");
        CourseOffering comp2521Offering = new CourseOffering(comp2521, "19T2");
        CourseOffering comp2511Offering = new CourseOffering(comp2511, "19T3");

        Student student1 = new Student("1411181443");
        Student student2 = new Student("609888680");

        // student1
        Enrolment enrolment1 = assertDoesNotThrow(() -> new Enrolment(comp1511Offering, student1));
        enrolment1.setGrade(new Grade(75, "D"));
        Enrolment enrolment1_2 = assertDoesNotThrow(() -> new Enrolment(comp1531Offering, student1));
        enrolment1_2.setGrade(new Grade(75, "D"));
        Enrolment enrolment2 = assertDoesNotThrow(() -> new Enrolment(comp2521Offering, student1));
        enrolment2.setGrade(new Grade(75, "D"));
        Enrolment enrolment3 = assertDoesNotThrow(() -> new Enrolment(comp2511Offering, student1));

        // fail student2
        Enrolment enrolment4 = assertDoesNotThrow(() -> new Enrolment(comp1511Offering, student2));
        enrolment4.setGrade(new Grade(75, "D"));
        Enrolment enrolment4_2 = assertDoesNotThrow(() -> new Enrolment(comp1531Offering, student1));
        enrolment4_2.setGrade(new Grade(75, "D"));
        Enrolment enrolment5 = assertDoesNotThrow(() -> new Enrolment(comp2521Offering, student2));
        enrolment5.setGrade(new Grade(49, "FL"));
        assertThrows(Exception.class, () -> new Enrolment(comp2511Offering, student2));
    }

    @Test
    public void testBasicMethod() {
        Course comp1511 = new Course("COMP1511", "Programming Fundamentals");
        Course comp1531 = new Course("COMP1531", "Software Engineering Fundamentals");
        comp1531.addPrereq(comp1511);
        Course comp2521 = new Course("COMP2521", "Data Structures and Algorithms");
        comp2521.addPrereq(comp1511);

        assertEquals("COMP1531", comp1531.getCourseCode());
        assertEquals(6, comp1531.getUOC());

        CourseOffering comp1511Offering = new CourseOffering(comp1511, "19T1");
        CourseOffering comp1531Offering = new CourseOffering(comp1531, "19T1");
        CourseOffering comp2521Offering = new CourseOffering(comp2521, "19T2");

        assertEquals("19T1", comp1511Offering.getTerm());

        Lecture comp1531Lecture = new Lecture("Edon", DayOfWeek.MONDAY, LocalTime.of(7, 0), LocalTime.of(9, 0), "Chancellor");
        Lab comp1531Lab = new Lab("Mc Connellsburg", DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0), "Ulices", "Candace");
        Tutorial comp1531Tutorial = new Tutorial("Mc Connellsburg", DayOfWeek.MONDAY, LocalTime.of(13, 0), LocalTime.of(15, 0), "Ulices");
        comp1511Offering.addSession(comp1531Lecture);
        comp1511Offering.addSession(comp1531Lab);
        comp1511Offering.addSession(comp1531Tutorial);

        assertEquals(DayOfWeek.MONDAY, comp1531Lecture.getDay());
        assertEquals("Edon", comp1531Lecture.getLocation());
        assertEquals(LocalTime.of(7, 0), comp1531Lecture.getStart());
        assertEquals(LocalTime.of(9, 0), comp1531Lecture.getEnd());

        assertEquals("Ulices", comp1531Lab.getTutor());
        assertEquals("Chancellor", comp1531Lecture.getLecturer());
        assertEquals("Ulices", comp1531Tutorial.getTutor());

        comp1531Lab.setTutor("Antwon Kirkwood");
        assertEquals("Antwon Kirkwood", comp1531Lab.getTutor());
        comp1531Lab.setLabAssistant("Keanna Mcfaddin");
        assertEquals("Keanna Mcfaddin", comp1531Lab.getLabAssistant());

        Student student1 = new Student("1411181443");
        Student student2 = new Student("609888680");
        Student student3 = new Student("1496646503");

        assertEquals("1411181443", student1.getZID());

        Enrolment enrolment1 = assertDoesNotThrow(() -> new Enrolment(comp1511Offering, student1));
        assertEquals("19T1", enrolment1.getTerm());
        Grade credit = new Grade(74, "Credit");
        credit.setGrade("HD");
        credit.setMark(95);

        assertEquals("HD", credit.getGrade());
    }
}
