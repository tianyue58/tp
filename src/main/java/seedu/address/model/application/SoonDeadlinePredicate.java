package seedu.address.model.application;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;

/**
 * Tests that a {@code Application}'s {@code Deadline} is coming soon.
 */
public class SoonDeadlinePredicate implements Predicate<Application> {

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);
    private final Index days;

    public SoonDeadlinePredicate(Index days) {
        this.days = days;
    }

    @Override
    public boolean test(Application application) {
        long diff = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String deadline = application.getDeadline().value;
        try {
            Date applicationDate = formatter.parse(deadline);
            Date currentDate = new Date();
            diff = TimeUnit.DAYS.convert(applicationDate.getTime()
                    - currentDate.getTime(), TimeUnit.MILLISECONDS);
        } catch (java.text.ParseException e) {
            logger.info("Invalid deadline format");
        }
        return diff >= 0 && diff <= days.getZeroBased();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SoonDeadlinePredicate); // instanceof handles nulls
    }

}
