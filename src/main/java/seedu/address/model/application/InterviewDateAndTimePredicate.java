package seedu.address.model.application;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;

/**
 * Tests that a {@code Application}'s {@code InterviewDateAndTime} is coming soon.
 */
public class InterviewDateAndTimePredicate implements Predicate<Application> {

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);
    private final Index days;

    public InterviewDateAndTimePredicate(Index days) {
        this.days = days;
    }

    @Override
    public boolean test(Application application) {
        long diff = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Set<InterviewDateAndTime> set = application.getInterviewDateAndTime();
        Iterator<InterviewDateAndTime> iterator = set.iterator();
        if (set.size() == 0) {
            return false;
        }
        while (iterator.hasNext()) {
            try {
                Date applicationDate = formatter.parse(iterator.next().value.substring(0, 10));
                Date currentDate = new Date();
                diff = TimeUnit.DAYS.convert(applicationDate.getTime()
                        - currentDate.getTime(), TimeUnit.MILLISECONDS);
            } catch (java.text.ParseException e) {
                logger.info("Invalid interview date and time format");
            }
            return diff >= 0 && diff <= days.getZeroBased() - 1;
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InterviewDateAndTimePredicate); // instanceof handles nulls
    }

}
