package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.application.Application;
import seedu.address.model.application.SoonDeadlinePredicate;

/**
 * Lists all applications whose deadlines are near in InternSHIP to the user.
 */
public class SoonCommand extends Command {

    public static final String COMMAND_WORD = "soon";
    public static final String MESSAGE_EMPTY_LIST = "There is no application close to the deadlines";
    public static final String MESSAGE_SUCCESS = "Listed all applications that are close to the deadlines";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows the applications that are close to the deadlines.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    private final Predicate<Application> predicate;
    private final Index days;

    /**
     * Creates a SoonCommand to list close {@code Application} deadlines.
     *
     * @param days Number of days from today's date.
     */
    public SoonCommand(Index days) {
        this.days = days;
        predicate = new SoonDeadlinePredicate(days);
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
                && days.equals(((SoonCommand) other).days); // instanceof handles nulls
    }
}
