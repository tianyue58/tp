package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

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

    private final Predicate<Application> predicate = new SoonDeadlinePredicate();
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredApplicationList(predicate);
        int listSize = model.getFilteredApplicationList().size();
        return new CommandResult(listSize == 0 ? MESSAGE_EMPTY_LIST : MESSAGE_SUCCESS);
    }
}
