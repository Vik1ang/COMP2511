package enrolment;

import java.util.ArrayList;
import java.util.List;

public class Enrolment {

    private CourseOffering offering;
    private Grade grade;
    private Student student;

    public Enrolment(CourseOffering offering, Student student) throws Exception {
        if (offering.getCourse().getPrereqs().size() > 0) {
            List<Course> prereqsList = offering.getCourse().getPrereqs();
            int count = 0;
            for (Course course : prereqsList) {
                List<Enrolment> enrolmentsList = student.getEnrolments();
                for (Enrolment enrolment : enrolmentsList) {
                    if (enrolment.getCourse() == course && enrolment.getGrade() != null && enrolment.getGrade().getMark() >= 50) {
                        count++;
                    }
                }
            }
            if (count < prereqsList.size()) {
                throw new Exception();
            }
        }
        this.offering = offering;
        this.student = student;
        this.student.getEnrolments().add(this);
    }

    public Course getCourse() {
        return offering.getCourse();
    }

    public String getTerm() {
        return offering.getTerm();
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Grade getGrade() {
        return grade;
    }
}
