package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.application.Application;
import seedu.address.model.application.Completion;
import seedu.address.model.application.Deadline;
import seedu.address.model.application.Name;
import seedu.address.model.application.Position;
import seedu.address.model.application.Status;
import seedu.address.model.tag.Tag;

public class RejectCommand extends Command {
    public static final String COMMAND_WORD = "reject";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the application identified by the index number as 'Rejected' "
            + "in the displayed application list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Marked application as 'Rejected': %1$s";


    private final Index targetIndex;

    public RejectCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Application> lastShownList = model.getFilteredApplicationList();


        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Application applicationToComplete = lastShownList.get(targetIndex.getZeroBased());

        Name name = applicationToComplete.getName();
        Position position = applicationToComplete.getPosition();
        Deadline deadline = applicationToComplete.getDeadline();
        Set<Tag> tagList = applicationToComplete.getTags();
        Status status = new Status("Rejected");
        Completion completion = new Completion("Completed");

        Application completedApplication = new Application(name, position, deadline, status, tagList, completion);
        model.setApplication(applicationToComplete, completedApplication);


        return new CommandResult(String.format(MESSAGE_SUCCESS, applicationToComplete));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RejectCommand// instanceof handles nulls
                && targetIndex.equals(((RejectCommand) other).targetIndex)); // state check
    }
}
