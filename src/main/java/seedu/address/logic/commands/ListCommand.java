package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;

/**
 * Lists all applications in InternSHIP to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all applications";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
        int listSize = model.getFilteredApplicationList().size();
        return new CommandResult(listSize == 0 ? Messages.MESSAGE_EMPTY_LIST : MESSAGE_SUCCESS);
    }
}
