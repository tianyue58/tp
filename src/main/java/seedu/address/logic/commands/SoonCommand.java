package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_OF_APPLICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE_AND_TIME;

import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.application.Application;
import seedu.address.model.application.InterviewDateAndTimePredicate;
import seedu.address.model.application.SoonDeadlinePredicate;

/**
 * Lists all applications whose deadlines are near in InternSHIP to the user.
 */
public class SoonCommand extends Command {

    public static final String COMMAND_WORD = "soon";
    public static final String MESSAGE_EMPTY_LIST =
            "There is no application close to the submission or interview deadlines";
    public static final String MESSAGE_SUCCESS =
            "Listed all applications that are close to the submission or interview deadlines";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows the applications that are close to the submission or interview deadlines.\n"
            + "Parameters: \n"
            + PREFIX_DEADLINE_OF_APPLICATION + " DAYS (must be 0 or a positive integer)\n"
            + PREFIX_INTERVIEW_DATE_AND_TIME + " DAYS (must be 0 or a positive integer)\n"
            + "Only one prefix should be supplied \n"
            + "Example: \n"
            + "Find by submission deadline: " + COMMAND_WORD + " "
            + PREFIX_DEADLINE_OF_APPLICATION + "1 \n"
            + "Find by interview deadline: " + COMMAND_WORD + " "
            + PREFIX_INTERVIEW_DATE_AND_TIME + "1 ";

    private final Predicate<Application> predicate;
    private final Index days;

    /**
     * Creates a SoonCommand to list close {@code Application} submission deadlines.
     *
     * @param days Number of days from today's date.
     */
    public SoonCommand(Index days, SoonDeadlinePredicate predicate) {
        this.days = days;
        this.predicate = predicate;
    }

    /**
     * Creates a SoonCommand to list close {@code Application} interview deadlines.
     *
     * @param days Number of days from today's date.
     */
    public SoonCommand(Index days, InterviewDateAndTimePredicate predicate) {
        this.days = days;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredApplicationList(predicate);
        int listSize = model.getFilteredApplicationList().size();
        return new CommandResult(listSize == 0 ? MESSAGE_EMPTY_LIST : MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SoonCommand)
                && days.equals(((SoonCommand) other).days)
                && predicate.equals(((SoonCommand) other).predicate); // instanceof handles nulls
    }
}
