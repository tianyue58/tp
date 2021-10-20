package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.application.Application;
import seedu.address.model.application.Company;
import seedu.address.model.application.Completion;
import seedu.address.model.application.Deadline;
import seedu.address.model.application.Position;
import seedu.address.model.application.Status;
import seedu.address.model.application.Requirements;
import seedu.address.model.tag.Tag;


public class CompleteCommand extends Command {
    public static final String COMMAND_WORD = "complete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Completes the application identified by the index number used in the displayed application list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Completed application: %1$s";

    private final Index targetIndex;

    /**
     * Creates an CompleteCommand to mark the specified {@code Application} as completed.
     */
    public CompleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Application> lastShownList = model.getFilteredApplicationList();


        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
        }

        Application applicationToComplete = lastShownList.get(targetIndex.getZeroBased());

        Company company = applicationToComplete.getCompany();
        Position position = applicationToComplete.getPosition();
        Deadline deadline = applicationToComplete.getDeadline();
        Status status = applicationToComplete.getStatus();
        Set<Tag> tagList = applicationToComplete.getTags();
        Requirements requirements = applicationToComplete.getRequirements();
        Completion completion = new Completion("Completed");

        Application completedApplication = new Application(company, position, deadline, completion, status, requirements, tagList);
        model.setApplication(applicationToComplete, completedApplication);
        return new CommandResult(String.format(MESSAGE_SUCCESS, completedApplication));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompleteCommand // instanceof handles nulls
                && targetIndex.equals(((CompleteCommand) other).targetIndex)); // state check
    }
}
