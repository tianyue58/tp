package seedu.address.model.application;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

/**
 * Tests that a {@code Application}'s {@code Deadline} is coming soon.
 */
public class SoonDeadlinePredicate implements Predicate<Application> {

    @Override
    public boolean test(Application application) {
        long diff = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String deadline = application.getDeadline().value;
        try {
            Date applicationDate = formatter.parse(deadline);
            Date currentDate = new Date();
            diff = TimeUnit.DAYS.convert(applicationDate.getTime() - currentDate.getTime(), TimeUnit.MILLISECONDS);
        //NEED TO CHANGE CATCH BLOCK
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return diff >= 0 && diff <= 3;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SoonDeadlinePredicate); // instanceof handles nulls
    }

}
