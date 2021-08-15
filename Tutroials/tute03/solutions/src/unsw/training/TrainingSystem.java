package unsw.training;

import java.time.LocalDate;
import java.util.List;

public class TrainingSystem {
    private List<Seminar> seminars;

    public LocalDate bookTraining(String employee, List<LocalDate> availability) {
        // By fixing up our relationships we simplify our solution
        // i.e. by having seminars be aggregated of trainers
        // and the training system be composed of seminars
        // it's a nicer structure and results in a better overal solution 
        for (LocalDate available : availability) {
            for (Seminar seminar : seminars) {
                if (seminar.canBook(employee, available)) {
                    return available;
                }
            }
        }
        return null;
    }
}
