package enrolment;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class Tutorial extends Session {

    private String tutor;

    public Tutorial(String location, DayOfWeek day, LocalTime start, LocalTime end, String tutor) {
        super(location, day, start, end);
        this.tutor = tutor;
    }

    public String getTutor() {
        return tutor;
    }
}
